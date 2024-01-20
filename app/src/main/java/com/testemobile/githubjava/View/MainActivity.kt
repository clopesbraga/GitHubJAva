package com.testemobile.githubjava

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.testemobile.githubjava.View.ListFragment
import com.testemobile.githubjava.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    companion object {

        private var  PAGE : Int=1
    }

  private lateinit var _binding: ActivityMainBinding
  private lateinit var  btnAnterior: Button
  private lateinit var  btnProximo: Button
  private lateinit var  txtNumPage : TextView
  private lateinit var  txtNum : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intialSetup()

        btnAnterior = _binding.btnAnterior
        btnProximo =  _binding.btnProximo

        txtNumPage = _binding.txtNumPage
        txtNum =     _binding.txtNumPage

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

    private fun intialSetup(){

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        setUpBar()
    }

    private fun setUpBar(){
        val toolbar: Toolbar = _binding.toolbar
        val titleTextColor = _binding.toolbar.resources.getColor(R.color.white)

        setSupportActionBar(toolbar)
        toolbar.setTitleTextColor(titleTextColor)
    }

}