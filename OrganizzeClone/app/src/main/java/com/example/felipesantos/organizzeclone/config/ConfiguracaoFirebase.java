package com.example.felipesantos.organizzeclone.config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConfiguracaoFirebase {
    private static FirebaseAuth autenticacao;
    private static DatabaseReference database;


//    Retorna a instancia do FirebaseAuth
    public static FirebaseAuth getFirebaseAutenticacao(){

        if ( autenticacao == null) {
            autenticacao = FirebaseAuth.getInstance();
        }
        return autenticacao;
    }

    //    Retorna a instancia do FirebaseDatabase
    public static DatabaseReference getFirebaseDatabase(){

        if ( database == null) {
            database = FirebaseDatabase.getInstance().getReference();
        }
        return database;
    }


}
