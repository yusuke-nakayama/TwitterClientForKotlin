package com.xygnx134gmail.twitterclientforandroidkotlin

import android.app.Application
import android.util.Log
import com.twitter.sdk.android.core.DefaultLogger
import com.twitter.sdk.android.core.Twitter
import com.twitter.sdk.android.core.TwitterAuthConfig
import com.twitter.sdk.android.core.TwitterConfig



class TwitterClientForAndroidKotlinApplication : Application() {
    private val CONSUMER_KEY = "rCfvvZWQbxmxdu5Epq6jaAEzu"
    private val CONSUMER_SECRET = "nNEeVy52svGyHGAJO2VUmfZudx0yzKhU7NzftNR57bIahaqocL"

    override fun onCreate() {
        super.onCreate()

        val config = TwitterConfig.Builder(this)
            .logger(DefaultLogger(Log.DEBUG))
            .twitterAuthConfig(TwitterAuthConfig(CONSUMER_KEY, CONSUMER_SECRET))
            .debug(true)
            .build()
        Twitter.initialize(config)
    }

}