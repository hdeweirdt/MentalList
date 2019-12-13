package be.harm.mentallist.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import be.harm.domain.Item
import be.harm.domain.ItemList

class ListViewModel(itemList: ItemList) : ViewModel() {

    private val _items = MutableLiveData<List<Item>>()
    val items: LiveData<List<Item>> = _items

}