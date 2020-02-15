package com.yourstar.words


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_words.*

/**
 * A simple [Fragment] subclass.
 */
class WordsFragment : Fragment() {

//    lateinit var wordViewModel: WordViewModel
//    lateinit var myAdapter: MyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_words, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val wordViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory(activity!!.application)
        ).get(WordViewModel::class.java)

        val myAdapter1 = MyAdapter(false, wordViewModel)
        val myAdapter2 = MyAdapter(true, wordViewModel)

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = myAdapter1

        wordViewModel.allWordsLive.observe(this, Observer {
            var temp = myAdapter1.itemCount
            myAdapter1.allWords = it
            myAdapter2.allWords = it

            if (temp != it.size) {
                myAdapter1.notifyDataSetChanged()
                myAdapter2.notifyDataSetChanged()
            }
        })

        floatingActionButton.setOnClickListener {
            val navController: NavController = Navigation.findNavController(it)
            navController.navigate(R.id.action_wordsFragment_to_addFragment)
        }
    }
}
