package be.harm.domain

import be.harm.domain.ShoppingList

interface ShoppingListRepository {
    fun getAll(): List<ShoppingList>
}