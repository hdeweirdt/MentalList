package be.harm.domain

class ItemList(
    var name: String,
    val id: Long = 0L
) {
    private val _itemList = mutableListOf<Item>()
    val itemList: List<Item> = _itemList

    fun add(newItem: Item) {
        require(!_itemList.contains(newItem)) { "Item $newItem is already in this list." }

        _itemList += newItem
    }

    fun removeItemAtPosition(index: Int) {
        require(index >= 0) { "Can't use negative indexes." }
        require(index < _itemList.size) { "There aren't that many items in this list." }

        _itemList.removeAt(index)
    }

    fun remove(item: Item) {
        _itemList.remove(item)
    }
}
