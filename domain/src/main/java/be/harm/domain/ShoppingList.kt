package be.harm.domain

class ShoppingList(
    val id: Long,
    var name: String
) {
    private val _itemList = mutableListOf<ShoppingItem>()
    val itemList: List<ShoppingItem> = _itemList

    fun add(newItem: ShoppingItem) {
        require(!_itemList.contains(newItem)) { "Item $newItem is already in this list." }

        _itemList += newItem
    }

    fun removeItemAtPosition(index: Int) {
        require(index >= 0) { "Can't use negative indexes." }
        require(index < _itemList.size) { "There aren't that many items in this list." }

        _itemList.removeAt(index)
    }

    fun remove(shoppingItem: ShoppingItem) {
        _itemList.remove(shoppingItem)
    }
}
