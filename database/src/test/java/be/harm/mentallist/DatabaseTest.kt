package be.harm.mentallist

import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import org.junit.After
import org.junit.Before

open class DatabaseTest {

    @Before
    fun setUpDatabase() {
        val driver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
        Schema.create(driver)
        ListDb.setupDatabase(driver)
    }

    @After
    fun tearDownDatabase() {
        ListDb.clearDatabase()
    }

    fun getDatabase(): ListDatabase {
        return ListDb.instance
    }
}
