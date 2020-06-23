package com.appogeodigital.ccare.wigets

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.DatePicker
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.appogeodigital.ccare.R
import com.appogeodigital.ccare.utils.format
import java.util.*

class InputDate(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs),
    DatePickerDialog.OnDateSetListener {

    private var tvTitle: TextView
    private val tvError: TextView
    private val tvEdit: EditText
    private var dayOfMonth: Int = 0
    private var month: Int = 0
    private var year: Int = 0
    private var listener: ((Calendar?) -> Unit)? = null

    init {
        View.inflate(this.context, R.layout.layout_input_date, this)
        tvTitle = findViewById(R.id.tv_title)
        tvEdit = findViewById(R.id.et_component)
        tvError = findViewById(R.id.tx_error)

        attrs?.let {
            val typeArray = context.obtainStyledAttributes(it, R.styleable.InputDate, 0, 0)
            val title = typeArray.getString(R.styleable.InputDate_date_title)
            tvTitle.text = title
            tvEdit.hint = title
            typeArray.recycle()
        }

        tvEdit.isFocusable = false
        tvEdit.setOnClickListener { this.openDate() }
        this.setOnClickListener { this.openDate() }
    }

    private fun openDate() {
        if(dayOfMonth == 0 || month == 0 || year == 0) {
            val calendar = Calendar.getInstance()
            dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
            month = calendar.get(Calendar.MONTH)
            year = calendar.get(Calendar.YEAR)

        }
        try {
            val inputMethodManager =
                context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager

            inputMethodManager.hideSoftInputFromWindow(this.windowToken, 0)
        } catch(ex: Exception) {
            ex.printStackTrace()
        }

        val diaglog = DatePickerDialog(this.context, this, year, month, dayOfMonth)
        diaglog.show()
    }

    private fun getPreview(date: Date): String {
        return try {
            format.format(date)
        } catch(ex: Exception) {
            ex.printStackTrace()
            ""
        }
    }

    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
        this.year = p1
        this.month = p2
        this.dayOfMonth = p3

        val calendar = Calendar.getInstance()

        calendar.run {
            set(Calendar.YEAR, p1)
            set(Calendar.MONTH, p2)
            set(Calendar.DAY_OF_MONTH, p3)
            tvEdit.setText(getPreview(time))
        }
        listener?.invoke(calendar)
    }



}