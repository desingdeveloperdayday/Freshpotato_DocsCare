package com.ddd.docscare.ui.main.folder

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ddd.docscare.R
import com.ddd.docscare.base.BaseFragment
import com.ddd.docscare.base.BaseRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_folder.*
import kotlinx.android.synthetic.main.fragment_folder_item.view.*

class FolderFragment: BaseFragment() {

    override val layoutId: Int = R.layout.fragment_folder
    private val adapter by lazy { FolderAdapter() }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initLayout()
    }

    private fun initLayout() {
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        recyclerView.addItemDecoration(SpacesItemDecoration(
                resources.getDimensionPixelSize(R.dimen.folder_item_space)))

        btnAddFolder.setOnClickListener {
            adapter.add(FolderItem(title = "TEST"))
            adapter.notifyDataSetChanged()
        }

        adapter.add(FolderItem(title = "커리어"))
        adapter.add(FolderItem(title = "학업"))
        adapter.add(FolderItem(title = "공공문서"))
        adapter.add(FolderItem(title = "금융"))
        adapter.add(FolderItem(title = "여행"))
        adapter.add(FolderItem(title = "부동산"))
        adapter.notifyDataSetChanged()
    }


    class FolderAdapter: BaseRecyclerAdapter<FolderItem, FolderViewHolder>() {

        override fun onBindView(
            holder: FolderViewHolder,
            item: FolderItem,
            position: Int
        ) {
            val holderView = holder.itemView

            holderView.tv_folder_title.text = item.title
        }

        override fun onCreateViewHolder(parent: ViewGroup, type: Int): FolderViewHolder {
            return FolderViewHolder(
                LayoutInflater.from(
                    parent.context
                ).inflate(R.layout.fragment_folder_item, parent, false)
            )
        }


    }

    class FolderViewHolder(view: View): RecyclerView.ViewHolder(view)

    companion object {
        fun newInstance(): FolderFragment {
            return FolderFragment()
        }
    }
}