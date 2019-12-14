package be.harm.database

import be.harm.database.mappers.ItemMapper
import be.harm.database.mappers.ListMapper
import be.harm.domain.Item
import be.harm.domain.ItemList
import be.harm.domain.ListRepository

class ListDatabase(
    private val shoppingListsQueries: ShoppingListsQueries,
    private val listMapper: ListMapper,
    private val itemMapper: ItemMapper
) : ListRepository {

    override fun getList(listId: Long): ItemList? {
        val result = shoppingListsQueries.getList(listId).executeAsOneOrNull()
        return if (result == null) {
            null
        } else {
            listMapper.toShoppingList(result)
        }
    }

    override fun getAll(): List<ItemList> {
        return shoppingListsQueries.getLists().executeAsList()
            .map { shoppingEntity ->
                val shoppingList = listMapper.toShoppingList(shoppingEntity)
                val itemEntities =
                    shoppingListsQueries.getItemsFromList(shoppingEntity.list_id).executeAsList()
                itemEntities.forEach { itemEntity ->
                    shoppingList.add(itemMapper.toShoppingItem(itemEntity))
                }
                shoppingList
            }
    }

    override fun addList(newList: ItemList) {
        shoppingListsQueries.insertList(
            listName = newList.name
        )
    }

    override fun addItem(item: Item, itemList: ItemList) {
        shoppingListsQueries.insertItem(item.name, itemList.id)
    }
}
