package com.example.ep;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class AddCategoryActivity extends AppCompatActivity {

    private TextView Back;
    private ImageView Large_App, Small_App, Information, Cons_eq;
    private ImageView Toy, Ligh_eq, Others;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        Back = (TextView) findViewById(R.id.Backk);

        Large_App= (ImageView) findViewById(R.id.large);
        Small_App= (ImageView) findViewById(R.id.small);
        Information = (ImageView) findViewById(R.id.it_waste);
        Cons_eq= (ImageView) findViewById(R.id.consumer);
        Toy = (ImageView) findViewById(R.id.toys);
        Ligh_eq = (ImageView) findViewById(R.id.light);
        Others = (ImageView) findViewById(R.id.misc);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  =new Intent(AddCategoryActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        Large_App.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddCategoryActivity.this, AddNewProductActivity.class);
                intent.putExtra("Category", "Large Household Appliances");
                startActivity(intent);
            }
        });

        Small_App.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AddCategoryActivity.this, AddNewProductActivity.class);
                intent.putExtra("Category", " Smalll Household Appliances");
                startActivity(intent);
            }
        });

        Information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AddCategoryActivity.this, AddNewProductActivity.class);
                intent.putExtra("Category", " Information technology (IT) and telecommunications equipment");
                startActivity(intent);
            }
        });

        Cons_eq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AddCategoryActivity.this, AddNewProductActivity.class);
                intent.putExtra("Category", " Consumer Equipment");
                startActivity(intent);
            }
        });

        Ligh_eq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AddCategoryActivity.this, AddNewProductActivity.class);
                intent.putExtra("Category", " Light Equipment");
                startActivity(intent);
            }
        });

        Toy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AddCategoryActivity.this, AddNewProductActivity.class);
                intent.putExtra("Category", " Toys, leisure and sporting equipments");
                startActivity(intent);
            }
        });

        Others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AddCategoryActivity.this, AddNewProductActivity.class);
                intent.putExtra("Category", "Miscellaneous");
                startActivity(intent);
            }
        });



    }
}
