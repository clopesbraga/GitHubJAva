package com.testemobile.githubjava

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.testemobile.githubjava.View.ListFragment


class MainActivity : AppCompatActivity() {

    lateinit var  btnlista: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnlista = findViewById<Button>(R.id.btnlista)

    }

    override fun onResume() {
        super.onResume()

        btnlista.setOnClickListener{

            val fragment = ListFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit()
        }

    }

}