package com.xygnx134gmail.twitterclientforandroidkotlin.Adapter

import android.content.Context
import android.widget.BaseAdapter
import com.twitter.sdk.android.core.models.Tweet
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.view.LayoutInflater
import android.R.attr.name
import android.view.View
import android.widget.TextView
import android.view.ViewGroup
import com.xygnx134gmail.twitterclientforandroidkotlin.R
import java.nio.file.Files.size
import android.R.attr.name
import android.widget.Button
import com.xygnx134gmail.twitterclientforandroidkotlin.Listener.MyAdapterListener




class TweetAdapter(context: Context, tweetList: List<Tweet>, listener:MyAdapterListener) : BaseAdapter(){
    private val mContext = context
    private val layoutInflater = LayoutInflater.from(mContext)
    private var tweetList: List<Tweet>? = tweetList
    // 追加
    private val mListener: MyAdapterListener = listener

    override fun getCount(): Int {
        return tweetList!!.size
    }

    override fun getItem(position: Int): Any {
        return tweetList!!.get(position)
    }

    override fun getItemId(position: Int): Long {
        return tweetList!!.get(position).getId()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var mConvertView = convertView
        if (mConvertView == null) {
            mConvertView = layoutInflater.inflate(R.layout.tweet_row, parent, false)
        }

        val tweet = tweetList!!.get(position)

        val screenNameTextView = mConvertView!!.findViewById<View>(R.id.screen_name) as TextView
        val TweetTextTextView = mConvertView.findViewById<View>(R.id.tweet_text) as TextView
        val favoriteCountTextView = mConvertView.findViewById(R.id.favorite_count) as TextView
        // 追加
        val replyButton = mConvertView.findViewById(R.id.reply_button) as Button

        screenNameTextView.text = tweetList!!.get(position).user.name
        TweetTextTextView.text = tweetList!!.get(position).text
        favoriteCountTextView.setText(tweet.favoriteCount.toString());


        // イベントを拾う
        replyButton.setOnClickListener {
            // ボタンが押されたら、独自リスナーのメソッドを呼ぶ。
            // 引数にはリプライ対象のtweetを渡す
            mListener.onClickReplyButton(tweet);
        }

        return mConvertView
    }

}