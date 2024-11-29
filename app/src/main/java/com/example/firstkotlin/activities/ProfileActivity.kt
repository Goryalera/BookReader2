package com.example.firstkotlin.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ImageButton
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.firstkotlin.R

class ProfileActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var nicknameEditText: EditText

    // Новый способ обработки результата выбора изображения
    private val pickImageLauncher =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            uri?.let {
                // Устанавливаем изображение в ImageView
                imageView.setImageURI(it)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile)

        imageView = findViewById(R.id.my_image_view2)  // Получаем ссылку на ImageView
        nicknameEditText = findViewById(R.id.nickname2) // Получаем ссылку на EditText для никнейма

        val changeProfilePhotoButton = findViewById<Button>(R.id.changeprofilephoto)
        val addMyBookButton = findViewById<Button>(R.id.addmybook)  // Кнопка для добавления книги
        val impessionsButton = findViewById<Button>(R.id.impessions)  // Кнопка для просмотра впечатлений
        val quotesButton = findViewById<Button>(R.id.quotes)  // Кнопка для цитат

        // Устанавливаем дефолтное изображение при запуске ProfileActivity
        imageView.setImageResource(R.drawable.default_avatar)

        // Получаем никнейм, переданный через Intent
        val nickname = intent.getStringExtra("NICKNAME") // Получаем никнейм из Intent
        if (nickname != null) {
            nicknameEditText.setText(nickname) // Устанавливаем никнейм в EditText
        }

        // Обработка кнопки "back_btn" (возврат на предыдущий экран)
        val backButton = findViewById<ImageButton>(R.id.back_btn)
        backButton.setOnClickListener {
            onBackPressed() // Возвращаемся на предыдущий экран
        }

        // Устанавливаем действие на кнопку изменения фото профиля
        // При желании можно раскомментировать код для изменения фотографии
        // changeProfilePhotoButton.setOnClickListener {
        // Запускаем Intent для выбора изображения
        //     pickImageLauncher.launch(ActivityResultContracts.PickVisualMedia.ImageOnly)
        // }

        // Устанавливаем действие на кнопку "Add my book"
        addMyBookButton.setOnClickListener {
            // При нажатии на кнопку переходим на экран добавления книги
            val intent = Intent(this, AddBookActivity::class.java)
            startActivity(intent)
        }

        // Устанавливаем действие на кнопку "Impessions"
        impessionsButton.setOnClickListener {
            // При нажатии на кнопку переходим на экран впечатлений
            val intent = Intent(this, ImpressionsActivity::class.java)
            startActivity(intent)
        }

        // Устанавливаем действие на кнопку "Quotes"
        quotesButton.setOnClickListener {
            // При нажатии на кнопку переходим на экран цитат
            val intent = Intent(this, QuotesActivity::class.java)
            startActivity(intent)
        }
    }
}
