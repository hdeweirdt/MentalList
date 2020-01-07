package be.harm.mentallist.list.todoList

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import be.harm.domain.ItemList
import be.harm.domain.ListRepository
import be.harm.mentallist.list.util.DummyListRepository
import be.harm.mentallist.list.util.getOrAwaitValue
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class LimitedListViewModelTest {

    private lateinit var subject: LimitedListViewModel
    private lateinit var listRepository: ListRepository

    private val listId: Long = 0L
    private lateinit var list: ItemList

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        list = ItemList(name = "testLimitedList", id = listId)
        Dispatchers.setMain(testDispatcher)
        listRepository = DummyListRepository()
        runBlockingTest {
            listRepository.addList(list)
        }
    }

    @Test
    fun whenNoItemsInList_showsEmptyList() = runBlockingTest {
        // Arrange
        subject = LimitedListViewModel(listId, listRepository, 3)

        // Assert
        assertTrue(subject.items.getOrAwaitValue().isEmpty())
    }

    @Test
    fun showsNoMoreThanRequestedNumberOfItems() = runBlockingTest {
        // Arrange
        val numberOfItemsToShow = 3
        repeat(numberOfItemsToShow + 10) {
            list.add(mockk())
        }
        subject = LimitedListViewModel(listId, listRepository, numberOfItemsToShow)

        // Assert
        assertEquals(3, subject.items.getOrAwaitValue().size)
    }
}
