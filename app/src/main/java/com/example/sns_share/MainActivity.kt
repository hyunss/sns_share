package com.example.sns_share

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.ByteArrayOutputStream

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        instagram_btn.setOnClickListener {
            instagramShare()
        }
        facebook_btn.setOnClickListener {

            val bmp = BitmapFactory.decodeResource(resources, R.drawable.abc)
            val uri: Uri? = getImageUri(this, bmp)
            val intent = Intent(android.content.Intent.ACTION_SEND)
            intent.type = "image/*"
            intent.putExtra(Intent.EXTRA_STREAM, uri)

            val shareIntent = Intent.createChooser(intent, null)
            startActivity(shareIntent)

        }

    }
    private fun getImageUri(context: Context, inImage: Bitmap): Uri? {
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path: String = MediaStore.Images.Media.insertImage(
            context.contentResolver,
            inImage,
            "Title",
            null
        )
        return Uri.parse(path)
    }
    //Instagram share
    private fun instagramShare(){
        val launchIntent = packageManager.getLaunchIntentForPackage("com.instagram.android")

        if (launchIntent == null) { // 단말기 내에 어플리케이션(앱)이 설치되어 있지 않음.
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("market://details?id=" + "com.instagram.android")
            )
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