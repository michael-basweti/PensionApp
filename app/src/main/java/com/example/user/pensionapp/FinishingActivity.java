package com.example.user.pensionapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FinishingActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private ProgressDialog mProgress;
    private DatabaseReference mDatabaseUsers;
    private Button mNext;
    private Spinner spinner2,spinner3;
    private EditText phone,account,idno,monthly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finishing);
        mAuth= FirebaseAuth.getInstance();


        mDatabaseUsers= FirebaseDatabase.getInstance().getReference().child("Users");
        mProgress=new ProgressDialog(this);

        spinner3=(Spinner)findViewById(R.id.spinner2);
        String [] banks={"ABC","Bank of Africa","Bank of Baroda","Bank of India","Barclays Bank of Kenya","citibank"
        ,"Commercial Bank of Kenya","Consolidated Bank of Kenya","Cooperative Bank of Kenya","Credit Bank","Diamond Trust Bank"
        ,"Ecobank","Equity Bank","Family Bank","KCB","I&M Bank","NIC Bank","National Bank Of Kenya","Stanbic Bank Kenya","Standard Chartered"};
        ArrayAdapter adapter2=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,banks);
        spinner3.setAdapter(adapter2);
        mNext=(Button) findViewById(R.id.next);
        phone=(EditText)findViewById(R.id.phone);
        account=(EditText)findViewById(R.id.account);
        idno=(EditText)findViewById(R.id.idno);
        monthly=(EditText)findViewById(R.id.monthly);


        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startSetupAccount();

            }
        });


    }


    private void startSetupAccount() {

        final String mphone = phone.getText().toString().trim();
        final String mmonthly=monthly.getText().toString().trim();
        final String maccount = account.getText().toString().trim();
        final String id = idno.getText().toString().trim();
       // final String relationship = spinner2.getSelectedItem().toString().trim();
        final String banks= spinner3.getSelectedItem().toString().trim();
        final String user_id=mAuth.getCurrentUser().getUid();
        if (!TextUtils.isEmpty(maccount)&&!TextUtils.isEmpty(mmonthly)&&!TextUtils.isEmpty(mphone)&&!TextUtils.isEmpty(id)
        &&!TextUtils.isEmpty(banks)){
            mProgress.setMessage("Finishing Setup");
            mProgress.show();



                    mDatabaseUsers.child(user_id).child("bank").setValue(banks);
                    mDatabaseUsers.child(user_id).child("id").setValue("ID:"+id);
                    mDatabaseUsers.child(user_id).child("account_no").setValue(maccount);
                    //mDatabaseUsers.child(user_id).child("select").setValue(relationship);
                    mDatabaseUsers.child(user_id).child("monthly").setValue("Monthly Ksh:"+mmonthly);
                    mDatabaseUsers.child(user_id).child("phone").setValue(mphone);

                    mProgress.dismiss();

                    Intent mainIntent = new Intent(FinishingActivity.this,DependantsActivity.class);
                    mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(mainIntent);









    }
    }
}
