package com.example.felipesantos.organizzeclone.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.felipesantos.organizzeclone.R;
import com.example.felipesantos.organizzeclone.config.ConfiguracaoFirebase;
import com.example.felipesantos.organizzeclone.helper.Base64Custom;
import com.example.felipesantos.organizzeclone.helper.DateCustom;
import com.example.felipesantos.organizzeclone.model.Movimentacao;
import com.example.felipesantos.organizzeclone.model.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import static java.lang.Double.parseDouble;

public class ReceitasActivity extends AppCompatActivity {

    private EditText campoValor, campoData, campoDescricao, campoCategoria;
    private Movimentacao movimentacao;
    private DatabaseReference databaseReference = ConfiguracaoFirebase.getFirebaseDatabase();
    private FirebaseAuth firebaseAuth = ConfiguracaoFirebase.getFirebaseAutenticacao();
    private double receitaTotal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receitas);

        campoValor = findViewById(R.id.editTextValor);
        campoData = findViewById(R.id.editTextData);
        campoCategoria = findViewById(R.id.editTextCategoria);
        campoDescricao = findViewById(R.id.editTextDescricao);

        //        preenche o campo data com a data atual
        campoData.setText(DateCustom.dataAtual());


        recuperarReceitaTotal();

    }

    public void salverReceita(View view) {

        if (validarCampos()) {
            Movimentacao movimentacao = new Movimentacao();
            String dataEscolhida = campoData.getText().toString();
            double valorReceita = Double.parseDouble(campoValor.getText().toString());

            movimentacao.setData(dataEscolhida);
            movimentacao.setDescricao(campoDescricao.getText().toString());
            movimentacao.setCategoria(campoCategoria.getText().toString());
            movimentacao.setValor(valorReceita);
            movimentacao.setTipo("r");

            Double receitaAtualizada = receitaTotal + valorReceita;

            atulaizarReceita(receitaAtualizada);

            movimentacao.salvar(dataEscolhida);

            finish();
        }

    }

    public boolean validarCampos() {

        if (!campoData.getText().toString().isEmpty()) {
            if (!campoValor.getText().toString().isEmpty()) {
                if (!campoCategoria.getText().toString().isEmpty()) {
                    if (!campoDescricao.getText().toString().isEmpty()) {
                        return true;
                    } else {
                        Toast.makeText(ReceitasActivity.this, "Preencha a descrição", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                } else {
                    Toast.makeText(ReceitasActivity.this, "Preencha a categoria", Toast.LENGTH_SHORT).show();
                    return false;
                }
            } else {
                Toast.makeText(ReceitasActivity.this, "Preencha o valor", Toast.LENGTH_SHORT).show();
                return false;
            }
        } else {
            Toast.makeText(ReceitasActivity.this, "Preencha a data", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void recuperarReceitaTotal() {
        String emailUsuario = firebaseAuth.getCurrentUser().getEmail();
        String idUsuario = Base64Custom.codificarBase64(emailUsuario);
        DatabaseReference usuarioRef = databaseReference.child("usuarios")
                .child(idUsuario);

        usuarioRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Usuario usuario = dataSnapshot.getValue(Usuario.class);
                receitaTotal = usuario.getReceitaTotal();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void atulaizarReceita(double receita) {
        String emailUssuario = firebaseAuth.getCurrentUser().getEmail();
        String idUsuario = Base64Custom.codificarBase64(emailUssuario);
        DatabaseReference usuarioRef = databaseReference.child("usuarios")
                .child(idUsuario);
        usuarioRef.child("receitaTotal").setValue(receita);
    }


}
