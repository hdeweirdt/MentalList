package be.harm.mentallist.domain

import be.harm.domain.ShoppingItem
import be.harm.domain.ShoppingList
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class ShoppingListTest {
    private lateinit var subject: ShoppingList

    private val shoppingListName = "Name"

    @Before
    fun setUp() {
        subject = ShoppingList(shoppingListName)
    }

    @Test
    fun shoppingList_constructor_emptyList() {
        // Act
        subject = ShoppingList(shoppingListName)

        // Assert
        assertTrue(
            "ShoppingList did not have an empty list of items on creation",
            subject.itemList.isEmpty()
        )
    }

    @Test
    fun shoppingList_addNewItem_isAddedToList() {
        // Arrange
        val newItem = mockk<ShoppingItem>()

        // Act
        subject.add(newItem)

        // Assert
        assertTrue(subject.itemList.contains(newItem))
    }

    @Test(expected = IllegalArgumentException::class)
    fun shoppingList_addItemAlreadyThere_throwsError() {
        // Arrange
        val newItem = mockk<ShoppingItem>()
        subject.add(newItem)

        // Act
        subject.add(newItem)

        // Assert
        assertEquals(1, subject.itemList.size)
    }

    @Test
    fun shoppingList_addNewItem_addedToEndOfList() {
        // Arrange
        val firstItem = mockk<ShoppingItem>()
        val secondItem = mockk<ShoppingItem>()
        subject.add(firstItem)

        // Act
        subject.add(secondItem)

        // Assert
        assertEquals(secondItem, subject.itemList.last())
    }

    @Test
    fun shoppingList_removeItemAtPosition_validPosition_removes() {
        // Arrange
        val firstItem = mockk<ShoppingItem>()
        val secondItem = mockk<ShoppingItem>()
        val thirdItem = mockk<ShoppingItem>()
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
        val firstItem = mockk<ShoppingItem>()
        val secondItem = mockk<ShoppingItem>()
        val thirdItem = mockk<ShoppingItem>()
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
        val firstItem = mockk<ShoppingItem>()
        val secondItem = mockk<ShoppingItem>()
        val thirdItem = mockk<ShoppingItem>()
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
