package com.jorfald.minigolfscorer.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.jorfald.minigolfscorer.MinigolfScorerApp
import com.jorfald.minigolfscorer.R
import com.jorfald.minigolfscorer.viewModel.SplashViewModel

class SplashFragment : Fragment() {
    private val viewModel: SplashViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindObservers()

        viewModel.loadAllGames()
        Toast.makeText(MinigolfScorerApp.application.applicationContext, "Hallo", Toast.LENGTH_SHORT).show()
    }

    private fun bindObservers() {
        viewModel.gamesLoaded.observe(viewLifecycleOwner) { success ->
            if (success) {
                requireActivity().runOnUiThread {
                    findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToFirstFragment())
                }
            }
            //Navigates to next screen when the list of games has been fetched from API.
        }

        viewModel.responseMessage.observe(viewLifecycleOwner) { message ->
            Toast.makeText(MinigolfScorerApp.application.applicationContext, message, Toast.LENGTH_SHORT).show()
        }
    }

}