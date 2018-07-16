package com.example.fullscreenactivity

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso
import java.io.File
import java.util.ArrayList

class ImageListAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val TAG = ImageListAdapter::class.java.simpleName
    private var mImageList: MutableList<ImageItem> = ArrayList()

    class EmptyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var mImage: ImageView = view.findViewById(R.id.img_image) as ImageView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View

        if (viewType == EMPTY_VIEW) {
            view = LayoutInflater.from(parent.context).inflate(R.layout.empty_view, parent, false)
            return EmptyViewHolder(view)
        }
        view = LayoutInflater.from(parent.context).inflate(R.layout.listitem_image, parent, false)
        return ViewHolder(view)
    }

    /**
     * Replace the contents of a view (invoked by the layout manager)
     * Don't bind anything if holder is not a ViewHolder (it's an EmptyViewHolder)
     *
     * https://www.icon2s.com/32916/android-icons/black-white-android-add-camera/
     * https://www.journaldev.com/13759/android-picasso-tutorial
     *
     */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            val imageItem = getItemAtPosition(position)
            imageItem.resource_id.let {
                Picasso.get()
                        .load(it)
                        .placeholder(R.drawable.placeholder)
                        .resize(200, 200)
                        .centerCrop()
                        .into(holder.mImage)
            }

            holder.mImage.contentDescription = imageItem.title
        }
    }

    public fun getItemAtPosition(position: Int): ImageItem {
        return mImageList[position]
    }


    /**
     * Return the size of the dataset
     * Return 1 if the list is empty in order
     * to make room for the EMPTY_VIEW message
     */
    override fun getItemCount(): Int {
        return if (mImageList.count() > 0) {
            mImageList.count()
        } else {
            1
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (this.mImageList.count() == 0) {
            EMPTY_VIEW
        } else {
            super.getItemViewType(position)
        }
    }

    /**
     * Clear all the photos from the RecyclerView,
     * usually in preparation for redisplaying
     * the list (possibly with different sort criteria).
     */
    fun clear() {
        val size = mImageList.size
        if (size <= 0) {
            return
        }
        for (i in 0 until size)
            mImageList.removeAt(0)
        mImageList.clear()
        notifyItemRangeRemoved(0, size)
    }

    fun addAll(ImageItemList: List<ImageItem>) {
        for (position in ImageItemList.indices) {
            addItem(ImageItemList.get(position), position)
        }
    }

    fun addItem(imgOrchid: ImageItem, position: Int) {
        mImageList.add(position, imgOrchid)
        notifyItemInserted(position)
    }

    /**
     * Add an item to the end of the list
     */
    fun add(imgOrchid: ImageItem) {
        mImageList.add(imgOrchid)
    }

    fun remove(imgOrchid: ImageItem) {
        val position = mImageList.indexOf(imgOrchid)
        mImageList.removeAt(position)
        notifyItemRemoved(position)
    }

    companion object {
        private val EMPTY_VIEW = 10
    }
}
