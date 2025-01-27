package com.example.pam

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.io.File

class ReceiptsAdapter(
    private val receipts: List<File>,
    private val onImageClick: (File) -> Unit,
    private val onDeleteClick: (File) -> Unit
) : RecyclerView.Adapter<ReceiptsAdapter.ReceiptViewHolder>() {

    inner class ReceiptViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val receiptImageView: ImageView = view.findViewById(R.id.receiptImageView)
        val receiptNameTextView: TextView = view.findViewById(R.id.receiptNameTextView)
        val deleteIcon: ImageView = view.findViewById(R.id.deleteIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReceiptViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_receipt, parent, false)
        return ReceiptViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReceiptViewHolder, position: Int) {
        val receipt = receipts[position]

        try {
            val bitmap = BitmapFactory.decodeFile(receipt.absolutePath)
            holder.receiptImageView.setImageBitmap(bitmap)
            holder.receiptNameTextView.text = receipt.name
        } catch (e: Exception) {
            holder.receiptNameTextView.text = "Błąd ładowania"
            holder.receiptImageView.setImageResource(R.drawable.ic_error) // placeholder błędu
        }

        holder.receiptImageView.setOnClickListener {
            onImageClick(receipt)
        }

        holder.deleteIcon.setOnClickListener {
            onDeleteClick(receipt)
        }
    }

    override fun getItemCount(): Int = receipts.size
}
