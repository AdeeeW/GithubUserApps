package com.example.githubuserapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.githubuserapp.databinding.ItemListUserBinding

class UserAdapter (private val listUser: ArrayList<User>) : RecyclerView.Adapter<UserAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallBack: OnItemClickCallBack

    fun setOnItemClickCallback(onItemClickCallBack: OnItemClickCallBack) {
        this.onItemClickCallBack = onItemClickCallBack
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        public val binding = ItemListUserBinding.bind(itemView)
        internal fun bind(user: User){
            binding.tvItemNama.setText(user.name)
            binding.tvItemPerusahan.setText(user.company)
            binding.tvItemUsername.setText(user.username)
            binding.imageItem.setImageResource(user.avatar)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_list_user, viewGroup, false)
        return ListViewHolder(view)
    }
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {

        val user = listUser[position]

        Glide.with(holder.itemView.context)
                .load(user.avatar)
                .apply(RequestOptions().override(70,70))
                .into(holder.binding.imageItem)

        holder.binding.tvItemNama.text = user.name
        holder.binding.tvItemPerusahan.text = user.company
        holder.binding.tvItemUsername.text = user.username

        holder.itemView.setOnClickListener {
            onItemClickCallBack.onItemClicked(listUser[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int {
        return listUser.size
    }

    interface OnItemClickCallBack {
        fun onItemClicked(data: User)
    }
}