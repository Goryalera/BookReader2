package com.example.firstkotlin.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firstkotlin.R
import com.example.firstkotlin.adapters.BookAdapter
import com.example.firstkotlin.adapters.BookAdapterForDetails
import com.example.firstkotlin.data.Book

class LibraryActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var bookImageAdapter: BookAdapter
    private lateinit var bookAdapterForDetails: BookAdapterForDetails

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.library_activity)

        // Инициализация RecyclerView
        recyclerView = findViewById(R.id.recycler_view_genre1_books)
        recyclerView.layoutManager = LinearLayoutManager(this)


        val books = listOf(
            Book(
                id = "1",
                title = "Отсрочка",
                author = "Жан Поль Сартр",
                genre = "Роман",
                description = "Европа замерла перед лицом неминуемой войны. С ужасом осознает Матье, университетский преподаватель философии, что его личные проблемы, радости и горести уже ровно ничего не значат - как песчинки в вихре гигантской бури, которая вот-вот сметет с лица земли весь привычный ему мир. Все стало неважным - любовь, дружба, размышления. И постепенно Матье, ранее не принимавший саму идею конечности человеческой жизни, начал ненавидеть саму жизнь - мучительную \"отсрочку\" смертного приговора.",
                text = "Some text here...",
                totalPages = 180,
                currentPage = 50,
                progress = 28,
                coverImageRes = R.drawable.book1
            ),
            Book(
                id = "2",
                title = "Тошнота",
                author = "Жан Поль Сартр",
                genre = "Роман",
                description = "Тошнота – это суть бытия людей, застрявших \"в сутолоке дня\". Людей – брошенных на милость чуждой, безжалостной, безотрадной реальности.\nТошнота – это невозможность любви и доверия, это – попросту – неумение мужчины и женщины понять друг друга.\nТошнота – это та самая \"другая сторона отчаяния\", по которую лежит Свобода.\nНо – что делать с этой проклятой свободой человеку, осатаневшему от одиночества?..",
                text = "Some text here...",
                totalPages = 328,
                currentPage = 100,
                progress = 30,
                coverImageRes = R.drawable.book2
            )
        )

        // Создаем и устанавливаем адаптер для изображений
        val bookImages = listOf(
            R.drawable.book1,
            R.drawable.book2
        ) // Список изображений для адаптера изображений
        bookImageAdapter = BookAdapter(bookImages) { imageResId ->
            // Обработка клика по изображению
            Toast.makeText(this, "Image selected: $imageResId", Toast.LENGTH_SHORT).show()
        }

        // Создаем и устанавливаем адаптер для объектов Book
        bookAdapterForDetails = BookAdapterForDetails(books) { book ->
            // Обработка клика по книге
            Toast.makeText(this, "Selected: ${book.title}", Toast.LENGTH_SHORT).show()
        }

        recyclerView.adapter = bookAdapterForDetails
    }
}
