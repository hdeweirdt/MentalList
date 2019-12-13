package be.harm.domain

interface ShoppingListRepository {
    fun getAll(): List<ShoppingList>
}
