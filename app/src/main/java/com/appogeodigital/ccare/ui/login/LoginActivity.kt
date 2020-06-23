package com.appogeodigital.ccare.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.appogeodigital.ccare.R
import com.appogeodigital.ccare.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_login.setOnClickListener {
            val intentMain = Intent(this, MainActivity::class.java)
            startActivity(intentMain)
        }
    }

}
