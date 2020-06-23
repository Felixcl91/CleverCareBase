package com.appogeodigital.ccare.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.appogeodigital.ccare.R
import com.appogeodigital.ccare.data.models.local.Familiar
import kotlinx.android.synthetic.main.fragment_familia.*

/**
 * Fragment de la pantalla Familiar
 */
@Suppress("UNREACHABLE_CODE")
class FamiliaFragment : Fragment() {

    companion object{
        val INTENT_PARCELABLE = "OBJECT_INTENT"
    }

    private val familyList = listOf<Familiar>(
        Familiar(
            1,
            R.drawable.nieta,
            "usuario@hotmail.com",
            "wqeweqw",
            "Elena",
            "Cruz Cano",
            674123456,
            94101815,
            "nieta",
            "administrador"
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_familia, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _recycler_family.apply {
            layoutManager = LinearLayoutManager(activity)
            // adapter = FamiliaAdapter(familyList)
        }
    }

}
