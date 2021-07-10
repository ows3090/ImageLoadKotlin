package ows.kotlinstudy.imageloadkotlin

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import ows.kotlinstudy.imageloadkotlin.adapter.ImageAdapter
import ows.kotlinstudy.imageloadkotlin.databinding.ActivityMainBinding
import ows.kotlinstudy.imageloadkotlin.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private var binding: ActivityMainBinding? = null
    private var imageAdapter: ImageAdapter? = null
    private var pageCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        binding = activityMainBinding
        setContentView(activityMainBinding.root)

        initPageTextView(activityMainBinding)
        initRecylcerView(activityMainBinding)
        initMainViewModel()
        initPageChangeButton()
    }

    fun initPageTextView(activityMainBinding: ActivityMainBinding){
        activityMainBinding.pageTextView.text = "${pageCount} 페이지"
   }

    fun initRecylcerView(activityMainBinding: ActivityMainBinding){
        imageAdapter = ImageAdapter {
            val url = Uri.parse(it.link)
            var intent = Intent(ACTION_VIEW, url)
            //val intent = Intent(this, WebLoadActivity::class.java)
            startActivity(intent)
        }

        activityMainBinding.ImageRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = imageAdapter
        }
    }

    fun initMainViewModel(){
        mainViewModel.apply {
            isLoadingState.observe(this@MainActivity){
                binding?.let { binding ->
                    binding.progressBar.isVisible = it
                }
            }
            mutableImageModels.observe(this@MainActivity){ imagedto ->
                binding?.let{ binding ->
                    binding.pageTextView.text = "${pageCount} 페이지"
                    binding.pageChangeButton.isEnabled = imagedto.nextPage

                    imageAdapter?.submitList(imagedto.images)
                }
            }
        }
        mainViewModel.fecthServer()
    }

    fun initPageChangeButton(){
        binding?.let{ binding ->
            binding.pageChangeButton.setOnClickListener {
                // TODO 다음페이지 Load
                pageCount++
            }
        }
    }
}