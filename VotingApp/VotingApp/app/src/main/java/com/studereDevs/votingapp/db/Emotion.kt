package com.studereDevs.votingapp.db
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Emotion
    (
    val Angry: Boolean,
    val Upset: Boolean,
    val Smiley: Boolean,
    val Happy: Boolean
){
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}