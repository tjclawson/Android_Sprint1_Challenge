package com.example.android_sprint1_challengeapp.ui

import android.app.Activity
import android.content.Intent
import android.graphics.Color.RED
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android_sprint1_challengeapp.Model.Movie
import com.example.android_sprint1_challengeapp.R
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetail : AppCompatActivity() {

    companion object {
        var watched = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        button_save_movie.setOnClickListener {
            var intentSaveMovie = Intent()
            intentSaveMovie.putExtra("movie", createMovie())
            setResult(Activity.RESULT_OK, intentSaveMovie)
            finish()
        }

        button_delete_movie.setOnClickListener {   //use this listener and intent to remove movie on list activity
            var intentDeleteMovie = Intent()
            intentDeleteMovie.putExtra("movie", createMovie())
            setResult(Activity.RESULT_CANCELED, intentDeleteMovie)
            finish()
        } //this now does samething as save button, do I need to change in this activity or list activity?

        switch_watched.setOnCheckedChangeListener{switch_watched, isChecked ->
            if (isChecked){
                watched = true
            } else {
                watched = false
            }
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
        var newMovie = Movie(edittext_movie_title.text.toString(), watched) //here I want to pass in the value set by the onClick
        return newMovie
    }


}


