package com.example.user.pensionapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
 import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseUser mCurrentUser;
    private Button mCalender,mSubmit;
    static final int DATE_DIALOG_ID=0;
    private int mYear,mMonth,mDay;
    private DatabaseReference mDatabase;
    private TextView mUser1;
    private DatabaseReference mDatabaseUser1;
    private String mPost_key=null;
    private DatabaseReference mDatabaseUser;
    private EditText mDate;
    private Spinner mSelect1;
    private ProgressDialog mProgress;
    private TextView link1,link2,link3,link4,link5,link6,link7,link8,link9;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth= FirebaseAuth.getInstance();
        mCurrentUser=mAuth.getCurrentUser();
        mDatabaseUser= FirebaseDatabase.getInstance().getReference().child("Users").child(mCurrentUser.getUid());
        mDatabaseUser1= FirebaseDatabase.getInstance().getReference().child("Users");
        mDatabase= FirebaseDatabase.getInstance().getReference().child("Request");

     /*   mPost_key=getIntent().getExtras().getString("blog_id");
        mDatabaseUser1.child(mPost_key).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mDatabaseUser1.keepSynced(true);
                String post_title=(String)dataSnapshot.child("name").getValue();

                String post_uid=(String)dataSnapshot.child("uid").getValue();

                mUser1.setText("Hello  "+post_title);

                if (mAuth.getCurrentUser().getUid().equals(post_uid)){


                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {



            }
        });
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        */

        mSelect1=(Spinner)findViewById(R.id.Select1);
        String [] relationship={"M-Pesa","Bank Account"};
        ArrayAdapter adapter1=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,relationship);
        mSelect1.setAdapter(adapter1);

        mCalender=(Button) findViewById(R.id.calender);
        mProgress=new ProgressDialog(this);
        mSubmit=(Button)findViewById(R.id.submit);
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                post();
            }
        });
        mDate=(EditText)findViewById(R.id.date);
        link1=(TextView)findViewById(R.id.link1);
        String linktext="<a href='http://www.rba.go.ke/index.php/en/#'>1.RBA</a>";
        link1.setText(Html.fromHtml(linktext));
        link1.setMovementMethod(LinkMovementMethod.getInstance());
        link2=(TextView)findViewById(R.id.link2);
        String linktext2="<a href='http://www.oldmutual.co.ke/home'>2.OLD MUTUAL</a>";
        link2.setText(Html.fromHtml(linktext2));
        link2.setMovementMethod(LinkMovementMethod.getInstance());
        link3=(TextView)findViewById(R.id.link3);
        String linktext3="<a href='https://jubileeinsure.com/index.php/personal_pension'>3.JUBILEE</a>";
        link3.setText(Html.fromHtml(linktext3));
        link3.setMovementMethod(LinkMovementMethod.getInstance());
        link4=(TextView)findViewById(R.id.link4);
        link5=(TextView)findViewById(R.id.link5);
        link6=(TextView)findViewById(R.id.link6);
        link7=(TextView)findViewById(R.id.link7);
        link8=(TextView)findViewById(R.id.link8);
        link9=(TextView)findViewById(R.id.link9);
        link3=(TextView)findViewById(R.id.link3);
        String linktext4="<a href='http://pioneerassurance.co.ke/individual-pension-plan/'>4.PIONEER</a>";
        link4.setText(Html.fromHtml(linktext4));
        link4.setMovementMethod(LinkMovementMethod.getInstance());
        link3=(TextView)findViewById(R.id.link3);
        String linktext5="<a href='https://www.britam.com/pension-insurance/individual/individual-pension-plan'>5.BRITAM</a>";
        link5.setText(Html.fromHtml(linktext5));
        link5.setMovementMethod(LinkMovementMethod.getInstance());
        String linktext6="<a href='https://www.icealion.com/product/personal-retirement-scheme-prs'>6.ICEA</a>";
        link6.setText(Html.fromHtml(linktext6));
        link6.setMovementMethod(LinkMovementMethod.getInstance());
        String linktext7="<a href='https://www.madison.co.ke/madison-flex-loan/personal-pension-mipp.html'>7.MADSON</a>";
        link7.setText(Html.fromHtml(linktext7));
        link7.setMovementMethod(LinkMovementMethod.getInstance());
        String linktext8="<a href='https://cic.co.ke/product/cic-jipange-pension-plan/'>8.CIC</a>";
        link8.setText(Html.fromHtml(linktext8));
        link8.setMovementMethod(LinkMovementMethod.getInstance());
        String linktext9="<a href='http://www.octagonafrica.com/octagon-pension-services-ltd/'>9.OCTAGON</a>";
        link9.setText(Html.fromHtml(linktext9));
        link9.setMovementMethod(LinkMovementMethod.getInstance());

        mCalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               showDialog(DATE_DIALOG_ID);
            }
        });
        Calendar c=Calendar.getInstance();
        mYear=c.get(Calendar.YEAR);
        mMonth=c.get(Calendar.MONTH);
        mDay=c.get(Calendar.DAY_OF_MONTH);



        mAuthListener=new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if (firebaseAuth.getCurrentUser()==null){

                    Intent homeIntent=new Intent(MainActivity.this,HomePageActivity.class);
                    homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(homeIntent);
                }

            }
        };
        mAuth= FirebaseAuth.getInstance();
    }
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    private void post() {

        mProgress.setMessage("sending request...");
        mProgress.show();
        final String relationship = mSelect1.getSelectedItem().toString().trim();
        final String select=mDate.getText().toString().trim();

        if (!TextUtils.isEmpty(relationship)&&!TextUtils.isEmpty(select)) {


            final DatabaseReference newPost = mDatabase.push();


            mDatabaseUser.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    newPost.child("selection").setValue("Through:"+relationship);
                    newPost.child("Date").setValue("Date:"+select);


                    newPost.child("uid").setValue(mCurrentUser.getUid());
                    newPost.child("first_Name").setValue("First:" + dataSnapshot.child("first_Name").getValue()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {

                            } else {
                                Toast.makeText(MainActivity.this, "Please fill all the spaces or check your internet connection...", Toast.LENGTH_LONG).show();

                            }
                        }
                    });
                    newPost.child("surname").setValue("Surname:"+dataSnapshot.child("surname").getValue()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {

                            } else {
                                Toast.makeText(MainActivity.this, "Please fill all the spaces or check your internet connection...", Toast.LENGTH_LONG).show();

                            }
                        }
                    });
                    newPost.child("phone").setValue("M-pesa No:" + dataSnapshot.child("phone").getValue()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {

                            } else {
                                Toast.makeText(MainActivity.this, "Please fill all the spaces or check your internet connection...", Toast.LENGTH_LONG).show();

                            }
                        }
                    });
                    newPost.child("monthly").setValue("Amount:" + dataSnapshot.child("monthly").getValue()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {

                            } else {
                                Toast.makeText(MainActivity.this, "Please fill all the spaces or check your internet connection...", Toast.LENGTH_LONG).show();

                            }
                        }
                    });
                    newPost.child("bank").setValue("Bank:" + dataSnapshot.child("bank").getValue()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {

                            } else {
                                Toast.makeText(MainActivity.this, "Please fill all the spaces or check your internet connection...", Toast.LENGTH_LONG).show();

                            }
                        }
                    });
                    newPost.child("account_no").setValue("Account No:" + dataSnapshot.child("account_no").getValue()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {

                            } else {
                                Toast.makeText(MainActivity.this, "Please fill all the spaces or check your internet connection...", Toast.LENGTH_LONG).show();

                            }
                        }
                    });

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            mProgress.dismiss();
            Toast.makeText(MainActivity.this, "Your Data Has been sent", Toast.LENGTH_LONG).show();

            Intent mainIntent = new Intent(MainActivity.this, MainActivity.class);
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(mainIntent);

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);

        mAuth= FirebaseAuth.getInstance();

    }
    protected Dialog onCreateDialog(int id){
        switch (id){
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this,
                        mDateSetListener,mYear,mMonth,mDay);
        }
        return null;
    }
    private DatePickerDialog.OnDateSetListener mDateSetListener= new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

            mYear=i;
            mMonth=i1;
            mDay=i2;
            mDate.setText(new StringBuilder().append(mDay).append("/").append(mMonth+1).append("/").append(mYear));

        }
    };
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if (item.getItemId() == R.id.action_logout) {

            logout();

        }


        if (item.getItemId() == R.id.action_settings) {

            Intent mainIntent = new Intent(MainActivity.this, HelpActivity.class);
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(mainIntent);

        }
        if (item.getItemId() == R.id.home) {

            Intent mainIntent = new Intent(MainActivity.this, HomePageActivity.class);
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(mainIntent);

        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {

        mAuth.signOut();

    }
}
