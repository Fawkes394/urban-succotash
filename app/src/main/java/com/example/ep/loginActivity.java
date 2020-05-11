package com.example.ep;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ep.Model.Users;
import com.example.ep.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class loginActivity extends AppCompatActivity {

    private Button Login;
    private EditText Number, Pass23;
    private ProgressDialog loadingBar4;
    private CheckBox checkBoxRemMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Login=(Button) findViewById(R.id.buttonlog1);
        Number =(EditText) findViewById(R.id.phone22);
        Pass23 =(EditText) findViewById(R.id.password);
        loadingBar4 = new ProgressDialog(this);
        checkBoxRemMe = (CheckBox) findViewById(R.id.checkBoxlog1);
        Paper.init(this);


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginuser();
            }
        });
    }

    private void loginuser() {

        String phone=Number.getText().toString();
        String password=Pass23.getText().toString();


         if (TextUtils.isEmpty(phone))
        {
            Toast.makeText(loginActivity.this, "Please Enter your Phone Number", Toast.LENGTH_SHORT).show();
        }

        else if (TextUtils.isEmpty(password))
        {
            Toast.makeText(loginActivity.this, "Please Enter you password", Toast.LENGTH_SHORT).show();
        }

        else
         {
             loadingBar4.setTitle("Logging In");
             loadingBar4.setMessage("Please wait, while we are checking the credentials.");
             loadingBar4.setCanceledOnTouchOutside(false);
             loadingBar4.show();

             Allow(phone, password);
         }
    }

    private void Allow(final String phone, final String password) {

        if (checkBoxRemMe.isChecked())
        {
            Paper.book().write(Prevalent.UserPhoneKey1, phone);
            Paper.book().write(Prevalent.UserPasswordKey1, password);

        }

        final DatabaseReference RootRef;
        RootRef= FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.child("Users").child(phone).exists())
                {
                    Users usersData = dataSnapshot.child("Users").child(phone).getValue(Users.class);

                    if (usersData.getPhone().equals(phone))
                    {
                        if (usersData.getPassword().equals(password))
                        {
                            Toast.makeText(loginActivity.this, "Welcome, you have logged in successfully", Toast.LENGTH_SHORT).show();
                            loadingBar4.dismiss();
                            Intent intent  =new Intent(loginActivity.this, HomeActivity.class);
                            Prevalent.CurrentOnlineUser = usersData;
                            startActivity(intent);
                        }

                        else
                        {
                            loadingBar4.dismiss();
                            Toast.makeText(loginActivity.this, "Password Entered is Incorrect", Toast.LENGTH_SHORT).show();
                        }
                    }

                    
                }
                else
                {
                    Toast.makeText(loginActivity.this, "Account with number " + phone + " doesn't exist.", Toast.LENGTH_SHORT).show();
                    loadingBar4.dismiss();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
