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

class AllGamesFragment : Fragment() {

    val viewModel: GamesViewModel by activityViewModels()

    lateinit var recyclerView: RecyclerView
    lateinit var customAdapter: GamesAdapter
    lateinit var customLayoutManager: LinearLayoutManager

    // TODO: Create view variables

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_all_games, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO: Initiate views

        // TODO: viewModel.getAllGames() and fill recyclerView
    }

    fun bindObservers() {
        //TODO: Bind all necessary liveData observers
    }
}