package com.example.sns_share

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Instagram share
        btn1.setOnClickListener {
            val launchIntent = packageManager.getLaunchIntentForPackage("com.instagram.android")

            if (launchIntent == null) { // 단말기 내에 어플리케이션(앱)이 설치되어 있지 않음.
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + "com.instagram.android"))
                startActivity(intent)          // 앱이 설치되어 있지 않은 경우 Play스토어로 이동
            } else {
                val backgroundAssetUri: Uri =
                    Uri.parse("android.resource://$packageName/drawable/back")
                val intent = Intent(android.content.Intent.ACTION_SEND)
                intent.type = "image/*"
                intent.putExtra(Intent.EXTRA_STREAM, backgroundAssetUri)
                intent.setPackage("com.instagram.android")

                startActivity(intent)
            }


        }


    }
}