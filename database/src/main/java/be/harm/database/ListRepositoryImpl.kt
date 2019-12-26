package be.harm.database

import be.harm.database.mappers.ItemMapper
import be.harm.database.mappers.ListMapper
import be.harm.domain.Item
import be.harm.domain.ItemList
import be.harm.domain.ListRepository

class ListRepositoryImpl(
    listDatabase: Database,
    private val listMapper: ListMapper,
    private val itemMapper: ItemMapper
) : ListRepository {

    private val listQueries = listDatabase.itemListQueries

    override fun getList(listId: Long): ItemList? {
        val listEntity = listQueries.getList(listId).executeAsOneOrNull()
        return if (listEntity == null) {
            null
        } else {
            val list = listMapper.toShoppingList(listEntity)
            addItemsToList(list)
            return list
        }
    }

    private fun addItemsToList(list: ItemList) {
        val itemEntities =
            listQueries.getItemsFromList(list.id).executeAsList()
        itemEntities.forEach { itemEntity ->
            list.add(itemMapper.toListItem(itemEntity))
        }
    }

    override fun getAll(): List<ItemList> {
        return listQueries.getLists().executeAsList()
            .map { listEntity ->
                val list = listMapper.toShoppingList(listEntity)
                addItemsToList(list)
                list
            }
    }

    override fun addList(newList: ItemList) {
        listQueries.insertList(
            listName = newList.name
        )
    }

    override fun addItem(item: Item, itemList: ItemList) {
        listQueries.insertItem(item.name, itemList.id)
    }
}
