package be.harm.domain

interface ShoppingListRepository {
    fun getAll(): List<ShoppingList>

    fun addList(newList: ShoppingList)

    fun addItem(shoppingItem: ShoppingItem, shoppingList: ShoppingList)
}
