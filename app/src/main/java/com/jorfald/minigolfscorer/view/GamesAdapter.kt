package com.jorfald.minigolfscorer.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.jorfald.minigolfscorer.R
import com.jorfald.minigolfscorer.model.dataClasses.Game
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("NotifyDataSetChanged")
class GamesAdapter(
    var dataSet: List<Game>,
    val onClickCallBack: (Game) -> Unit
) : RecyclerView.Adapter<GamesAdapter.GamesViewHolder>() {
    inner class GamesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val container: ConstraintLayout = view.findViewById(R.id.game_cell_container)

        val gameName: TextView = view.findViewById(R.id.game_cell_name)
        val timeCreated: TextView = view.findViewById(R.id.game_cell_time_created)
        val active: ImageView = view.findViewById(R.id.game_cell_active)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesViewHolder {
        val cellLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.game_cell_item, parent, false)

        val params: ViewGroup.LayoutParams = cellLayout.layoutParams
        params.width = ViewGroup.LayoutParams.MATCH_PARENT
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT
        cellLayout.layoutParams = params

        return GamesViewHolder(cellLayout)
    }

    override fun onBindViewHolder(holder: GamesViewHolder, position: Int) {
        val game = dataSet[position]

        holder.gameName.text = game.gameName

        val simpleDate = SimpleDateFormat("hh:mm dd/MM/yyyy", Locale.getDefault())
        holder.timeCreated.text = simpleDate.format(game.created)

        holder.container.setOnClickListener {
            onClickCallBack(game)
        }
        //TODO: Fill in data in the cell
        //TODO: Add clickListener to cell
    }

    override fun getItemCount(): Int = dataSet.size

    fun updateData(newList: List<Game>) {
        dataSet = newList
        notifyDataSetChanged()
    }
}
