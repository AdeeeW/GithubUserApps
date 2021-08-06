package com.example.githubuserapp

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import com.example.githubuserapp.databinding.ActivityDetailUserBinding

class DetailUser : AppCompatActivity() {
    companion object {
        const val EXTRA_USER = "extra_user"
    }
    private lateinit var binding: ActivityDetailUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title ="Detail User"

        val user = intent.getParcelableExtra<User>(EXTRA_USER) as User
        val imgAvatar = "${user.avatar}"
        val text = "Name    : ${user.name}\n" +
                "Username   : ${user.username}\n" +
                "Location   : ${user.location}\n" +
                "Repositori : ${user.repository}\n" +
                "Company    : ${user.company}\n" +
                "Followers  : ${user.followers}\n" +
                "Following  : ${user.following}"
        binding.detailAvatar.setImageResource(imgAvatar.toInt())
        binding.tvObjek.text = text
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater = menuInflater
        inflater.inflate(R.menu.menu_share, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.share ->{
                val user = intent.getParcelableExtra<User>(EXTRA_USER) as User
                val text = "Name    : ${user.name}\n" +
                        "Username   : ${user.username}\n" +
                        "Location   : ${user.location}\n" +
                        "Repositori : ${user.repository}\n" +
                        "Company    : ${user.company}\n" +
                        "Followers  : ${user.followers}\n" +
                        "Following  : ${user.following}"

                val shareIntent = Intent().apply {
                    this.action = Intent.ACTION_SEND
                    this.putExtra(Intent.EXTRA_TEXT,"Data User Github\n" +
                            "$text")
                    this.type="text/plain"
                }
                startActivity(Intent.createChooser(shareIntent,"Share melalui:"))
            }
        }
        return super.onOptionsItemSelected(item)
    }
}