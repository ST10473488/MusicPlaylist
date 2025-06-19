package vcmsa.ci.musicplaylist

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import vcmsa.ci.mymusicplaylist.R

class MainActivity : AppCompatActivity() {

    companion object {
        val songTitles = mutableListOf<String>()
        val artistNames = mutableListOf<String>()
        val ratings = mutableListOf<Double>()
        val comments = mutableListOf<String>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Input fields
        val titleInput = findViewById<EditText>(R.id.editSongTitle)
        val artistInput = findViewById<EditText>(R.id.editArtistName)
        val ratingInput = findViewById<EditText>(R.id.editRating)
        val commentInput = findViewById<EditText>(R.id.editComment)

        // Buttons
        val btnAdd = findViewById<Button>(R.id.btnAddToPlaylist)
        val btnNext = findViewById<Button>(R.id.btnGoToDetails)
        val btnExit = findViewById<Button>(R.id.btnExitApp)

        // Add to Playlist
        btnAdd.setOnClickListener {
            val title = titleInput.text.toString().trim()
            val artist = artistInput.text.toString().trim()
            val ratingStr = ratingInput.text.toString().trim()
            val comment = commentInput.text.toString().trim()

            // Validate inputs
            if (title.isEmpty() || artist.isEmpty() || ratingStr.isEmpty()) {
                Toast.makeText(this, "Please fill in all required fields.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val rating = ratingStr.toDoubleOrNull()
            if (rating == null || rating !in 1.0..5.0) {
                Toast.makeText(this, "Rating must be between 1 and 5.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Add to lists
            songTitles.add(title)
            artistNames.add(artist)
            ratings.add(rating)
            comments.add(comment)

            Toast.makeText(this, "Added: $title by $artist", Toast.LENGTH_SHORT).show()

            // Clear input
            titleInput.text.clear()
            artistInput.text.clear()
            ratingInput.text.clear()
            commentInput.text.clear()
        }

        // Go to details
        btnNext.setOnClickListener {
            val intent = Intent(this, DetailedViewActivity::class.java)
            startActivity(intent)
        }

        // Exit app
        btnExit.setOnClickListener {
            finish()
        }
    }

    class DetailedViewActivity {

    }
}




