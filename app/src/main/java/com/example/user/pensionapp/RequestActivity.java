package com.example.user.pensionapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RequestActivity extends AppCompatActivity {
    private RecyclerView mBlogList;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference mDatabaseUsers;
    private boolean mProcessLike=false;
    private DatabaseReference mDatabaseLikes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        mAuth=FirebaseAuth.getInstance();


        mDatabase= FirebaseDatabase.getInstance().getReference().child("Request");



        mDatabase.keepSynced(true);


        mBlogList=(RecyclerView)findViewById(R.id.blog_list);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        mBlogList.setHasFixedSize(true);
        mBlogList.setLayoutManager(new LinearLayoutManager(this));


    }

    @Override
    protected void onStart() {
        super.onStart();

        mDatabase.keepSynced(true);




        FirebaseRecyclerAdapter<Blog1,BlogViewHolder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Blog1, BlogViewHolder>(
                Blog1.class,
                R.layout.blog_row1,
                BlogViewHolder.class,
                mDatabase

        ) {
            @Override
            protected void populateViewHolder(BlogViewHolder viewHolder, Blog1 model, int position) {

                final String post_key=getRef(position).getKey();

                viewHolder.setAccount_no(model.getAccount_no());
                viewHolder.setPhone(model.getPhone());
                viewHolder.setBank(model.getBank());
                viewHolder.setFirst_Name(model.getFirst_Name());
                viewHolder.setDate(model.getDate());
                viewHolder.setSelect(model.getSelection());
                viewHolder.setSurname(model.getSurname());
                viewHolder.setMonthly(model.getMonthly());




                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Toast.makeText(MainActivity.this,post_key,Toast.LENGTH_LONG).show();

                        Intent singleBlogIntent=new Intent(RequestActivity.this,RequestActivity.class);
                        singleBlogIntent.putExtra("blog_id",post_key);
                        startActivity(singleBlogIntent);

                    }
                });
                viewHolder.mView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        Intent singleBlogIntent=new Intent(RequestActivity.this,RequestActivity.class);
                        singleBlogIntent.putExtra("blog_id",post_key);
                        startActivity(singleBlogIntent);
                        return true;

                    }
                });


            }
        };

        mBlogList.setAdapter(firebaseRecyclerAdapter);

    }




    public static class BlogViewHolder extends RecyclerView.ViewHolder{


        View mView;

        FirebaseAuth mAuth;
        public BlogViewHolder(View itemView) {
            super(itemView);

            mView=itemView;

            mAuth=FirebaseAuth.getInstance();


        }
        public void setAccount_no(String gender){
            TextView post_title=(TextView)mView.findViewById(R.id.account_number);
            post_title.setText(gender);
        }
        public void setPhone(String gender){
            TextView post_title=(TextView)mView.findViewById(R.id.m_pesa);
            post_title.setText(gender);
        }

        public void setBank(String age){
            TextView post_title=(TextView)mView.findViewById(R.id.Bank);
            post_title.setText(age);
        }
        public void setFirst_Name(String desc){
            TextView post_desc=(TextView)mView.findViewById(R.id.first_name);
            post_desc.setText(desc);
        }

        public void setDate(String username){
            TextView post_username=(TextView)mView.findViewById(R.id.date);
            post_username.setText(username);
        }
        public void setSelect(String latitude){
            TextView textLat=(TextView)mView.findViewById(R.id.through);
            textLat.setText(latitude);
        }
        public void setSurname(String latitude){
            TextView textLat=(TextView)mView.findViewById(R.id.surname);
            textLat.setText(latitude);
        }
        public void setMonthly(String latitude){
            TextView textLat=(TextView)mView.findViewById(R.id.ammount);
            textLat.setText(latitude);
        }


    }
}
