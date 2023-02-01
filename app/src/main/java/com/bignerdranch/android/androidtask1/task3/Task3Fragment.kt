package com.bignerdranch.android.androidtask1.task3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bignerdranch.android.androidtask1.MainActivity
import com.bignerdranch.android.androidtask1.R
import com.bignerdranch.android.androidtask1.databinding.FragmentTask3Binding
import com.bignerdranch.android.androidtask1.task3.adapter.NewsAdapter
import com.bignerdranch.android.androidtask1.task3.common.Common
import com.bignerdranch.android.androidtask1.task3.newsInterface.NewsResponse
import com.bignerdranch.android.androidtask1.task3.newsInterface.RetrofitServices
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_task3.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Task3Fragment : Fragment() {
    private lateinit var binding: FragmentTask3Binding
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var mService: RetrofitServices
    private lateinit var adapter: NewsAdapter

    private var itemsList = ArrayList<String>()
    private val request = object : Callback<NewsResponse> {
        override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
            loadNews(response)
        }
        override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
            showToast("Failed to load news")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTask3Binding.inflate(layoutInflater)
        binding.apply{
            mService = Common.retrofitService
            recyclerNewsList.layoutManager = LinearLayoutManager(activity)
            recyclerNewsList.adapter
            createSpinner()
            swipeRefresh.setOnRefreshListener {
                createSpinner()
        }
            return root
        }
    }

    private fun getSoftwareNews() {
        mService.getSoftwareNewsList().enqueue(request)
        (requireContext() as MainActivity).topAppBar.title = "Software"
    }

    private fun getEntertainmentNews() {
        mService.getEntertainmentNewsList().enqueue(request)
        (requireContext() as MainActivity).topAppBar.title = "Entertainment"

    }

    private fun getHealthNews() {
        mService.getHealthNewsList().enqueue(request)
        (requireContext() as MainActivity).topAppBar.title = "Health"

    }

    private fun getScienceNews() {
        mService.getScienceNewsList().enqueue(request)
        (requireContext() as MainActivity).topAppBar.title = "Science"

    }

    private fun getSportsNews() {
        mService.getSportsNewsList().enqueue(request)
        (requireContext() as MainActivity).topAppBar.title = "Sports"
    }

    private fun loadNews(response: Response<NewsResponse>){
        swipeRefresh.isRefreshing = false
        showToast("Loading successful")
        adapter = NewsAdapter(requireNotNull(response.body()?.news))
        recyclerNewsList.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun showToast(text : String){
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
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
                when (newsThemes[position]) {
                    "software" -> getSoftwareNews()
                    "entertainment" -> getEntertainmentNews()
                    "health" -> getHealthNews()
                    "science" -> getScienceNews()
                    "sports" -> getSportsNews()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                showToast("Nothing selected")
            }
        }
    }
}