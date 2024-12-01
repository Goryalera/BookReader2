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
                imageView.setImageURI(it)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile)

        imageView = findViewById(R.id.my_image_view2)
        nicknameEditText = findViewById(R.id.nickname2)

        val changeProfilePhotoButton = findViewById<Button>(R.id.changeprofilephoto)
        val addMyBookButton = findViewById<Button>(R.id.addmybook)
        val impessionsButton = findViewById<Button>(R.id.impessions)
        val quotesButton = findViewById<Button>(R.id.quotes)

        // Устанавливаем дефолтное изображение при запуске ProfileActivity
        imageView.setImageResource(R.drawable.default_avatar)

        // Получаем никнейм, переданный через Intent
        val nickname = intent.getStringExtra("NICKNAME") // Получаем никнейм из Intent
        if (nickname != null) {
            nicknameEditText.setText(nickname)
        }

        val backButton = findViewById<ImageButton>(R.id.back_btn)
        backButton.setOnClickListener {
            onBackPressed()
        }

        // Устанавливаем действие на кнопку изменения фото профиля
        // changeProfilePhotoButton.setOnClickListener {
        // Запускаем Intent для выбора изображения
        //     pickImageLauncher.launch(ActivityResultContracts.PickVisualMedia.ImageOnly)
        // }

        addMyBookButton.setOnClickListener {
            val intent = Intent(this, AddBookActivity::class.java)
            startActivity(intent)
        }

        impessionsButton.setOnClickListener {
            val intent = Intent(this, ImpressionsActivity::class.java)
            startActivity(intent)
        }

        quotesButton.setOnClickListener {
            val intent = Intent(this, QuotesActivity::class.java)
            startActivity(intent)
        }
    }
}
