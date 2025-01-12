package com.jorfald.minigolfscorer.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.jorfald.minigolfscorer.viewModel.GamesViewModel
import com.jorfald.minigolfscorer.R
import com.jorfald.minigolfscorer.model.dataClasses.Game

class AllGamesFragment : Fragment() {

    private val viewModel: GamesViewModel by activityViewModels()

    lateinit var recyclerView: RecyclerView
    lateinit var customAdapter: GamesAdapter
    lateinit var customLayoutManager: LinearLayoutManager
    lateinit var loader: ProgressBar
    lateinit var createButton: FloatingActionButton
    lateinit var refreshButton: FloatingActionButton

    // TODO: Create view variables

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        customAdapter = GamesAdapter(listOf()) { game ->
            viewModel.selectGame(game)
            findNavController().navigate(AllGamesFragmentDirections.actionFirstFragmentToSecondFragment())
        }
        viewModel.fetchAllGames()
        //Gets all saved games from database -> updates allGames livedata.

        return inflater.inflate(R.layout.fragment_all_games, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loader = view.findViewById(R.id.games_loader)
        createButton = view.findViewById(R.id.games_create_game_button)
        refreshButton = view.findViewById(R.id.games_refresh_dataset_button)

        bindObservers()

        bindButtons()

        recyclerView = view.findViewById(R.id.games_all_recyclerview)
        customLayoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = customLayoutManager

        recyclerView.adapter = customAdapter

        // TODO: viewModel.getAllGames() and fill recyclerView
    }

    private fun bindObservers() {
        viewModel.allGames.observe(viewLifecycleOwner) { games ->
            customAdapter.updateData(games)
            loader.visibility = View.GONE
            //Hides the progress bar
        }
    }

    private fun bindButtons() {
        createButton.setOnClickListener {
            findNavController().navigate(AllGamesFragmentDirections.actionFirstFragmentToCreateGameFragment())
        }

        refreshButton.setOnClickListener {
            viewModel.fetchAllGames()
        }
    }
}