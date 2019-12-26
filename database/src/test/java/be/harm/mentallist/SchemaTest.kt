package be.harm.mentallist

import org.junit.Assert.assertTrue
import org.junit.Test

class SchemaTest : DatabaseTest() {

    @Test
    fun whenCreated_shouldContainShoppingList() {
        val lists = getDatabase().itemListQueries.getLists().executeAsList()

        assertTrue(lists.any { list -> list.listName == "Shopping" })
    }

    @Test
    fun whenCreated_shouldContainTodoList() {
        val lists = getDatabase().itemListQueries.getLists().executeAsList()

        assertTrue(lists.any { list -> list.listName == "Todo" })
    }
}
