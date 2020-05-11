 package com.example.ep;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ep.Model.Users;
import com.example.ep.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

 public class MainActivity extends AppCompatActivity {

     private Button joinNowBtn, loginBtn;
     private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        joinNowBtn = (Button) findViewById(R.id.button3);
        loginBtn = (Button) findViewById(R.id.button2);
        loadingBar = new ProgressDialog(this);

        Paper.init(this);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  =new Intent(MainActivity.this, loginActivity.class);
                startActivity(intent);
            }
        });

        joinNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });


        String UserPhoneKey1 = Paper.book().read(Prevalent.UserPhoneKey1);
        String UserPasswordKey1 = Paper.book().read(Prevalent.UserPasswordKey1);

        if (UserPhoneKey1 != "" && UserPasswordKey1 != "")
        {
            if (!TextUtils.isEmpty(UserPhoneKey1)  &&  !TextUtils.isEmpty(UserPasswordKey1))
            {
                AllowAccess(UserPhoneKey1, UserPasswordKey1);

                loadingBar.setTitle("Already Logged In");
                loadingBar.setMessage("Please wait");
                loadingBar.setCanceledOnTouchOutside(false);
                loadingBar.show();
            }

        }


    }

     private void AllowAccess(final String phone, final String password) {

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
                             Toast.makeText(MainActivity.this, "Already Logged In", Toast.LENGTH_SHORT).show();
                             loadingBar.dismiss();

                             Intent intent  =new Intent(MainActivity.this, HomeActivity.class);
                             Prevalent.CurrentOnlineUser=usersData;
                             startActivity(intent);
                         }

                         else
                         {
                             loadingBar.dismiss();
                             Toast.makeText(MainActivity.this, "Password Entered is Incorrect", Toast.LENGTH_SHORT).show();
                         }
                     }


                 }
                 else
                 {
                     Toast.makeText(MainActivity.this, "Account with number " + phone + " doesn't exist.", Toast.LENGTH_SHORT).show();
                     loadingBar.dismiss();

                 }

             }

             @Override
             public void onCancelled(@NonNull DatabaseError databaseError) {

             }
         });
     }
 }
