package dys.mcdemo.frame.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup


/**
 * Created by dys on 2018/4/6 0006.
 */
class MultiTypeAdapter : RecyclerView.Adapter<MultiTypeAdapter.ItemViewHolder>() {

    private var items = ArrayList<IItem>()

    interface IItem {
        // get the xml layout this type item used in
        val layout: Int

        // get the variable name in the xml
        val variableId: Int
    }

    ///////////////////////////////////////////////////////

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder.create(parent, viewType)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindTo(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].layout
    }

    ///////////////////////////////////////////////////////
    // operate items

    fun getItems(): List<IItem> {
        return items
    }

    fun findPos(item: IItem): Int {
        return items.indexOf(item)
    }

    fun setItem(item: IItem) {
        clearItems()
        addItem(item)
    }

    fun setItems(items: List<IItem>) {
        clearItems()
        addItems(items)
    }

    fun addItem(item: IItem) {
        items.add(item)
    }

    fun addItem(item: IItem, index: Int) {
        items.add(index, item)
    }

    fun addItems(items: List<IItem>) {
        this.items.addAll(items)
    }

    fun removeItem(item: IItem): Int {
        val pos = findPos(item)
        items.remove(item)
        return pos
    }

    fun clearItems() {
        items.clear()
    }

    ///////////////////////////////////////////////////

    class ItemViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindTo(item: MultiTypeAdapter.IItem) {
            binding.setVariable(item.variableId, item)
            binding.executePendingBindings()
        }

        companion object {

            fun create(parent: ViewGroup, viewType: Int): ItemViewHolder {
                val binding = DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(parent.context),
                        viewType, parent, false)
                return ItemViewHolder(binding)
            }
        }
    }
}