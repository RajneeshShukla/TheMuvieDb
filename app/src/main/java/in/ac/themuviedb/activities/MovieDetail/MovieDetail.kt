package `in`.ac.themuviedb.activities.MovieDetail

import `in`.ac.themuviedb.R
import `in`.ac.themuviedb.model.Result
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class MovieDetail : AppCompatActivity() {
    var mMoviePoster: ImageView? = null
    var mMovieHorPoster: ImageView? = null
    var mMovieTitle: TextView? = null
    var mMovieRating: TextView? = null
    var mLauchDate: TextView? = null
    var mMovieOverview: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val movieData = intent.extras.get("MOVIE_INFO") as Result

        mMoviePoster = findViewById(R.id.movie_poster)
        mMovieHorPoster = findViewById(R.id.movie_hor_poster)
        mMovieTitle = findViewById(R.id.movie_title)
        mMovieRating = findViewById(R.id.movie_rating)
        mLauchDate = findViewById(R.id.release_date)
        mMovieOverview = findViewById(R.id.movie_overview)

        Picasso.get().load("https://image.tmdb.org/t/p/w500_and_h282_face" + movieData.posterPath)
            .into(mMoviePoster)

        Picasso.get().load("https://image.tmdb.org/t/p/w600_and_h900_bestv2" + movieData.posterPath)
            .into(mMovieHorPoster)

        mMovieTitle?.text = movieData.originalTitle
        mMovieRating?.text = movieData.voteAverage.toString()

        try {
            mLauchDate?.text = "Release Date : " + movieData.releaseDate.toString()
        } catch (e: NullPointerException) {
            Log.e("TAG", e.localizedMessage)
        }

        mMovieOverview?.text = movieData.overview
    }
}

