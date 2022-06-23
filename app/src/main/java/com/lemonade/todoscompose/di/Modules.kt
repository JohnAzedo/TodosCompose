package com.lemonade.todoscompose.di

import com.lemonade.todoscompose.domain.TodoRepository
import com.lemonade.todoscompose.infra.fake.TodoRepositoryInMemoryImpl
import com.lemonade.todoscompose.ui.TodosViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val koinModule = module {
    // TodosViewModel
    factoryOf(::TodoRepositoryInMemoryImpl) { bind<TodoRepository>() }
    viewModelOf(::TodosViewModel)
}