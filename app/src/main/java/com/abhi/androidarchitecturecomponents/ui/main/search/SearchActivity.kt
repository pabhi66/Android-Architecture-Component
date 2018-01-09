package com.abhi.androidarchitecturecomponents.ui.main.search

import android.os.Bundle
import com.abhi.androidarchitecturecomponents.R
import com.abhi.androidarchitecturecomponents.databinding.ActivitySearchBinding
import com.abhi.androidarchitecturecomponents.ui.base.BaseActivity
import android.view.View
import com.facebook.stetho.Stetho
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.search_view.*
import android.speech.RecognizerIntent
import android.content.Intent
import android.support.v7.widget.RecyclerView
import com.abhi.androidarchitecturecomponents.util.search.MaterialSearchView
import android.view.LayoutInflater
import android.view.ViewGroup
import kotlinx.android.synthetic.main.row_search_history.*
import kotlinx.android.synthetic.main.row_search_result.*


class SearchActivity : BaseActivity<ActivitySearchBinding>() {

    private val mSearchHistoryRowAdapter = SearchHistoryRowAdapter()
    private val mSearchResultRowAdapter = SearchResultRowAdapter()

    private val LOG_TAG = SearchActivity::class.java.simpleName
    private val RECOGNIZER_REQ_CODE = 1234

    override fun getLayoutRes(): Int = R.layout.activity_search

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Stetho.initializeWithDefaults(this)

        search_view.setVoiceSearch(true)
        search_view.showSearch(false)

        action_voice_btn.visibility = View.VISIBLE
        action_voice_btn.setOnClickListener({
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            startActivityForResult(intent, RECOGNIZER_REQ_CODE)
        })

        action_empty_btn.setOnClickListener({ v ->
            action_voice_btn.visibility = View.VISIBLE
            v.visibility = View.GONE
            searchTextView.setText("")
        })

        action_up_btn.setOnClickListener({ onBackPressed() })

        search_view.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                println(query)
//                val searchResult = SearchHistory(query)
//                searchResult.save()
//                mSearchHistoryRowAdapter.setSearchResults(SearchHistory.listAll(SearchHistory::class.java) as ArrayList<SearchHistory>)
//                mSearchHistoryRowAdapter.notifyDataSetChanged()
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                println(newText)
                if (newText.length > 0) {
                    recycler_search_history.visibility = View.GONE
                    recycler_search_results.visibility = View.GONE
                    // val usersList = User.findWithQuery(User::class.java, "SELECT * FROM user WHERE email LIKE ?", "%$newText%")
                    // mSearchResultRowAdapter.setSearchResults(usersList as ArrayList<User>)
                } else {
                    action_voice_btn.visibility = View.VISIBLE
                    recycler_search_history.visibility = View.VISIBLE
                    recycler_search_results.visibility = View.GONE
                }

                return true
            }
        })

    }

    internal inner class SearchHistoryRowAdapter : RecyclerView.Adapter<SearchActivity.SearchHistoryRowAdapter.ViewHolder>() {
        private var searchHistoryList = ArrayList<SearchHistory>()

        internal inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {

            fun updateSearchHistory(searchHistory: SearchHistory) {
                stock_title.text = searchHistory.title
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchActivity.SearchHistoryRowAdapter.ViewHolder {
            val v = LayoutInflater.from(parent.context).inflate(R.layout.row_search_history, parent, false)
            return ViewHolder(v)
        }

        override fun onBindViewHolder(holder: SearchHistoryRowAdapter.ViewHolder, position: Int) {
            holder.updateSearchHistory(searchHistoryList[position])
        }

        override fun getItemCount(): Int {
            return searchHistoryList.size
        }

        fun setSearchResults(searchHistoryList: ArrayList<SearchHistory>) {
            this.searchHistoryList = searchHistoryList
            notifyDataSetChanged()
        }
    }

    internal inner class SearchResultRowAdapter: RecyclerView.Adapter<SearchActivity.SearchResultRowAdapter.ViewHolder>() {
        private var searchResults = ArrayList<String>()

        internal inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {

            fun updateSearchResult(query: String){
                stock_full_name.text = query
                stock_symbol.text = query
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchActivity.SearchResultRowAdapter.ViewHolder {
            val v = LayoutInflater.from(parent.context).inflate(R.layout.row_search_result, parent, false)
            return ViewHolder(v)
        }

        override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
            holder?.updateSearchResult(searchResults[position])
        }

        override fun getItemCount(): Int = searchResults.size

        fun setSearchResults(searchResults: ArrayList<String>) {
            this.searchResults = searchResults
            notifyDataSetChanged()
        }
    }
}
