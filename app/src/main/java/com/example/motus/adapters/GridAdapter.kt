package com.example.motus.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.motus.R

class GridAdapter(context: Context, private var data: MutableList<MutableList<Pair<Char, Int>>>) : BaseAdapter() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    fun updateData(newData: MutableList<MutableList<Pair<Char, Int>>>) {
        this.data = newData
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return data.size * data[0].size
    }

    override fun getItem(position: Int): Pair<Char, Int> {
        val row = position / data[0].size
        val col = position % data[0].size
        return data[row][col]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("ResourceAsColor")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val vh: ViewHolder

        if (convertView == null) {
            view = inflater.inflate(R.layout.grid_item, parent, false)
            vh = ViewHolder(view)
            view.tag = vh
        } else {
            view = convertView
            vh = view.tag as ViewHolder
        }

        vh.textView.text = getItem(position).first.toString()

        when(getItem(position).second){
            0 -> {
                vh.textView.setBackgroundColor(Color.TRANSPARENT)
            }
            1 -> {
                vh.textView.setBackgroundColor(Color.rgb(176, 224, 230))
            }
            2 -> {
                vh.textView.setBackgroundColor(Color.rgb(255, 215, 0))
            }
            3 -> {
                vh.textView.setBackgroundColor(Color.rgb(220, 20, 60))
            }
        }
        return view
    }

    private class ViewHolder(view: View) {
        val textView: TextView = view.findViewById(R.id.grid_item_text)
    }
}
