package com.testemobile.githubjava

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.testemobile.githubjava.View.ListFragment


class MainActivity : AppCompatActivity() {

    companion object {

        private var  PAGE : Int=1
    }

  private lateinit var  btnAnterior: Button
  private lateinit var  btnProximo: Button
  private lateinit var txtNumPage : TextView
  private lateinit var txtNum : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnAnterior = findViewById(R.id.btnAnterior)
        btnProximo =  findViewById(R.id.btnProximo)

        txtNumPage = findViewById((R.id.txtNumPage))
        txtNum =  findViewById((R.id.txtPage))

    }

    override fun onResume() {
        super.onResume()

        txtNum.text=PAGE.toString()
        chargeRepoList(PAGE.toString())

        btnProximo.setOnClickListener {
            PAGE++
            txtNum.text=PAGE.toString()
            chargeRepoList(PAGE.toString())
        }

        btnAnterior.setOnClickListener {
            PAGE--
            if(isMinimumPage()) {
                txtNum.text = PAGE.toString()
            }else{
                txtNum.text = PAGE.toString()
                chargeRepoList(PAGE.toString())
            }
        }

    }

   private fun isMinimumPage(): Boolean{

        if(PAGE<1){
            PAGE =1
            return true
        }

        return false
    }

  private fun chargeRepoList(page: String){

            val fragment = ListFragment()
            val args = Bundle()
            args.putString("page",page)
            fragment.arguments = args
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit()
    }
}