package com.github.lipenathan.teamcreator.views.components.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.widget.doAfterTextChanged
import com.github.lipenathan.teamcreator.databinding.NumberIncrementerBinding

class NumberIncrementer @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyle, defStyleRes) {

    private val binding: NumberIncrementerBinding
    var value = 1
        private set

    init {
        binding = NumberIncrementerBinding.inflate(LayoutInflater.from(context), this, true)
        setListeners()
    }

    private fun setListeners() {
        binding.apply {
            buttonMinus.setOnClickListener {
                if (value > 0) {
                    value--
                }
                editValue.setText(value.toString())
            }
            buttonPlus.setOnClickListener {
                value++
                editValue.setText(value.toString())
            }
            editValue.doAfterTextChanged {
                if (it.toString().isEmpty()) {
                    value = 0
                } else {
                    value = it.toString().toInt()
                }
            }
        }
    }

    fun setCustomClickListener(clickListener: (NumberIncrementer)-> Unit) {
        clickListener(this)
    }

}