package com.example.myapplicationtest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText fname,mobileno,addressnew;
    RecyclerView recyclerView;
    ArrayList<datauser>list=new ArrayList<>();
    FirebaseFirestore firebaseFirestore;
    Button log;
    Adapteritem adapteritem;

  Context context;
    FirebaseAuth mauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fname=findViewById(R.id.name);
        mobileno=findViewById(R.id.mno);
        addressnew=findViewById(R.id.address);
        recyclerView=findViewById(R.id.recyclerview);
        mauth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        log=findViewById(R.id.save);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ffname=fname.getText().toString();
                String number=mobileno.getText().toString();
                String add=addressnew.getText().toString();
                save(ffname,number,add);
            }
        });

        showdata();
    }

    private void save(String ffname, String number, String add) {

        datauser datauser=new datauser(ffname,number,add);
        CollectionReference reference=firebaseFirestore.collection("USER");

        reference.add(datauser).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(MainActivity.this, "data saved", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void showdata(){
        firebaseFirestore.collection("USER")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        for (DocumentChange dc:value.getDocumentChanges()){
                            if (dc.getType()== DocumentChange.Type.ADDED){

                                list.add(dc.getDocument().toObject(datauser.class));
                            }
                            adapteritem=new Adapteritem(context,list);
                            recyclerView.setAdapter(adapteritem);
                            adapteritem.notifyDataSetChanged();


                        }
                    }
                });
    }
}