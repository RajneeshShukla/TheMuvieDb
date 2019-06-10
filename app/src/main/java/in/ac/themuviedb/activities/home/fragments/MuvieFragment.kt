package `in`.ac.themuviedb.activities.home.fragments


import `in`.ac.themuviedb.activities.MovieDetail.MovieDetail
import `in`.ac.themuviedb.activities.home.MovieAdapter
import `in`.ac.themuviedb.model.MuvieDetailModel
import `in`.ac.themuviedb.model.Result
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import www.mindfire.thenews.service.ApiClient


/**
 * A simple [Fragment] subclass.
 *
 */
class MuvieFragment : Fragment(), Callback<MuvieDetailModel>, MovieAdapter.MovieItemClickListener {

    var mProgressBar: ProgressBar? = null
    var mVisibleItemCount: Int = 0
    var mTotalItemCount: Int = 0
    var isScrolling: Boolean = false
    var mLayoutManager = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(`in`.ac.themuviedb.R.layout.fragment_muvie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mProgressBar = activity?.findViewById(`in`.ac.themuviedb.R.id.progress_bar)

        var movieData = ApiClient.create()
        movieData.getMovies().enqueue(this)

        mProgressBar?.visibility = View.VISIBLE
    }


    override fun onFailure(call: Call<MuvieDetailModel>, t: Throwable) {
        mProgressBar?.visibility = View.GONE
    }

    override fun onResponse(call: Call<MuvieDetailModel>, response: Response<MuvieDetailModel>) {

        val recyclerView = activity?.findViewById(`in`.ac.themuviedb.R.id.recyclerView) as RecyclerView

        val gridLayoutManager = GridLayoutManager(context, 2)
        recyclerView.apply {
            recyclerView.layoutManager = gridLayoutManager // set LayoutManager to RecyclerView
            recyclerView.itemAnimator = DefaultItemAnimator()
            adapter = MovieAdapter(context, response.body()?.results, this@MuvieFragment)
        }

        mProgressBar?.visibility = View.GONE
    }

    override fun onMovieClicked(movieItem: Result) {
        val intent = Intent(context, MovieDetail::class.java)
        intent.putExtra("MOVIE_INFO", movieItem)
        startActivity(intent)
    }
}
