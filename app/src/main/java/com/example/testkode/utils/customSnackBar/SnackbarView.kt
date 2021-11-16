package com.example.testkode.utils

import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.postDelayed
import com.example.testkode.R
import com.google.android.material.snackbar.ContentViewCallback
import kotlinx.android.synthetic.main.snackbar_view.view.*

class SnackBarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), ContentViewCallback {

    private val textError: TextView
    init {
        View.inflate(context, R.layout.snackbar_view, this)
        clipToPadding = false
        this.textError = findViewById(R.id.textError)
    }

    override fun animateContentIn(delay: Int, duration: Int) {
    }

    override fun animateContentOut(delay: Int, duration: Int) {
        val handler = Handler()
        handler.postDelayed(2500){
        }
    }
}