package com.example.felipesantos.organizzeclone.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.felipesantos.organizzeclone.R;
import com.example.felipesantos.organizzeclone.config.ConfiguracaoFirebase;
import com.google.firebase.auth.FirebaseAuth;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

public class PrincipalActivity extends AppCompatActivity {

    private Button btSair;
    private FirebaseAuth autenticacao;
    private MaterialCalendarView calendarView;
    private TextView textSaudacao, textSaldo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        calendarView = findViewById(R.id.calendarView);
        textSaudacao = findViewById(R.id.textSaudacao);
        textSaldo = findViewById(R.id.textSaldo);


//        Configurar Calendario
        CharSequence months[] = {"Janeiro","Fevereiro","Março", "Abril","Maio","Junho","Julho","Agosto","Setembro","Outubro","Novembro","Dezembro"};
        calendarView.setTitleMonths(months);

        CharSequence weekDays[] = {"Dom","Seg","Ter","Qua","Qui","Sex","Sab"};
        calendarView.setWeekDayLabels(weekDays);


        calendarView.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {

            }
        });

       /*
       FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */

/*        btSair = findViewById(R.id.btSair);

        btSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deslogarUsuario();
            }
        });*/
    }

/*    public void deslogarUsuario(){
        autenticacao = ConfiguracaoFirebase.GET_FIREBASE_AUTENTICACAO();
        autenticacao.signOut();
        finish();
    }*/

    public void  adicionarDespesa(View v){

        startActivity(new Intent(this,DespesasActivity.class));

    }
    public void  adicionarReceita(View v){
        startActivity(new Intent(this,ReceitasActivity.class));
    }

}
