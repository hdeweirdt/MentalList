package be.harm.mentallist.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.harm.domain.Item
import be.harm.domain.ItemList
import be.harm.domain.ListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListViewModel(
    private val listId: Long,
    private val listRepository: ListRepository
) : ViewModel() {

    private var list: ItemList? = null

    private val _items = MutableLiveData<List<Item>>()
    val items: LiveData<List<Item>> = _items

    init {
        viewModelScope.launch(context = Dispatchers.IO) {
            updateItems()
        }
    }

    fun addItemWithName(itemName: String) {
        viewModelScope.launch {
            val newItem = Item(itemName)
            withContext(Dispatchers.IO) {
                listRepository.addItem(newItem, list!!)
            }
            updateItems()
        }
    }

    private suspend fun updateItems() {
        list = listRepository.getList(listId)
        _items.postValue(list?.itemList)
    }
}
