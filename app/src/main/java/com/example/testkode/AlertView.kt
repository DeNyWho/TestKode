package com.example.testkode

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.example.testkode.enums.AlertStyle

class AlertView(private var title: String, private var style: AlertStyle) {

    private var cancelButton: String = ""
    private var actions: ArrayList<AlertAction> = ArrayList()

    fun addAction(action: AlertAction){
        actions.add(action)
    }

    fun setCancelButtonText(string: String){
        this.cancelButton = string
    }

    fun show(activity: AppCompatActivity){
        when(style){
            AlertStyle.PHONE -> {
                val bottomSheet = DialogFragment(title, actions, cancelButton)
                bottomSheet.show(activity.supportFragmentManager, bottomSheet.tag)
            }
        }
    }
}