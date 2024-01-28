package com.testemobile.githubjava

import android.graphics.Color
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

    }

    override fun onResume() {
        super.onResume()

        numberPageSetup()
        buttonSetup()
    }

    private fun intialSetup(){

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        btnAnterior = _binding.btnAnterior
        btnProximo =  _binding.btnProximo

        desableBackButton()

        txtNumPage = _binding.txtNumPage
        txtNum =     _binding.txtNumPage

        setUpBar()
    }

    private fun setUpBar(){
        val toolbar: Toolbar = _binding.toolbar
        val titleTextColor = _binding.toolbar.resources.getColor(R.color.white,theme)

        setSupportActionBar(toolbar)
        toolbar.setTitleTextColor(titleTextColor)
    }

    private fun numberPageSetup(){
        txtNum.text=PAGE.toString()
        chargeRepoList(PAGE.toString())
    }

    private fun buttonSetup(){

        btnProximo.setOnClickListener {
            PAGE++

            enableBackButton()
            txtNum.text=PAGE.toString()
            chargeRepoList(PAGE.toString())
        }

        btnAnterior.setOnClickListener {
            PAGE--
            if(isMinimumPage()) {
                desableBackButton()
                txtNum.text = PAGE.toString()

            }else{
                enableBackButton()
                txtNum.text = PAGE.toString()
                chargeRepoList(PAGE.toString())
            }
        }
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

    private fun isMinimumPage(): Boolean{

        if(PAGE<=0){
            PAGE =1
            return true
        }
        return false
    }

    private fun enableBackButton(){
        btnAnterior.setBackgroundColor(Color.parseColor("#6750A4"))
        btnAnterior.isEnabled = true
    }

    private fun desableBackButton(){
        btnAnterior.setBackgroundColor(Color.LTGRAY)
        btnAnterior.isEnabled = false
    }

}