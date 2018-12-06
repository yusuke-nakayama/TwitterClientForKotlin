package com.xygnx134gmail.twitterclientforandroidkotlin.Activity

import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import android.widget.Toast
import com.twitter.sdk.android.core.models.Tweet
import android.provider.SyncStateContract.Helpers.update
import com.twitter.sdk.android.core.services.StatusesService
import com.twitter.sdk.android.core.TwitterCore
import com.twitter.sdk.android.core.TwitterApiClient
import com.xygnx134gmail.twitterclientforandroidkotlin.R.id.fab
import android.support.design.widget.FloatingActionButton
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import com.xygnx134gmail.twitterclientforandroidkotlin.R
import com.twitter.sdk.android.core.Callback
import com.twitter.sdk.android.core.TwitterException


class PostTweetActivity : AppCompatActivity() {
    lateinit private var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_tweet)
//        val toolbar = findViewById(R.id.toolbar) as Toolbar
//        setSupportActionBar(toolbar)

        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                val text = editText.text.toString()

                val replyToStatusId = intent.getIntExtra("REPLY_TO_STATUS_ID", -1)

                // リプライかどうか
                if (replyToStatusId == -1) {
                    // 普通の投稿
                    post(text)
                } else {
                    //　リプライとして投稿
                    postAsReply(text, replyToStatusId.toLong())
                }
            }
        })

        editText = findViewById(R.id.edit_post_text) as EditText
    }

    private fun post(postText: String) {

        val twitterApiClient = TwitterCore.getInstance().apiClient

        val statusesService = twitterApiClient.statusesService
        val call = statusesService.update(postText, null, null, null, null, null, null, null, null)

        call.enqueue(object : Callback<Tweet>() {
            override fun success(result: com.twitter.sdk.android.core.Result<Tweet>?) {
                val toast = Toast.makeText(this@PostTweetActivity, "投稿成功", Toast.LENGTH_LONG)
                toast.show()
            }

            override fun failure(exception: TwitterException) {
                val toast = Toast.makeText(this@PostTweetActivity, "投稿失敗", Toast.LENGTH_LONG)
                toast.show()
            }
        })
    }

    private fun postAsReply(postText: String, replyToStatusId: Long) {

        val twitterApiClient = TwitterCore.getInstance().apiClient

        val statusesService = twitterApiClient.statusesService
        val call = statusesService.update(postText, replyToStatusId, null, null, null, null, null, null, null)

        call.enqueue(object : Callback<Tweet>() {
            override fun success(result: com.twitter.sdk.android.core.Result<Tweet>?) {
                val toast = Toast.makeText(this@PostTweetActivity, "リプライ成功", Toast.LENGTH_LONG)
                toast.show()
            }

            override fun failure(exception: TwitterException) {
                val toast = Toast.makeText(this@PostTweetActivity, "リプライ失敗", Toast.LENGTH_LONG)
                toast.show()
            }
        })
    }


}