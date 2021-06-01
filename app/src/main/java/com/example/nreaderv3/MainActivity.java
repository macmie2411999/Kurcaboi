package com.example.nreaderv3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //khai báo biến
    ImageButton iconStart;
    ConstraintLayout mainLayout1;
    ArrayList<Integer> arrayBackground1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // gọi hàm
        mapping();
        addImage();
        changeBackground();

        // đặt sự kiện cho iconStart
        iconStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // chuyển sang MainActiviti2 khi click
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
    }

    // mapping
    public void mapping(){
        iconStart = (ImageButton) findViewById(R.id.iconStart);
        mainLayout1 = (ConstraintLayout) findViewById(R.id.mainBackground1);
    }

    // thêm các background vào arrayBackground
    public void addImage(){
        arrayBackground1 = new ArrayList<>();
        arrayBackground1.add(R.drawable.background1_1);
        arrayBackground1.add(R.drawable.background1_2);
        arrayBackground1.add(R.drawable.background1_3);
        arrayBackground1.add(R.drawable.background1_4);
    }

    // hàm tạo số ngẫu nhiên và đặt ảnh background ngẫu nhiên
    public void changeBackground(){
        Random random = new Random();
        mainLayout1.setBackgroundResource(arrayBackground1.get(random.nextInt(arrayBackground1.size())));
    }
}