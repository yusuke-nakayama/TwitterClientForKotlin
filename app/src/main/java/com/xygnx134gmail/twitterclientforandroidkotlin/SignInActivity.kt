package com.xygnx134gmail.twitterclientforandroidkotlin

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import com.twitter.sdk.android.core.identity.TwitterLoginButton
import android.widget.Toast
import com.twitter.sdk.android.core.TwitterException
import com.twitter.sdk.android.core.TwitterSession
import com.twitter.sdk.android.core.Callback
import android.content.Intent




class SignInActivity : AppCompatActivity(){
    private var API_KEY = "rCfvvZWQbxmxdu5Epq6jaAEzu"
    private var API_SECRET = "nNEeVy52svGyHGAJO2VUmfZudx0yzKhU7NzftNR57bIahaqocL"
    private var ACCESS_TOKEN = "80335782-t9ZMQuQKhuN9dnxpfIazLiAWVSi6JnpCqVJnUAO3V"
    private var ACCESS_TOKEN_SECRET = "GpQqTcXWnDZDqAxcbJbfTcWiL5Hyuy8VFvl2kKsUKsmM3"

    var signInButton:TwitterLoginButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val signInButton = findViewById<TwitterLoginButton>(R.id.login_button)
        signInButton.callback = object : Callback<TwitterSession>() {
            override fun success(result: com.twitter.sdk.android.core.Result<TwitterSession>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                val toast = Toast.makeText(this@SignInActivity, "ログイン成功", Toast.LENGTH_LONG)
                toast.show()
            }

    //            override fun success(result: Result<TwitterSession>) {
    //                // Do something with result, which provides a TwitterSession for making API calls
    //
    //                val toast = Toast.makeText(this@SignInActivity, "ログイン成功", Toast.LENGTH_LONG)
    //                toast.show()
    //            }

            override fun failure(exception: TwitterException) {
                // Do something on failure

                val toast = Toast.makeText(this@SignInActivity, "ログイン失敗", Toast.LENGTH_LONG)
                toast.show()
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)

        // Pass the activity result to the login button.
        signInButton.onActivityResult(requestCode, resultCode, data)
    }

}