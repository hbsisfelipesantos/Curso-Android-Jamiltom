package com.example.felipesantos.organizzeclone.activity;

import android.content.Intent;
import android.os.Bundle;
import android.print.PrinterCapabilitiesInfo;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.felipesantos.organizzeclone.R;
import com.example.felipesantos.organizzeclone.activity.CadastroActivity;
import com.example.felipesantos.organizzeclone.activity.Loginctivity;
import com.example.felipesantos.organizzeclone.config.ConfiguracaoFirebase;
import com.google.firebase.auth.FirebaseAuth;
import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;

public class MainActivity extends IntroActivity {

    private FirebaseAuth autenticazao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        setButtonBackVisible(false);
        setButtonNextVisible(false);


        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_1)
                .canGoBackward(false)
                .build());

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_2)
                .canGoBackward(true)
                .build());

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_3)
                .canGoBackward(true)
                .build());

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_4)
                .build());

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_cadastro)
                .canGoForward(false)
                .canGoBackward(false)
                .build());


    }

    @Override
    protected void onStart() {
        super.onStart();
        verificarUsuarioLogado();
    }

    public void btEntrar(View view) {
        startActivity(new Intent(this, Loginctivity.class));
    }

    public void btCadastrar(View view) {
        startActivity(new Intent(this, CadastroActivity.class));

    }

    public void verificarUsuarioLogado() {
        autenticazao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        //autenticazao.signOut();
        if (autenticazao.getCurrentUser() != null) {
            abrirTelaPrincipal();
            finish();
        }
    }

    public void abrirTelaPrincipal() {
        startActivity(new Intent(this, PrincipalActivity.class));
    }
}
