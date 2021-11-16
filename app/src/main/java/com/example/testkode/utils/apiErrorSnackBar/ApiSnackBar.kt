package com.example.testkode.utils.apiErrorSnackBar


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.testkode.R
import com.example.testkode.utils.findSuitableParent
import com.google.android.material.snackbar.BaseTransientBottomBar

class ApiSnackBar(
    parent: ViewGroup,
    content: ApiBarView
):BaseTransientBottomBar<ApiSnackBar>(parent,content,content) {

    init {
        getView().setBackgroundColor(ContextCompat.getColor(view.context, android.R.color.transparent))
        getView().setPadding(0, 0, 0, 0)
    }

    companion object {

        fun make(view: View): ApiSnackBar {

            val parent = view.findSuitableParent() ?: throw IllegalArgumentException(
                "No suitable parent found from the given view. Please provide a valid view."
            )

            val customView = LayoutInflater.from(view.context).inflate(
                R.layout.api_layout,
                parent,
                false
            ) as ApiBarView

            return ApiSnackBar(
                parent,
                customView
            )
        }

    }

}