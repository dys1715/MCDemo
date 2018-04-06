package dys.mcdemo.frame

import android.databinding.BindingAdapter
import android.graphics.drawable.Drawable
import android.widget.ImageView

import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

object ImageViewExt {
    @BindingAdapter(
            value = *arrayOf("android:src", "android:placeholder"),
            requireAll = false)
    @JvmStatic
    fun setImageUrl(view: ImageView, url: String, placeholder: Drawable?) {
        var requestBuilder = Glide.with(view.context).load(url)
        if (placeholder != null) {
            requestBuilder = requestBuilder
                    .transition(DrawableTransitionOptions().crossFade(500))
                    .apply(RequestOptions().placeholder(placeholder))
                    .apply(RequestOptions().error(placeholder))
        }
        requestBuilder.into(view)
    }
}
