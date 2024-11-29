package com.example.firstkotlin.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firstkotlin.R
import com.example.firstkotlin.data.Book
//для объектов Book

class BookAdapterForDetails(
    private val books: List<Book>, // Список объектов Book
    private val onBookSelected: (Book) -> Unit // Функция обработки нажатия на книгу
) : RecyclerView.Adapter<BookAdapterForDetails.BookViewHolder>() {

    inner class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.book_cover)
        val titleTextView: TextView = itemView.findViewById(R.id.book_title)
        val authorTextView: TextView = itemView.findViewById(R.id.book_author)

        init {
            itemView.setOnClickListener {
                onBookSelected(books[adapterPosition]) // Обработка нажатия на книгу
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_library, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = books[position]
        holder.titleTextView.text = book.title
        holder.authorTextView.text = book.author
        holder.imageView.setImageResource(book.coverImageRes) // Устанавливаем изображение книги
    }

    override fun getItemCount() = books.size
}
