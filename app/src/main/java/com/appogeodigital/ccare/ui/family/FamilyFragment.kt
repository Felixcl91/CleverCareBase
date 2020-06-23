package com.appogeodigital.ccare.ui.family

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.appogeodigital.ccare.R
import com.appogeodigital.ccare.base.BaseMVPFragment
import com.appogeodigital.ccare.data.models.local.Familiar
import javax.inject.Inject

class FamilyFragment : BaseMVPFragment<FamilyContract.Presenter>(), FamilyContract.View {

    private lateinit var inflatedView: View

    @Inject
    lateinit var mainPresenter: FamilyContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        this.inflatedView = inflater.inflate(R.layout.fragment_familia, container, false)
        return inflatedView
    }

     override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()

        val component = activityComponent
        component.inject(this)
        mainPresenter.onAttach(this)

        mainPresenter.loadItems(false)
    }

    private fun setupListeners() {
        // refresh_item_btn.setOnClickListener { FamilyPresenter.loadItems(true) }
    }

    override fun refreshItemList(itemList: List<Familiar>) {
        /*item_recycler_view.toVisible()
        empty_list_text.toGone()

        val linearLayoutManager = LinearLayoutManager(context)
        val itemAdapter = MainItemListAdapter(context, itemList)

        item_recycler_view.layoutManager = linearLayoutManager
        item_recycler_view.adapter = itemAdapter*/
    }

    override fun showEmptyListUI() {
        /*item_recycler_view.toGone()
        empty_list_text.toVisible()*/
    }

    override fun setPresenter(presenter: FamilyContract.Presenter) {
        this.mainPresenter = presenter
    }

    override fun onDestroy() {
        mainPresenter.onDetach()
        super.onDestroy()
    }

    companion object {
        fun newInstance(): FamilyFragment {
            return FamilyFragment()
        }
    }
}