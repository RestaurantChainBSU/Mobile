package com.example.mobile

import android.app.Application

class App : Application() {
   companion object {
       private val listOfDishesAtOrder = mutableListOf<DishItem>()
       fun insert(dish: DishItem) {
           listOfDishesAtOrder.add(dish)
       }
       fun getList() = listOfDishesAtOrder
   }
}