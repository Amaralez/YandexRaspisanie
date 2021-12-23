package com.bignerdranch.android.yandexraspisanie

import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.yandexraspisanie.responsedata.Schedule


class CustomRecyclerAdapter (private val Schedule: List<Schedule>):
    RecyclerView.Adapter<CustomRecyclerAdapter.MyViewHolder>()
{
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
      var  ydeparture        :TextView? = null
      var  ydescription :TextView? = null
      var  ystops        :TextView? = null

        init {
            ydeparture = itemView.findViewById(R.id.ydeparture)
            ydescription = itemView.findViewById(R.id.ydescription)
            ystops = itemView.findViewById(R.id.ystops)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        //нужно указать идентификатор макета для отдельного элемента списка
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_y, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       //связываем используемые текстовые метки с данными
        holder.ydeparture?.text        = Schedule[position].departure.toString()
        holder.ydescription?.text  = Schedule[position].thread.short_title.toString()
        holder.ystops?.text         = Schedule[position].stops.toString()

        //val tag: String = Items[position].link.toString()
        //holder.textHHLink?.setOnClickListener {
        //    onClickListener.onClick(tag)
        //}

    }

    override fun getItemCount(): Int {
        return Schedule.size
    }



    }