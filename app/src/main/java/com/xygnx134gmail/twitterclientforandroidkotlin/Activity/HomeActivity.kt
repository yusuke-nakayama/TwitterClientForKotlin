package com.xygnx134gmail.twitterclientforandroidkotlin.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;
import com.crashlytics.android.answers.Answers
import com.crashlytics.android.answers.ContentViewEvent
import android.widget.Toast
import android.content.Intent
import com.twitter.sdk.android.core.TwitterCore
import com.xygnx134gmail.twitterclientforandroidkotlin.R


class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        Fabric.with(this, Crashlytics())
        // TODO: Use your own attributes to track content views in your app
        Answers.getInstance().logContentView(
            ContentViewEvent()
                .putContentName("Tweet")
                .putContentType("Video")
                .putContentId("1234")
                .putCustomAttribute("Favorites Count", 20)
                .putCustomAttribute("Screen Orientation", "Landscape")
        )
        if (TwitterCore.getInstance().sessionManager.activeSession == null) {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        } else {
            val toast = Toast.makeText(this@HomeActivity, "ログイン中", Toast.LENGTH_LONG)
            toast.show()

            val intent = Intent(this@HomeActivity, TimelineActivity::class.java)
            startActivity(intent)

        }

    }


}
