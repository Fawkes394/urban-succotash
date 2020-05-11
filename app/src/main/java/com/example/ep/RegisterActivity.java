package com.example.ep;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private Button Account;
    private EditText InputName, InputPhone, InputPass;
    private ProgressDialog loadingBar;
    public String parentDb ="Users";
    private TextView Seller, NotSeller,Register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Account =(Button) findViewById(R.id.buttonlogseller);
        InputName =(EditText) findViewById(R.id.editname);
        InputPhone =(EditText) findViewById(R.id.phone22);
        InputPass =(EditText) findViewById(R.id.passseller);
        loadingBar = new ProgressDialog(this);
        Register=(TextView) findViewById(R.id.register4);

        Seller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Account.setText("Register for Seller");
                Seller.setVisibility(View.INVISIBLE);
                NotSeller.setVisibility(View.VISIBLE);
                Register.setText("New Seller");

            }
        });

        NotSeller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Account.setText("Register for User");
                Seller.setVisibility(View.VISIBLE);
                NotSeller.setVisibility(View.INVISIBLE);
                Register.setText("New User");

            }
        });



        Account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                CreateAccount();
            }




            private void CreateAccount() {
                parentDb ="Users";

                String name=InputName.getText().toString();
                String phone=InputPhone.getText().toString();
                String pass=InputPass.getText().toString();

                if(TextUtils.isEmpty(name))
                {
                    Toast.makeText(RegisterActivity.this, "Please Enter you Name", Toast.LENGTH_SHORT).show();

                }
                else if (TextUtils.isEmpty(phone))
                {
                    Toast.makeText(RegisterActivity.this, "Please Enter your Phone Number", Toast.LENGTH_SHORT).show();
                }

                else if (TextUtils.isEmpty(pass))
                {
                    Toast.makeText(RegisterActivity.this, "Please Enter you password", Toast.LENGTH_SHORT).show();
                }

                else
                {
                    loadingBar.setTitle("Creating Account");
                    loadingBar.setMessage("Please wait, while we are checking the credentials.");
                    loadingBar.setCanceledOnTouchOutside(false);
                    loadingBar.show();

                    ValidatephoneNumber(name, phone, pass);


                }


            }

            private void ValidatephoneNumber(final String name, final String phone, final String pass) {


                final DatabaseReference RootRef;
                //final DatabaseReference ChildRef;

                RootRef= FirebaseDatabase.getInstance().getReference();
                //ChildRef=RootRef.child("Sellers");

                RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (!(dataSnapshot.child("Users").child(phone).exists()))
                        {
                            HashMap<String, Object> userdataMap = new HashMap<>();
                            userdataMap.put("Phone", phone);
                            userdataMap.put("Password", pass);
                            userdataMap.put("Name", name);

                            RootRef.child("Users").child(phone).updateChildren(userdataMap)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            if (task.isSuccessful())
                                            {
                                                Toast.makeText(RegisterActivity.this, "Your Account has been Successfully created!", Toast.LENGTH_SHORT).show();
                                                loadingBar.dismiss();

                                                Intent intent  =new Intent(RegisterActivity.this, loginActivity.class);
                                                startActivity(intent);
                                            }

                                            else
                                            {
                                                Toast.makeText(RegisterActivity.this, "Network Error: Please try again after some time", Toast.LENGTH_SHORT).show();
                                                loadingBar.dismiss();


                                            }

                                        }
                                    });
                        }

                        else
                        {
                            Toast.makeText(RegisterActivity.this, "An account for number " + phone +  " already exists", Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();
                           // Toast.makeText(RegisterActivity.this, "Please make an account using another number", Toast.LENGTH_SHORT).show();
                            //loadingBar.dismiss();

                            Intent intent  =new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(intent);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });


    }
}

