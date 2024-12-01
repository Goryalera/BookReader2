package com.example.firstkotlin.activities

import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.firstkotlin.R
import com.example.firstkotlin.data.Book
import com.example.firstkotlin.data.BookRepository
import java.io.InputStream
import java.nio.charset.Charset

class AddBookActivity : AppCompatActivity() {

    private lateinit var bookTitle: EditText
    private lateinit var bookAuthor: EditText
    private lateinit var bookGenre: EditText
    private lateinit var selectFileButton: Button
    private lateinit var saveBookButton: Button
    private lateinit var backButton: ImageButton

    private var selectedBookUri: Uri? = null
    private lateinit var bookRepository: BookRepository // Экземпляр репозитория

    // Новый способ обработки результата выбора файла
    private val pickBookLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            selectedBookUri = uri
            uri?.let {
                Toast.makeText(this, "File Selected", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_book)

        // Создаем экземпляр BookRepository с контекстом активности
        bookRepository = BookRepository(applicationContext)

        bookTitle = findViewById(R.id.bookTitle)
        bookAuthor = findViewById(R.id.bookAuthor)
        bookGenre = findViewById(R.id.bookGenre)
        selectFileButton = findViewById(R.id.selectFileButton)
        saveBookButton = findViewById(R.id.saveBookButton)
        backButton = findViewById(R.id.back_btn)

        // Инициализируем жанры, если они еще не были добавлены
        bookRepository.initializeGenres()

        backButton.setOnClickListener {
            onBackPressed()
        }

        // Открытие диалога выбора файла
        selectFileButton.setOnClickListener {
            pickBookLauncher.launch("text/plain")
        }

        // Сохранение книги
        saveBookButton.setOnClickListener {
            val title = bookTitle.text.toString()
            val author = bookAuthor.text.toString()
            val genre = bookGenre.text.toString()

            if (selectedBookUri != null && title.isNotEmpty() && author.isNotEmpty() && genre.isNotEmpty()) {
                // Проверяем, существует ли жанр (статический метод)
                val genres = BookRepository.Companion.getStaticGenres() // Здесь вызываем статический метод
                if (genre in genres) {
                    // Если жанр существует, сохраняем книгу
                    readBookFile(selectedBookUri!!)
                    saveBook(title, author, genre)
                } else {
                    // Если жанр не существует, показываем ошибку
                    Toast.makeText(this, "Error: Genre not found in database.", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please fill all fields and select a file.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun readBookFile(uri: Uri) {
        try {
            val inputStream: InputStream? = contentResolver.openInputStream(uri)
            val bookContent = inputStream?.bufferedReader(Charset.forName("UTF-8"))?.use { it.readText() }
            Toast.makeText(this, "Book read successfully!", Toast.LENGTH_SHORT).show()

            // Логика сохранения содержимого или метаданных книги
        } catch (e: Exception) {
            Toast.makeText(this, "Error reading file: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun saveBook(title: String, author: String, genre: String) {
        // Предположим, что вы получаете текст книги и описание
        val bookDescription = "Sample description"
        val bookText = "Sample text content"
        val totalPages = 100 // Вы можете изменить это значение, в зависимости от длины книги

        val book = Book(
            id = title + author,
            title = title,
            author = author,
            genre = genre,
            description = bookDescription,
            text = bookText,
            totalPages = totalPages,
            currentPage = 0,
            progress = 0,
            coverImageRes = R.drawable.default_book_cover // Добавляем значение по умолчанию для coverImageRes
        )
        // Сохраняем книгу через экземпляр репозитория
        bookRepository.saveBook(book)
        Toast.makeText(this, "Book saved!", Toast.LENGTH_SHORT).show()
    }
}
