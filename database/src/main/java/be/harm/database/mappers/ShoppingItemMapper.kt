package be.harm.database.mappers

import be.harm.database.ShoppingItemEntity
import be.harm.domain.ShoppingItem

class ShoppingItemMapper {
    fun toShoppingItem(shoppingItemEntity: ShoppingItemEntity): ShoppingItem {
        return ShoppingItem(
            id = shoppingItemEntity.item_id,
            name = shoppingItemEntity.itemName
        )
    }
}
