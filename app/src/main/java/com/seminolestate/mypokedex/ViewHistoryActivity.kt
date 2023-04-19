package com.seminolestate.mypokedex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_view_history.*

class ViewHistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_history)

        title = "MyPokeDex"
        val context = this
        val db = DataBaseHandler(context)

        btnBack.setOnClickListener(View.OnClickListener { startActivity(Intent(this, MainActivity::class.java)) })

        val data = db.readData()
        dbResult.text = ""
        for (i in 0 until data.size) {
            dbResult.append(
                data[i].searchText + "\n "
            )
        }


    }


}