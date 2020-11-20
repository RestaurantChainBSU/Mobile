package com.example.mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_dishes.*

class DishesActivity : AppCompatActivity() {

    private val adapter = DishAdapter()

    val listOfDishes = listOf(
        DishItem("Блины",  "https://static.independent.co.uk/s3fs-public/thumbnails/image/2017/03/14/14/pancakes-istock-stephaniefrey-.jpg?width=982&height=726", 5F),
        DishItem("Устрицы", "https://loveincorporated.blob.core.windows.net/contentimages/gallery/8af07ba5-d39f-487d-aa37-4e62a9fdad37-worlds-most-delicious-dishes-youll-want-to-try.jpg", 7F),
        DishItem("Бургер", "https://images.immediate.co.uk/production/volatile/sites/30/2020/08/mexican-chicken-burger_1-b5cca6f.jpg?quality=90&resize=440%2C400", 4F),

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dishes)

        val bundle = intent.extras
        val name = bundle!!.getString("key")!!
        Log.d("WTF", "$name")

        list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        list.adapter = adapter

        adapter.submitList(listOfDishes)
    }
}