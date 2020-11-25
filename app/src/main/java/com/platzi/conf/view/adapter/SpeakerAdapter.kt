package com.platzi.conf.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.platzi.conf.R
import com.platzi.conf.model.Conference
import com.platzi.conf.model.Speaker

class SpeakerAdapter(
    val speakerListener: SpeakerListener
) : RecyclerView.Adapter<SpeakerAdapter.ViewHolder>() {

    var listSpeakers = ArrayList<Speaker>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(
        R.layout.item_speaker, parent, false))

    override fun getItemCount() = listSpeakers.size

    override fun onBindViewHolder(holder: SpeakerAdapter.ViewHolder, position: Int) {
        val speaker = listSpeakers[position] as Speaker

        Glide.with(holder.itemView.context)
            .load(speaker.image)
            .apply(RequestOptions.circleCropTransform())
            .into(holder.ivSpeakerPhoto)

        holder.tvSpeakerName.text = speaker.name
        holder.tvWorkplaceSpeaker.text = speaker.workplace

        holder.itemView.setOnClickListener {
            speakerListener.onSpeakerClicked(speaker, position)
        }
    }

    fun updateDate(data: List<Speaker>) {
        listSpeakers.clear()
        listSpeakers.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivSpeakerPhoto = itemView.findViewById<ImageView>(R.id.ivPhotoSpeaker)
        val tvSpeakerName = itemView.findViewById<TextView>(R.id.tvSpeakerName)
        val tvWorkplaceSpeaker = itemView.findViewById<TextView>(R.id.tvWorkplaceSpeaker)
    }
}