package com.testemobile.githubjava

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.testemobile.githubjava.Model.GitHubRepo
import com.testemobile.githubjava.Retrofit.RepoGetService
import com.testemobile.githubjava.Retrofit.RetrofitService
import com.testemobile.githubjava.View.ListFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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