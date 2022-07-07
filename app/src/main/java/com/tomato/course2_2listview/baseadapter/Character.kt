package com.tomato.course2_2listview.baseadapter

class Character(val id: Long, val text: String, val isCustom: Boolean) {
    override fun toString(): String {
        return text
    }
}