package com.xygnx134gmail.twitterclientforandroidkotlin.Activity

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ListView
import com.xygnx134gmail.twitterclientforandroidkotlin.R
import com.xygnx134gmail.twitterclientforandroidkotlin.Adapter.TweetAdapter
import com.twitter.sdk.android.core.models.Tweet
import android.widget.Toast
import com.twitter.sdk.android.core.TwitterCore
import com.twitter.sdk.android.core.Callback
import com.twitter.sdk.android.core.TwitterException
import com.xygnx134gmail.twitterclientforandroidkotlin.Listener.MyAdapterListener
import android.content.Intent




class TimelineActivity : AppCompatActivity() , MyAdapterListener{
    lateinit var listView: ListView
    val tweetList: MutableList<Tweet> = ArrayList()
    lateinit var adapter: TweetAdapter

    @Override
    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
//        val toolbar = findViewById<Toolbar>(R.id.toolbar);
//        setSupportActionBar(toolbar);

        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
            getHomeTimeline()
        }
        listView = findViewById<View>(R.id.my_list_view) as ListView
        adapter = TweetAdapter(this, tweetList, this)

        listView.setAdapter(adapter)

        getHomeTimeline()

    }


    private fun getHomeTimeline() {
        val twitterApiClient = TwitterCore.getInstance().apiClient

        val statusesService = twitterApiClient.statusesService

        val call = statusesService.homeTimeline(20, null, null, false, false, false, false)
        call.enqueue(object : Callback<List<Tweet>>() {
            override fun success(result: com.twitter.sdk.android.core.Result<List<Tweet>>?) {
                // ListViewのListに取得したツイートのリストを追加
                tweetList.addAll(result!!.data)
                // ListViewの表示を更新
                adapter.notifyDataSetChanged()

                val toast = Toast.makeText(this@TimelineActivity, "タイムライン取得成功", Toast.LENGTH_LONG)
                toast.show()
            }

            override fun failure(exception: TwitterException) {
                val toast = Toast.makeText(this@TimelineActivity, "タイムライン取得エラー", Toast.LENGTH_LONG)
                toast.show()
            }
        })
    }

    // 追加
    override fun onClickReplyButton(tweet: Tweet) {
        // PostTweetActivityに画面遷移
        val intent = Intent(this, PostTweetActivity::class.java)
        intent.putExtra("REPLY_TO_STATUS_ID", tweet.id)
        startActivity(intent)

    }

}
