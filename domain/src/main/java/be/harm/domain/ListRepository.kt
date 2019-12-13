package be.harm.domain

interface ListRepository {
    fun getAll(): List<ItemList>

    fun addList(newList: ItemList)

    fun addItem(item: Item, itemList: ItemList)
}
