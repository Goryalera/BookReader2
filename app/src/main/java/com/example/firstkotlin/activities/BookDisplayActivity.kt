package com.example.firstkotlin.activities
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.firstkotlin.R
//для отображения только изображения книги
class BookDisplayActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_display)

        val bookImageId = intent.getIntExtra("BOOK_IMAGE", -1)
        val bookImageView = findViewById<ImageView>(R.id.book_image_view)

        if (bookImageId != -1) {
            bookImageView.setImageResource(bookImageId)
        }
    }
}
