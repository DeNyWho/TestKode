package com.example.testkode

import com.example.testkode.enums.AlertActionStyle
import com.example.testkode.interfaces.AlertActionListener

class AlertAction {
    var title: String
    var style: AlertActionStyle
    var action: ((AlertAction) -> Unit?)
    var actionListener: AlertActionListener?

    constructor(title: String, style: AlertActionStyle, action: (AlertAction) -> Unit) {
        this.title = title
        this.style = style
        this.action = action
        this.actionListener = null
    }
}