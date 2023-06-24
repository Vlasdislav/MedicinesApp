package com.example.e_commerce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_commerce.model.Order;

public class ProductPage extends AppCompatActivity {

    ImageButton btnContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_page);

        ImageView productImage = findViewById(R.id.productPageImage);
        TextView productTitle = findViewById(R.id.productPageTitle);
        TextView productPrice = findViewById(R.id.productPagePrice);
        TextView productCountry = findViewById(R.id.productPageCountry);
        TextView productText = findViewById(R.id.productPageText);

        productImage.setImageResource(getIntent().getIntExtra("productImage",0));
        productTitle.setText(getIntent().getStringExtra("productTitle"));
        productPrice.setText(getIntent().getStringExtra("productPrice"));
        productCountry.setText(getIntent().getStringExtra("productCountry"));
        productText.setText(getIntent().getStringExtra("productText"));

        btnContact = findViewById(R.id.btnContact);
        btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ProductPage.this, "v 1.0", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void addToCart(View view) {
        int item_id = getIntent().getIntExtra("productId",0);
        Order.items_id.add(item_id);
        Toast.makeText(this, "Добавлено в корзину!", Toast.LENGTH_LONG).show();
    }

    public void openShoppingCart(View view) {
        Intent intent = new Intent(this, OrderPage.class);
        startActivity(intent);
    }

    public void openHomePage(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}