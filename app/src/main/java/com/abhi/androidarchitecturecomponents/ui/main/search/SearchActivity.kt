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
import com.abhi.androidarchitecturecomponents.util.search.MaterialSearchView

class SearchActivity : BaseActivity<ActivitySearchBinding>() {

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
//                if (newText.length > 0) {
//                    mSearchHistoryRecyclerView.setVisibility(View.GONE)
//                    mSearchResultRecyclerView.setVisibility(View.VISIBLE)
//                    val usersList = User.findWithQuery(User::class.java, "SELECT * FROM user WHERE email LIKE ?", "%$newText%")
//                    mSearchResultRowAdapter.setSearchResults(usersList as ArrayList<User>)
//                } else {
//                    mVoiceSearchButton.setVisibility(View.VISIBLE)
//                    mSearchHistoryRecyclerView.setVisibility(View.VISIBLE)
//                    mSearchResultRecyclerView.setVisibility(View.GONE)
//                }

                return true
            }
        })

    }
}
