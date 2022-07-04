package com.pirksni.leantech.presentation.customview

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.widget.doOnTextChanged
import com.pirksni.leantech.databinding.ItemPersonEatBinding
import com.pirksni.leantech.extensions.setThrottledClickListener

class ItemPersonEat @JvmOverloads constructor(context: Context) : LinearLayout(context) {

    private val binding = ItemPersonEatBinding.inflate(LayoutInflater.from(context), this, true)

    fun setText(text: String) {
        binding.tvNameEat.text = text
    }

    fun onCountChangeListener(onTextChange: (String, String) -> Unit) {
        with(binding) {
            tvCount.doOnTextChanged { text, _, _, _ ->
                onTextChange(tvNameEat.text.toString(), text.toString())
            }
        }
    }

    init {
        with(binding) {
            ivAdd.setThrottledClickListener(DELAY) {
                val oldText = tvCount.text.toString().toInt()
                if (oldText < MAX_NUMBER_CONT) {
                    val newText = oldText + DIFFERENCE_NUMBER
                    tvCount.text = newText.toString()
                } else {
                    return@setThrottledClickListener
                }
            }
            ivRemove.setThrottledClickListener(DELAY) {
                val oldText = tvCount.text.toString().toInt()
                if (oldText == 0) {
                    return@setThrottledClickListener
                } else {
                    val newText = oldText - DIFFERENCE_NUMBER
                    tvCount.text = newText.toString()
                }
            }
        }
    }

    companion object {
        private const val DIFFERENCE_NUMBER = 1
        private const val MAX_NUMBER_CONT = 100
        private const val DELAY = 100L
    }
}
