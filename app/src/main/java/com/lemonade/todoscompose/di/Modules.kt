package com.lemonade.todoscompose.di

import androidx.room.Room
import com.lemonade.todoscompose.domain.TodoRepository
import com.lemonade.todoscompose.infra.room.AppDatabase
import com.lemonade.todoscompose.ui.TodosViewModel
import com.lemonade.todoscompose.infra.room.TodoRepositoryImpl
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val koinModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "todos"
        ).allowMainThreadQueries().build()
    }

    single {
        val database = get<AppDatabase>()
        database.dataSource()
    }

    single {
        Dispatchers.IO
    }

    factoryOf(::TodoRepositoryImpl) { bind<TodoRepository>()}
    viewModelOf(::TodosViewModel)
}