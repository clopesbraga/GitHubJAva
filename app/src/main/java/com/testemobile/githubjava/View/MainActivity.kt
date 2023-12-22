package com.testemobile.githubjava

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.testemobile.githubjava.View.ListFragment
import com.testemobile.githubjava.ViewModel.RepositorioViewModel


class MainActivity : AppCompatActivity() {

    lateinit var  btnAnterior: Button
    lateinit var  btnProximo: Button
    lateinit var txtNumPage : TextView
    lateinit var txtNum : TextView
    private var page =1

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

        btnProximo.setOnClickListener {
            page++
            txtNum.text=page.toString()
            buttonAction(page.toString())
        }

        btnAnterior.setOnClickListener {
            page--
            txtNum.text= page.toString()
            buttonAction(page.toString())
        }

    }

    fun buttonAction(page: String){

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