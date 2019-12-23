package be.harm.database.mappers

import be.harm.database.ItemEntity
import be.harm.domain.Item

class ItemMapper {
    fun toListItem(itemEntity: ItemEntity): Item {
        return Item(
            id = itemEntity.item_id,
            name = itemEntity.itemName
        )
    }
}
