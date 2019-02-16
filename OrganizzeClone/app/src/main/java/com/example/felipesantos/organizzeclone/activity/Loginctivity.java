package com.example.felipesantos.organizzeclone.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.felipesantos.organizzeclone.R;
import com.example.felipesantos.organizzeclone.config.ConfiguracaoFirebase;
import com.example.felipesantos.organizzeclone.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class Loginctivity extends AppCompatActivity {

    private EditText campoEmail, campoSenha;
    private Button btEntrar;
    private Usuario usuario;
    private FirebaseAuth firebaseAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginctivity);

        getSupportActionBar().setTitle("Já Tenho uma conta");

        campoEmail = findViewById(R.id.editEmail);
        campoSenha = findViewById(R.id.editSenha);
        btEntrar = findViewById(R.id.buttonEntrar);
        progressBar = findViewById(R.id.progressBar);

        progressBar.setVisibility(View.INVISIBLE);

        btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textEmail = campoEmail.getText().toString();
                String textSenha = campoSenha.getText().toString();

                if (!textEmail.isEmpty()) {
                    if (!textSenha.isEmpty()) {

                        progressBar.setVisibility(View.VISIBLE);

                        usuario = new Usuario();
                        usuario.setEmail(textEmail);
                        usuario.setSenha(textSenha);

                        validarLogin();

                    } else {
                        Toast.makeText(Loginctivity.this, "Preencha o Senha", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(Loginctivity.this, "Preencha o e-mail", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    public void validarLogin() {

        firebaseAuth = ConfiguracaoFirebase.getFirebaseAutenticacao();
        firebaseAuth.signInWithEmailAndPassword(usuario.getEmail(), usuario.getSenha())
        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){

                    abrirTelaPrincipal();

                } else{
                    String excecao = "";
                    try {
                        throw task.getException();
                    } catch (FirebaseAuthInvalidUserException e){
                        excecao = "Usúario não existente";
                    } catch (FirebaseAuthInvalidCredentialsException e){
                        excecao = "E-mail e senha não correspondem a um usúario cadastrado";
                    } catch (Exception e){
                        excecao = "Erro ao logar usúario " + e.getMessage();
                        e.printStackTrace();
                    }
                    Toast.makeText(Loginctivity.this, excecao, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void abrirTelaPrincipal(){
        startActivity(new Intent(this,PrincipalActivity.class));
        finish();
    }

}
