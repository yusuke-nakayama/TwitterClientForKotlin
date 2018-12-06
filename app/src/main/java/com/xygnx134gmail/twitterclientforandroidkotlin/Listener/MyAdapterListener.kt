package com.xygnx134gmail.twitterclientforandroidkotlin.Listener

import com.twitter.sdk.android.core.models.Tweet



interface MyAdapterListener {
    // リプライボタンが押されたことをActivityに知らせる
    fun onClickReplyButton(tweet: Tweet)

}