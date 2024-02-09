package com.example.newappdi.utils

import android.content.res.ColorStateList
import android.graphics.BitmapFactory
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.Drawable
import android.text.method.LinkMovementMethod
import android.text.util.Linkify
import android.util.Base64
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import java.util.concurrent.TimeUnit

var imageBaseURL: String? = null;

@BindingAdapter(value = ["imageLocal", "filter"], requireAll = false)
fun loadLocalImage(imageView: ImageView, imageLocal: Int, filter: Int? = null) {
    if (filter != null)
        imageView.setColorFilter(filter)
    imageView.setImageResource(imageLocal)
}

@BindingAdapter("tintColor")
fun filter(imageView: ImageView, filter: Int) {
    imageView.imageTintList = ColorStateList.valueOf(filter)
}

//@BindingAdapter(value = ["imageLocal"], requireAll = false)
//fun loadLocalImage(imageView: ImageView, imageLocal: String?) {
//    if (imageLocal != null) {
//        val resources = imageView.context.resources
//        val resourceId = resources.getIdentifier(
//            imageLocal, "drawable",
//            imageView.context.packageName
//        )
//        imageView.setColorFilter(Color.WHITE)
//        imageView.setImageResource(resourceId)
//    }
//}

@BindingAdapter("imageResource")
fun loadImageResource(imageView: ImageView, @DrawableRes imageLocal: Int) {
//    imageView.setImageDrawable(ContextCompat.getDrawable(imageView.context, imageLocal))
    imageView.setImageResource(imageLocal)
}
@BindingAdapter("android:onSingleClick")
fun View.setOnSingleClickListener(clickListener: View.OnClickListener) {
/*    clicks()
        .throttleFirst(400, TimeUnit.MILLISECONDS)
        .subscribe(
            { clickListener.onClick(this) },
            { error -> error.printStackTrace() }
        )*/

}
/*

@BindingAdapter(value = ["imageUrlWithLoader", "placeHolder"], requireAll = false)
fun loadImageUrlWithLoader(imageView: ImageView, url: String?, placeHolder: Drawable? = null) {
    */
/**
     * This is not working with circular image view.
     *//*


    var newUrl = url
    if (url != null && url.startsWith("/"))
        newUrl = imageBaseURL + url

    val circularProgressDrawable = CircularProgressDrawable(imageView.context)
    circularProgressDrawable.strokeWidth = imageView.context.resources.getDimension(R.dimen._8pxTab)
    circularProgressDrawable.centerRadius =
        imageView.context.resources.getDimension(R.dimen._45pxTab)
    circularProgressDrawable.setColorSchemeColors(
        ContextCompat.getColor(
            imageView.context,
            R.color.blue
        )
    )
    circularProgressDrawable.start()

    Glide.with(imageView)
        .applyDefaultRequestOptions(
            RequestOptions().placeholder(placeHolder).error(placeHolder).override(
                Target.SIZE_ORIGINAL
            )
        )
        .load(newUrl ?: "")
        .placeholder(circularProgressDrawable)
        .into(imageView)
}

@BindingAdapter(value = ["imageUrlWithLoader", "placeHolder"], requireAll = false)
fun loadImageUrlWithLoaderFromAws(
    imageView: ImageView,
    url: String?,
    placeHolder: Drawable? = null
) {
    */
/**
     * This is not working with circular image view.
     *//*


    var newUrl = url
    if (url != null && url.startsWith("/"))
        newUrl = imageBaseURL + url

    val circularProgressDrawable = CircularProgressDrawable(imageView.context)
    circularProgressDrawable.strokeWidth = imageView.context.resources.getDimension(R.dimen._8pxTab)
    circularProgressDrawable.centerRadius =
        imageView.context.resources.getDimension(R.dimen._45pxTab)
    circularProgressDrawable.setColorSchemeColors(
        ContextCompat.getColor(
            imageView.context,
            R.color.blue
        )
    )
    circularProgressDrawable.start()

    Glide.with(imageView)
        .applyDefaultRequestOptions(
            RequestOptions().placeholder(placeHolder).error(placeHolder).override(
                Target.SIZE_ORIGINAL
            )
        )
        .load(newUrl ?: "")
        .placeholder(circularProgressDrawable)
        .into(imageView)
}


@BindingAdapter(value = ["imageUrl", "placeHolder"], requireAll = false)
fun loadImageUrl(imageView: ImageView, url: String?, placeHolder: Drawable? = null) {
    var newUrl = url
    if (url != null && url.startsWith("/"))
        newUrl = imageBaseURL + url

//    val circularProgressDrawable = CircularProgressDrawable(imageView.context)
//    circularProgressDrawable.strokeWidth = 5f
//    circularProgressDrawable.centerRadius = 30f
//    circularProgressDrawable.start()

    Glide.with(imageView)
        .applyDefaultRequestOptions(
            RequestOptions().placeholder(placeHolder).error(placeHolder).override(
                Target.SIZE_ORIGINAL
            )
        )
        .load(newUrl ?: "")
//        .placeholder(circularProgressDrawable)
        .into(imageView)
}

@BindingAdapter(value = ["loadProfile", "placeHolder"], requireAll = false)
fun loadProfile(imageView: ImageView, url: String?, placeHolder: Drawable? = null) {
    var newUrl = url
    if (url != null && url.startsWith("/"))
        newUrl = imageBaseURL + url

    val circularProgressDrawable = CircularProgressDrawable(imageView.context)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.start()

    Glide.with(imageView)
        .applyDefaultRequestOptions(
            RequestOptions().placeholder(placeHolder).error(placeHolder).override(
                350, 350
            )
        )
        .load(newUrl ?: "")
//        .placeholder(circularProgressDrawable)
        .into(imageView)
}

@BindingAdapter(value = ["font"])
fun font(view: TextView, font: String) {
    when (font.toUpperCase()) {
        "BOLD" -> {
            CalligraphyUtils.applyFontToTextView(
                view,
                TypefaceUtils.load(view.context.assets, "fonts/Gilroy-SemiBold.ttf")
            )
        }
        "MEDIUM" -> {
            CalligraphyUtils.applyFontToTextView(
                view,
                TypefaceUtils.load(view.context.assets, "fonts/Gilroy-Medium.ttf")
            )
        }
        "NORMAL" -> {
            CalligraphyUtils.applyFontToTextView(
                view,
                TypefaceUtils.load(view.context.assets, "fonts/Gilroy-Medium.ttf")
            )
        }
        "LIGHT" -> {
            CalligraphyUtils.applyFontToTextView(
                view,
                TypefaceUtils.load(view.context.assets, "fonts/Gilroy-Light.ttf")
            )
        }
        "THIN" -> {
            CalligraphyUtils.applyFontToTextView(
                view,
                TypefaceUtils.load(view.context.assets, "fonts/Gilroy-Light.ttf")
            )
        }
    }
}

//@BindingAdapter("residentStatus")
//fun residentStatus(view: View, rectColored: Int) {
//    val mDrawable = ContextCompat.getDrawable(view.context, R.drawable.bg_rect_status)
//
//    mDrawable?.colorFilter =
//        PorterDuffColorFilter(
//            rectColored,
//            PorterDuff.Mode.SRC_IN
//        )
//
//    view.background = mDrawable
//}

@BindingAdapter("rectColor")
fun rectColor(view: View, rectColored: Int) {
    val mDrawable =
        ContextCompat.getDrawable(view.context, R.drawable.bg_round_corner_fill_style_five_px_white)

    mDrawable?.colorFilter =
        PorterDuffColorFilter(
            rectColored,
            PorterDuff.Mode.SRC_IN
        )

    view.background = mDrawable
}


@BindingAdapter("dotColor")
fun dotColor(view: View, color: Int) {
    val mDrawable = ContextCompat.getDrawable(view.context, R.drawable.bg_rect_status)
    mDrawable?.colorFilter =
        PorterDuffColorFilter(
            color,
            PorterDuff.Mode.SRC_IN
        )

    view.background = mDrawable
}

@BindingAdapter("rectColored")
fun rectColored(view: View, rectColored: String) {
    val mDrawable = ContextCompat.getDrawable(
        view.context,
        R.drawable.bg_round_corner_fill_style_five_px_white
    )
    when (rectColored.toUpperCase()) {
        "RED" -> {
            mDrawable?.colorFilter =
                PorterDuffColorFilter(
                    ContextCompat.getColor(view.context, R.color.action1),
                    PorterDuff.Mode.SRC_IN
                )
        }
        "ORANGE" -> {
            mDrawable?.colorFilter =
                PorterDuffColorFilter(
                    ContextCompat.getColor(view.context, R.color.action2),
                    PorterDuff.Mode.SRC_IN
                )
        }
        "PURPLE" -> {
            mDrawable?.colorFilter =
                PorterDuffColorFilter(
                    ContextCompat.getColor(view.context, R.color.action3),
                    PorterDuff.Mode.SRC_IN
                )
        }
        "GREEN" -> {
            mDrawable?.colorFilter =
                PorterDuffColorFilter(
                    ContextCompat.getColor(view.context, R.color.action4),
                    PorterDuff.Mode.SRC_IN
                )
        }
        "MINT" -> {
            mDrawable?.colorFilter =
                PorterDuffColorFilter(
                    ContextCompat.getColor(view.context, R.color.action5),
                    PorterDuff.Mode.SRC_IN
                )
        }
        "LIGHT_BLUE" -> {
            mDrawable?.colorFilter =
                PorterDuffColorFilter(
                    ContextCompat.getColor(view.context, R.color.action6),
                    PorterDuff.Mode.SRC_IN
                )
        }
        "GRAY" -> {
            mDrawable?.colorFilter =
                PorterDuffColorFilter(
                    ContextCompat.getColor(view.context, R.color.gray),
                    PorterDuff.Mode.SRC_IN
                )
        }
        "BLACK" -> {
            mDrawable?.colorFilter =
                PorterDuffColorFilter(
                    ContextCompat.getColor(view.context, R.color.black),
                    PorterDuff.Mode.SRC_IN
                )
        }
        "BLUE" -> {
            mDrawable?.colorFilter =
                PorterDuffColorFilter(
                    ContextCompat.getColor(view.context, R.color.blue),
                    PorterDuff.Mode.SRC_IN
                )
        }
    }

    view.background = mDrawable
}


@BindingAdapter(value = ["imageBase64"])
fun loadBase64(imageView: ImageView, imageBase64: String?) {
    if (imageBase64 != null) {
        try {

            val decodedString = Base64.decode(imageBase64, Base64.DEFAULT)
            val decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
            imageView.setImageBitmap(decodedByte)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}


@BindingAdapter(value = ["htmlText"])
fun setHtmlText(textView: TextView, text: String?) {
    if (text != null)
        textView.text = HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_LEGACY)
    textView.movementMethod = LinkMovementMethod.getInstance()
    Linkify.addLinks(textView, Linkify.ALL)
}

@BindingAdapter(value = ["viewVisibility", "isInvisible"], requireAll = false)
fun setVisibility(view: View, isVisible: Boolean, isInvisible: Boolean) {
    view.visibility =
        if (isVisible) View.VISIBLE else if (isInvisible) View.INVISIBLE else View.GONE
}

//hide view when text is empty
@BindingAdapter("hideWhenEmpty")
fun hideEmpty(view: View, text: String?) {
    view.visibility = if (text != null && text.trim().length > 0) View.VISIBLE else View.GONE
}


@BindingAdapter("naText")
fun hideEmpty(view: TextView, isDisable: Boolean) {
    view.setTextColor(
        ContextCompat.getColor(
            view.context,
            if (isDisable) R.color.colorPrimary else R.color.inputTxtColor
        )
    )
}


@BindingAdapter("android:layout_alignParentEnd")
fun setAlignParentEnd(view: View, alignParentEnd: Boolean) {
    val layoutParams = view.layoutParams as RelativeLayout.LayoutParams

    if (alignParentEnd) {
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_END)
    }

    view.layoutParams = layoutParams
}

@BindingAdapter(value = ["makeUnderline"], requireAll = false)
fun setUnderline(view: TextView, bool: Boolean) {
    if (bool) {
        view.paintFlags = view.paintFlags or Paint.UNDERLINE_TEXT_FLAG
    } else {
        view.paintFlags = view.paintFlags and (Paint.UNDERLINE_TEXT_FLAG.inv())
    }
//    if (bool) {
//        view.text =
//            SpannableString(view.text).setSpan(UnderlineSpan(), 0, view.text.length, 0).toString()
//    } else {
//        view.text = view.text
//    }
}


@BindingAdapter(value = ["strikeThrough"], requireAll = false)
fun setStrikeThrough(textView: TextView, strikeThrough: Boolean?) {
    if (strikeThrough == true) {
        textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    } else {
        textView.paintFlags = textView.paintFlags and (Paint.STRIKE_THRU_TEXT_FLAG.inv())
    }
}



//show "-" when text is empty if replacementText is set then show replacement text
@BindingAdapter(value = ["replaceWhenEmpty", "replacementText"], requireAll = false)
fun dashEmpty(view: TextView, text: String?, replacementText: String? = " - ") {
    var finalReplacement = replacementText
    if (text != null && text.trim().isNotEmpty()) view.text = text else {
        if (finalReplacement == null) finalReplacement = " - "
        view.text = finalReplacement
    }
}

@BindingAdapter(value = ["fontStyle"])
fun fontStyle(view: TextView, font: String) {
    CalligraphyUtils.applyFontToTextView(
        view,
        TypefaceUtils.load(view.context.assets, font)
    )
}*/
