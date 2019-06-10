package `in`.ac.themuviedb.activities.home

import `in`.ac.themuviedb.R
import `in`.ac.themuviedb.model.Result
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso

class MovieAdapter(
    val context: Context,
    private val movieList: List<Result>?,
    private var mListener: MovieItemClickListener
) :
    RecyclerView.Adapter<MovieAdapter.MovieListViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MovieListViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.movie_item, p0, false)
        return MovieListViewHolder(v)
    }

    override fun getItemCount(): Int {
        return movieList!!.size
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        Picasso.get().load("https://image.tmdb.org/t/p/w600_and_h900_bestv2" + this.movieList!![position].posterPath)
            .into(holder.movieImg)
        holder.movieView.setOnClickListener { v: View? ->
            mListener.onMovieClicked(movieList[position])
        }
    }

    // View Holder Class
    class MovieListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var movieImg: ImageView? = view.findViewById(R.id.movie_poster_img)
        var movieView: View = view
    }

    interface MovieItemClickListener {
        fun onMovieClicked(movieItem: Result)
    }

}