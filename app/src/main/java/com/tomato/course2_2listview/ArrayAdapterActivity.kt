package com.tomato.course2_2listview

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import com.tomato.course2_2listview.databinding.ActivityArrayAdapterBinding
import com.tomato.course2_2listview.databinding.CustomDialogBinding
import java.util.*

class ArrayAdapterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArrayAdapterBinding
    private lateinit var adapter: ArrayAdapter<Character>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArrayAdapterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupArrayAdapterList()

        binding.fab1.setOnClickListener { addCharacter() }
    }

    private fun addCharacter() {
        val dialogBinding = CustomDialogBinding.inflate(layoutInflater)

        AlertDialog.Builder(this)
            .setView(dialogBinding.root)
            .setTitle("Введите имя")
            .setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
                val name = dialogBinding.inputText.text.toString()
                if (name.isNotBlank()) {
                    createCharacter(name)
                }
            })
            .create()
            .show()
    }

    private fun createCharacter(name: String) {
        val character = Character(id = UUID.randomUUID().toString(), name)
        adapter.add(character)
    }

    private fun setupArrayAdapterList() {
        val data = mutableListOf(
            Character(id = UUID.randomUUID().toString(), "Pavel"),
            Character(id = UUID.randomUUID().toString(), "Ivan"),
            Character(id = UUID.randomUUID().toString(), "Sidor"),
            Character(id = UUID.randomUUID().toString(), "Alex")
        )

        adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            android.R.id.text1 ,
            data
        )

        binding.listview.adapter = adapter
        binding.listview.setOnItemClickListener { parent, view, position, id ->
            adapter.getItem(position)?.let {
                deleteCharacter(it)
            }
        }
    }

    private fun deleteCharacter(item: Character) {
        val listener = DialogInterface.OnClickListener { dialog, which ->
            if (which == DialogInterface.BUTTON_POSITIVE) {
                adapter.remove(item)
            }
        }
        
        AlertDialog.Builder(this)
            .setTitle("Удалить строку?")
            .setMessage("Вы действительно хотите удалить строку ${item.text}?")
            .setPositiveButton("ДА", listener)
            .setNegativeButton("НЕТ", listener)
            .create()
            .show()
    }

    class Character(val id: String, val text: String) {
        override fun toString(): String {
            return text
        }
    }
}