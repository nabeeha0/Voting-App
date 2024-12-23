package com.studereDevs.votingapp.ui
import android.os.Bundle
import com.github.mikephil.charting.components.XAxis.XAxisPosition
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.studereDevs.votingapp.R
import com.studereDevs.votingapp.db.AppDatabase
import com.studereDevs.votingapp.db.Emotion
import kotlinx.android.synthetic.main.activity_graph.*
import kotlinx.coroutines.launch
import java.util.*


class GraphActivity : BaseAppCompatActivity() {
    var angryPercentageResult = 0f
    var upsetPercentageResult = 0f
    var smileyPercentageResult = 0f
    var happyPercentageResult = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graph)
        getData()
    }

    private fun getData() {

        launch {
           baseContext?.let {
               var totalCount = 0
               var upsetCount =0
               var angryCount = 0
               var smileyCount = 0
               var happyCount = 0
               val getAllEntries = AppDatabase(it).getEmotionDao().getAll()
               getAllEntries.forEach {emotion : Emotion ->
                   totalCount++
                   when {
                       emotion.Angry -> angryCount++
                       emotion.Upset -> upsetCount++
                       emotion.Smiley -> smileyCount++
                       emotion.Happy -> happyCount++
                   }
               }
               val angryPercentage = (angryCount.toDouble() / totalCount) * 100
               angryPercentageResult = angryPercentage.toFloat()
               val upsetPercentage = (upsetCount.toDouble() / totalCount) * 100
               upsetPercentageResult = upsetPercentage.toFloat()
               val smileyPercentage = (smileyCount.toDouble() / totalCount) * 100
               smileyPercentageResult = smileyPercentage.toFloat()
               val happyPercentage = (happyCount.toDouble() / totalCount) * 100
               happyPercentageResult = happyPercentage.toFloat()
           }
            setBarChart()

        }
    }
    private fun setBarChart(){

        val entries = ArrayList<BarEntry>()
        entries.add(BarEntry(angryPercentageResult, 0))
        entries.add(BarEntry(upsetPercentageResult, 2))
        entries.add(BarEntry(smileyPercentageResult, 1))
        entries.add(BarEntry(happyPercentageResult, 3))
        val barDataSet = BarDataSet(entries, "Cells")

        val labels = ArrayList<String>()
        labels.add(resources.getString(R.string.angry))
        labels.add(resources.getString(R.string.upset))
        labels.add(resources.getString(R.string.smiley))
        labels.add(resources.getString(R.string.happy))
        val data = BarData(labels, barDataSet)
        barChart.data = data // set the data and list of lables into chart
        val xAxis = barChart.getXAxis()
        xAxis.setPosition(XAxisPosition.BOTTOM)
        xAxis.setDrawGridLines(true)

        val yAxisLeft = barChart.getAxisLeft()
        yAxisLeft.setAxisMaxValue(100f)
        val yAxisRight = barChart.getAxisRight()
        yAxisRight.setAxisMaxValue(100f)
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barChart.getLegend().setEnabled(false);

        barChart.animateY(5000)
    }
}
