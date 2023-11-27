package com.testemobile.githubjava

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.testemobile.githubjava.View.ListFragment
import com.testemobile.githubjava.ViewModel.RepositorioViewModel
import org.koin.android.ext.android.inject


class MainActivity : AppCompatActivity() {

    lateinit var  btnlista: Button
    private val viewModel : RepositorioViewModel by inject()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnlista = findViewById<Button>(R.id.btnlista)



    }

    override fun onResume() {
        super.onResume()

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