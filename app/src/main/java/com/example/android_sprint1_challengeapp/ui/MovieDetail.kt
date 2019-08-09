package com.example.android_sprint1_challengeapp.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android_sprint1_challengeapp.Model.Movie
import com.example.android_sprint1_challengeapp.R
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        button_save_movie.setOnClickListener {
            var intentSaveMovie = Intent()
            intentSaveMovie.putExtra("movie", createMovie())
            setResult(Activity.RESULT_OK, intentSaveMovie)
            finish()
        }

        button_watched.setOnClickListener {

        }



        var bundle: Bundle? = intent.extras
        if(bundle != null) {
            loadMovie(bundle!!.getSerializable("textViewMovie") as Movie)
        }




    }

    fun loadMovie(movie: Movie) {
        edittext_movie_title.setText(movie.title)

    }


    fun createMovie(): Movie {
        var newMovie = Movie(edittext_movie_title.text.toString())
        return newMovie
    }


}
