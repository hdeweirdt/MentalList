package be.harm.domain

interface ListRepository {
    suspend fun getList(listId: Long): ItemList?

    suspend fun getAll(): List<ItemList>

    suspend fun addList(newList: ItemList)

    suspend fun addItem(item: Item, itemList: ItemList)

    suspend fun removeItem(item: Item)
}
