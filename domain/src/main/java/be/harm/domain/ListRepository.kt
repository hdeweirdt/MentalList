package be.harm.domain

interface ListRepository {
    fun getList(listId: Long): ItemList?

    fun getAll(): List<ItemList>

    fun addList(newList: ItemList)

    fun addItem(item: Item, itemList: ItemList)
}
