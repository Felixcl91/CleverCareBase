package com.appogeodigital.ccare.wigets

import android.content.Context
import android.text.*
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.appogeodigital.ccare.R
import kotlinx.android.synthetic.main.component_input.view.*

/**
 * input text password
 */
class InputTextPass(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {

    private var tvTitle: TextView
    private val txError: TextView
    private val editTextPassword: EditText
    private val btnClear: ImageView
    private val btnEye: ImageView
    private var see: Boolean = false

    init {
        View.inflate(this.context, R.layout.component_input_password, this)
        tvTitle = findViewById(R.id.tv_title)
        editTextPassword = findViewById(R.id.et_component_password)
        txError = findViewById(R.id.tx_error)
        btnClear = findViewById(R.id.image_clear)
        btnEye = findViewById(R.id.image_eye)
        btnEye.visibility = GONE
        txError.visibility = GONE
        btnClear.visibility = GONE

        attrs.let {

            val typeArray = context.obtainStyledAttributes(it, R.styleable.ComponentInput, 0, 0)
            val title = typeArray.getString(R.styleable.ComponentInput_input_title)
            val error = typeArray.getString(R.styleable.ComponentInput_input_error)
            val hint = typeArray.getString(R.styleable.ComponentInput_input_hint)
            val text = typeArray.getString(R.styleable.ComponentInput_input_text)
            val textoff = typeArray.getString(R.styleable.ComponentInput_input_text_off)
            val dateBlock = typeArray.getString(R.styleable.ComponentInput_date_block)
            val allCaps = typeArray.getString(R.styleable.ComponentInput_all_caps)


            val inputType = typeArray.getInt(R.styleable.ComponentInput_android_inputType,
                InputType.TYPE_CLASS_TEXT)


            editTextPassword.inputType = inputType



            if(inputType == InputType.TYPE_TEXT_VARIATION_PASSWORD + InputType.TYPE_CLASS_TEXT) {
                btnEye.visibility = View.VISIBLE
                see = false
            }
            if(allCaps != null) {
                editTextPassword.filters = arrayOf(InputFilter.AllCaps())
            }

            if(title != null) tvTitle.text = title

            if(error != null) txError.text = error

            if(text != null) editTextPassword.setText(text)

            if(textoff != null) editTextPassword.isEnabled = false

            if(hint != null) editTextPassword.hint = hint
            typeArray.recycle()
        }


        btnClear.setOnClickListener { this.onDelete() }

        editTextPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                txError.visibility = GONE
                btnClear.visibility = GONE
            }
        })

        editTextPassword.setOnFocusChangeListener { _, hasFocus ->
            if(hasFocus) {
                ly_input.setBackgroundResource(R.drawable.camera_border_input_focus)
            } else {
                ly_input.setBackgroundResource(R.drawable.camera_border_input_un_focus)
            }
        }
    }


    fun passwordClick(listener: () -> Unit) {
        btnEye.setOnClickListener { listener.invoke() }
    }


    private fun onDelete() {
        txError.visibility = GONE
        btnClear.visibility = GONE
        editTextPassword.setText("")
    }

    fun setError(error: CharSequence?) {
        txError.visibility = VISIBLE
        btnClear.visibility = VISIBLE
        txError.text = error
    }

    fun setText(text: String) {
        editTextPassword.setText(text)
    }

    fun getText(): String {
        return editTextPassword.text.toString()
    }

    fun getAsDouble(): Double {
        val text = editTextPassword.text.toString()
        return if(TextUtils.isEmpty(text)) {
            0.0
        } else {
            text.toDouble()
        }
    }


    /*private fun getPreview(date: Date): String {
        return try {
            format.format(date)
        } catch(ex: Exception) {
            ex.printStackTrace()
            ""
        }
    }*/


    fun addTextChangedListener(listener: TextWatcher?) {
        editTextPassword.addTextChangedListener(listener)
    }

    fun setTextInputType(input: InputTextPass) {
        if(!see) {
            input.editTextPassword.transformationMethod = null
            input.editTextPassword.setSelection(input.editTextPassword.text.length)
            btnEye.setColorFilter(
                ContextCompat.getColor(editTextPassword.context,
                R.color.care_cian))
            see = true
        } else {
            input.editTextPassword.transformationMethod = PasswordTransformationMethod()
            input.editTextPassword.setSelection(input.editTextPassword.text.length)
            btnEye.setColorFilter(
                ContextCompat.getColor(editTextPassword.context,
                R.color.care_basic))
            see = false

        }

    }


}
