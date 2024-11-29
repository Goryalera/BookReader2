package com.example.firstkotlin.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.firstkotlin.R
//для изображений книг
class BookAdapter(
    private val bookImages: List<Int>,
    private val onBookSelected: (Int) -> Unit // Функция для обработки нажатия на книжку
) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    inner class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.book_image_view)

        init {
            imageView.setOnClickListener {
                onBookSelected(bookImages[adapterPosition]) // Обработка нажатия
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.imageView.setImageResource(bookImages[position]) // Установите изображение книги

    }

    override fun getItemCount() = bookImages.size
}