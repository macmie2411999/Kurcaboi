package com.example.nreaderv3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity2 extends AppCompatActivity {

    // khai báo biến
    ConstraintLayout mainBackground;
    ImageButton buttonSport, buttonHealth, buttonTravel, buttonScient;
    ArrayList<Integer> arraySport, arrayHealth, arrayTravel, arrayScient, arrayBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // gọi hàm
        mapping();
        addImageToArray();
        randomImageBackground();

        // đặt sự kiện cho các button, mỗi khi người dùng chọn vào một button, đường link của RSS sẽ được truyền đến cho MainActivity3
        buttonSport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                Bundle bunder = new Bundle();
                bunder.putString("linkRSS", "https://vnexpress.net/rss/the-gioi.rss");
                intent.putExtras(bunder);
                startActivity(intent);
            }
        });

        buttonHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                Bundle bunder = new Bundle();
                bunder.putString("linkRSS", "https://vnexpress.net/rss/suc-khoe.rss");
                intent.putExtras(bunder);
                startActivity(intent);
            }
        });

        buttonTravel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                Bundle bunder = new Bundle();
                bunder.putString("linkRSS", "https://vnexpress.net/rss/du-lich.rss");
                intent.putExtras(bunder);
                startActivity(intent);
            }
        });

        buttonScient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                Bundle bunder = new Bundle();
                bunder.putString("linkRSS", "https://vnexpress.net/rss/khoa-hoc.rss");
                intent.putExtras(bunder);
                startActivity(intent);
            }
        });
    }

    private void mapping() {
        buttonSport = (ImageButton) findViewById(R.id.imageButtonSport);
        buttonHealth = (ImageButton) findViewById(R.id.imageButtonHealth);
        buttonTravel = (ImageButton) findViewById(R.id.imageButtonTravel);
        buttonScient = (ImageButton) findViewById(R.id.imageButtonScient);
        mainBackground = (ConstraintLayout) findViewById(R.id.mainBackground);
    }

    // thêm ảnh vào các arays
    public void addImageToArray(){
        arraySport = new ArrayList<>();
        arraySport.add(R.drawable.sport_1);
        arraySport.add(R.drawable.sport_2);
        arraySport.add(R.drawable.sport_3);

        arrayHealth = new ArrayList<>();
        arrayHealth.add(R.drawable.health_1);
        arrayHealth.add(R.drawable.health_2);
        arrayHealth.add(R.drawable.health_3);

        arrayTravel = new ArrayList<>();
        arrayTravel.add(R.drawable.travel_1);
        arrayTravel.add(R.drawable.travel_2);
        arrayTravel.add(R.drawable.travel_3);

        arrayScient = new ArrayList<>();
        arrayScient.add(R.drawable.scient_1);
        arrayScient.add(R.drawable.scient_2);
        arrayScient.add(R.drawable.scient_3);


        arrayBackground = new ArrayList<>();
        arrayBackground.add(R.drawable.background2);
        arrayBackground.add(R.drawable.background3);
        arrayBackground.add(R.drawable.background4);
        arrayBackground.add(R.drawable.background5);
    }

    // tạo số ngẫu nhiên và đặt background ngẫu nhiên
    public void randomImageBackground(){
        Random random = new Random();
        buttonSport.setBackgroundResource(arraySport.get(random.nextInt(arraySport.size())));
        buttonHealth.setBackgroundResource(arrayHealth.get(random.nextInt(arrayHealth.size())));
        buttonTravel.setBackgroundResource(arrayTravel.get(random.nextInt(arrayTravel.size())));
        buttonScient.setBackgroundResource(arrayScient.get(random.nextInt(arrayScient.size())));
        mainBackground.setBackgroundResource(arrayBackground.get(random.nextInt(arrayBackground.size())));
    }
}
