package com.example.user.pensionapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DependantsActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private ProgressDialog mProgress;
    private DatabaseReference mDatabaseUsers;
    private Spinner mspinner4,mspinner5;
    private EditText phone1,phone2,name1,name2;
    private Button finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dependants);

        mAuth= FirebaseAuth.getInstance();


        mDatabaseUsers= FirebaseDatabase.getInstance().getReference().child("Users");
        mProgress=new ProgressDialog(this);
        mspinner4=(Spinner)findViewById(R.id.spinner4);
        String [] relationship1={"Wife","Husband","Daughter","Son","Sister","Brother"};
        ArrayAdapter adapter1=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,relationship1);
        mspinner4.setAdapter(adapter1);
        mspinner5=(Spinner)findViewById(R.id.spinner5);
        String [] relationship2={"Wife","Husband","Daughter","Son","Sister","Brother"};
        ArrayAdapter adapter2=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,relationship2);
        mspinner5.setAdapter(adapter2);
        finish=(Button) findViewById(R.id.finish);
        phone1=(EditText)findViewById(R.id.phone1);
        phone2=(EditText)findViewById(R.id.phone2);
        name1=(EditText)findViewById(R.id.name1);
       name2=(EditText)findViewById(R.id.name2);


        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startSetupAccount();

            }
        });


    }


    private void startSetupAccount() {

        final String mname1 = name1.getText().toString().trim();
        final String mname2=name2.getText().toString().trim();
        final String mphone2 = phone2.getText().toString().trim();
        final String mphone1 = phone1.getText().toString().trim();
        final String relationship1 = mspinner4.getSelectedItem().toString().trim();
        final String relationship2= mspinner5.getSelectedItem().toString().trim();
        final String user_id=mAuth.getCurrentUser().getUid();
        if (!TextUtils.isEmpty(mname1)&&!TextUtils.isEmpty(mname2)&&!TextUtils.isEmpty(mphone1)&&!TextUtils.isEmpty(mphone2)
                &&!TextUtils.isEmpty(relationship1)&&!TextUtils.isEmpty(relationship2)){
            mProgress.setMessage("Finishing Setup");
            mProgress.show();




            mDatabaseUsers.child(user_id).child("relationship1").setValue(relationship1);
            mDatabaseUsers.child(user_id).child("relationship2").setValue(relationship2);
            mDatabaseUsers.child(user_id).child("dependant1_phone").setValue(mphone1);
            mDatabaseUsers.child(user_id).child("dependant2phone").setValue(mphone2);
            mDatabaseUsers.child(user_id).child("dependant1name").setValue(mname1);
            mDatabaseUsers.child(user_id).child("dependant2_name").setValue(mname2);

            mProgress.dismiss();

            Intent mainIntent = new Intent(DependantsActivity.this,MainActivity.class);
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(mainIntent);









        }
    }
}
