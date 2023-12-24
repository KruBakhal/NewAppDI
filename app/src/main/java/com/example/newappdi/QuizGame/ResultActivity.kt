package com.example.newappdi.QuizGame

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.newappdi.NewsApp.Adapter.GameAdapter
import com.example.newappdi.databinding.ActivityResultBinding
import com.google.common.reflect.TypeToken
import com.google.gson.Gson

class ResultActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_result)
        val binding = ActivityResultBinding.inflate(layoutInflater)

        setContentView(binding.root)
        var list= arrayListOf<ResultModel>()
        list = Gson().fromJson(intent.getStringExtra("list"), object : TypeToken<ArrayList<ResultModel>>() {}.getType())
        val adapater = GameAdapter()
        adapater.differ.submitList(list)
        binding.rvList.adapter = adapater
        binding.btnHome.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }
        binding.btnNextLevel.setOnClickListener {

            setResult(RESULT_OK)
            finish()
        }

    }
}