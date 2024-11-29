package com.example.firstkotlin.utilities

import android.content.Context
import android.content.Intent
import com.example.firstkotlin.activities.BookDetailActivity
import com.example.firstkotlin.activities.LibraryActivity
import com.example.firstkotlin.activities.MainActivity
import com.example.firstkotlin.activities.ProfileActivity

object IntentUtils {

    // Метод для запуска BookDetailActivity с передачей ID книги
    fun startBookDetailActivity(context: Context, bookId: String) {
        val intent = Intent(context, BookDetailActivity::class.java).apply {
            putExtra("BOOK_ID", bookId) // Передаем ID книги
        }
        context.startActivity(intent)
    }

    // Метод для запуска LibraryActivity
    fun startLibraryActivity(context: Context) {
        val intent = Intent(context, LibraryActivity::class.java)
        context.startActivity(intent)
    }

    // Метод для запуска MainActivity с передачей изображения книги
    fun startMainActivity(context: Context, bookImage: Int) {
        val intent = Intent(context, MainActivity::class.java).apply {
            putExtra("BOOK_IMAGE", bookImage) // Передаем идентификатор изображения книги
        }
        context.startActivity(intent)
    }
    // Новый метод для запуска BookDetailActivity с передачей имени файла книги
    fun startBookTextActivity(context: Context, bookFileName: String) {
        val intent = Intent(context, BookDetailActivity::class.java).apply {
            putExtra("BOOK_FILE_NAME", bookFileName) // Передаем имя файла книги
        }
        context.startActivity(intent)
    }
    // Метод для запуска ProfileActivity с передачей никнейма
    fun startProfileActivity(context: Context, nickname: String) {
        val intent = Intent(context, ProfileActivity::class.java).apply {
            putExtra("USER_NICKNAME", nickname) // Передаем никнейм
        }
        context.startActivity(intent)
    }

}
