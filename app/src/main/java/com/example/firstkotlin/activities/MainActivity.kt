package com.example.firstkotlin.activities

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.example.firstkotlin.R
import com.example.firstkotlin.ScrollableContentFragment
import com.example.firstkotlin.utilities.IntentUtils

class MainActivity : AppCompatActivity() {

    private lateinit var nicknameEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val libraryButton = findViewById<Button>(R.id.button_library)
        libraryButton.setOnClickListener {
            // Ваш код для перехода в библиотеку
        }

        val bookButton = findViewById<Button>(R.id.button_book)
        bookButton.setOnClickListener {
            // Ваш код для перехода в экран с книгой
        }

        val searchButton = findViewById<Button>(R.id.button_search)
        searchButton.setOnClickListener {
            // Ваш код для перехода на экран поиска
        }

        // Настройка ImageView для перехода в ProfileActivity и установка базового изображения
        val imageView = findViewById<ImageView>(R.id.my_image_view)
        imageView.setImageResource(R.drawable.default_avatar)
        imageView.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent) // Переход на экран профиля
        }

        // Получаем EditText для никнейма
        nicknameEditText = findViewById(R.id.nickname)

        nicknameEditText.setOnEditorActionListener { v, actionId, event ->
            // Проверяем, если была нажата клавиша "Enter"
            if (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER) {
                val enteredNickname = nicknameEditText.text.toString()

                // Проверяем, что никнейм не пустой
                if (enteredNickname.isNotEmpty()) {
                    // Переходим в ProfileActivity, передаем никнейм
                    IntentUtils.startProfileActivity(this, enteredNickname)
                } else {
                    // Если никнейм пустой, показываем ошибку
                    Toast.makeText(this, "Nickname cannot be empty", Toast.LENGTH_SHORT).show()
                }
                true // Возвращаем true, чтобы предотвратить стандартное поведение
            } else {
                false // Возвращаем false, если клавиша не "Enter"
            }
        }

        // Загружаем фрагмент
        if (savedInstanceState == null) {
            val fragmentManager: FragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            val fragment = ScrollableContentFragment()
            fragmentTransaction.replace(R.id.scrollable_content_fragment, fragment)
            fragmentTransaction.commit()
        }
    }
}
