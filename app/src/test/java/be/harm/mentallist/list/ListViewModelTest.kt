package be.harm.mentallist.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import be.harm.domain.Item
import be.harm.domain.ItemList
import be.harm.domain.ListRepository
import be.harm.mentallist.list.util.DummyListRepository
import be.harm.mentallist.list.util.getOrAwaitValue
import io.mockk.clearAllMocks
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ListViewModelTest {
    private lateinit var subject: ListViewModel
    private lateinit var listRepository: ListRepository

    private val listId: Long = 0L
    private lateinit var list: ItemList

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        list = ItemList(name = "testList", id = listId)
        Dispatchers.setMain(testDispatcher)
        listRepository = DummyListRepository()
        runBlockingTest {
            listRepository.addList(list)
        }
    }

    @After
    fun tearDown() {
        clearAllMocks()
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun whenLoaded_shouldShowList() = runBlockingTest {
        // Arrange
        list.add(Item("Item1"))
        list.add(Item("Item2"))

        // Act
        subject = ListViewModel(listId, listRepository)

        // Assert
        assertNotNull(subject.items)
        assertEquals(list.itemList, subject.items.getOrAwaitValue())
    }

    @Test
    fun whenAddItem_shouldShowUpInList() = runBlockingTest {
        // Arrange
        subject = ListViewModel(listId, listRepository)

        // Act
        subject.addItemWithName("TestName")

        // Assert
        assertTrue(subject.items.getOrAwaitValue().any { it.name == "TestName" })
    }

    @Test
    fun whenRemoveItem_removedFromList() = runBlockingTest {
        // Arrange
        val item = Item("WillBeDeleted")
        listRepository.addItem(item, list)
        subject = ListViewModel(listId, listRepository)

        // Act
        subject.removeItem(item)

        // Assert
        assertTrue(subject.items.getOrAwaitValue().contains(item))
    }

    @Test
    fun givenEmptyList_whenRemoveItem_noError() = runBlockingTest {
        // Arrange
        val item = Item("WillBeDeleted")
        subject = ListViewModel(listId, listRepository)

        // Act
        subject.removeItem(item)
    }
}
