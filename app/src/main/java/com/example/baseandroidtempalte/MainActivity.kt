package com.example.baseandroidtempalte

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.baseandroidtemplate.actions.Actions

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Actions.openHomeIntent(this))
    }
}