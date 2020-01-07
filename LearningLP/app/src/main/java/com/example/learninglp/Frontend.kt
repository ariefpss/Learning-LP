package com.example.learninglp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_list.view.*

class Frontend : AppCompatActivity() {

    lateinit var mRecyclerView : RecyclerView
    lateinit var mDatabase : DatabaseReference
    lateinit var FirebaseRecyclerAdapter : FirebaseRecyclerAdapter<DtMateri, MtrViewHolder>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frontend)

        mRecyclerView = findViewById(R.id.frontend_list)
        mDatabase = FirebaseDatabase.getInstance().getReference("mtfrontend")


        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.setLayoutManager(LinearLayoutManager(this))

        logRecycleView()
    }

    private fun logRecycleView() {
        FirebaseRecyclerAdapter = object : FirebaseRecyclerAdapter<DtMateri, MtrViewHolder>(

            DtMateri::class.java,
            R.layout.view_list,
            MtrViewHolder::class.java,
            mDatabase
        ) {
            override fun populateViewHolder(viewHolder: MtrViewHolder, model: DtMateri?, position: Int) {


                viewHolder.mview.desc.setText(model?.desc)
                viewHolder.mview.language.setText(model?.language)
                Picasso.with(applicationContext).load(model?.img).into(viewHolder.mview.imgList)

                viewHolder.mview.setOnClickListener{
                    val intent = Intent(viewHolder.mview.context, LearnFrontend::class.java)
                    intent.putExtra("languagefrontend", model?.language)
                    viewHolder.mview.context.startActivity(intent)
                }

            }
        }

        mRecyclerView.adapter = FirebaseRecyclerAdapter
    }
}
