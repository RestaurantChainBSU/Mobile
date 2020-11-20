package com.example.mobile

import android.app.Application
import com.example.mobile.network.dishes.DishesItem

class App : Application() {
   companion object {
       private val listOfDishesAtOrder = mutableListOf<DishesItem>()
       fun insert(dish: DishesItem) {
           listOfDishesAtOrder.add(dish)
       }
       fun getList() = listOfDishesAtOrder
   }
}