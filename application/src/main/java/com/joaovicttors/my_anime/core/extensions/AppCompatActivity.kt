package com.joaovicttors.my_anime.core.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar


fun AppCompatActivity.setupToolbar(
    toolbar: Toolbar?,
    title: String,
    showHome: Boolean
) {
    setSupportActionBar(toolbar)
    supportActionBar?.apply { ->
        toolbar?.title = title
        setSupportActionBar(toolbar)
        setDisplayHomeAsUpEnabled(showHome)
        setDisplayShowHomeEnabled(showHome)
    }
}