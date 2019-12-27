package be.harm.mentallist.list.util

import be.harm.domain.Item
import be.harm.domain.ItemList
import be.harm.domain.ListRepository

class DummyListRepository : ListRepository {
    private val lists = mutableMapOf<Long, ItemList>()

    override suspend fun getList(listId: Long): ItemList? {
        return lists[listId]
    }

    override suspend fun getAll(): List<ItemList> {
        return lists.values.toList()
    }

    override suspend fun addList(newList: ItemList) {
        lists[newList.id] = newList
    }

    override suspend fun addItem(item: Item, itemList: ItemList) {
        lists[itemList.id]!!.add(item)
    }
}
