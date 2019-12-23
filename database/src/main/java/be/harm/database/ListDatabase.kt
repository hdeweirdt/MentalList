package be.harm.database

import be.harm.database.mappers.ItemMapper
import be.harm.database.mappers.ListMapper
import be.harm.domain.Item
import be.harm.domain.ItemList
import be.harm.domain.ListRepository

class ListDatabase(
    private val listQueries: ShoppingListsQueries,
    private val listMapper: ListMapper,
    private val itemMapper: ItemMapper
) : ListRepository {

    override fun getList(listId: Long): ItemList? {
        val result = listQueries.getList(listId).executeAsOneOrNull()
        return if (result == null) {
            null
        } else {
            listMapper.toShoppingList(result)
        }
    }

    override fun getAll(): List<ItemList> {
        return listQueries.getLists().executeAsList()
            .map { listEntity ->
                val list = listMapper.toShoppingList(listEntity)
                val itemEntities =
                    listQueries.getItemsFromList(listEntity.list_id).executeAsList()
                itemEntities.forEach { itemEntity ->
                    list.add(itemMapper.toListItem(itemEntity))
                }
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
