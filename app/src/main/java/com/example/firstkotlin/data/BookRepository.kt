package com.example.firstkotlin.data

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

// Репозиторий для работы с книгами и жанрами
class BookRepository(private val context: Context) {

    // SharedPreferences для сохранения данных
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("BookProgressPrefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    // Экземплярные методы

    // Сохраняем книгу в SharedPreferences
    fun saveBook(book: Book) {
        val editor = sharedPreferences.edit()
        val bookJson = gson.toJson(book)
        editor.putString(book.id, bookJson)
        editor.apply()
    }

    // Получаем книгу по ID
    fun getBook(bookId: String): Book? {
        val bookJson = sharedPreferences.getString(bookId, null)
        return if (bookJson != null) {
            gson.fromJson(bookJson, Book::class.java)
        } else {
            null
        }
    }

    // Обновление текущей страницы книги и прогресса
    fun updateBookProgress(bookId: String, newCurrentPage: Int) {
        val book = getBook(bookId)
        if (book != null) {
            val updatedBook = book.copy(
                currentPage = newCurrentPage,
                progress = book.calculateProgress()
            )
            saveBook(updatedBook)
        }
    }

    // Получаем список жанров (из SharedPreferences)
    fun getGenres(): Set<String> {
        return sharedPreferences.getStringSet("genres", mutableSetOf()) ?: mutableSetOf()
    }

    // Добавляем жанр в список жанров
    fun addGenre(genre: String) {
        val genres = getGenres().toMutableSet()
        genres.add(genre)
        val editor = sharedPreferences.edit()
        editor.putStringSet("genres", genres)
        editor.apply()
    }

    // Инициализация жанров (с добавлением стандартных значений, если еще не существует)
    fun initializeGenres() {
        val defaultGenres = setOf("Fiction", "Non-Fiction", "Science", "Fantasy", "Biography")
        val genres = getGenres().toMutableSet()

        if (genres.isEmpty()) {
            genres.addAll(defaultGenres)
            val editor = sharedPreferences.edit()
            editor.putStringSet("genres", genres)
            editor.apply()
        }
    }

    // Проверка, существует ли жанр
    fun isGenreExist(genre: String): Boolean {
        return getGenres().contains(genre)
    }

    // Статические методы для работы с жанрами

    companion object {
        private var staticGenres: Set<String> = setOf("Fiction", "Non-Fiction", "Science", "Fantasy", "Biography")

        // Получаем глобальные жанры
        fun getStaticGenres(): Set<String> {
            return staticGenres
        }

        // Добавляем новый жанр в глобальный список
        fun addStaticGenre(genre: String) {
            staticGenres = staticGenres + genre
        }
    }
}
