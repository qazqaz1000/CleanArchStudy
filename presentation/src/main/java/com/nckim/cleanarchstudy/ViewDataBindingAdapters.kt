package com.nckim.cleanarchstudy

import android.text.Html
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter


@BindingAdapter("htmlText")
fun TextView.setHtmlText(html : String){
    text = Html.fromHtml(html, HtmlCompat.FROM_HTML_MODE_COMPACT)
}