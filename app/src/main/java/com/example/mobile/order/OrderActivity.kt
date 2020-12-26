package com.example.mobile.order

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobile.App
import com.example.mobile.R
import com.example.mobile.dishes.DishAdapter
import com.example.mobile.network.Apifactory
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_dishes.*
import kotlinx.android.synthetic.main.activity_dishes.list
import kotlinx.android.synthetic.main.activity_map.*
import kotlinx.android.synthetic.main.activity_order.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OrderActivity : AppCompatActivity() {

    private val adapter = OrderAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter

        adapter.submitList(App.getList())

        makeOrder.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) {
                try {
                    val mutableList = mutableListOf<Any>(Address(addres.text.toString()))
                    for (item in App.getList()) {
                        mutableList.add(item)
                    }
                    Apifactory.api.makeOrder(mutableList.toList()).await()
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@OrderActivity, "Заказ оформлен", Toast.LENGTH_LONG ).show()
                    }
                } catch (e: Exception) {
                }
            }
        }
    }
}

data class Address (
    val address: String
)