package com.example.notekeeper.di

import android.content.Context
import com.example.notekeeper.data.DataManager
import com.example.notekeeper.db.NotesDatabase
import com.example.notekeeper.db.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DatabaseProvider {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): NotesDatabase = NotesDatabase(context)

    @Singleton
    @Provides
    fun provideDataManager(repository: Repository) : DataManager = DataManager(repository)

    @Singleton
    @Provides
    fun provideCoursesDao(db: NotesDatabase) = db.courseDao()

    @Singleton
    @Provides
    fun provideNotesDao(db: NotesDatabase) = db.noteDao()

}