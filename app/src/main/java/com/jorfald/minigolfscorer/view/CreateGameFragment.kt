package com.jorfald.minigolfscorer.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.jorfald.minigolfscorer.R
import com.jorfald.minigolfscorer.viewModel.GamesViewModel


class CreateGameFragment : Fragment() {
    private val viewModel: GamesViewModel by activityViewModels()

    lateinit var gameNameInput: EditText
    lateinit var playerNameInput: EditText
    lateinit var addPlayerButton: Button
    lateinit var playerListView: ListView
    lateinit var createGameButton: Button

    private val playerList: MutableList<String> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gameNameInput = view.findViewById(R.id.create_input_game_name)
        playerNameInput = view.findViewById(R.id.create_input_player_name)
        addPlayerButton = view.findViewById(R.id.create_add_player_button)
        playerListView = view.findViewById(R.id.create_player_list_layout)
        createGameButton = view.findViewById(R.id.create_game_button)

        bindButtons()
        bindObservers()
    }

    private fun bindButtons() {
        addPlayerButton.setOnClickListener {

            playerList.add(playerNameInput.text.toString())

        }

        createGameButton.setOnClickListener {
            if (gameNameInput.text.isNotEmpty() && playerList.isNotEmpty()) {

                viewModel.createNewGame(gameNameInput.text.toString(), playerList)
            } else {
                Toast.makeText(requireContext(), "Noe er feil.", Toast.LENGTH_SHORT).show()
            }
        }


    }

    private fun bindObservers() {
        viewModel.createGameSuccess.observe(viewLifecycleOwner) { success ->
            if (!success) {
                Toast.makeText(requireContext(), "Kunne ikke skape spill.", Toast.LENGTH_SHORT).show()
            }
        }
    }

}