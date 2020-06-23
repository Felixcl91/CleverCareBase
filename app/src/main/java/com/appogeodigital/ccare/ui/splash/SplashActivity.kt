package com.appogeodigital.ccare.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.appogeodigital.ccare.R
import com.appogeodigital.ccare.ui.login.LoginActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            val intentLogin = Intent(this, LoginActivity::class.java)
            startActivity(intentLogin)
            finish()
        }, 2000)

    }
}
