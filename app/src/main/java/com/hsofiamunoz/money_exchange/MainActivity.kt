package com.hsofiamunoz.money_exchange

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hsofiamunoz.money_exchange.databinding.ActivityMainBinding

private const val SPACE = " "
private const val EMPTY = ""

class MainActivity : AppCompatActivity() {

    // newBinding
    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        //action
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setIcon(R.mipmap.ic_launcher)

        mainBinding.exhangeButton.setOnClickListener {
            // Variables
            val dinero = mainBinding.valueEditText.text.toString()
            var resultado: Double = 0.0


            val dolar = if (mainBinding.convertDolarRadioButton.isChecked) getString(R.string.convert_dolar) else getString(R.string.convert_euro)
            val cop = if (mainBinding.convertCopRadioButton.isChecked) getString(R.string.convert_cop) else getString(R.string.convert_euro)


            val dolar_f = if (mainBinding.convertedDolarRadioButton.isChecked) getString(R.string.convert_dolar) else EMPTY
            val euro_f = if (mainBinding.convertedEuroRadioButton.isChecked) getString(R.string.convert_euro) else EMPTY
            val cop_f = if (mainBinding.convertedCopRadioButton.isChecked) getString(R.string.convert_cop) else EMPTY


            if (dinero.isNotEmpty()) {
                // DOLARES A EUROS Y PESOS
                if (dolar == getString(R.string.convert_dolar)) {
                    if (euro_f == getString(R.string.convert_euro)) {
                        resultado = dinero.toDouble() * 0.84
                        saveCoin(dinero, resultado)


                    } else {
                        if (cop_f == getString(R.string.convert_cop)) {
                            resultado = dinero.toDouble() * 3659.5
                            saveCoin(dinero, resultado)

                        }
                        else {
                            Toast.makeText(this, getString(R.string.dif_coin), Toast.LENGTH_LONG).show()
                            cleanViews()
                        }

                    }
                } else {
                    // COP A DOLARES Y EUROS
                    if (cop == getString(R.string.convert_cop)) {
                        if (dolar_f == getString(R.string.convert_dolar)) {
                            resultado = dinero.toDouble() * 0.00028
                            saveCoin(dinero, resultado)

                        } else {
                            if (euro_f == getString(R.string.convert_euro)) {
                                resultado = dinero.toDouble() * 0.00023
                                saveCoin(dinero, resultado)

                            } else {
                                Toast.makeText(
                                    this,
                                    getString(R.string.dif_coin),
                                    Toast.LENGTH_LONG
                                ).show()
                                cleanViews()
                            }
                        }

                    }
                    // EURO A DOLAR Y COP
                    else {
                        if (dolar_f == getString(R.string.convert_dolar)) {
                            resultado = dinero.toDouble() * 1.19
                            saveCoin(dinero, resultado)

                        } else
                        {
                            if (cop_f == getString(R.string.convert_cop)) {
                                resultado = dinero.toDouble() * 4355.08
                                saveCoin(dinero, resultado)
                            } else {
                                Toast.makeText(this, getString(R.string.dif_coin), Toast.LENGTH_LONG).show()
                                cleanViews()
                            }

                        }
                    }

                }


            } else Toast.makeText(this, getString(R.string.warning_quantity), Toast.LENGTH_SHORT).show()
        }
    }

    private fun cleanViews() {
        with(mainBinding) {
            resultTextView.text = EMPTY
            valueEditText.setText(EMPTY)
        }
    }


    private fun saveCoin(dinero: String, resultado: Double) {
        val euro_f = if (mainBinding.convertedEuroRadioButton.isChecked) getString(R.string.convert_euro) else EMPTY
        val cop_f = if (mainBinding.convertedCopRadioButton.isChecked) getString(R.string.convert_cop) else EMPTY

        val newCoin = Coin( dinero, resultado)
        if(cop_f == getString(R.string.convert_cop))
        {
            mainBinding.resultTextView.text = getString(R.string.result_exchange) + SPACE + SPACE + newCoin.resultado.toFloat().toString() + SPACE + getString(R.string.cop_plural)
        }
        else{
            if(euro_f == getString(R.string.convert_euro))
                mainBinding.resultTextView.text = getString(R.string.result_exchange) + SPACE + SPACE + newCoin.resultado.toFloat().toString() + SPACE + getString(R.string.euro_plural)
            else
                mainBinding.resultTextView.text = getString(R.string.result_exchange) + SPACE + SPACE + newCoin.resultado.toFloat().toString() + SPACE + getString(R.string.dolar_plural)
        }

    }

}