package com.joaovicttors.my_anime.core.adapters

import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.joaovicttors.my_anime.R
import com.squareup.picasso.Picasso

@BindingAdapter("bind:imageUrl")
fun loadImage(imageView: ImageView, imageUrl: String?) {
    Picasso.get()
        .load(imageUrl)
        .into(imageView)
}

@BindingAdapter("bind:favoriteIcon")
fun favoriteIcon(imageButton: ImageButton, isFavorite: Boolean) {
    imageButton.setImageResource(if (isFavorite) R.drawable.outline_favorite_24 else R.drawable.outline_favorite_border_24 )
}


@BindingAdapter("bind:floatText")
fun floatText(textView: TextView, text: Float) {
    textView.text = text.toString()
}

@BindingAdapter("bind:text2")
fun text2(textView: TextView, text: String) {
    textView.text = text.replace("\\<.*?\\>", "")
}
