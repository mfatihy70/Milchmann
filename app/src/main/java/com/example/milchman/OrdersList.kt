package com.example.milchman

class OrdersList (
    private val orders: MutableList<Order> = mutableListOf()
) {
    fun addOrder(order: Order) {
        orders.add(order)
    }

    fun removeOrder(order: Order) {
        orders.remove(order)
    }

    fun getOrder(index: Int): Order {
        return orders[index]
    }

    fun getOrders(): List<Order> {
        return orders
    }
}