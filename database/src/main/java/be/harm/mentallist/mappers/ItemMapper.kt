package be.harm.mentallist.mappers

import be.harm.domain.Item
import be.harm.mentallist.ItemEntity

class ItemMapper {
    fun toListItem(itemEntity: ItemEntity): Item {
        return Item(
            id = itemEntity.item_id,
            name = itemEntity.itemName
        )
    }
}
