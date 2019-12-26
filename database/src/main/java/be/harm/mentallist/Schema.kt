package be.harm.mentallist

import com.squareup.sqldelight.db.SqlDriver

object Schema : SqlDriver.Schema by ListDatabase.Schema {
    override fun create(driver: SqlDriver) {
        ListDatabase.Schema.create(driver)

        ListDatabase(driver).apply {
            itemListQueries.insertListWithId(0, "Shopping")
            itemListQueries.insertListWithId(1, "Todo")
        }
    }
}
