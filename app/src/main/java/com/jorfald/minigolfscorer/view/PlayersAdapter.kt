package com.jorfald.minigolfscorer.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.jorfald.minigolfscorer.R
import com.jorfald.minigolfscorer.model.dataClasses.PlayerScores

@SuppressLint("NotifyDataSetChanged")
class PlayersAdapter(
    var dataSet: List<PlayerScores>,
    var onClickCallBack: (PlayerScores, Int, Int) -> Unit
) : RecyclerView.Adapter<PlayersAdapter.PlayerViewHolder>() {

    inner class PlayerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val container: ConstraintLayout = view.findViewById(R.id.player_cell_container)
        val playerName: TextView = view.findViewById(R.id.player_name_text)
        val scoreContainer: LinearLayout = view.findViewById(R.id.player_score_container)

        //TODO: Instantiate other views
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val cellLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.player_cell_item, parent, false)

        val params: ViewGroup.LayoutParams = cellLayout.layoutParams
        params.width = ViewGroup.LayoutParams.WRAP_CONTENT
        params.height = ViewGroup.LayoutParams.MATCH_PARENT
        cellLayout.layoutParams = params

        return PlayerViewHolder(cellLayout)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player = dataSet[position]

        //TODO: Fill in data in views
        holder.playerName.text = "Per\n100 slag"

        holder.scoreContainer.removeAllViews()
        for (i in 1..18) {
            val holeTextView = TextView(holder.container.context)

            //TODO: Get the correct score in the text
            var holeText = "Hull $i\n"
            holeText += "Slag: 0"

            holeTextView.text = holeText

            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(50, 0, 50, 50)
            holeTextView.layoutParams = params
            holeTextView.background =
                ContextCompat.getDrawable(holder.container.context, R.drawable.hole_background)

            //TODO: Set clickListener on hole to be able to update score

            holder.scoreContainer.addView(holeTextView)
        }
    }

    override fun getItemCount(): Int = dataSet.size

    fun updateData(newList: List<PlayerScores>) {
        dataSet = newList
        notifyDataSetChanged()
    }
}