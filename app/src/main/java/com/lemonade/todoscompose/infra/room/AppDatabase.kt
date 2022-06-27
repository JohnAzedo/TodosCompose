package com.lemonade.todoscompose.infra.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TodoEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun dataSource(): RoomDataSource

    companion object {
        private lateinit var instance: AppDatabase

        fun setupDatabase(context: Context) {
            instance = Room.databaseBuilder(
                context,
                AppDatabase::class.java, "todos"
            ).allowMainThreadQueries().build()
        }

        fun getDataSource() = instance.dataSource()
    }
}