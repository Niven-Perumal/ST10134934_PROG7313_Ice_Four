package com.niven.prog7313_ice_four

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.niven.prog7313_ice_four.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var rightAnswer: String? = null
    private var rightAnswerCount = 0
    private var quizCount = 1
    private val QUIZ_COUNT = 10


    private val quizData = mutableListOf(
        //question,answer,choice 1, choice 2, choice 3
        mutableListOf("What noise does the dog make?","Bark","Moo","Meow","squawk"),
        mutableListOf("What noise does the cat make?","Meow","Moo","Bark","squawk"),
        mutableListOf("What noise does the bird make?","Squawk","Moo","Meow","Bark"),
        mutableListOf("What noise does the cow make?","Moo","Bark","Meow","squawk"),
        mutableListOf("What noise does the pig make?","Oink","Meow","Bark","Moo"),
        mutableListOf("What animal can fly?","Bird","Cow","Dog","Cat"),
        mutableListOf("What animal can breath under water?","Fish","Cat","Cow","Dog"),
        mutableListOf("Which animal is the tallest in the world?","Giraffe","Cat","Dog","Cow"),
        mutableListOf("What animal is a joey?","Baby kangaroo","Dog","Horse","Zebra"),
        mutableListOf("What is a group of lions named?","A pride","A tribe","A flock","A clan")

    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)

// shuffle quiz
        quizData.shuffle()

        showNextQuiz()


    }


    fun showNextQuiz(){
        //Update countLabel
        binding.countLabel.text = getString(R.string.count_label, quizCount)

//pick one quiz set
        val quiz = quizData[0]


        //set question & right answer
        binding.questionLabel.text = quiz[0]

        rightAnswer = quiz[1]

// remove country from quiz
        quiz.removeAt(0)

        //shuffle answer & choices
        quiz.shuffle()


        //set choices
        binding.answerBtn1.text = quiz[0]
        binding.answerBtn2.text = quiz[1]
        binding.answerBtn3.text = quiz[2]
        binding.answerBtn4.text = quiz[3]

        //remove this quiz from quizData
        quizData.removeAt(0)

    }


    fun checkAnswer( view: View  ){
//Get pushed button
        val answerBtn: Button = findViewById(view.id)
        val btnText= answerBtn.text.toString()


        val alertTitle: String
        if (btnText==rightAnswer){
            //correct
            alertTitle = "Correct "
            rightAnswerCount++

        }
        else {
//wrong
            alertTitle = "Wrong"

        }

// create Dialoge
        AlertDialog.Builder(this)
            .setTitle(alertTitle)
            .setMessage("Answer: $rightAnswer")
            .setPositiveButton("ok ") {dialogInterface, i->
                checkQuizCount()

            }

            .setCancelable(false)
            .show()

    }


    fun checkQuizCount(){
        if (quizCount == QUIZ_COUNT ) {
            // show result

            val intent = Intent(this@MainActivity, ResultActivity::class.java)
            intent.putExtra("RIGHT_ANSWER_COUNT", rightAnswerCount)
            startActivity(intent)


        } else {
            quizCount++
            showNextQuiz()

        }

    }



}