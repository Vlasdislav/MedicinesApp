package com.example.e_commerce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.e_commerce.adapter.CategoryAdapter;
import com.example.e_commerce.adapter.ProductAdapter;
import com.example.e_commerce.model.Category;
import com.example.e_commerce.model.Product;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageButton btnContact;
    RecyclerView categoryRecycler, productRecycler;
    CategoryAdapter categoryAdapter;
    static ProductAdapter productAdapter;
    static List<Product> productList = new ArrayList<>();
    static List<Product> fullProductsList = new ArrayList<>();
    ImageView filter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Category> categoryList = new ArrayList<>();

        categoryList.add(new Category(1, "Зрение"));
        categoryList.add(new Category(2, "Диабет"));
        categoryList.add(new Category(3, "Гигиена"));
        categoryList.add(new Category(4, "Тесты"));
        categoryList.add(new Category(5, "Прочее"));

        setCategoryRecycler(categoryList);

        productList.clear();
        fullProductsList.clear();

        productList.add(new Product(1, "product_1", "Widmer Acne plus Creme 2 % / 5 %, 20 g Cream", "990 руб.", "США", "Крем против прыщей Видмер Акне Плюс используется наружно для лечения прыщей (угри, прыщи, акне). Он подавляет рост бактерий прыщей, обладает легким отшелушивающим действием и уменьшает выработку кожного сала.\n\nКрем Видмер Акне Плюс не следует использовать, если известно, что вы гиперчувствительны к бензоилпероксиду, миконазолу или связанным с ним противогрибковым препаратам или любым из вспомогательных веществ.", 3));
        productList.add(new Product(2, "product_2", "RefectoCil Cream Hair Dye (Blue Black)", "654 руб.", "Германия", "Blue black - это оттенок черного с мягким голубым мерцанием, который придает дополнительную глубину и блеск и создает умопомрачительные сине-черные стили.\n" +
                "-Оттенок черного, который придает голубоватое мерцание каждому волоску\n" +
                "-Смешивается со всеми 8 кремовыми красками для волос RefectoCil\n" +
                "-Размазывающийся и водонепроницаемый\n" +
                "- Длится 6 недель", 5));
        productList.add(new Product(3, "product_3", "Крем от угрей Адапален", "582 руб.", "Россия", "Адапален - метаболит ретиноида, который действует на патологический механизм развития Acne vulgaris, является сильным модулятором клеточной дифференцировки и кератинизации и обладает комедонолитической и противовоспалительной активностью. Механизм действия адапалена основан на взаимодействии со специфическими γ- рецепторами эпидермальных клеток кожи. В результате действия адапалена происходит снижение \"сцепленности\" эпителиальных клеток в устье сально-волосяного фолликула и уменьшение образования микрокомедонов.", 3));
        productList.add(new Product(4, "product_4", "CUVUE OASYS 1-Day", "981 руб.", "США", "Независимо от того, собираете ли вы жизненный опыт или проводите продуктивное экранное время в вашем мире, зависящем от цифровых технологий, вам нужно зрение, чтобы довести свое видение до конца. 1-дневные контактные линзы ACUVUE OASYS помогут вам сфокусировать зрение с исключительным комфортом каждый день.\n" +
                "\n" +
                "Разработанный с использованием технологии HydraLuxe, уникальный дизайн, вдохновленный слезами, который помогает обеспечить превосходный комфорт и более четкое зрение.", 1));
        productList.add(new Product(5, "product_5", "Экспресс-ВАК SARS-CoV-2-ИХА", "2000 руб.", "Россия", "За 15 минут он определит, образовался ли иммунный ответ после прививки от COVID-19 или есть необходимость в повторной вакцинации. Также тест способен выявлять антитела, возникшие после перенесенной болезни. Тем самым целесообразно его проводить и перед прививкой с целью выбора подходящей вакцины (двух - или однокомпонентной).", 4));

        fullProductsList.addAll(productList);

        setProductRecycler(productList);

        filter = findViewById(R.id.filter);

        filter.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View view) {
                productList.clear();
                productList.addAll(fullProductsList);

                productAdapter.notifyDataSetChanged();
            }
        });

        btnContact = findViewById(R.id.btnContact);
        btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "v 1.0", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void openShoppingCart(View view) {
        Intent intent = new Intent(this, OrderPage.class);
        startActivity(intent);
    }

    private void setProductRecycler(List<Product> productList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        productRecycler = findViewById(R.id.productRecycler);
        productRecycler.setLayoutManager(layoutManager);
        productAdapter = new ProductAdapter(this, productList);
        productRecycler.setAdapter(productAdapter);
    }

    private void setCategoryRecycler(List<Category> categoryList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        categoryRecycler = findViewById(R.id.categoryRecycler);
        categoryRecycler.setLayoutManager(layoutManager);
        categoryAdapter = new CategoryAdapter(this, categoryList);
        categoryRecycler.setAdapter(categoryAdapter);
    }

    @SuppressLint("NotifyDataSetChanged")
    public static void showProductByCategory(int category) {
        List<Product> filterProduct = new ArrayList<>();

        productList.clear();
        productList.addAll(fullProductsList);

        for (Product c : productList) {
            if (c.getCategory() == category) {
                filterProduct.add(c);
            }
        }
        productList.clear();
        productList.addAll(filterProduct);

        productAdapter.notifyDataSetChanged();
    }
}