package dev.joshvocal.wordlecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dev.joshvocal.wordlecompose.ui.game.WordleGameScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val wordList: List<String> = assets.open("word_list.txt")
            .bufferedReader()
            .use { it.readText() }
            .split("\n")
        val wordOfTheDay = wordList.random()

        setContent {
            WordleGameScreen(wordList = wordList, wordOfTheDay = wordOfTheDay)
        }
    }
}
