package com.bignerdranch.android.androidtask1.task3.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.bignerdranch.android.androidtask1.MainActivity
import com.bignerdranch.android.androidtask1.R
import com.bignerdranch.android.androidtask1.databinding.FragmentTask3Binding
import com.bignerdranch.android.androidtask1.task3.presenter.NewsContract
import com.bignerdranch.android.androidtask1.task3.presenter.NewsPresenter
import com.bignerdranch.android.androidtask1.task3.presenter.NewsAdapter
import com.bignerdranch.android.androidtask1.task3.model.News
import kotlinx.android.synthetic.main.activity_main.*

class Task3Fragment : Fragment(), NewsContract {
    private lateinit var binding: FragmentTask3Binding
    private val adapter = NewsAdapter()
    lateinit var newsPresenter: NewsPresenter
    private var itemsList = ArrayList<String>()
    private fun injectPresenter() {
        @InjectPresenter
        newsPresenter = NewsPresenter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        injectPresenter()
        binding = FragmentTask3Binding.inflate(layoutInflater)
        binding.apply{
            recyclerNewsList.layoutManager = LinearLayoutManager(activity)
            recyclerNewsList.adapter = adapter
            createSpinner()
            swipeRefresh.setOnRefreshListener {
                createSpinner()
            }
            return root
        }
    }

    private fun createSpinner() {
        val newsThemes = resources.getStringArray(R.array.NewsThemes)
        val newsSpinner = binding.newsSpinner
        val adapter = this@Task3Fragment.context?.let {
            ArrayAdapter(it, android.R.layout.simple_spinner_item, newsThemes)
        }
        newsSpinner.adapter = adapter
        newsSpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?, position: Int, id: Long
            ) {
                itemsList.clear()
                newsPresenter.getNews(newsThemes[position])
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                showToast("Nothing selected")
            }
        }
    }

    fun showToast(text : String){
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }

    override fun loadNews(data: List<News>?) {
        adapter.addNews(data)
    }

    override fun showLoading(show: Boolean) {
        binding.swipeRefresh.isRefreshing = show
    }

    override fun setTitle(text: String) {
        (activity as MainActivity).topAppBar.title = text
    }
}