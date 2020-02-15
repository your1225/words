package com.yourstar.words

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val useCardView: Boolean, private val wordViewModel: WordViewModel) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    var allWords: List<Word> = mutableListOf()

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvNumber: TextView = itemView.findViewById(R.id.tvNumber)
        var tvEnglish: TextView = itemView.findViewById(R.id.tvEnglish)
        var tvChinese: TextView = itemView.findViewById(R.id.tvChinese)
        var swChineseVisible: Switch = itemView.findViewById(R.id.swChineseVisible)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val itemView: View

        if (useCardView) {
            itemView = layoutInflater.inflate(R.layout.cell_card_2, parent, false)
        } else {
            itemView = layoutInflater.inflate(R.layout.cell_normal_2, parent, false)
        }

        var holder: MyViewHolder = MyViewHolder(itemView)

        holder.itemView.setOnClickListener {
            var uri: Uri = Uri.parse("https://m.youdao.com/dict?le=eng&q=" + holder.tvEnglish.text)
            var intent: Intent = Intent(Intent.ACTION_VIEW)
            intent.data = uri
            holder.itemView.context.startActivity(intent)
        }

        holder.swChineseVisible.setOnCheckedChangeListener { _, isChecked ->
            val word: Word = holder.itemView.getTag(R.id.word_for_view_holder) as Word

            if (isChecked) {
                holder.tvChinese.visibility = View.GONE
                word.chineseInvisible = true

                wordViewModel.updateWords(word)
            } else {
                holder.tvChinese.visibility = View.VISIBLE
                word.chineseInvisible = false

                wordViewModel.updateWords(word)
            }
        }

        return holder
    }

    override fun getItemCount(): Int {
        return allWords.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val word = allWords[position]

//        holder.tvNumber.text = word.id.toString()
        holder.itemView.setTag(R.id.word_for_view_holder, word)
        holder.tvNumber.text = (position + 1).toString()
        holder.tvEnglish.text = word.word
        holder.tvChinese.text = word.chineseMeaning
//        holder.swChineseVisible.setOnCheckedChangeListener(null)

        if (word.chineseInvisible) {
            holder.tvChinese.visibility = View.GONE
            holder.swChineseVisible.isChecked = true
        } else {
            holder.tvChinese.visibility = View.VISIBLE
            holder.swChineseVisible.isChecked = false
        }
    }
}