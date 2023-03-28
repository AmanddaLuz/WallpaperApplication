package com.amandaluz.wallpaperapplication.framework.di

import com.amandaluz.core.usecase.base.AppCoroutinesDispatcher
import com.amandaluz.core.usecase.base.CoroutinesDispatchers
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DispatcherModule {

    @Binds
    fun bindDispatcher(appCoroutinesDispatcher : AppCoroutinesDispatcher): CoroutinesDispatchers
}