package be.harm.mentallist.mappers

import be.harm.domain.ItemList
import be.harm.mentallist.ListEntity

class ListMapper {

    fun toShoppingList(listEntity: ListEntity): ItemList {
        return ItemList(
            id = listEntity.list_id,
            name = listEntity.listName
        )
    }
}
