package be.harm.mentallist

import be.harm.domain.Item
import be.harm.domain.ItemList
import be.harm.mentallist.mappers.ItemMapper
import be.harm.mentallist.mappers.ListMapper
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class ItemListRepositoryImplTest : DatabaseTest() {

    private lateinit var subject: ListRepositoryImpl

    private val queries by lazy { getDatabase().itemListQueries }

    @Before
    fun setUp() {
        subject = ListRepositoryImpl(getDatabase(), ListMapper(), ItemMapper())
    }

    @Test
    fun shoppingListDatabase_getList_returnsDomainListWithItems() {
        // Arrange
        queries.insertListWithId(list_id = 100, listName = "List 1")
        queries.insertItemWithId(item_id = 0, itemName = "Item 11", list_id = 100)
        queries.insertItemWithId(item_id = 1, itemName = "Item 12", list_id = 100)

        queries.insertListWithId(list_id = 200, listName = "List 2")
        queries.insertItemWithId(item_id = 2, itemName = "Item 21", list_id = 200)

        runBlocking {
            // Act
            val foundList = subject.getList(100)

            // Assert
            assertNotNull(foundList)
            assertEquals(2, foundList!!.itemList.size)
            assertTrue(foundList.itemList.any { item: Item -> item.id == 0L })
            assertTrue(foundList.itemList.any { item: Item -> item.id == 1L })
        }
    }

    @Test
    fun shoppingListDatabase_getShoppingLists_returnsDomainListsWithItems() {
        // Arrange
        queries.insertListWithId(list_id = 100, listName = "List 1")
        queries.insertItemWithId(item_id = 0, itemName = "Item 11", list_id = 100)
        queries.insertItemWithId(item_id = 1, itemName = "Item 12", list_id = 100)

        queries.insertListWithId(list_id = 200, listName = "List 2")
        queries.insertItemWithId(item_id = 2, itemName = "Item 21", list_id = 200)

        runBlocking {
            // Act
            val lists = subject.getAll()

            // Assert
            val firstList = lists.find { it.id == 200L }
            val secondList = lists.find { it.id == 100L }
            assertNotNull(firstList)
            assertNotNull(secondList)
        }
    }

    @Test
    fun shoppingListDatabase_addList_adds() {
        // Arrange
        val newList = ItemList(name = "TestList")

        // Act
        runBlocking {
            subject.addList(newList)
        }

        // Assert
        val listsInDatabase = queries.getLists().executeAsList()

        assertTrue(listsInDatabase.any { list -> list.listName == newList.name })
    }

    @Test
    fun shoppingListDatabase_addItem_linkedToList() {
        // Arrange
        val list = ItemList(id = 10, name = "TestList")
        val item = Item(name = "TestItem")
        queries.insertListWithId(list.id, list.name)

        // Act
        runBlocking {
            subject.addItem(item, list)
        }

        // Assert
        val itemsInList = queries.getItemsFromList(list.id).executeAsList()
        assertEquals(1, itemsInList.size)
        val itemInDatabase = itemsInList.first()
        assertEquals(item.name, itemInDatabase.itemName)
    }
}
