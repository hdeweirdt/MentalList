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

open class ListViewModel(
    private val listId: Long,
    private val listRepository: ListRepository
) : ViewModel() {

    private var list: ItemList? = null

    protected val _items = MutableLiveData<List<Item>>()
    open val items: LiveData<List<Item>> = _items

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

    fun removeItem(item: Item) {
        viewModelScope.launch(Dispatchers.IO) {
            listRepository.removeItem(item)
            updateItems()
        }
    }

    private suspend fun updateItems() {
        withContext(Dispatchers.IO) {
            list = listRepository.getList(listId)
        }
        _items.postValue(list?.itemList)
    }
}
