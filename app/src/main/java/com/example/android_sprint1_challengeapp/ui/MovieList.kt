package com.example.android_sprint1_challengeapp.ui

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.Color.RED
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.android_sprint1_challengeapp.Model.Movie
import com.example.android_sprint1_challengeapp.R
import com.example.android_sprint1_challengeapp.ui.MovieDetail.Companion.watched
import kotlinx.android.synthetic.main.activity_movie_list.*

class MovieList : AppCompatActivity() {

    var movieList = mutableListOf<Movie>() //declaring list of movies that we can add to

    companion object {
        const val REQUEST_CODE_MOVIE = 1 //this object is our key for the key value pair
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        button_add_movie.setOnClickListener {
            var addMovieIntent = Intent(this, MovieDetail::class.java)
            startActivityForResult(addMovieIntent, REQUEST_CODE_MOVIE)
        }
    }

    fun refreshList() {
        linlay_movieList.removeAllViews()
        for((counter, movie) in movieList.withIndex()) {
            linlay_movieList.addView(createTextView(movie, counter))
        }
    }

    override fun onPostResume() {
        refreshList()
        super.onPostResume()
    }

    fun createTextView(movie: Movie, index: Int): TextView {
        var newMovieView = TextView(this)
        newMovieView.textSize = 22f
        newMovieView.id = index
        newMovieView.text = movie.title
        if(watched == true){                        //this is changing the color, but it's changing the color for all textviews
            newMovieView.setTextColor(Color.RED)
        } else {
            newMovieView.setTextColor(Color.BLACK)
        }

        newMovieView.setOnClickListener{
            var textViewIntent = Intent(this, MovieDetail::class.java)
            textViewIntent.putExtra("textViewMovie", movieList[newMovieView.id])
            movieList.removeAt(newMovieView.id)
            startActivityForResult(textViewIntent, REQUEST_CODE_MOVIE)
        }
        return newMovieView
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) { //would I just need to edit this to include delete case?
        if (requestCode == REQUEST_CODE_MOVIE && resultCode == Activity.RESULT_OK) {  // that's what I did, not sure if it's a hack or not, but it works
            val newResultMovie = data?.getSerializableExtra("movie") as Movie
            movieList.add(newResultMovie)
        } else {
            val newResultMovie = data?.getSerializableExtra("movie") as Movie
            movieList.remove(newResultMovie)
        }


    }

}