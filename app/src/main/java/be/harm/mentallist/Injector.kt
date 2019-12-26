package be.harm.mentallist

import android.content.Context
import be.harm.database.Database
import be.harm.domain.ListRepository
import be.harm.mentallist.mappers.ItemMapper
import be.harm.mentallist.mappers.ListMapper
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

class Injector(private val context: Context) {

    fun provideListRepository(): ListRepository {
        return ListRepositoryImpl(
            listDatabase = provideListDatabase(),
            itemMapper = provideItemMapper(),
            listMapper = provideListMapper()
        )
    }

    private fun provideListDatabase(): Database {
        if (!ListDatabase.ready) {
            val androidDriver = provideDatabaseDriver()
            ListDatabase.setupDatabase(androidDriver)
        }
        return ListDatabase.instance
    }

    private fun provideDatabaseDriver(): SqlDriver {
        // Use own implementation of Schema with a Shopping -and Todolist already available
        return AndroidSqliteDriver(Schema, context, "listDatabase.db")
    }

    private fun provideItemMapper(): ItemMapper {
        return ItemMapper()
    }

    private fun provideListMapper(): ListMapper {
        return ListMapper()
    }
}
