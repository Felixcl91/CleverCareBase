package com.appogeodigital.ccare.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.appogeodigital.ccare.R
import com.appogeodigital.ccare.base.BaseActivity
import com.appogeodigital.ccare.ui.family.FamilyFragment
import com.appogeodigital.ccare.ui.fragment.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*val mainFragment: FamilyFragment? = findFragmentById(R.id.fl_wrapper)
        if (mainFragment == null)
            setFragment(FamilyFragment.newInstance(), R.id.fl_wrapper)*/

        //variables menu
        val infoFragment = InfoFragment()
        val emergencyFragment = EmergencyFragment()
        val incidFragment = IncidFragment()
        val perfilFragment = PerfilFragment()
        val familiaFragment = FamilyFragment()

        makeCurrentFragment(infoFragment)

        //navegacion
        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.info -> makeCurrentFragment(infoFragment)
                R.id.emergencias -> makeCurrentFragment(emergencyFragment)
                R.id.incidencias -> makeCurrentFragment(incidFragment)
                R.id.perfil -> makeCurrentFragment(perfilFragment)
                R.id.familia -> makeCurrentFragment(familiaFragment)

            }
            true
        }

    }

    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
        }


}
