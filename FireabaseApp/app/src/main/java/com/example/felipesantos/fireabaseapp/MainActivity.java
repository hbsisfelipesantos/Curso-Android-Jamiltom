package com.example.felipesantos.fireabaseapp;

import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

/*    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();*/

    private ImageView imageFoto;
    private Button btUpload;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageFoto = findViewById(R.id.imageFoto);
        btUpload = findViewById(R.id.btUpload);



        btUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Configura imagem para ser salva em memoria
                imageFoto.setDrawingCacheEnabled(true);
                imageFoto.buildDrawingCache();

//                Recupera bitmap da imagem (image a ser carregada)
                Bitmap bitmap = imageFoto.getDrawingCache();

//                Comprime bitmap para um formato png/Jpg
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 75, baos);

//                converte o baos para pixel em uma matriz de bytes
//                (dados da imagem)
                byte[] dadosImage = baos.toByteArray();

//                Define nós para o storage
                StorageReference storageReference = FirebaseStorage.getInstance().getReference();
                StorageReference imagens = storageReference.child("imagens");
                StorageReference imagemRef = imagens.child("celular.jpg");

                Glide.with(MainActivity.this)
                        .load(imagemRef).into(imageFoto);


//                Deletar arquivo
/*                imagemRef.delete().addOnFailureListener(MainActivity.this,new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"Erro ao deletar imagem" +
                                e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                })
                .addOnSuccessListener(MainActivity.this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(),"Sucesso ao deletar imagem", Toast.LENGTH_SHORT).show();
                    }
                });*/

//                Nome da imagem
               /* String nomeArquivo = UUID.randomUUID().toString();
//                StorageReference imagemRef = imagens.child(nomeArquivo +".jpg");

//                Retorna objeto que irá controlar o uplod
                UploadTask uploadTask = imagemRef.putBytes(dadosImage);
                uploadTask.addOnFailureListener(MainActivity.this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"Upload da imagem falhou: " +
                                e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }).addOnSuccessListener(MainActivity.this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        Uri url = taskSnapshot.getUploadSessionUri();

                        Toast.makeText(getApplicationContext(),"Sucesso ao fazer upload da imagem"
                                , Toast.LENGTH_SHORT).show();

                    }
                });*/



            }
        });

//        DatabaseReference usuarios = referencia.child("usuarios");
//        DatabaseReference usuarioPesquisa = usuarios.child("-LTDH-68SmqqRmY_oPlU");

//        Query usuarioPesquisa = usuarios.orderByChild("nome").equalTo("Felipe");
//        Query usuarioPesquisa = usuarios.orderByKey().limitToFirst(2);
//        Query usuarioPesquisa = usuarios.orderByKey().limitToLast(2);
        /*Maior ou igual =>*/
//        Query usuarioPesquisa = usuarios.orderByChild("idade").startAt(40);
        /*Menor ou igual =<*/
//        Query usuarioPesquisa = usuarios.orderByChild("idade").endAt(30);
        /*Entre dois valores =< and =>*/
//        Query usuarioPesquisa = usuarios.orderByChild("idade").startAt(20).endAt(30);
//        Query usuarioPesquisa = usuarios.orderByChild("nome").startAt("F").endAt("O" + "\uf8ff");

/*        usuarioPesquisa.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Usuario dadosUsuario = dataSnapshot.getValue(Usuario.class);


//                Log.i("Dados usuario","nome: " +  dadosUsuario.getNome() + " idade: " + dadosUsuario.getIdade());
                Log.i("Dados usuario", dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/


//        Gerar identificador unico
/*        DatabaseReference usuarios =  referencia.child("usuarios");
        Usuario usuario = new Usuario();

        usuario.setNome("Marco");
        usuario.setSobrenome("Antonio");
        usuario.setIdade(26);

        usuarios.push().setValue(usuario);*/

//        Logar usuario
   /*     firebaseAuth.signInWithEmailAndPassword("ozzfelipe@gmail.com","fel1234")
        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.i("CreateUser","Sucesso ao Logar usuario");
                }else{
                    Log.i("CreateUser","Erro ao logar usuario");
                }
            }
        });*/


//        Deslogar usuario
        /*firebaseAuth.signOut();*/

//        Verifica usuario logado
/*
        if (firebaseAuth.getCurrentUser() != null) {
            Log.i("CreateUser", "Usuario logado");
        } else {
            Log.i("CreateUser", "Usuario desconectado");
        }
*/

//        Cadastro de usuario
  /*      firebaseAuth.createUserWithEmailAndPassword("ozzfelipe@gmail.com","fel1234")
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            Log.i("CreateUser","Sucesso ao cadastrar usuario");
                        }else{
                            Log.i("CreateUser","Erro ao cadastrar usuario");
                        }

                    }
                });*/


/*        DatabaseReference usuarios =  referencia.child("usuarios").child("001");
        DatabaseReference produtos = referencia.child("Produtos");


        usuarios.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.i("FIREBASE", dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/

// Salvar dados no Firebase
       /* Usuario usuario = new Usuario();

        usuario.setNome("Felipe");
        usuario.setSobrenome("Cardoso dos Santos");
        usuario.setIdade(26);

        usuarios.child("001").setValue(usuario);*/


        /*Produtos iphone = new Produtos();
        Produtos playstation = new Produtos();

        iphone.setDescricao("Iphone X");
        iphone.setMarca("Aple");
        iphone.setPreco(7.800);

        playstation.setDescricao("Playstation 5");
        playstation.setMarca("Sony");
        playstation.setPreco(3.015);

        produtos.child("001").setValue(iphone);
        produtos.child("002").setValue(playstation);*/


    }
}
