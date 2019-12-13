package be.harm.mentallist.list

import be.harm.domain.ItemList
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ListViewModelTest {
    private lateinit var subject: ListViewModel

    private val list = ItemList(name = "Shopping")

    @Before
    fun setUp() {
        subject = ListViewModel(list)
    }

    @Test
    fun listVM_onLoaded_showList() {
        // Assert
        Assert.assertNotNull(subject.items)
    }
}