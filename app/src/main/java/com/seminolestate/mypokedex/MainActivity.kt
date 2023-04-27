package com.seminolestate.mypokedex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.seminolestate.mypokedex.data.PokemonData
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://pokeapi.co/api/v2/pokemon/"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        title = "KotlinApp"
        val context = this
        val db = DataBaseHandler(context)

        btnViewHistory.setOnClickListener(View.OnClickListener { startActivity(Intent(this, ViewHistoryActivity::class.java)) })
        btnInsert.setOnClickListener {
            if (inputSearchText.text.toString().isNotEmpty()
            ) {

                getPokemonData(inputSearchText.text.toString())

                val searchItem = SearchItem(inputSearchText.text.toString())
                db.insertData(searchItem)
                clearField()
            }
            else {
                Toast.makeText(context, "Please Fill All Data's", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun getPokemonData(url: String) {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData(url)

        retrofitData.enqueue(object : Callback<PokemonData?> {
            override fun onResponse(call: Call<PokemonData?>, response: Response<PokemonData?>) {
                val responseBody = response.body()!!

                val myStringBuilder = StringBuilder()

                myStringBuilder.append(responseBody.name)

                val myTextView: TextView = findViewById(R.id.tvResult)
                myTextView.text = myStringBuilder

            }

            override fun onFailure(call: Call<PokemonData?>, t: Throwable) {
                Log.d("MainActivity", "onFailure: "+t.message)
            }
        })
    }

    private fun clearField() {
        inputSearchText.text.clear()
    }
}