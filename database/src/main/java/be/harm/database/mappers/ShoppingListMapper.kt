package be.harm.database.mappers

import be.harm.database.ShoppingListEntity
import be.harm.domain.ShoppingList

class ShoppingListMapper {

    fun toShoppingList(shoppingListEntity: ShoppingListEntity): ShoppingList {
        return ShoppingList(
            id = shoppingListEntity.list_id,
            name = shoppingListEntity.listName
        )
    }
}
