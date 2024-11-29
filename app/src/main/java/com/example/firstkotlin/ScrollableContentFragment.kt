package com.example.firstkotlin

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firstkotlin.activities.BookDetailActivity
import com.example.firstkotlin.adapters.BookAdapter

class ScrollableContentFragment : Fragment() {

    private lateinit var getImage: ActivityResultLauncher<Intent>
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var percentageTextView: TextView

    private val bookImages = listOf(
        R.drawable.book1,
        R.drawable.book2,
        R.drawable.book3,
        R.drawable.book4,
        R.drawable.book5
    )

    private val totalPages = 300
    private val readPages = 75

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_scrollable_content, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI(view)
        setupRecyclerView()
        // Удалили setupButtons, так как кнопки теперь управляются в MainActivity
        setupTextViews(view)
    }

    private fun initUI(view: View) {
        progressBar = view.findViewById(R.id.progress_bar)
        percentageTextView = view.findViewById(R.id.percentage_text_view)

        val progressPercentage = ((readPages.toFloat() / totalPages) * 100).toInt()
        progressBar.progress = progressPercentage
        percentageTextView.text = "$progressPercentage%"
    }

    private fun setupRecyclerView() {
        recyclerView = requireView().findViewById(R.id.recyclerView)
        val bookAdapter = BookAdapter(bookImages, this::onBookSelected)

        // Настройка RecyclerView для горизонтального прокручивания
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = bookAdapter
    }

    private fun onBookSelected(imageResId: Int) {
        val bookText = "Это текст вашей книги" // Замените на текст как вам нужно
        val intent = Intent(context, BookDetailActivity::class.java).apply {
            putExtra("BOOK_TEXT", bookText)
        }
        startActivity(intent)
    }

    private fun setupTextViews(view: View) {
        // Закомментировано, так как кнопки теперь в MainActivity
        // val impressionsText = view.findViewById<TextView>(R.id.Impressions)
        // impressionsText.setOnClickListener {
        //     val intent = Intent(context, ImpressionsActivity::class.java)
        //     startActivity(intent)
        // }
        //
        // val quotesText = view.findViewById<TextView>(R.id.Quotes)
        // quotesText.setOnClickListener {
        //     val intent = Intent(context, QuotesActivity::class.java)
        //     startActivity(intent)
        // }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK).apply {
            type = "image/*"
        }
        getImage.launch(intent)
    }
}
