package com.appogeodigital.ccare.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.appogeodigital.ccare.R
import com.appogeodigital.ccare.data.models.local.Familiar
import com.appogeodigital.ccare.utils.ext.loadImageFromLink


class FamilyItemListAdapter(
    private val context: Context?,
    private val items: List<Familiar>) :
    RecyclerView.Adapter<FamilyItemListAdapter.FamilyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FamilyViewHolder {
        return FamilyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_family, parent, false))
    }

    override fun onBindViewHolder(holder: FamilyViewHolder, position: Int) {
        val item = items[position]
        holder.name.text = item.name
        holder.rol.text = item.rol
        // holder.image.loadImageFromLink(item.imageLink)
    }

    override fun getItemCount(): Int = items.size

    /**
     * View Holder for recycler view.
     */
    inner class FamilyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // var image: ImageView = itemView.findViewById(R.id.item_image)
        var name: TextView = itemView.findViewById(R.id._name)
        var rol: TextView = itemView.findViewById(R.id._rol)

    }

}