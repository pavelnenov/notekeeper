package com.example.notekeeper.di

import android.content.Context
import com.example.notekeeper.db.NotesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DatabaseProvider {

//    @Singleton
    @Provides
    fun provideDatabasa(@ApplicationContext context: Context) :NotesDatabase {
        return NotesDatabase(context)
    }
}