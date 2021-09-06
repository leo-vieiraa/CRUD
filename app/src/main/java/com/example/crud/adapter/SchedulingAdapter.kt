package com.example.crud.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.crud.R
import com.example.crud.model.SchedulingPOJO

class SchedulingAdapter : RecyclerView.Adapter<SchedulingViewHolder>(){

    private var listOfScheduling = mutableListOf<SchedulingPOJO>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchedulingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.scheduling_item_fragment, parent, false)
        return SchedulingViewHolder(view)
    }

    override fun onBindViewHolder(holder: SchedulingViewHolder, position: Int) {
        listOfScheduling[position].apply {
            holder.bind(this)
        }
    }

    override fun getItemCount(): Int = listOfScheduling.size

    fun refresh(schedulingPOJO: List<SchedulingPOJO>) {
        listOfScheduling = mutableListOf()
        listOfScheduling.addAll(schedulingPOJO)
        notifyDataSetChanged()
    }
}

class SchedulingViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(schedulingPOJO: SchedulingPOJO) {

    }

}