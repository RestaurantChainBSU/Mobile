package com.example.mobile.dishes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import coil.load
import com.example.mobile.App
import com.example.mobile.R
import com.example.mobile.network.dishes.DishesItem
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val bundle = intent.extras
        val item = bundle!!.getParcelable<DishesItem>("item")!!
        text.text = item.dish_descr
        collapsingToolbar.title = item.dish_name
        toolbarImage.load(item.image_link)

        add.setOnClickListener {
            App.insert(item)
            Toast.makeText(applicationContext, "Добавлен в заказ", Toast.LENGTH_SHORT).show()
        }

    }
}