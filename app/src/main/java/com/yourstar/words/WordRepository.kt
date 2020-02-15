package com.yourstar.words

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData

class WordRepository(context: Context) {
    lateinit var wordDao: WordDao
    lateinit var allWordsLive: LiveData<List<Word>>

    init {
        val wordDatabase: WordDatabase = WordDatabase.getInstance(context.applicationContext)
        wordDao = wordDatabase.getWordDao()
        allWordsLive = wordDao.getAllWordsLive()
    }

    fun insertWords(vararg words: Word) {
        InseretAsyncTask(wordDao).execute(*words)
    }

    fun updateWords(vararg words: Word) {
        UpdateAsyncTask(wordDao).execute(*words)
    }

    fun deleteWords(vararg words: Word) {
        DeleteAsyncTask(wordDao).execute(*words)
    }

    fun deleteAllWords() {
        DeleteAllAsyncTask(wordDao).execute()
    }

    class InseretAsyncTask(var wordDao: WordDao) : AsyncTask<Word, Void?, Void?>() {
        override fun doInBackground(vararg params: Word?): Void? {
            wordDao.insertWords(*params as Array<out Word>)

            return null
        }
    }

    class UpdateAsyncTask(var wordDao: WordDao) : AsyncTask<Word, Void?, Void?>() {
        override fun doInBackground(vararg params: Word?): Void? {
            wordDao.updateWords(*params as Array<out Word>)

            return null
        }
    }

    class DeleteAsyncTask(var wordDao: WordDao) : AsyncTask<Word, Void?, Void?>() {
        override fun doInBackground(vararg params: Word?): Void? {
            wordDao.deleteWords(*params as Array<out Word>)

            return null
        }
    }

    class DeleteAllAsyncTask(var wordDao: WordDao) : AsyncTask<Void?, Void?, Void?>() {
        override fun doInBackground(vararg params: Void?): Void? {
            wordDao.deleteAllWords()

            return null
        }
    }
}