package com.example.recyclerview;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
ArrayList<ContactModel> arrContacts = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerContacts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        LayoutInflater inflater = getLayoutInflater();
        View contact_row = inflater.inflate(R.layout.contact_row,null);
        AppCompatButton btnUpdate = contact_row.findViewById(R.id.btnUpdate);

        FloatingActionButton btnOpenDialog = findViewById(R.id.btnOpenDialog);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.add_update_lay);

                EditText edtName = dialog.findViewById(R.id.edtName);
                EditText edtNumber = dialog.findViewById(R.id.edtNumber);
                Button btnAction = dialog.findViewById(R.id.btnAction);

                dialog.show();
            }
        });
        btnOpenDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.add_update_lay);

                EditText edtName = dialog.findViewById(R.id.edtName);
                EditText edtNumber = dialog.findViewById(R.id.edtNumber);
                Button btnAction = dialog.findViewById(R.id.btnAction);

                btnAction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = "", number = "";
                        if(!edtName.getText().toString().equals("")){
                            name = edtName.getText().toString();
                        }else {
                            Toast.makeText(MainActivity.this, "Please enter Your Name", Toast.LENGTH_SHORT).show();
                        }
                        if(!edtNumber.getText().toString().equals("")){
                            number = edtNumber.getText().toString();
                        }else {
                            Toast.makeText(MainActivity.this, "Please enter Your Number", Toast.LENGTH_SHORT).show();
                        }

                        arrContacts.add(new ContactModel(R.drawable.baseline_account_circle_24,name,number));
                        ContactAdapter adapter = new ContactAdapter(MainActivity.this,arrContacts);
                        recyclerView.setAdapter(adapter);
                        recyclerView.scrollToPosition(arrContacts.size()-1);
                        dialog.dismiss();

                    }
                });
                dialog.show();

            }
        });

        ContactModel model = new ContactModel(R.drawable.baseline_assignment_ind_24,"ABC","6307843065");
        arrContacts.add(model);
        arrContacts.add(new ContactModel(R.drawable.baseline_android_24,"Android","9607843875"));
        arrContacts.add(new ContactModel(R.drawable.baseline_3p_24,"Android","9607843875"));
        arrContacts.add(model);
        arrContacts.add(new ContactModel(R.drawable.baseline_android_24,"Android","9607843875"));
        arrContacts.add(new ContactModel(R.drawable.baseline_3p_24,"Android","9607843875"));
        arrContacts.add(model);
        arrContacts.add(new ContactModel(R.drawable.baseline_android_24,"Android","9607843875"));
        arrContacts.add(new ContactModel(R.drawable.baseline_3p_24,"Android","9607843875"));
        arrContacts.add(new ContactModel(R.drawable.baseline_android_24,"Android","9607843875"));
        arrContacts.add(new ContactModel(R.drawable.baseline_3p_24,"Android","9607843875"));
        arrContacts.add(model);
        arrContacts.add(new ContactModel(R.drawable.baseline_android_24,"Android","9607843875"));
        arrContacts.add(new ContactModel(R.drawable.baseline_3p_24,"Android","9607843875"));
        arrContacts.add(model);
        arrContacts.add(new ContactModel(R.drawable.baseline_android_24,"Android","9607843875"));
        arrContacts.add(new ContactModel(R.drawable.baseline_3p_24,"Android","9607843875"));
        arrContacts.add(new ContactModel(R.drawable.baseline_android_24,"Android","9607843875"));
        arrContacts.add(new ContactModel(R.drawable.baseline_3p_24,"Android","9607843875"));
        arrContacts.add(model);
        arrContacts.add(new ContactModel(R.drawable.baseline_android_24,"Android","9607843875"));
        arrContacts.add(new ContactModel(R.drawable.baseline_3p_24,"Android","9607843875"));
        arrContacts.add(model);
        arrContacts.add(new ContactModel(R.drawable.baseline_android_24,"Android","9607843875"));
        arrContacts.add(new ContactModel(R.drawable.baseline_3p_24,"Android","9607843875"));

        ContactAdapter adapter = new ContactAdapter(this,arrContacts);
        recyclerView.setAdapter(adapter);

    }
}