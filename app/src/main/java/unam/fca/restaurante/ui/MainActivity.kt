package unam.fca.restaurante.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import unam.fca.restaurante.databinding.ActivityMainBinding
import unam.fca.restaurante.model.Order
import unam.fca.restaurante.model.OrderViewState
import unam.fca.restaurante.utils.setGone
import unam.fca.restaurante.utils.setVisible
import unam.fca.restaurante.viewModel.OrderViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: OrderViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[OrderViewModel::class.java]
        setContentView(binding.root)
        setUpListeners()

        viewModel.orderViewState.observe(this, ::renderOder)
    }

    private fun setUpListeners() {
        binding.rgStart.setGone()
        binding.rgMiddleTime.setGone()
        binding.rgEntree.setGone()
        binding.rgBeverages.setGone()

        binding.btnOrder.setOnClickListener {
            orderCommand()
        }

        binding.btnShowOrders.setOnClickListener {
            viewModel.getOrderCommand()
        }

        binding.cbStart.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                binding.rgStart.setVisible()
            } else {
                binding.rgStart.setGone()
            }
        }

        binding.cbMiddleTime.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                binding.rgMiddleTime.setVisible()
            } else {
                binding.rgMiddleTime.setGone()
            }
        }

        binding.cbEntree.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                binding.rgEntree.setVisible()
            } else {
                binding.rgEntree.setGone()
            }
        }

        binding.cbBeverages.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                binding.rgBeverages.setVisible()
            } else {
                binding.rgBeverages.setGone()
            }
        }
    }

    private fun renderOder(orderViewState: OrderViewState) {
        when(orderViewState) {
            is OrderViewState.GetOrderCommandSuccess -> {
                showOrderCommand(orderViewState.orderList)
            }
            is OrderViewState.OrderCommandSuccess -> {
                showMessage(orderViewState.message)
            }
        }
    }

    private fun orderCommand() {
        if (binding.cbStart.isChecked || binding.cbMiddleTime.isChecked || binding.cbEntree.isChecked || binding.cbEntree.isChecked) {


            val order = Order()
            if (binding.cbStart.isChecked) {
                val start =
                    findViewById<RadioButton>(binding.rgStart.checkedRadioButtonId).text.toString()
                order.start = start
            }

            if (binding.cbMiddleTime.isChecked) {
                val middleTime =
                    findViewById<RadioButton>(binding.rgMiddleTime.checkedRadioButtonId).text.toString()
                order.middleTime = middleTime
            }

            if (binding.cbEntree.isChecked) {
                val entree =
                    findViewById<RadioButton>(binding.rgEntree.checkedRadioButtonId).text.toString()
                order.entree = entree
            }

            if (binding.cbBeverages.isChecked) {
                val beverage =
                    findViewById<RadioButton>(binding.rgBeverages.checkedRadioButtonId).text.toString()
                order.beverage = beverage
            }

            viewModel.orderCommand(order)
            clearValues()
        } else {
            showMessage("Necesita seleccionar productos para ordenar")
        }
    }

    private fun clearValues() {
        binding.cbStart.isChecked = false
        binding.cbMiddleTime.isChecked = false
        binding.cbEntree.isChecked = false
        binding.cbBeverages.isChecked = false

        binding.rgStart.setGone()
        binding.rgMiddleTime.setGone()
        binding.rgEntree.setGone()
        binding.rgBeverages.setGone()

        binding.rgStart.clearCheck()
        binding.rgMiddleTime.clearCheck()
        binding.rgEntree.clearCheck()
        binding.rgBeverages.clearCheck()
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun showOrderCommand(orderCommand: String) {
        CustomToast.makeText(this, orderCommand, Toast.LENGTH_LONG)?.show()
    }
}