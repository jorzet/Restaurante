package unam.fca.restaurante.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import unam.fca.restaurante.model.Order
import unam.fca.restaurante.model.OrderViewState
import unam.fca.restaurante.repository.SharedPreferencesManager
import unam.fca.restaurante.usecase.GetOrdersUseCase
import unam.fca.restaurante.usecase.SaveOrderUseCase

class OrderViewModel(
    private val saveOrderUseCase: SaveOrderUseCase = SaveOrderUseCase(),
    private val getOrdersUseCase: GetOrdersUseCase = GetOrdersUseCase()
): ViewModel() {

    val orderViewState = MutableLiveData<OrderViewState>()

    fun orderCommand(order: Order) {
        saveOrderUseCase.saveOrder(order)
        orderViewState.value = OrderViewState.OrderCommandSuccess("Orden registrada con exito")
    }

    fun getOrderCommand() {
        val orders = getOrdersUseCase.getOrders().replace(",", "\n")
        orderViewState.value = OrderViewState.GetOrderCommandSuccess(orders)
    }
}