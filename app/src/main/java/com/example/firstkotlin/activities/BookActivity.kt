package com.example.firstkotlin.activities

import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.firstkotlin.R
import com.example.firstkotlin.data.BookRepository
import com.example.firstkotlin.utilities.BookReaderUtils
//функции с главного экрана, для работы книг, починить листалку книги prev and next button
class BookActivity : AppCompatActivity() {

    private lateinit var bookRepository: BookRepository
    private lateinit var progressBar: ProgressBar
    private lateinit var bookId: String
    private var currentPage: Int = 0
    private lateinit var pages: List<String>  // Список страниц книги

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_scrollable_content)

        // Инициализация BookRepository
        bookRepository = BookRepository(this)
        progressBar = findViewById(R.id.progress_bar)

        // Получаем ID книги из Intent
        bookId = intent.getStringExtra("BOOK_ID") ?: ""

        // Получаем книгу из репозитория
        val book = bookRepository.getBook(bookId)

        book?.let {
            // Разбиваем текст книги на страницы
            pages = BookReaderUtils.splitTextIntoPages(it.text)

            currentPage = it.currentPage // имя по умолчанию для параметра, если его не назвать явно. То есть it ссылается на объект, который был передан в блок let

            // Устанавливаем прогресс книги в ProgressBar
            progressBar.max = 100
            progressBar.progress = it.progress

            // Отображаем первую страницу книги
            val textView = findViewById<TextView>(R.id.book_text_view)
            BookReaderUtils.displayPage(textView, pages, currentPage)

            // Обработчик для кнопки "Previous"
            findViewById<Button>(R.id.prev_button)?.setOnClickListener {
                onPrevPage()
            }

            // Обработчик для кнопки "Next"
            findViewById<Button>(R.id.next_button)?.setOnClickListener {
                onNextPage()
            }
        }
    }

    private fun onNextPage() {
        val book = bookRepository.getBook(bookId)

        book?.let {
            if (currentPage < pages.size - 1) { //Здесь проверяется, что текущая страница меньше последней страницы (pages.size - 1). Важно заметить, что индексация массива/списка начинается с 0, то есть последняя страница будет иметь индекс pages.size - 1.Это условие говорит, что можно переходить к следующей странице, если текущая страница еще не является последней.

                currentPage++
                // Обновляем прогресс
                val progress = BookReaderUtils.calculateProgress(currentPage, pages.size)
                progressBar.progress = progress

                // Отображаем следующую страницу
                val textView = findViewById<TextView>(R.id.book_text_view)
                BookReaderUtils.displayPage(textView, pages, currentPage)

                // Обновляем прогресс книги в репозитории
                bookRepository.updateBookProgress(bookId, currentPage)
            }
        }
    }

    private fun onPrevPage() {
        val book = bookRepository.getBook(bookId)

        book?.let {
            if (currentPage > 0) {
                currentPage--
                // Обновляем прогресс
                val progress = BookReaderUtils.calculateProgress(currentPage, pages.size)
                progressBar.progress = progress

                // Отображаем предыдущую страницу
                val textView = findViewById<TextView>(R.id.book_text_view)
                BookReaderUtils.displayPage(textView, pages, currentPage)

                // Обновляем прогресс книги в репозитории
                bookRepository.updateBookProgress(bookId, currentPage)
            }
        }
    }
}
