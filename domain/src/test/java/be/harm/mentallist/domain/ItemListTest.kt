package be.harm.mentallist.domain

import be.harm.domain.Item
import be.harm.domain.ItemList
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class ItemListTest {
    private lateinit var subject: ItemList

    private val listName = "Name"

    @Before
    fun setUp() {
        subject = ItemList(listName)
    }

    @Test
    fun shoppingList_constructor_emptyList() {
        // Act
        subject = ItemList(listName)

        // Assert
        assertTrue(
            "ShoppingList did not have an empty list of items on creation",
            subject.itemList.isEmpty()
        )
    }

    @Test
    fun shoppingList_addNewItem_isAddedToList() {
        // Arrange
        val newItem = mockk<Item>()

        // Act
        subject.add(newItem)

        // Assert
        assertTrue(subject.itemList.contains(newItem))
    }

    @Test(expected = IllegalArgumentException::class)
    fun shoppingList_addItemAlreadyThere_throwsError() {
        // Arrange
        val newItem = mockk<Item>()
        subject.add(newItem)

        // Act
        subject.add(newItem)

        // Assert
        assertEquals(1, subject.itemList.size)
    }

    @Test
    fun shoppingList_addNewItem_addedToEndOfList() {
        // Arrange
        val firstItem = mockk<Item>()
        val secondItem = mockk<Item>()
        subject.add(firstItem)

        // Act
        subject.add(secondItem)

        // Assert
        assertEquals(secondItem, subject.itemList.last())
    }

    @Test
    fun shoppingList_removeItemAtPosition_validPosition_removes() {
        // Arrange
        val firstItem = mockk<Item>()
        val secondItem = mockk<Item>()
        val thirdItem = mockk<Item>()
        subject.add(firstItem)
        subject.add(secondItem)
        subject.add(thirdItem)

        // Act
        subject.removeItemAtPosition(1)

        // Assert
        assertEquals(2, subject.itemList.size)
        assertFalse(subject.itemList.contains(secondItem))
    }

    @Test(expected = IllegalArgumentException::class)
    fun shoppingList_removeItemAtPosition_positionOutOfBounds_exception() {
        // Arrange
        fillListWithItems(3)

        // Act
        subject.removeItemAtPosition(3)

        // Assert
        assertEquals(
            "Number of items in shoppingList changed after using invalid position when removing an item.",
            3,
            subject.itemList.size
        )
    }

    @Test(expected = IllegalArgumentException::class)
    fun shoppingList_removeItemAtPosition_negativePosition_exception() {
        // Arrange
        fillListWithItems(3)

        // Act
        subject.removeItemAtPosition(-1)

        // Assert
        assertEquals(
            "Number of items in shoppingList changed after using invalid position when removing an item.",
            3,
            subject.itemList.size
        )
    }

    @Test
    fun shoppingList_removeExistingItem_removed() {
        // Arrange
        val firstItem = mockk<Item>()
        val secondItem = mockk<Item>()
        val thirdItem = mockk<Item>()
        subject.add(firstItem)
        subject.add(secondItem)
        subject.add(thirdItem)

        // Act
        subject.remove(thirdItem)

        // Assert
        assertFalse(subject.itemList.contains(thirdItem))
        assertEquals(2, subject.itemList.size)
    }

    @Test
    fun shoppingList_removeNonExistingItem_NOP() {
        // Arrange
        val firstItem = mockk<Item>()
        val secondItem = mockk<Item>()
        val thirdItem = mockk<Item>()
        subject.add(firstItem)
        subject.add(secondItem)
        subject.add(thirdItem)

        // Act
        subject.remove(mockk())

        // Assert
        assertEquals(3, subject.itemList.size)
    }

    private fun fillListWithItems(numberOfItems: Int) {
        repeat(numberOfItems) {
            subject.add(mockk())
        }
    }
}
