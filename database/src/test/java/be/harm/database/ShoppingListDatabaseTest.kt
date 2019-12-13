package be.harm.database

import be.harm.database.mappers.ShoppingItemMapper
import be.harm.database.mappers.ShoppingListMapper
import be.harm.domain.ShoppingList
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class ShoppingListDatabaseTest {

    private val inMemoryDriver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY).apply {
        Database.Schema.create(this)
    }

    private val queries = Database(inMemoryDriver).shoppingListsQueries

    private val subject = ShoppingListDatabase(queries, ShoppingListMapper(), ShoppingItemMapper())

    @Test
    fun shoppingListDatabase_getShoppingLists_returnsDomainListWithItems() {
        // Arrange
        queries.insertListWithId(list_id = 0, listName = "List 1")
        queries.insertItemWithId(item_id = 0, itemName = "Item 11", list_id = 0)
        queries.insertItemWithId(item_id = 1, itemName = "Item 12", list_id = 0)

        queries.insertListWithId(list_id = 1, listName = "List 2")
        queries.insertItemWithId(item_id = 2, itemName = "Item 21", list_id = 1)

        // Act
        val lists = subject.getAll()

        // Assert
        val firstList = lists.find { it.itemList.size == 2 }
        val secondList = lists.find { it.itemList.size == 1 }
        assertNotNull(firstList)
        assertNotNull(secondList)
    }

    @Test
    fun shoppingListDatabase_addList_adds() {
        // Arrange
        val newList = ShoppingList(name = "TestList")

        // Act
        subject.addList(newList)

        // Assert
        val listsInDatabase = queries.getLists().executeAsList()
        assertEquals(1, listsInDatabase.size)

        assertEquals(newList.name, listsInDatabase.first().listName)
    }
}
