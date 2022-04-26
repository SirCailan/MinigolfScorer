package com.jorfald.minigolfscorer.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jorfald.minigolfscorer.viewModel.GamesViewModel
import com.jorfald.minigolfscorer.R
import com.jorfald.minigolfscorer.model.dataClasses.Player

class GameDetailsFragment : Fragment() {

    val viewModel: GamesViewModel by activityViewModels()

    //TODO: Create other view variables

    lateinit var recyclerView: RecyclerView
    lateinit var customAdapter: PlayersAdapter
    lateinit var customLayoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO: Initiate views

        // TODO: viewModel.fetchPlayersForSelectedGame() and fill recyclerView
        recyclerView = view.findViewById(R.id.game_details_recycler_view)
        customAdapter = PlayersAdapter(
            listOf(
                Player(
                    "Ole",
                    listOf()
                ),
                Player(
                    "Per",
                    listOf()
                )
            )
        ) { _, _, _ ->

        }
        customLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        recyclerView.adapter = customAdapter
        recyclerView.layoutManager = customLayoutManager
    }
}