package dys.mcdemo.frame.base

import android.view.View
import com.android.databinding.library.baseAdapters.BR

/**
 * Created by dys on 2018/4/6 0006.
 */
abstract class BaseItem : MultiTypeAdapter.IItem {

    override val variableId: Int = BR.item

    private var onClickListener: View.OnClickListener? = null

    fun getOnClickListener(): View.OnClickListener {
        return onClickListener!!
    }

    fun setOnClickListener(onClickListener: View.OnClickListener) {
        this.onClickListener = onClickListener
    }
}