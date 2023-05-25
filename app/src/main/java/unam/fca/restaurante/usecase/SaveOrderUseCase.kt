package unam.fca.restaurante.usecase

import com.google.gson.Gson
import unam.fca.restaurante.App
import unam.fca.restaurante.model.Order
import unam.fca.restaurante.repository.SharedPreferencesManager

class SaveOrderUseCase {

    fun saveOrder(order: Order) {
        App.INSTANCE?.baseContext?.let {
            val orderJson = SharedPreferencesManager(it).getOrders()
            val orderList: List<Order>
            if (orderJson.isNullOrEmpty()) {
                orderList = arrayListOf()
                orderList.add(order)
                val orderToStore = Gson().toJson(orderList)
                SharedPreferencesManager(it).storeOrder(orderToStore)
            } else {
                orderList = Gson().fromJson(orderJson, Array<Order>::class.java).toList()
                order.orderId = orderList[orderList.lastIndex].orderId + 1
                val newArray = arrayListOf<Order>()
                newArray.addAll(orderList)
                newArray.add(order)
                val orderToStore = Gson().toJson(newArray)
                SharedPreferencesManager(it).storeOrder(orderToStore)
            }


        }
    }
}