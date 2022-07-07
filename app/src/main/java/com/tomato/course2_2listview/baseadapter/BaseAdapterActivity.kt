package com.tomato.course2_2listview.baseadapter

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.tomato.course2_2listview.databinding.ActivityBaseAdapterBinding
import com.tomato.course2_2listview.databinding.CustomDialogBinding
import kotlin.random.Random

class BaseAdapterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBaseAdapterBinding

    private val data = mutableListOf<Character>(
        Character(1, "John", false),
        Character(2, "Neo", false),
        Character(3, "Morpheus", false),
        Character(4, "Trinity", false),
    )

    private lateinit var adapter: CharacterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBaseAdapterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupList()
        binding.fab1.setOnClickListener { addPressed() }
    }

    private fun addPressed() {
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
        val character = Character(id = Random.nextLong(), name, true)
        data.add(character)
        adapter.notifyDataSetChanged()
    }

    private fun setupList() {
        adapter = CharacterAdapter(data) {
            deleteCharacter(it)
        }
        binding.listview.adapter = adapter
        binding.listview.setOnItemClickListener { parent, view, position, id ->
            showInfo(adapter.getItem(position))
        }
    }

    private fun showInfo(item: Character) {
        AlertDialog.Builder(this)
            .setTitle("Выбран элемент")
            .setMessage("Выбран персонаж ${item.text}")
            .setPositiveButton("OK", { dialog, which ->  })
            .create()
            .show()
    }

    private fun deleteCharacter(character: Character) {
        AlertDialog.Builder(this)
            .setTitle("Удалить строку?")
            .setMessage("Вы действительно хотите удалить строку ${character.text}?")
            .setPositiveButton("ДА", {dialog, which ->
                    data.remove(character)
                    adapter.notifyDataSetChanged()
                })
            .setNegativeButton("НЕТ", {dialog, which -> })
            .create()
            .show()
    }
}