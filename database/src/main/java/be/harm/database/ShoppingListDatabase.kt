package be.harm.database

import be.harm.database.mappers.ShoppingItemMapper
import be.harm.database.mappers.ShoppingListMapper
import be.harm.domain.ShoppingList
import be.harm.domain.ShoppingListRepository

class ShoppingListDatabase(
    private val shoppingListsQueries: ShoppingListsQueries,
    private val shoppingListMapper: ShoppingListMapper,
    private val shoppingItemMapper: ShoppingItemMapper
) : ShoppingListRepository {

    override fun getAll(): List<ShoppingList> {
        return shoppingListsQueries.getLists().executeAsList()
            .map { shoppingEntity ->
                val shoppingList = shoppingListMapper.toShoppingList(shoppingEntity)
                val itemEntities =
                    shoppingListsQueries.getItemsFromList(shoppingEntity.list_id).executeAsList()
                itemEntities.forEach { itemEntity ->
                    shoppingList.add(shoppingItemMapper.toShoppingItem(itemEntity))
                }
                shoppingList
            }
    }

    override fun addList(newList: ShoppingList) {
        shoppingListsQueries.insertList(
            listName = newList.name
        )
    }

}
