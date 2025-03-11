package com.ahmedkenawy.moviesapp.features.main

import android.os.Bundle
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.ahmedkenawy.moviesapp.R
import com.ahmedkenawy.moviesapp.core.base.BaseActivity
import com.ahmedkenawy.moviesapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity<Any>() {
    /**
     * Tag used for logging and debugging purposes.
     */
    override val mTag = "MainActivity"
    /**
     * View binding instance for accessing views in the layout.
     */
    override val mBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreateActivity(savedInstanceState: Bundle?) {
        setUpViews()
    }

    override fun setUpViews() {
        installSplashScreen()
        window.statusBarColor = ContextCompat.getColor(this, android.R.color.black)
    }


}