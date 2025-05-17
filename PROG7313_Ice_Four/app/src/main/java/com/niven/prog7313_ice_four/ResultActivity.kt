package com.niven.prog7313_ice_four

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.niven.prog7313_ice_four.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {


    private lateinit var binding: ActivityResultBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)

        binding = ActivityResultBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val score = intent.getIntExtra("RIGHT_ANSWER_COUNT", 0)
        binding.resultLabel.text = getString(R.string.result_score, score)

        binding.tryAgainBtn.setOnClickListener {

            startActivity(Intent(this@ResultActivity, MainActivity::class.java))

        }


        val output = findViewById(R.id.iv10) as ImageView
        val out = findViewById(R.id.tv10) as TextView

        val outputTwo = findViewById(R.id.iv11) as ImageView

        if (score<7){

            out.setText("   Bear is not happy with result!!! ")
            output.visibility= View.VISIBLE
            outputTwo.visibility= View.INVISIBLE
        }



        else if (score>=7){

            out.setText(" Rabbit is happy with result!!!")
            output.visibility= View.INVISIBLE
            outputTwo.visibility= View.VISIBLE

        }


        showGIF()


        }


    fun showGIF(){

        val imageView:ImageView= findViewById(R.id.iv10)
        Glide.with( this).load(R.drawable.kotlinfail).into(imageView)

        val pass:ImageView= findViewById(R.id.iv11)
        Glide.with(this).load(R.drawable.kotlinpass).into(pass)


    }









}