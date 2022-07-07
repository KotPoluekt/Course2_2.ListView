package com.tomato.course2_2listview

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SimpleAdapter
import androidx.appcompat.app.AlertDialog
import com.tomato.course2_2listview.databinding.ActivitySimpleAdapterBinding

class SimpleAdapterActivity : AppCompatActivity() {

    private val KEY_DESCRIPTION = "KEY_DESCRIPTION"
    private val KEY_TITLE = "KEY_TITLE"
    private lateinit var binding: ActivitySimpleAdapterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySimpleAdapterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListViewSimple()
    }

    private fun setupListViewSimple() {
        val data = listOf(
            mapOf(KEY_TITLE to "First title", KEY_DESCRIPTION to "First description"),
            mapOf(KEY_TITLE to "Second title", KEY_DESCRIPTION to "Second description"),
            mapOf(KEY_TITLE to "Third title", KEY_DESCRIPTION to "Third description")
        )

        val adapter = SimpleAdapter(
            this,
            data,
            //android.R.layout.simple_list_item_2,
            R.layout.custom_item,
            arrayOf(KEY_TITLE, KEY_DESCRIPTION),
        //    intArrayOf(android.R.id.text1, android.R.id.text2))
            intArrayOf(R.id.text1, R.id.text2))

        binding.listview.adapter = adapter

        binding.listview.setOnItemClickListener { parent, view, position, id ->
            val selectedTitle = data[position][KEY_TITLE]
            val selectedDesc = data[position][KEY_DESCRIPTION]

            val dialog = AlertDialog.Builder(this)
                .setTitle(selectedTitle)
                .setMessage(selectedDesc)
                .setPositiveButton("ok", { dialog, which ->  })
                .create()
                .show()
        }
    }
}