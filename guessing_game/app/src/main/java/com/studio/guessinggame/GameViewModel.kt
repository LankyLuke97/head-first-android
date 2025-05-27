package com.studio.guessinggame

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    private val words = listOf("Android", "Activity", "Fragment")
    private val secretWord = words.random().uppercase()
    private var correctGuesses = ""
    private val _secretWordDisplay = MutableLiveData<String>()
    val secretWordDisplay: LiveData<String> get() = _secretWordDisplay
    private val _incorrectGuesses = MutableLiveData<String>("")
    val incorrectGuesses: LiveData<String> get() = _incorrectGuesses
    private val _livesLeft = MutableLiveData<Int>(8)
    val livesLeft: LiveData<Int> get() = _livesLeft
    private val _gameOver = MutableLiveData<Boolean>(false)
    val gameOver: LiveData<Boolean> get() = _gameOver

    init {
        _secretWordDisplay.value = deriveSecretWordDisplay()
    }

    private fun checkLetter(str: String) : String =
        when(correctGuesses.contains(str)) {
            true -> str
            false -> "_"
        }

    private fun deriveSecretWordDisplay() : String {
        var display = ""
        secretWord.forEach {
            display += checkLetter(it.toString())
        }
        return display
    }

    private fun isWon() = secretWord.equals(_secretWordDisplay.value, true)

    private fun isLost() = (_livesLeft.value ?: 0) <= 0

    fun makeGuess(guess: String) {
        if (guess.length == 1) {
            if (secretWord.contains(guess)) {
                correctGuesses += guess
                _secretWordDisplay.value = deriveSecretWordDisplay()
            } else {
                _incorrectGuesses.value = _incorrectGuesses.value?.plus("$guess ")
                _livesLeft.value = _livesLeft.value?.minus(1)
            }
            if (isWon() || isLost()) _gameOver.value = true
        }
    }

    fun wonLostMessage() : String {
        var message = ""
        if (isWon()) message = "You won!"
        else if (isLost()) message = "You lost!"
        message += " The word was $secretWord."
        return message
    }
}