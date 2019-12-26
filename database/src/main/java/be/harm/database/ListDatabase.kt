package be.harm.database

import com.squareup.sqldelight.db.SqlDriver

object ListDatabase {
    private var driver: SqlDriver? = null
    private var db: Database? = null

    val ready: Boolean
        get() = driver != null

    fun setupDatabase(driver: SqlDriver) {
        db = Database(driver)
        this.driver = driver
    }

    internal fun clearDatabase() {
        driver!!.close()
        db = null
        driver = null
    }

    val instance: Database
        get() = db!!
}
