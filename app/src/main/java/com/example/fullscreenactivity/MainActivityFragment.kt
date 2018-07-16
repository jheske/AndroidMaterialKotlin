package com.example.fullscreenactivity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.*
import kotlinx.android.synthetic.main.fragment_activity_main.*
import java.io.*
import kotlin.collections.ArrayList

class MainActivityFragment : Fragment() {

    private val TAG = MainActivityFragment::class.java.simpleName
    // TODO: Rename and change types of parameters
    private lateinit var mContext: Context
    private lateinit var mImageListAdapter: ImageListAdapter
    private lateinit var mImageStorageDir: File
    private lateinit var mCurrentImagePath: String
    private var mImageList: MutableList<ImageItem> = ArrayList()

    var mNickname: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_activity_main,
                container, false)
        return view
    }

    override fun onResume() {
        super.onResume()
        setupRecyclerView()
        displayImageItems()
    }

    private fun setupRecyclerView() {
        rv_images.setHasFixedSize(true)
        rv_images.layoutManager = LinearLayoutManager(mContext)
        rv_images.setNestedScrollingEnabled(false)
        mImageListAdapter = ImageListAdapter(mContext as AppCompatActivity)
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
        mImageListAdapter.add(ImageItem("Oncidium Orchid",R.drawable.placeholder))
        mImageListAdapter.add(ImageItem("Hello Dixie",R.drawable.hello_dixie))
        mImageListAdapter.add(ImageItem("Mocha",R.drawable.mocha))
        mImageListAdapter.add(ImageItem("Mocha",R.drawable.charlie))
        //mImageListAdapter.addAll(mImageList)
    }

    /**
     * This is called before onCreate() so save context
     */
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
}
