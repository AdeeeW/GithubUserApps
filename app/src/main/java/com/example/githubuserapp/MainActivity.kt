package com.example.githubuserapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuserapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var list: ArrayList<User> = arrayListOf()

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title ="Github User's"

        binding.rvUser.setHasFixedSize(true)

        list.addAll(DataUser.listData)
        showRecyclerUser()
    }

    private fun showRecyclerUser() {
        binding.rvUser.layoutManager = LinearLayoutManager(this)
        val adapterListUser = UserAdapter(list)
        binding.rvUser.adapter = adapterListUser

        adapterListUser.setOnItemClickCallback(object : UserAdapter.OnItemClickCallBack{
            override fun onItemClicked(data: User) {
                showSelectedHero(data)
            }
        })
    }

    private fun showSelectedHero(data: User) {
        val detailIntent = Intent(this@MainActivity, DetailUser::class.java)
        detailIntent.putExtra(DetailUser.EXTRA_USER, data)
        startActivity(detailIntent)
    }
}
