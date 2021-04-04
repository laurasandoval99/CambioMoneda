package com.hsofiamunoz.money_exchange

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hsofiamunoz.money_exchange.databinding.ActivityMainBinding

private const val SPACE = " "


class MainActivity : AppCompatActivity() {

    // newBinding
    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(mainBinding.root)

        mainBinding.exhangeButton.setOnClickListener {
            // Variables
            val dinero = mainBinding.valueEditText.text.toString()
            var resultado = 0.0

            val initial_coin = if(mainBinding.convertDolarRadioButton.isChecked) getString(R.string.convert_dolar) else
                            if(mainBinding.convertedEuroRadioButton.isChecked) getString(R.string.convert_euro) else getString(R.string.convert_cop)

            val final_coin = if(mainBinding.convertedDolarRadioButton.isChecked) getString(R.string.dolars) else
                            if(mainBinding.convertedEuroRadioButton.isChecked) getString(R.string.convert_euro) else getString(R.string.convert_cop)

            if (dinero.isNotEmpty()) {
                // DOLARES A EUROS Y PESOS
                if (initial_coin == getString(R.string.convert_dolar))
                {
                    if(final_coin == getString(R.string.convert_euro)){
                        resultado = dinero.toDouble()*1.8
                        saveCoin(initial_coin,final_coin,dinero,resultado)
                    }
                    else{
                        if (final_coin == getString(R.string.convert_cop)){
                            resultado = dinero.toDouble()*3405.90
                            saveCoin(initial_coin,final_coin,dinero,resultado)
                        }
                        else Toast.makeText(this, getString(R.string.dif_coin),Toast.LENGTH_SHORT).show()
                    }
                }

                else{
                    // COP A DOLARES Y EUROS
                    if (initial_coin == getString(R.string.convert_cop))
                    {
                        if(final_coin == getString(R.string.convert_dolar))
                        {
                            resultado = dinero.toDouble()*0.00027
                            saveCoin(initial_coin,final_coin,dinero,resultado)
                        }
                        else{
                            if(final_coin == getString(R.string.convert_euro)){
                                resultado = dinero.toDouble()*0.00023
                                saveCoin(initial_coin,final_coin,dinero,resultado)
                            }
                            else Toast.makeText(this, getString(R.string.dif_coin),Toast.LENGTH_SHORT).show()
                        }

                    }
                    // EURO A DOLAR Y COP
                    else
                    {
                        if(final_coin == getString(R.string.convert_dolar))
                        {
                            resultado = dinero.toDouble()*1.18
                            saveCoin(initial_coin,final_coin,dinero,resultado)
                        }
                        else{
                            if (final_coin == getString(R.string.convert_cop)){
                                resultado = dinero.toDouble()*4306.16
                                saveCoin(initial_coin,final_coin,dinero,resultado)
                            }
                            else Toast.makeText(this, getString(R.string.dif_coin),Toast.LENGTH_SHORT).show()
                        }
                    }

                }

            }
            else Toast.makeText(this, getString(R.string.warning_quantity), Toast.LENGTH_SHORT).show()
        }

    }


    private fun saveCoin(initialCoin: String, finalCoin: String, dinero: String, resultado: Double) {
        val newCoin = Coin(initialCoin,finalCoin,dinero,resultado)
        mainBinding.resultTextView.text = newCoin.initial_coin+SPACE+ newCoin.final_coin+ SPACE+ newCoin.resultado.toFloat().toString()

    }

}