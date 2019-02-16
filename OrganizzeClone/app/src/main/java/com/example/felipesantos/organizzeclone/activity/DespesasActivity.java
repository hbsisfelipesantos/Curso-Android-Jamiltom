package com.example.felipesantos.organizzeclone.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.felipesantos.organizzeclone.R;
import com.example.felipesantos.organizzeclone.config.ConfiguracaoFirebase;
import com.example.felipesantos.organizzeclone.fragment.CarregamentoFragment;
import com.example.felipesantos.organizzeclone.helper.Base64Custom;
import com.example.felipesantos.organizzeclone.helper.DateCustom;
import com.example.felipesantos.organizzeclone.model.Movimentacao;
import com.example.felipesantos.organizzeclone.model.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class DespesasActivity extends AppCompatActivity {

    private EditText campoData, campoCategoria, campoDescricao;
    private EditText campoValor;
    private Movimentacao movimentacao;
    private DatabaseReference databaseReference = ConfiguracaoFirebase.getFirebaseDatabase();
    private FirebaseAuth firebaseAuth = ConfiguracaoFirebase.getFirebaseAutenticacao();
    private double despesaTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despesas);

        campoCategoria = findViewById(R.id.editTextCategoria);
        campoDescricao = findViewById(R.id.editTextDescricao);
        campoData = findViewById(R.id.editTextData);
        campoValor = findViewById(R.id.editTextValor);

//        preenche o campo data com a data atual
        campoData.setText(DateCustom.dataAtual());

        recuperarDespesaTotal();
    }

    public void salvarDespesa(View view) {

        if (validarCampos()) {
            movimentacao = new Movimentacao();
            String dataEscolhida = campoData.getText().toString();
            double valorRecuperado = Double.parseDouble(campoValor.getText().toString());

            movimentacao.setValor(valorRecuperado);
            movimentacao.setCategoria(campoCategoria.getText().toString());
            movimentacao.setDescricao(campoDescricao.getText().toString());
            movimentacao.setData(dataEscolhida);
            movimentacao.setTipo("d");

            double despesaAtualizada = despesaTotal + valorRecuperado;

            atualizarDespesa(despesaAtualizada);

            movimentacao.salvar(dataEscolhida);

            finish();
        }

    }


    public boolean validarCampos(){

        if (!campoData.getText().toString().isEmpty()) {
            if (!campoValor.getText().toString().isEmpty()) {
                if (!campoCategoria.getText().toString().isEmpty()) {
                    if (!campoDescricao.getText().toString().isEmpty()) {
                        return true;
                    } else{
                        Toast.makeText(DespesasActivity.this, "Preencha a descrição", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                } else {
                    Toast.makeText(DespesasActivity.this, "Preencha a categoria", Toast.LENGTH_SHORT).show();
               return false;
                }
            } else {
                Toast.makeText(DespesasActivity.this, "Preencha o valor", Toast.LENGTH_SHORT).show();
                return false;
            }
        } else {
            Toast.makeText(DespesasActivity.this, "Preencha a data", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public void recuperarDespesaTotal(){

        String emailUssuario = firebaseAuth.getCurrentUser().getEmail();
        String idUsuario = Base64Custom.codificarBase64(emailUssuario);
        DatabaseReference usuarioRef = databaseReference.child("usuarios")
                .child(idUsuario);

        usuarioRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Usuario usuario = dataSnapshot.getValue(Usuario.class);
                despesaTotal = usuario.getDespesaTotal();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void atualizarDespesa(double despesa){
        String emailUssuario = firebaseAuth.getCurrentUser().getEmail();
        String idUsuario = Base64Custom.codificarBase64(emailUssuario);
        DatabaseReference usuarioRef = databaseReference.child("usuarios")
                .child(idUsuario);
        usuarioRef.child("despesaTotal").setValue(despesa);

    }
}
