package be.harm.mentallist.list.todoList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import be.harm.domain.ListRepository

class LimitedListViewModelFactory(
    private val listId: Long,
    private val listRepository: ListRepository,
    private val numberOfItemsToShow: Int
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LimitedListViewModel::class.java)) {
            return LimitedListViewModel(listId, listRepository, numberOfItemsToShow) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
