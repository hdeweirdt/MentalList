package be.harm.mentallist.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import be.harm.domain.Item
import be.harm.domain.ItemList
import be.harm.domain.ListRepository

class ListViewModel(
    listId: Long,
    private val listRepository: ListRepository
) : ViewModel() {

    private var list: ItemList? = null

    private val _items = MutableLiveData<List<Item>>()
    val items: LiveData<List<Item>> = _items

    init {
        list = listRepository.getList(listId)
        _items.value = list?.itemList
    }

    fun addItemWithName(itemName: String) {
        val newItem = Item(itemName)
        listRepository.addItem(newItem, list!!)
    }
}
