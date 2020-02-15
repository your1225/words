package com.yourstar.words

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao //Database access object
interface WordDao {
    @Insert
    fun insertWords(vararg words: Word)

    @Update
    fun updateWords(vararg words: Word)

    @Delete
    fun deleteWords(vararg words: Word)

    @Query("DELETE FROM WORD")
    fun deleteAllWords()

    @Query("SELECT * FROM WORD ORDER BY ID DESC")
//    fun getAllWords(): List<Word>
    fun getAllWordsLive(): LiveData<List<Word>>
}