package ows.kotlinstudy.imageloadkotlin.adapter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.LruCache
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ows.kotlinstudy.imageloadkotlin.data.Images
import ows.kotlinstudy.imageloadkotlin.databinding.ItemImageBinding
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URI

class ImageAdapter(private val callback: (Images) -> Unit): ListAdapter<Images, ImageAdapter.ViewHolder>(
    diffUtil
) {
    private var memoryCache: LruCache<String, Bitmap>

    init {
        val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt()
        val cacheSize = maxMemory / 8

        memoryCache = object : LruCache<String, Bitmap>(cacheSize){
            override fun sizeOf(key: String, value: Bitmap): Int {
                return value.byteCount / 1024
            }
        }
    }

    inner class ViewHolder(var binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(images: Images){
            binding.titleTextView.text = images.title

            if(binding.imageView.height > images.sizeHeight || binding.imageView.width > images.sizeWidth){
                binding.imageView.scaleType = ImageView.ScaleType.CENTER_CROP
            }else{
                binding.imageView.scaleType = ImageView.ScaleType.FIT_XY
            }

            Glide.with(binding.imageView.context)
                .load(images.thumbnail)
                .into(binding.imageView)

            /**
             * Bitmap Cache 처리까지 하였으나, URL로 link하는 방법을 몰라서 임시방편으로 Glide 외부라이브러리를 사용했습니다.
             * Cache에 존재하면 Cache의 Bitmap으로 Image loading, 존재하지 않으면 Images의 link를 통해 bitmap 생성
             */
//            var bitmap: Bitmap? = memoryCache.get(images.thumbnail)?.also {
//                binding.imageView.setImageBitmap(it)
//            }
//
//            if(bitmap == null){
//                bitmap = BitmapFactory.decodeFile(images.thumbnail)
//                binding.imageView.setImageBitmap(bitmap)
//
//                binding.imageView.post(
//                    Runnable {
//                        memoryCache.put(images.thumbnail, bitmap)
//                    }
//                )
//            }

            binding.root.setOnClickListener {
                callback(images)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemImageBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object{
        object diffUtil : DiffUtil.ItemCallback<Images>(){
            override fun areItemsTheSame(oldItem: Images, newItem: Images): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Images, newItem: Images): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

}