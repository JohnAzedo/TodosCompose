package com.lemonade.todoscompose

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class Modules {

    @Singleton
    @Binds
    abstract fun bindTodoRepository(
        repository: TodoRepositoryImpl
    ): TodoRepository

}