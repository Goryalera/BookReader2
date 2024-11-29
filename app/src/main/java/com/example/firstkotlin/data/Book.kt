package com.example.firstkotlin.data

data class Book (
    val id: String,
    val title: String,
    val totalPages: Int,    // Общее количество страниц
    val currentPage: Int,   // Текущая страница
    val progress: Int, // Прогресс (в процентах)
    val text: String,
    val genre: String,
    val description: String,
    val author: String,
    val coverImageRes: Int

) {
    // Рассчитываем прогресс в процентах
    fun calculateProgress(): Int {
        return (currentPage.toFloat() / totalPages * 100).toInt()
    }
}
