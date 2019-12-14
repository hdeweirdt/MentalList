package be.harm.mentallist.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import be.harm.domain.ListRepository

class ListViewModelFactory(
    private val listId: Long,
    private val listRepository: ListRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListViewModel::class.java)) {
            return ListViewModel(listId, listRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
