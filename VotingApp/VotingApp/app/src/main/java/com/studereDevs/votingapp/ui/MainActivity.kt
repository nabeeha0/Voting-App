package com.studereDevs.votingapp.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import com.studereDevs.votingapp.R
import com.studereDevs.votingapp.db.AppDatabase
import com.studereDevs.votingapp.db.Emotion
import com.studereDevs.votingapp.util.toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : BaseAppCompatActivity() {

    private var angry : Boolean = false
    private var upset : Boolean = false
    private var smiley : Boolean = false
    private var happy : Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showDateTime()
        getTotalVotes()
        onCLickFunctions()
    }

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    private fun showDateTime() {
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())
        val date = currentDate
        tvDate.text = "Date: " + date.substring(0, date.indexOf(' '))
        tvTime.text = "Time: " + date.substring(date.indexOf(' ') + 1)
    }

    private fun onCLickFunctions() {
        btnAngry.setOnClickListener{_ ->
            angry = true
            upset = false
            smiley = false
            happy = false
            launch{
                baseContext?.let {
                    val emotion = Emotion(angry, upset, smiley, happy)
                    AppDatabase(it).getEmotionDao().insert(emotion)
                    it.toast(baseContext.getString(R.string.angry_selected))
                    getTotalVotes()
                }
            }
        }
        btnUpset.setOnClickListener{_ ->
            upset = true
            angry = false
            smiley = false
            happy = false
            launch{
                baseContext?.let {
                    val emotion = Emotion(angry, upset, smiley, happy)
                    AppDatabase(it).getEmotionDao().insert(emotion)
                    it.toast(baseContext.getString(R.string.upset_selected))
                    getTotalVotes()
                }
            }
        }

        btnSmiley.setOnClickListener{_ ->
            smiley = true
            upset = false
            angry = false
            happy = false
            launch{
                baseContext?.let {
                    val emotion = Emotion(angry, upset, smiley, happy)
                    AppDatabase(it).getEmotionDao().insert(emotion)
                    it.toast(baseContext.getString(R.string.smiley_selected))
                    getTotalVotes()
                }
            }
        }

        btnHappy.setOnClickListener{_ ->
            happy = true
            upset = false
            smiley = false
            angry = false
            launch{
                baseContext?.let {
                    val emotion = Emotion(angry, upset, smiley, happy)
                    AppDatabase(it).getEmotionDao().insert(emotion)
                    it.toast(baseContext.getString(R.string.happy_selected))
                    getTotalVotes()
                }
            }
        }
        btnGraphActivity.setOnClickListener{
            val intent = Intent(this@MainActivity, GraphActivity::class.java)
            startActivity(intent)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun getTotalVotes() {
        launch {
            baseContext?.let {
                var totalCount =0
                val getAllEntries = AppDatabase(it).getEmotionDao().getAll()
                repeat(getAllEntries.size) {
                    totalCount++
                }
                tvTotalVotes.text = "Number of Votes : $totalCount"
            }
        }
    }
}

