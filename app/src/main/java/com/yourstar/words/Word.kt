package com.yourstar.words

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Word(word: String, chineseMeaning: String) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    @ColumnInfo(name = "english_word")
    var word: String = word
    @ColumnInfo(name = "chinese_meaning")
    var chineseMeaning: String = chineseMeaning
    @ColumnInfo(name = "chinese_invisible")
    var chineseInvisible: Boolean = false
}