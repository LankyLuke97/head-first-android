package com.studio.guessinggame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.studio.guessinggame.databinding.FragmentGameBinding
import androidx.lifecycle.ViewModelProvider

class GameFragment : Fragment() {
    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: GameViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModel = ViewModelProvider(this)[GameViewModel::class.java]


        viewModel.secretWordDisplay.observe(viewLifecycleOwner, Observer { newWord ->
            binding.word.text = viewModel.secretWordDisplay.value
        })

        viewModel.livesLeft.observe(viewLifecycleOwner, Observer { newLivesLeft->
            binding.lives.text = getString(R.string.you_have_lives_left, newLivesLeft)
        })

        viewModel.incorrectGuesses.observe(viewLifecycleOwner, Observer { guesses ->
            binding.incorrectGuesses.text = getString(R.string.incorrect_guesses, guesses)
        })

        viewModel.gameOver.observe(viewLifecycleOwner, Observer { over ->
            if (over) {
                val action = GameFragmentDirections.actionGameFragmentToResultFragment(viewModel.wonLostMessage())
                view.findNavController().navigate(action)
            }
        })

        binding.guessButton.setOnClickListener {
            viewModel.makeGuess(binding.guess.text.toString().uppercase())
            binding.guess.text = null
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}