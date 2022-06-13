package com.lemonade.todoscompose.di

import com.lemonade.todoscompose.infra.TodoRepositoryInMemoryImpl
import com.lemonade.todoscompose.domain.TodoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class Modules {

    @Singleton
    @Binds
    abstract fun bindTodoRepository(
        repository: TodoRepositoryInMemoryImpl
    ): TodoRepository
}