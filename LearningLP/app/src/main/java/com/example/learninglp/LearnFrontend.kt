package com.example.learninglp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.viewlearn.view.*

class LearnFrontend : AppCompatActivity() {

    lateinit var mRecyclerView : RecyclerView
    lateinit var mDatabase : DatabaseReference
    lateinit var FirebaseRecyclerAdapter : FirebaseRecyclerAdapter<DtLearn, MtrViewHolder>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn_frontend)

        mDatabase = FirebaseDatabase.getInstance().getReference("learnfrontend")
        mRecyclerView = findViewById(R.id.learn_frontend)

        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.setLayoutManager(LinearLayoutManager(this))


        val dtlearn = intent.getStringExtra("languagefrontend")

        if (dtlearn == "CSS"){
            loadFirebaseData(dtlearn)
        }else if (dtlearn == "Django"){
            loadFirebaseData(dtlearn)
        }else if (dtlearn == "HTML"){
            loadFirebaseData(dtlearn)
        }else if (dtlearn == "Angular"){
            loadFirebaseData(dtlearn)
        }else if (dtlearn == "Javascript"){
            loadFirebaseData(dtlearn)
        }
    }

    private fun loadFirebaseData(dtlearn: String) {
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
