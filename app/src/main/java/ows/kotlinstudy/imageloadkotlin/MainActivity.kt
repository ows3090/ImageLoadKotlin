package ows.kotlinstudy.imageloadkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ows.kotlinstudy.imageloadkotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        binding = activityMainBinding
        setContentView(activityMainBinding.root)
    }
}