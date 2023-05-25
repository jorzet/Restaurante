package unam.fca.restaurante.model

data class Order(
    var orderId: Int = 1,
    var start: String = "",
    var middleTime: String = "",
    var entree: String = "",
    var beverage: String = ""
)
