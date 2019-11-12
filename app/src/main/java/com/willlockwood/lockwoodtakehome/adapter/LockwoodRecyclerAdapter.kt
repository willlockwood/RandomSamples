package com.willlockwood.lockwoodtakehome.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.willlockwood.lockwoodtakehome.R
import com.willlockwood.lockwoodtakehome.data.model.Lockwood
import kotlinx.android.synthetic.main.item_recycler_view_row.view.*

class LockwoodRecyclerAdapter internal constructor(
    context: Context
): RecyclerView.Adapter<LockwoodRecyclerAdapter.LockwoodViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var lockwoods = emptyList<Lockwood>()
    private var listener: OnItemClickListener? = null

    inner class LockwoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val username: TextView = itemView.username_text
        val image: ImageView = itemView.image_view
        val card: CardView = itemView.recyclerViewCard
        val likes: TextView = itemView.likes_text
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LockwoodViewHolder {
        val itemView = inflater.inflate(R.layout.item_recycler_view_row, parent, false)
        return LockwoodViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: LockwoodViewHolder, position: Int) {
        val lockwood = lockwoods[position]
        holder.username.text = lockwood.user
        holder.likes.text = when (lockwood.likes) {
            1 -> "${lockwood.likes} like"
            else -> "${lockwood.likes} likes"
        }

        Glide.with(holder.itemView.context)
            .load(lockwood.largeImageURL)
            .into(holder.image)

        holder.card.setOnClickListener { view -> listener!!.onItemClick(view, position) }
    }

    internal fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    fun setLockwoods(lockwoods: List<Lockwood>) {
        this.lockwoods = lockwoods
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = lockwoods.size

}