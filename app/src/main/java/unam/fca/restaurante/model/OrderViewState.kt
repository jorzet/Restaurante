package unam.fca.restaurante.model

sealed class OrderViewState {
    data class OrderCommandSuccess(val message: String): OrderViewState()
    data class GetOrderCommandSuccess(val orderList: String): OrderViewState()
}