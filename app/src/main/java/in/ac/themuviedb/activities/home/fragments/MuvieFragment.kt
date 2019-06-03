package `in`.ac.themuviedb .activities.home.fragments


import `in`.ac.themuviedb.R
import `in`.ac.themuviedb.activities.home.MovieListAdapter
import `in`.ac.themuviedb.model.MuvieDetailModel
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    var progressBar: ProgressBar? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_muvie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        progressBar = activity?.findViewById(R.id.action_bar)

        var movieData = ApiClient.create()
        movieData.getMovies().enqueue(this)

        progressBar?.visibility  = View.VISIBLE
    }

    override fun onFailure(call: Call<MuvieDetailModel>, t: Throwable) {

    }

    override fun onResponse(call: Call<MuvieDetailModel>, response: Response<MuvieDetailModel>) {
        val gridview = activity!!.findViewById<GridView>(R.id.gridview)

        val adapter = MovieListAdapter(
            activity!!,
            R.layout.movie_item,
            response.body()!!.results
        )
        gridview.adapter = adapter

        progressBar?.visibility  = View.GONE
    }
}
