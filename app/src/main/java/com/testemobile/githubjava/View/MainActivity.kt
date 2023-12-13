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
    private lateinit var viewModel: RepositorioViewModel

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

        viewModel = ViewModelProvider(this).get(RepositorioViewModel::class.java)

        btnProximo.setOnClickListener {
            txtNum.text="2"
            buttonAction("2")
        }

        btnAnterior.setOnClickListener {
            txtNum.text="1"
            buttonAction("1")
        }

    }

    fun buttonAction(page: String){

            viewModel.requestGitHubRepo(page)
            val fragment = ListFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit()
    }

}