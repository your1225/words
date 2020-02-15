package com.yourstar.words

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

//singleton
@Database(entities = [(Word::class)], version = 6, exportSchema = false)
abstract class WordDatabase() : RoomDatabase() {

    companion object {
        private var INSTANCE: WordDatabase? = null

        @Synchronized
        fun getInstance(context: Context): WordDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    WordDatabase::class.java,
                    "word_database"
                )
                    .fallbackToDestructiveMigration()
//                    .addMigrations(this.m23)
                    .build()
            }

            return INSTANCE!!
        }

        private var m23 = MIGRATION3To4(3, 4)

        private class MIGRATION2To3(startVersion: Int, endVersion: Int) :
            Migration(startVersion, endVersion) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE word ADD COLUMN bar_data INTEGER NOT NULL DEFAULT 1")
            }
        }

        private class MIGRATION3To4(startVersion: Int, endVersion: Int) :
            Migration(startVersion, endVersion) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE word_temp (id INTEGER PRIMARY KEY NOT NULL, english_word TEXT, chinese_meaning TEXT)")
                database.execSQL("INSERT INTO word_temp (id, english_word, chinese_meaning) select id, english_word, chinese_meaning from word")
                database.execSQL("DROP TABLE word")
                database.execSQL("ALTER TABLE word_temp RENAME to word")
            }
        }
    }

    abstract fun getWordDao(): WordDao

}