package `in`.ac.themuviedb.activities.home.fragments


import `in`.ac.themuviedb.R
import `in`.ac.themuviedb.activities.MovieDetail.MovieDetail
import `in`.ac.themuviedb.activities.home.MovieListAdapter
import `in`.ac.themuviedb.model.MuvieDetailModel
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.AdapterView
import android.widget.GridView
import android.widget.ProgressBar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import www.mindfire.thenews.service.ApiClient


/**
 * A simple [Fragment] subclass.
 *
 */
class MuvieFragment : Fragment(), Callback<MuvieDetailModel> {

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
        return inflater.inflate(R.layout.fragment_muvie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mProgressBar = activity?.findViewById(R.id.progress_bar)

        var movieData = ApiClient.create()
        movieData.getMovies().enqueue(this)

        mProgressBar?.visibility = View.VISIBLE
    }


    override fun onFailure(call: Call<MuvieDetailModel>, t: Throwable) {
        mProgressBar?.visibility = View.GONE
    }

    override fun onResponse(call: Call<MuvieDetailModel>, response: Response<MuvieDetailModel>) {
        val gridview = activity!!.findViewById<GridView>(R.id.gridview)

        val adapter = MovieListAdapter(
            activity!!,
            R.layout.movie_item,
            response.body()!!.results
        )
        gridview.adapter = adapter
        mProgressBar?.visibility = View.GONE

        gridview.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val intent = Intent(context, MovieDetail::class.java)
            intent.putExtra("MOVIE_INFO", response.body()!!.results[position])
            startActivity(intent)
        }

        gridview.setOnScrollChangeListener(object : AbsListView.OnScrollListener, View.OnScrollChangeListener {
            override fun onScrollChange(v: View?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int) {
                print("onScrollChange")

            }

            override fun onScroll(
                view: AbsListView?,
                firstVisibleItem: Int,
                visibleItemCount: Int,
                totalItemCount: Int
            ) {
                print("onScroll")

            }

            override fun onScrollStateChanged(view: AbsListView?, scrollState: Int) {
                print("onScrollStateChanged")
            }

        })
    }

}
