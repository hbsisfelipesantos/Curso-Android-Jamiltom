package com.example.felipesantos.slider;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.SimpleSlide;
import com.heinrichreimersoftware.materialintro.slide.Slide;

public class MainActivity extends IntroActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        addSlide( new SimpleSlide.Builder()
        .title("Titulo")
        .description("descrição")
        .image(R.drawable.um)
                .background(android.R.color.holo_orange_dark)
        .build());

    }
}
