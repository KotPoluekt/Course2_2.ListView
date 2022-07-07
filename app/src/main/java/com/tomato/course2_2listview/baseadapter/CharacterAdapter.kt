package com.tomato.course2_2listview.baseadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.tomato.course2_2listview.databinding.ItemCharacterBinding

typealias OnDeletedPressListener = (Character) -> Unit

class CharacterAdapter(
    private val characters: List<Character>,
    private val onDeleteListener: OnDeletedPressListener) : BaseAdapter(), View.OnClickListener {

    override fun getCount(): Int {
        return characters.size
    }

    override fun getItem(position: Int): Character {
        return characters[position]
    }

    override fun getItemId(position: Int): Long {
        return characters[position].id
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = convertView?.tag as ItemCharacterBinding? ?:
            createBinding(parent.context)

        val character = getItem(position)

        binding.titleTextView.text = character.text
        binding.deleteImageView.tag = character
        binding.deleteImageView.visibility = if (character.isCustom) View.VISIBLE else View.GONE

        return binding.root


    }

    private fun createBinding(context: Context): ItemCharacterBinding {
        val binding = ItemCharacterBinding.inflate(LayoutInflater.from(context))
        binding.deleteImageView.setOnClickListener(this)
        binding.root.tag = binding
        return binding
    }

    override fun onClick(v: View) {
        onDeleteListener.invoke(v.tag as Character)
    }
}