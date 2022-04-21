package com.joaovicttors.my_anime.core.bases

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.joaovicttors.my_anime.BR
import com.joaovicttors.my_anime.core.RecyclerViewInterface

abstract class BaseAdapter<T>(
    private val layoutId: Int,
    private val recyclerViewInterface: RecyclerViewInterface
) : RecyclerView.Adapter<BaseAdapter<T>.BaseViewHolder>(){

    private val itemCollection: MutableList<T> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(items: List<T>) {
        itemCollection.addAll(items)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clearItems() {
        itemCollection.clear()
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun removeItem(item: T) {
        itemCollection.remove(item)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        return holder.bind(itemCollection[position], recyclerViewInterface)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), layoutId, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return itemCollection.size
    }

    inner class BaseViewHolder(
        private val dataBinding: ViewDataBinding
    ) : RecyclerView.ViewHolder(dataBinding.root) {

        fun <T> bind(data: T?, contract: RecyclerViewInterface) {
            dataBinding.setVariable(BR.data, data)
            dataBinding.setVariable(BR.contract, contract)
            dataBinding.executePendingBindings()
        }
    }
}