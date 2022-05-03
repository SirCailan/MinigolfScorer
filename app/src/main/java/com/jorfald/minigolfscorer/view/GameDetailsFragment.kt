package com.jorfald.minigolfscorer.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jorfald.minigolfscorer.MinigolfScorerApp
import com.jorfald.minigolfscorer.R
import com.jorfald.minigolfscorer.model.dataClasses.Game
import com.jorfald.minigolfscorer.viewModel.GamesViewModel

class GameDetailsFragment : Fragment() {
    private val viewModel: GamesViewModel by activityViewModels()

    lateinit var recyclerView: RecyclerView
    lateinit var customAdapter: PlayersAdapter
    lateinit var customLayoutManager: LinearLayoutManager
    lateinit var loader: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        customAdapter = PlayersAdapter(listOf()) { _, _, _ ->

        }

        bindObservers()

        viewModel.fetchPlayersForSelectedGame()

        return inflater.inflate(R.layout.fragment_game_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loader = view.findViewById(R.id.details_loader)
        recyclerView = view.findViewById(R.id.game_details_recycler_view)

        customLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = customLayoutManager

        // TODO: viewModel.fetchPlayersForSelectedGame() and fill recyclerView

        recyclerView.adapter = customAdapter

    }

    private fun bindObservers() {
        viewModel.playerScores.observe(viewLifecycleOwner) { scores ->
            customAdapter.updateData(scores)
            loader.visibility = View.GONE
        }

        viewModel.responseMessage.observe(viewLifecycleOwner) { message ->
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }
    }
}