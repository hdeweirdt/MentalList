package be.harm.mentallist

import com.squareup.sqldelight.db.SqlDriver

object ListDb {
    private var driver: SqlDriver? = null
    private var db: ListDatabase? = null

    val ready: Boolean
        get() = driver != null

    fun setupDatabase(driver: SqlDriver) {
        db = ListDatabase(driver)
        this.driver = driver
    }

    internal fun clearDatabase() {
        driver!!.close()
        db = null
        driver = null
    }

    val instance: ListDatabase
        get() = db!!
}
