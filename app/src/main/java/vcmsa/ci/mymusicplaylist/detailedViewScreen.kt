package com.example.playlistapp

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import vcmsa.ci.musicplaylist.MainActivity
import vcmsa.ci.mymusicplaylist.R

class DetailedViewActivity : AppCompatActivity() {

    private lateinit var avgRatingText: TextView
    private lateinit var songListView: ListView
    private lateinit var backButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_view)

        avgRatingText = findViewById(R.id.textAvgRating)
        songListView = findViewById(R.id.listViewSongs)
        backButton = findViewById(R.id.btnBack)

        // Load song data from MainActivity's companion object
        val titles = MainActivity.songTitles
        val artists = MainActivity.artistNames
        val ratings = MainActivity.ratings
        val comments = MainActivity.comments

        if (titles.isEmpty()) {
            avgRatingText.text = "No songs in the playlist yet."
            return
        }

        // Prepare a formatted list for display
        val songDetails = titles.indices.map { i ->
            """
            🎵 Title: ${titles[i]}
            🎤 Artist: ${artists[i]}
            ⭐ Rating: ${ratings[i]}
            💬 Comment: ${comments[i]}
            """.trimIndent()
        }

        // Set adapter for ListView
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, songDetails)
        songListView.adapter = adapter

        // Calculate and show average rating
        val average = ratings.average()
        avgRatingText.text = "⭐ Average Rating: %.2f".format(average)

        // Back button logic
        backButton.setOnClickListener {
            finish() // closes this screen and returns to MainActivity
        }
    }
}
