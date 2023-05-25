package unam.fca.restaurante.usecase

import com.google.gson.Gson
import unam.fca.restaurante.App
import unam.fca.restaurante.model.Order
import unam.fca.restaurante.repository.SharedPreferencesManager

class GetOrdersUseCase {

    fun getOrders(): String {
        App.INSTANCE?.baseContext?.let {
            val orderJson = SharedPreferencesManager(it).getOrders()
            if (orderJson.isNullOrEmpty())
                return ""
            val orderList = Gson().fromJson(orderJson, Array<Order>::class.java).toList()

            var text = ""
            orderList.map {
                text += "Orden N: ${it.orderId}\n"
                if (it.start.isNotEmpty())
                    text += "Entrada: ${it.start}\n"
                if (it.middleTime.isNotEmpty())
                    text += "Entretiempos: ${it.middleTime}\n"
                if (it.entree.isNotEmpty())
                    text += "Plato fuerte: ${it.entree}\n"
                if (it.beverage.isNotEmpty())
                    text += "Bebida: ${it.beverage}\n"
                text += "\n"
            }

            return text
        }
        return ""
    }
}