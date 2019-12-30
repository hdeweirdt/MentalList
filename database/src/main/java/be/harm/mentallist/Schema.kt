package be.harm.mentallist

import com.squareup.sqldelight.db.SqlDriver

const val SHOPPING_LIST_ID = 0L
const val TODO_LIST_ID = 1L

object Schema : SqlDriver.Schema by ListDatabase.Schema {
    override fun create(driver: SqlDriver) {
        ListDatabase.Schema.create(driver)

        ListDatabase(driver).apply {
            itemListQueries.insertListWithId(SHOPPING_LIST_ID, "Shopping")
            itemListQueries.insertListWithId(TODO_LIST_ID, "Todo")
        }
    }
}
