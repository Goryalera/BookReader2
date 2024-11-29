package com.example.firstkotlin.adapters

import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.firstkotlin.R
import com.example.firstkotlin.data.Quote

class QuoteAdapter(private val quotes: List<Quote>) : RecyclerView.Adapter<QuoteAdapter.QuoteViewHolder>() {

    class QuoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val quoteContent: TextView = view.findViewById(R.id.quote_content) // Используется для отображения текста цитаты
        val imageView: ImageView = view.findViewById(R.id.quotesimage) // Добавляем ссылку на ImageView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_quote, parent, false)
        return QuoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        val quote = quotes[position]
        holder.quoteContent.text = quote.content // Здесь будет отображаться текст цитаты
    }

    override fun getItemCount() = quotes.size
}

