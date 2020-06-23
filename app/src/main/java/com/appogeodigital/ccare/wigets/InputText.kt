package com.appogeodigital.ccare.wigets

import android.content.Context
import android.text.Editable
import android.text.InputFilter
import android.text.InputType
import android.text.TextWatcher
import android.text.method.DigitsKeyListener
import android.util.AttributeSet
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.appogeodigital.ccare.R
import kotlinx.android.synthetic.main.component_input.view.*
import java.text.DecimalFormatSymbols

/**
 * input text
 */
class InputText(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {

    private var tvTitle: TextView
    private val txError: TextView
    private val editText: EditText
    private val btnClear: ImageView
    private val btnEye: ImageView

    init {
        View.inflate(this.context, R.layout.component_input, this)
        tvTitle = findViewById(R.id.tv_title)
        editText = findViewById(R.id.et_component)
        txError = findViewById(R.id.tx_error)
        btnClear = findViewById(R.id.image_clear)
        btnEye = findViewById(R.id.image_eye)
        btnEye.visibility = GONE
        txError.visibility = GONE
        btnClear.visibility = GONE
        val separator: Char = DecimalFormatSymbols.getInstance().decimalSeparator
        val separatorThousand: Char = DecimalFormatSymbols.getInstance().groupingSeparator

        attrs.let {

            val typeArray = context.obtainStyledAttributes(it, R.styleable.ComponentInput, 0, 0)
            val title = typeArray.getString(R.styleable.ComponentInput_input_title)
            val error = typeArray.getString(R.styleable.ComponentInput_input_error)
            val hint = typeArray.getString(R.styleable.ComponentInput_input_hint)
            val text = typeArray.getString(R.styleable.ComponentInput_input_text)
            val textoff = typeArray.getString(R.styleable.ComponentInput_input_text_off)
            //val dateBlock = typeArray.getString(R.styleable.ComponentInput_date_block)
            val allCaps = typeArray.getString(R.styleable.ComponentInput_all_caps)


            val inputType = typeArray.getInt(
                R.styleable.ComponentInput_android_inputType,
                InputType.TYPE_CLASS_TEXT
            )
            editText.inputType = inputType


            if(inputType == InputType.TYPE_CLASS_PHONE) {

                editText.keyListener.apply {
                    DigitsKeyListener.getInstance("0123456789$separator$separatorThousand")
                }

                editText.addTextChangedListener(object : TextWatcher {
                    override fun onTextChanged(s: CharSequence?,
                                               start: Int,
                                               before: Int,
                                               count: Int) {

                        if(!s.isNullOrEmpty()) {


                            when {
                                s!!.split(separator).size > 1 || s!!.last() == separatorThousand || s.last() == separator -> {
                                    editText.keyListener =
                                        DigitsKeyListener.getInstance("0123456789")

                                }

                                else -> {
                                    editText.keyListener =
                                        DigitsKeyListener.getInstance("0123456789$separator$separatorThousand")

                                }
                            }
                        }
                    }

                    override fun afterTextChanged(s: Editable?) {
                    }

                    override fun beforeTextChanged(s: CharSequence?,
                                                   start: Int,
                                                   count: Int,
                                                   after: Int) {

                    }
                })

            }

            if(allCaps!=null){
                editText.filters = arrayOf(InputFilter.AllCaps())
            }


            if(title != null) tvTitle.text = title

            if(error != null) txError.text = error

            if(text != null) editText.setText(text)

            if(textoff != null) editText.isEnabled = false

            if(hint != null) editText.hint = hint
            typeArray.recycle()

        }

        btnClear.setOnClickListener { this.onDelete() }

        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {


            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                txError.visibility = GONE
                btnClear.visibility = GONE
            }
        })

        editText.setOnFocusChangeListener { _, hasFocus ->
            if(hasFocus) {
                ly_input.setBackgroundResource(R.drawable.camera_border_input_focus)
            } else {
                ly_input.setBackgroundResource(R.drawable.camera_border_input_un_focus)
            }
        }

    }

    private fun onDelete() {
        txError.visibility = GONE
        btnClear.visibility = GONE
        editText.setText("")
    }

}
