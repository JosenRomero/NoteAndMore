package com.josenromero.notesandmore.di

import android.content.Context
import androidx.room.Room
import com.josenromero.notesandmore.data.notes.NoteDao
import com.josenromero.notesandmore.data.notes.NoteDatabase
import com.josenromero.notesandmore.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun providesNotesRoomDatabase(@ApplicationContext app: Context): NoteDatabase {

        // create database
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            Constants.note_database
        ).build()

    }

    @Provides
    @Singleton
    fun providesNotesDao(noteDatabase: NoteDatabase): NoteDao {
        return noteDatabase.noteDao()
    }

}