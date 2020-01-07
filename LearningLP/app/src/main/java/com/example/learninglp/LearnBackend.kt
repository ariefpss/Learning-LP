package com.example.learninglp

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_learn_backend.view.*
import kotlinx.android.synthetic.main.viewlearn.view.*


class LearnBackend : AppCompatActivity() {
    lateinit var mRecyclerView : RecyclerView
    lateinit var mDatabase : DatabaseReference
    lateinit var FirebaseRecyclerAdapter : FirebaseRecyclerAdapter<DtLearn, MtrViewHolder>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn_backend)

        mDatabase = FirebaseDatabase.getInstance().getReference("learnbackend")
        mRecyclerView = findViewById(R.id.learn_backend)

        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.setLayoutManager(LinearLayoutManager(this))


        val dtlearn = intent.getStringExtra("languagebackend")

        if (dtlearn == "NodeJS"){
            loadFirebaseData(dtlearn)
        }else if (dtlearn == "PHP"){
            loadFirebaseData(dtlearn)
        }else if (dtlearn == "Python"){
            loadFirebaseData(dtlearn)
        }else if (dtlearn == "Ruby"){
            loadFirebaseData(dtlearn)
        }else if (dtlearn == "Java"){
            loadFirebaseData(dtlearn)
        }


    }

    private fun loadFirebaseData(dtlearn: String?) {

        val firebaseSearchQuery = mDatabase.orderByChild("language").equalTo(dtlearn)

        FirebaseRecyclerAdapter = object : FirebaseRecyclerAdapter<DtLearn, MtrViewHolder>(
           DtLearn::class.java,
           R.layout.viewlearn,
           MtrViewHolder::class.java,
           firebaseSearchQuery

        ) {
           override fun populateViewHolder(viewHolder: MtrViewHolder, model: DtLearn?, position: Int) {

              viewHolder.mview.definisi.setText(model?.definisi)
              Picasso.with(applicationContext).load(model?.img).into(viewHolder.mview.imglearning)

           }

        }

        mRecyclerView.adapter = FirebaseRecyclerAdapter


    }
}
