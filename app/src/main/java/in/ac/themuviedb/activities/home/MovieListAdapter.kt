package `in`.ac.themuviedb.activities.home

import `in`.ac.themuviedb.R
import `in`.ac.themuviedb.model.Result
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import com.squareup.picasso.Picasso

class MovieListAdapter(context: Context, private val resource: Int, private val movieList: List<Result>) :
    ArrayAdapter<MovieListAdapter.MovieListViewHolder>(context, resource) {

    override fun getCount(): Int {
        return if (this.movieList != null) this.movieList.size else 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var convertView = convertView

        val holder: MovieListViewHolder
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, null)
            holder = MovieListViewHolder()
            holder.movieImg = convertView.findViewById(R.id.movie_poster_img)
            convertView.tag = holder
        } else {
            holder = convertView.tag as MovieListViewHolder
        }

        Picasso.get().load("https://image.tmdb.org/t/p/w600_and_h900_bestv2" + movieList[position].posterPath)
            .into(holder.movieImg)
        return convertView
    }

    // View Holder Class
    class MovieListViewHolder {
        var movieImg: ImageView? = null
    }
}