package com.example.testkode

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.example.testkode.enums.AlertActionStyle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.action_layout_light.view.*
import kotlinx.android.synthetic.main.alert_layout_light.view.*
import java.util.ArrayList


class DialogFragment(
    private val title: String,
    private val actions: ArrayList<AlertAction>,
    private val cancelButtonText: String) : BottomSheetDialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.alert_layout_light, container, false)

        view?.tvCancel?.text = cancelButtonText
        view?.tvTitle?.text = title
        view?.tvCancel?.visibility = View.VISIBLE

        view?.tvCancel?.setOnClickListener { dismiss() }
        view?.tvTitle?.setOnClickListener {

            val array = CharArray(title.length)
            for ( i in title.indices) array[i] = title[i]

            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:" + "${array[0]}${array[1]}${array[4]}${array[5]}${array[6]}${array[9]}${array[10]}${array[11]}${array[13]}${array[14]}${array[16]}")
            startActivity(dialIntent)
        }


        if(title.isEmpty()) view?.tvTitle?.visibility = View.GONE
        if(cancelButtonText.isEmpty()) view?.tvCancel?.text = "Отмена"

        inflateActionsView(view?.actionsLayout, actions)

        return view
    }

    private fun inflateActionsView(actionsLayout: LinearLayout?, actions: ArrayList<AlertAction>){

        for (action in actions){

            val view = LayoutInflater.from(context).inflate(R.layout.action_layout_light, null)

            view?.tvAction?.text = action.title

            view?.tvAction?.setOnClickListener{
                dismiss()

                action.action?.invoke(action)
            }

            if (context != null){
                when(action.style){
                    AlertActionStyle.DEFAULT -> view?.tvAction?.setTextColor(ContextCompat.getColor(requireContext(), R.color.optionsColor))
                }
            }

            actionsLayout?.addView(view)
        }
    }

}