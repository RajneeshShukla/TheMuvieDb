package `in`.ac.themuviedb.activities.home.fragments


import `in`.ac.themuviedb.activities.MovieDetail.MovieDetail
import `in`.ac.themuviedb.activities.home.MovieAdapter
import `in`.ac.themuviedb.model.MuvieDetailModel
import `in`.ac.themuviedb.model.Result
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
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
    var mMoviesList: MutableList<Result>? = null

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
        makeGetMovieApiCall()
    }

    private fun makeGetMovieApiCall() {
        mProgressBar?.visibility = View.VISIBLE
        val movieData = ApiClient.create()
        movieData.getMovies().enqueue(this)
    }

    override fun onFailure(call: Call<MuvieDetailModel>, t: Throwable) {
        mProgressBar?.visibility = View.GONE
    }

    override fun onResponse(call: Call<MuvieDetailModel>, response: Response<MuvieDetailModel>) {
        val recyclerView = activity?.findViewById(`in`.ac.themuviedb.R.id.recyclerView) as RecyclerView

        val gridLayoutManager = GridLayoutManager(context, 2)
        recyclerView.apply {
            recyclerView.layoutManager = gridLayoutManager // set LayoutManager to RecyclerView
            adapter = MovieAdapter(context, response.body()?.results, this@MuvieFragment)
        }

        mProgressBar?.visibility = View.GONE

        var isScrolling: Boolean = false
        var mCurrentItems: Int
        var mTotalItems: Int
        var mScrollOutItems: Int

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                mCurrentItems = gridLayoutManager.childCount
                mTotalItems = gridLayoutManager.itemCount
                mScrollOutItems = (gridLayoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

                Log.e("RRR", "mCurrentItems $mCurrentItems $mScrollOutItems $mTotalItems")

                if (isScrolling && (mCurrentItems + mScrollOutItems == mTotalItems)) {

                    if (dy > 0) {
                        Log.e("RRR", "Condition meet")
                        makeGetMovieApiCall()
                    }
                }

            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true
                }
            }
        })
    }

    override fun onMovieClicked(movieItem: Result) {
        val intent = Intent(context, MovieDetail::class.java)
        intent.putExtra("MOVIE_INFO", movieItem)
        startActivity(intent)
    }
}

