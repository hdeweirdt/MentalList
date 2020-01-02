package be.harm.mentallist.list.todoList

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import be.harm.domain.Item
import be.harm.domain.ListRepository
import be.harm.mentallist.list.ListViewModel

class LimitedListViewModel(
    listId: Long,
    listRepository: ListRepository,
    private val numberOfItemsToShow: Int
) : ListViewModel(listId, listRepository) {

    override val items: LiveData<List<Item>>
        get() = _items.map { it.subList(0, numberOfItemsToShow) }
}
