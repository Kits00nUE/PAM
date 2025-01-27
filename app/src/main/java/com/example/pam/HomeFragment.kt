package com.example.pam

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File
import java.io.FileOutputStream

class HomeFragment : Fragment() {

    private lateinit var receiptsRecyclerView: RecyclerView
    private val receipts = mutableListOf<File>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        receiptsRecyclerView = view.findViewById(R.id.receiptsRecyclerView)
        receiptsRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        loadReceipts()

        return view
    }

    private fun loadReceipts() {
        val receiptsDir = File(requireContext().filesDir, "receipts")
        if (receiptsDir.exists()) {
            receipts.clear()
            receipts.addAll(receiptsDir.listFiles()?.toList() ?: emptyList())
        }
        receiptsRecyclerView.adapter = ReceiptsAdapter(receipts,
            onImageClick = { receipt ->
                val intent = Intent(requireContext(), FullscreenImageActivity::class.java)
                intent.putExtra("imagePath", receipt.absolutePath)
                startActivity(intent)
            },
            onDeleteClick = { receipt ->
                if (receipt.exists() && receipt.delete()) {
                    Toast.makeText(requireContext(), "Paragon usunięty", Toast.LENGTH_SHORT).show()
                    loadReceipts()
                } else {
                    Toast.makeText(requireContext(), "Nie udało się usunąć paragonu", Toast.LENGTH_SHORT).show()
                }
            })
    }
}
