package com.example.assignment3.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.assignment3.Assignment3Application
import com.example.assignment3.R
import com.example.assignment3.viewmodel.SearchViewModel
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as Assignment3Application).appComponent.inject(this)
    }
}
