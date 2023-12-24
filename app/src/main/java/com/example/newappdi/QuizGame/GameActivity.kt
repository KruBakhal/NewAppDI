package com.example.newappdi.QuizGame

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.newappdi.R
import com.example.newappdi.databinding.ActivityGameBinding
import com.google.gson.Gson
import java.util.Random


class GameActivity : AppCompatActivity() {


    private var op4: Int = 0
    private var ans: Int = 0
    private var num2: Int = 0
    private var num1: Int = 0
    private lateinit var binding: ActivityGameBinding
    var totalQues = 10
    var currentQuesCount = 0
    var round = 0;
    var points = 0;
    var wrongAns = 10;
    var rightAns = 30;
    lateinit var countDownTimwe: CountDownTimer
    lateinit var roundTimer: CountDownTimer
    val listRound = arrayListOf<ResultModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)


        click()
        startCountDown()

    }

    private fun click() {
        binding.tvOp1.setOnClickListener {
            val value = binding.tvOp1.text.toString().toInt()

            checkAns(value)
        }
        binding.tvOp2.setOnClickListener {
            val value = binding.tvOp2.text.toString().toInt()

            checkAns(value)
        }
        binding.tvOp3.setOnClickListener {
            val value = binding.tvOp3.text.toString().toInt()

            checkAns(value)
        }
        binding.tvOp4.setOnClickListener {
            val value = binding.tvOp4.text.toString().toInt()
            checkAns(value)
        }
    }

    private fun checkAns(value: Int) {
        val result = num1 + num2

        if (result == value) {
            points = points + rightAns
        } else {
            points = points - wrongAns
            if (points < 0)
                points = 0
        }
        binding.tvPoints.setText("$points")


        generateNextQuestion()
    }


    private fun startCountDown() {
        op4 = 0
        ans = 0
        num2 = 0
        num1 = 0
        totalQues = 10
        currentQuesCount = 0
        round++;
        points = 0;
        restValue()
        binding.tvCountDown.visibility = View.VISIBLE
        countDownTimwe = object : CountDownTimer(3000, 1000) {

            override fun onTick(p0: Long) {
                binding.tvCountDown.setText("Count Down: ${(p0 / 1000).toInt()}")
            }

            override fun onFinish() {
                binding.tvCountDown.visibility = View.GONE
                countDownTimwe.cancel()
                startRound()
            }

        }
        countDownTimwe.start()
    }

    private fun restValue() {
        binding.tvOp1.setText("0")
        binding.tvOp2.setText("0")
        binding.tvOp3.setText("0")
        binding.tvOp4.setText("0")
        binding.tvQuestion.setText("")
        binding.tvTime.setText("Time: 0/45 Sec")
        binding.tvQuesAns.setText("Ques: $currentQuesCount/${totalQues}")
        binding.tvPoints.setText("Points: $points")
        binding.tvLevel.setText("Level: $round")
    }

    private fun startRound() {
        generateNextQuestion()
        roundTimer = object : CountDownTimer(45000, 1000) {

            override fun onTick(p0: Long) {
                binding.tvTime.setText("Time: ${(p0 / 1000).toInt()}/45 Sec")
            }

            override fun onFinish() {
                val intent = Intent(this@GameActivity, ResultActivity::class.java)
                val resultModel =
                    ResultModel(round.toString(), points.toString(), currentQuesCount.toString())
                listRound.add(resultModel)
                roundTimer.cancel()
                intent.putExtra("list", Gson().toJson(listRound))
                startActivityForResult(intent, 1234)
            }
        }

        roundTimer.start()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1234 && resultCode == RESULT_OK) {
            startCountDown()
        } else if (requestCode == 1234 && resultCode == RESULT_CANCELED) {
            finish()
        }

    }

    fun generateNextQuestion() {
        if (currentQuesCount == totalQues) {
            if (roundTimer != null) roundTimer.onFinish()
            return
        }
        currentQuesCount++
        num1 = generateRandomNumber()
        num2 = generateRandomNumber()
        op4 = generateRandomNumber()
        ans = num1 + num2
        binding.tvQuestion.setText(" $num1 + $num2 ")
        val list = arrayListOf<Int>(num1, num2, ans, op4)
        list.shuffle()
        binding.tvOp1.setText("${list.get(0)}")
        binding.tvOp2.setText("${list.get(1)}")
        binding.tvOp3.setText("${list.get(2)}")
        binding.tvOp4.setText("${list.get(3)}")
        binding.tvQuesAns.setText("Ques: $currentQuesCount/${totalQues}")

    }

    fun generateRandomNumber(): Int {
        val max = 100
        val min = 0
        val r = Random()
        val randInt: Int = r.nextInt(max - min) + min
        return randInt
    }

    override fun onBackPressed() {
        if (countDownTimwe != null) countDownTimwe.cancel()
        if (roundTimer != null) roundTimer.cancel()
        finish()
    }
}