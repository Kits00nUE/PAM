package com.example.pam

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File

class DataFragment : Fragment() {

    private lateinit var receiptsRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_data, container, false)

        // Inicjalizacja RecyclerView
        receiptsRecyclerView = view.findViewById(R.id.receiptsRecyclerView)

        // Konfiguracja RecyclerView
        receiptsRecyclerView.layoutManager = LinearLayoutManager(context)
        receiptsRecyclerView.adapter = MyAdapter()

        return view
    }
}
