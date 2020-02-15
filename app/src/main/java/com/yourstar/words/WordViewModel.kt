package com.yourstar.words

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class WordViewModel(application: Application) : AndroidViewModel(application) {
    private var wordRepository: WordRepository = WordRepository(application)
    lateinit var allWordsLive: LiveData<List<Word>>

    init {
        allWordsLive = wordRepository.allWordsLive
    }

    fun insertWords(vararg words: Word){
        wordRepository.insertWords(*words)
    }

    fun updateWords(vararg words: Word){
        wordRepository.updateWords(*words)
    }

    fun deleteWords(vararg words: Word){
        wordRepository.deleteWords(*words)
    }

    fun deleteAllWords(){
        wordRepository.deleteAllWords()
    }
}