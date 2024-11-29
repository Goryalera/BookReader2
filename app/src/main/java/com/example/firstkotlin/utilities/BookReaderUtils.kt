package com.example.firstkotlin.utilities
import android.content.Context
import android.widget.TextView
import android.widget.ProgressBar
import com.example.firstkotlin.data.Book
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
//Функции для разбивки текста на страницы (splitTextIntoPages), отображения страницы (displayPage), расчёта прогресса (calculateProgress), и загрузки текста книги из assets (loadBookText).
// Класс с общими методами для работы с книгой
object BookReaderUtils {

    // Разбиение текста на страницы
    fun splitTextIntoPages(text: String, pageSize: Int = 2000): List<String> {
        val pagesList = mutableListOf<String>()
        var currentIndex = 0
        while (currentIndex < text.length) {
            val nextIndex = (currentIndex + pageSize).coerceAtMost(text.length)
            pagesList.add(text.substring(currentIndex, nextIndex))
            currentIndex = nextIndex
        }
        return pagesList
    }

    // Отображение страницы в TextView
    fun displayPage(textView: TextView, pages: List<String>, pageNumber: Int) {
        if (pageNumber >= 0 && pageNumber < pages.size) {
            textView.text = pages[pageNumber]
        }
    }

    // Рассчитываем прогресс на основе текущей страницы
    fun calculateProgress(pageNumber: Int, totalPages: Int): Int {
        return ((pageNumber.toDouble() / (totalPages - 1)) * 100).toInt()
    }

    // Метод для загрузки текста книги из assets (для BookDetailActivity)
    fun loadBookText(context: Context, fileName: String): String {
        val stringBuilder = StringBuilder()
        try {
            val inputStream = context.assets.open(fileName)
            val reader = BufferedReader(InputStreamReader(inputStream))
            reader.forEachLine { line -> stringBuilder.append(line).append("\n") }
        } catch (e: IOException) {
            // Логируем ошибку
            e.printStackTrace()
        }
        return stringBuilder.toString()
    }
}
