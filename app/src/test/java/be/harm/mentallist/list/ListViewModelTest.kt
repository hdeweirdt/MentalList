package be.harm.mentallist.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import be.harm.domain.ItemList
import be.harm.domain.ListRepository
import be.harm.mentallist.list.util.getOrAwaitValue
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import org.junit.After
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class ListViewModelTest {
    private lateinit var subject: ListViewModel
    private val listRepository: ListRepository = mockk()

    private val listId: Long = 0L

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun listVM_onLoaded_showItemsOnList() {
        // Arrange
        val list = ItemList(name = "testList", id = listId)
        every { listRepository.getList(listId) } returns list

        // Act
        subject = ListViewModel(listId, listRepository)

        // Assert
        Assert.assertNotNull(subject.items)
        assertEquals(list.itemList, subject.items.getOrAwaitValue())
    }
}
