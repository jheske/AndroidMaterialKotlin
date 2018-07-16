package com.example.fullscreenactivity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.NavUtils
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_activity_fullsize_image.*
import java.io.File


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
const val ARG_RESOURCE_ID = "resource_id"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [OrchidActivityFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [OrchidActivityFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 * http://blog.raffaeu.com/archive/2015/04/11/android-and-the-transparent-status-bar.aspx
 * https://forums.xamarin.com/discussion/122066/transparent-toolbar-over-page-content
 */
class FullsizeImageActivityFragment : Fragment() {
    private val TAG = FullsizeImageActivityFragment::class.java.simpleName
    private var mImageResourceId: Int? = R.drawable.placeholder

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @return A new instance of fragment BlankFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(imageResourceId: Int) =
                FullsizeImageActivityFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_RESOURCE_ID, imageResourceId)
                    }
                }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            //mOrchidImageUri = Uri.parse(arguments?.getString(ARG_IMAGE_PATH))
            mImageResourceId = arguments?.getInt(ARG_RESOURCE_ID)
        }
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_activity_fullsize_image, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageResId = mImageResourceId
        mImageResourceId?.let {
            Picasso.get()
                    .load(it)
                    .into(img_fullsize)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                // Respond to the action bar's Up/Home button
                //Return true to indicate the option has been handled
                NavUtils.navigateUpFromSameTask(currentActivity)
                return true
            }
            else -> return false
        }
    }
}
