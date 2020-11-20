package com.example.mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import coil.load
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.activity_details.view.*

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val bundle = intent.extras
        val name = bundle!!.getString("name")!!
        val url = bundle!!.getString("url")!!
        collapsingToolbar.title = name
        toolbarImage.load(url)
    }
}