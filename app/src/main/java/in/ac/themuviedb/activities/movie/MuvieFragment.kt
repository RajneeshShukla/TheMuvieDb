package `in`.ac.themuviedb.activities.movie


import `in`.ac.themuviedb.R
import `in`.ac.themuviedb.activities.movie.MovieListAdapter
import `in`.ac.themuviedb.model.MuvieDetailModel
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import www.mindfire.thenews.service.ApiClient


/**
 * A simple [Fragment] subclass.
 *
 */
class MuvieFragment : Fragment(), Callback<MuvieDetailModel> {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_muvie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var movieData = ApiClient.create()
        movieData.getMovies().enqueue(this)
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

    }
}
