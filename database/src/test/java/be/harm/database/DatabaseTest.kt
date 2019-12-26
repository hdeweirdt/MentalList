package be.harm.database

import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import org.junit.After
import org.junit.Before

open class DatabaseTest {

    @Before
    fun setUpDatabase() {
        val driver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
        Schema.create(driver)
        ListDatabase.setupDatabase(driver)
    }

    @After
    fun tearDownDatabase() {
        ListDatabase.clearDatabase()
    }

    fun getDatabase(): Database {
        return ListDatabase.instance
    }
}
