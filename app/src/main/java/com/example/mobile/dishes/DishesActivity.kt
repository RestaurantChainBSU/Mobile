package com.example.mobile.dishes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobile.R
import com.example.mobile.network.Apifactory
import kotlinx.android.synthetic.main.activity_dishes.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DishesActivity : AppCompatActivity() {

    private val adapter = DishAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dishes)

        val bundle = intent.extras
        val name = bundle!!.getString("key")!!
        Log.d("WTF", "$name")

        loadingDishes.visibility = View.VISIBLE
        spin_kitDishes.visibility = View.VISIBLE
        GlobalScope.launch(Dispatchers.IO) {

            val restaraunts = Apifactory.api.getDishes(Integer.parseInt(name)).await()
            withContext(Dispatchers.Main) {
                adapter.submitList(restaraunts)
                loadingDishes.visibility = View.INVISIBLE
                spin_kitDishes.visibility = View.INVISIBLE
            }
        }

        list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        list.adapter = adapter


    }
}