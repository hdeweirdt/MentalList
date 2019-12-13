package be.harm.database.mappers

import be.harm.database.ListEntity
import be.harm.domain.ItemList

class ListMapper {

    fun toShoppingList(listEntity: ListEntity): ItemList {
        return ItemList(
            id = listEntity.list_id,
            name = listEntity.listName
        )
    }
}
