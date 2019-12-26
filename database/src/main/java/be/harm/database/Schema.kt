package be.harm.database

import com.squareup.sqldelight.db.SqlDriver

object Schema : SqlDriver.Schema by Database.Schema {
    override fun create(driver: SqlDriver) {
        Database.Schema.create(driver)

        Database(driver).apply {
            itemListQueries.insertListWithId(0, "Shopping")
            itemListQueries.insertListWithId(1, "Todo")
        }
    }
}
