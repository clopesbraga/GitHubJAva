package com.testemobile.githubjava

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.testemobile.githubjava.View.ListFragment
import com.testemobile.githubjava.ViewModel.RepositorioViewModel


class MainActivity : AppCompatActivity() {

    lateinit var  btnlista: Button
    private lateinit var viewModel: RepositorioViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnlista = findViewById<Button>(R.id.btnlista)

    }

    override fun onResume() {
        super.onResume()

        viewModel = ViewModelProvider(this).get(RepositorioViewModel::class.java)
        viewModel.requestGitHubRepo()

        btnlista.setOnClickListener{

            val fragment = ListFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit()
        }

    }

}