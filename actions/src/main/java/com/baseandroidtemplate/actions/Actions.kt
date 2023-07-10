package com.baseandroidtemplate.actions

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent

object Actions {

    fun openHomeIntent(context: Context) =
        internalIntent(context, "com.baseandroidtemplate.home.open")

    @SuppressLint("NewApi")
    private fun internalIntent(context: Context, action: String) =
        Intent(action).setPackage(context.packageName)
}