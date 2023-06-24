package com.example.e_commerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.e_commerce.model.Order;
import com.example.e_commerce.model.Product;

import java.util.ArrayList;
import java.util.List;

public class OrderPage extends AppCompatActivity {
    ListView orders_list;
    ImageButton btnContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_page);

        orders_list = findViewById(R.id.orders_list);

        List<String> productsTitle = new ArrayList<>();

        for (Product p : MainActivity.fullProductsList) {
            if (Order.items_id.contains(p.getId())) {
                productsTitle.add(p.getTitle());
            }
        }

        orders_list.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, productsTitle));

        btnContact = findViewById(R.id.btnContact);
        btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(OrderPage.this, "v 1.0", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void openHomePage(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}