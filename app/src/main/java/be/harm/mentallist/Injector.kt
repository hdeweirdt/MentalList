package be.harm.mentallist

import android.content.Context
import be.harm.database.Database
import be.harm.database.ListDatabase
import be.harm.database.ShoppingListsQueries
import be.harm.database.mappers.ItemMapper
import be.harm.database.mappers.ListMapper
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

class Injector(private val context: Context) {

    fun provideListDatabase(): ListDatabase {
        return ListDatabase(
            listQueries = provideQueries(),
            itemMapper = provideItemMapper(),
            listMapper = provideListMapper()
        )
    }

    fun provideQueries(): ShoppingListsQueries {
        val androidDriver = provideDatabaseDriver()
        return Database(androidDriver).shoppingListsQueries
    }

    fun provideDatabaseDriver(): SqlDriver {
        return AndroidSqliteDriver(Database.Schema, context, "listDatabase.db")
    }

    fun provideItemMapper(): ItemMapper {
        return ItemMapper()
    }

    fun provideListMapper(): ListMapper {
        return ListMapper()
    }
}
