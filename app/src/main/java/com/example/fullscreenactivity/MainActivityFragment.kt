package com.example.fullscreenactivity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.*
import kotlinx.android.synthetic.main.fragment_activity_main.*

class MainActivityFragment : Fragment() {

    private val TAG = MainActivityFragment::class.java.simpleName
    // TODO: Rename and change types of parameters
    private lateinit var mContext: Context
    private lateinit var mImageListAdapter: ImageListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_activity_main,
                container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        displayImageItems()
    }

    /**
     * This is called before onCreate() so save context
     */
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    private fun setupRecyclerView() {
        rv_images.setHasFixedSize(true)
        rv_images.setLayoutManager(GridLayoutManager(mContext, 2))
        mImageListAdapter = ImageListAdapter(mContext as AppCompatActivity)
        rv_images.setNestedScrollingEnabled(false)
        rv_images.adapter = mImageListAdapter
        //See RecyclerView extensions in Extensions.kt
        rv_images.addOnItemClickListener(object : OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                Log.d(TAG, "[setupRecyclerView onItemClicked] position = $position")
                displayFullSizeImage(mImageListAdapter.getItemAtPosition(position))
            }
        })
    }

    private fun displayFullSizeImage(imageItem: ImageItem) {
        val intent = Intent(currentActivity, FullsizeImageActivity::class.java)
        val bundle = Bundle()
        bundle.putInt(ARG_RESOURCE_ID, imageItem.resource_id)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    fun displayImageItems() {
        mImageListAdapter.clear()
        mImageListAdapter.add(ImageItem("Peach Cake",R.drawable.peach_cake))
        mImageListAdapter.add(ImageItem("Hello Dixie",R.drawable.hello_dixie))
        mImageListAdapter.add(ImageItem("Oncidium Orchid",R.drawable.oncidium))
        mImageListAdapter.add(ImageItem("London Bridge",R.drawable.london_bridge))
        mImageListAdapter.add(ImageItem("Mocha",R.drawable.mocha))
        mImageListAdapter.add(ImageItem("Curry Puffs",R.drawable.curry_puffs))
    }

}
