package com.xently.shoppingassistant

import android.os.Bundle
import com.xently.utilities.ui.SearchableActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : SearchableActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onSearchIntentReceived(query: String, searchData: Bundle?) {
        TODO("Not yet implemented")
    }
}