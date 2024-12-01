package com.example.firstkotlin.activities

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.firstkotlin.R
import com.example.firstkotlin.data.BookRepository
import com.example.firstkotlin.utilities.BookReaderUtils
//класс для просмотра книги с детальной информацией о ней
class BookDetailActivity : AppCompatActivity() {

    private lateinit var bookRepository: BookRepository
    private lateinit var bookId: String
    private var currentPage: Int = 0
    private lateinit var pages: List<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        bookRepository = BookRepository(this)
        val textView = findViewById<TextView>(R.id.book_text_view)

        bookId = intent.getStringExtra("BOOK_ID") ?: ""
        val book = bookRepository.getBook(bookId)

        book?.let {
            pages = BookReaderUtils.splitTextIntoPages(it.text)

            currentPage = it.currentPage
            BookReaderUtils.displayPage(textView, pages, currentPage)
        }
    }
}
