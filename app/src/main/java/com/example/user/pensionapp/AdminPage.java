package com.example.user.pensionapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class AdminPage extends AppCompatActivity {

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
        setContentView(R.layout.activity_admin_page);

        mAuth=FirebaseAuth.getInstance();
        mAuthListener=new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if (firebaseAuth.getCurrentUser()==null){

                    Intent homeIntent=new Intent(AdminPage.this,HomePageActivity.class);
                    homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(homeIntent);
                }

            }
        };

        mDatabase= FirebaseDatabase.getInstance().getReference().child("Users");



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



        mAuth.addAuthStateListener(mAuthListener);

        FirebaseRecyclerAdapter<Blog,BlogViewHolder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Blog, BlogViewHolder>(
                Blog.class,
                R.layout.blog_row,
                BlogViewHolder.class,
                mDatabase

        ) {
            @Override
            protected void populateViewHolder(BlogViewHolder viewHolder, Blog model, int position) {

                final String post_key=getRef(position).getKey();

                viewHolder.setAccount_no(model.getAccount_no());
                viewHolder.setSurname(model.getSurname());
                viewHolder.setSecond_Name(model.getSecond_Name());
                viewHolder.setGender(model.getGender());
                viewHolder.setImage(getApplicationContext(),model.getImage());
                viewHolder.setGetRelationship2(model.getRelationship2());
                viewHolder.setRelationship1(model.getRelationship1());
                viewHolder.setPhone(model.getPhone());
                viewHolder.setMonthly(model.getMonthly());
                viewHolder.setId(model.getId());
                viewHolder.setFirst_Name(model.getFirst_Name());
                viewHolder.setDependant2phone(model.getDependant2phone());
                viewHolder.setDependant2_name(model.getDependant2_name());
                viewHolder.setDependant1name(model.getDependant1name());
                viewHolder.setDependant1_phone(model.getDependant1_phone());
                viewHolder.setCounty(model.getCounty());
                viewHolder.setBank(model.getBank());
                viewHolder.setAmount(model.getAmount());



                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Toast.makeText(MainActivity.this,post_key,Toast.LENGTH_LONG).show();

                        Intent singleBlogIntent=new Intent(AdminPage.this,AdminPage.class);
                        singleBlogIntent.putExtra("blog_id",post_key);
                        startActivity(singleBlogIntent);

                    }
                });
                viewHolder.mView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        Intent singleBlogIntent=new Intent(AdminPage.this,AdminPage.class);
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
        public void setSurname(String gender){
            TextView post_title=(TextView)mView.findViewById(R.id.surname);
            post_title.setText(gender);
        }

        public void setSecond_Name(String age){
            TextView post_title=(TextView)mView.findViewById(R.id.second_name);
            post_title.setText(age);
        }
        public void setGender(String desc){
            TextView post_desc=(TextView)mView.findViewById(R.id.gender);
            post_desc.setText(desc);
        }

        public void setGetRelationship2(String username){
            TextView post_username=(TextView)mView.findViewById(R.id.relationship2);
            post_username.setText(username);
        }
        public void setRelationship1(String latitude){
            TextView textLat=(TextView)mView.findViewById(R.id.relationship1);
            textLat.setText(latitude);
        }
        public void setPhone(String longitude){
            TextView textLong=(TextView)mView.findViewById(R.id.phone);
            textLong.setText(longitude);
        }
        public void setImage(Context ctx, String image){

            ImageView post_image=(ImageView) mView.findViewById(R.id.post_image);
            Picasso.with(ctx).load(image).into(post_image);

        }

        public void setMonthly(String longitude){
            TextView textLong=(TextView)mView.findViewById(R.id.monthly);
            textLong.setText(longitude);
        }
        public void setId(String longitude){
            TextView textLong=(TextView)mView.findViewById(R.id.id);
            textLong.setText(longitude);
        }
        public void setFirst_Name(String longitude){
            TextView textLong=(TextView)mView.findViewById(R.id.first_name);
            textLong.setText(longitude);
        }
        public void setDependant2phone(String longitude){
            TextView textLong=(TextView)mView.findViewById(R.id.phone2);
            textLong.setText(longitude);
        }
        public void setDependant2_name(String longitude){
            TextView textLong=(TextView)mView.findViewById(R.id.name2);
            textLong.setText(longitude);
        }
        public void setDependant1name(String longitude){
            TextView textLong=(TextView)mView.findViewById(R.id.name1);
            textLong.setText(longitude);
        }
        public void setDependant1_phone(String longitude){
            TextView textLong=(TextView)mView.findViewById(R.id.phone1);
            textLong.setText(longitude);
        }
        public void setCounty(String longitude){
            TextView textLong=(TextView)mView.findViewById(R.id.county);
            textLong.setText(longitude);
        }
        public void setBank(String longitude){
            TextView textLong=(TextView)mView.findViewById(R.id.bank);
            textLong.setText(longitude);
        }
        public void setAmount(String longitude){
            TextView textLong=(TextView)mView.findViewById(R.id.amount);
            textLong.setText(longitude);
        }

    }
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu1, menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if (item.getItemId() == R.id.action_logout) {

            logout();

        }


        if (item.getItemId() == R.id.action_settings) {

            Intent mainIntent = new Intent(AdminPage.this, RequestActivity.class);
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(mainIntent);

        }

        return super.onOptionsItemSelected(item);
    }

    private void logout() {

        mAuth.signOut();

    }
}
