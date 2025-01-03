package com.example.recyclerview;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.preference.DialogPreference;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    ArrayList<ContactModel> arrContacts;
    Context context;
    private ArrayList<ContactModel> contacts;
    private int lastPosition = -1;

    // Constructor to initialize the contact list
    public ContactAdapter(Context context ,ArrayList<ContactModel> contacts) {
        this.context = context;
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each row (contact_row.xml)
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Use 'contacts' instead of 'arrContacts'
        ContactModel contact = contacts.get(position);
        holder.imgContact.setImageResource(contact.img);
        holder.txtName.setText(contact.name);
        holder.txtNumber.setText(contact.number);
        setAnimation(holder.itemView,position);

        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.add_update_lay);

                EditText edtName = dialog.findViewById(R.id.edtName);
                EditText edtNumber = dialog.findViewById(R.id.edtNumber);
                Button btnAction = dialog.findViewById(R.id.btnAction);

                btnAction.setText("update");

                btnAction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = "", number = "";
                        if (!edtName.getText().toString().equals("")) {
                            name = edtName.getText().toString();
                        } else {
                            Toast.makeText(context, "Please enter Your Name", Toast.LENGTH_SHORT).show();
                        }
                        if (!edtNumber.getText().toString().equals("")) {
                            number = edtNumber.getText().toString();
                        } else {
                            Toast.makeText(context, "Please enter Your Number", Toast.LENGTH_SHORT).show();
                        }

                        // Update the contact data
                        contacts.set(position, new ContactModel(contacts.get(position).img,name, number));
                        notifyItemChanged(position);
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        holder.llrow.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context)
                        .setTitle("Delete Contact")
                        .setMessage("Are you sure you want to delete this message? (are you fr bro💀)")
                        .setIcon(R.drawable.twotone_auto_delete_24)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                contacts.remove(position);
                                notifyItemRemoved(position);
                                notifyItemRangeChanged(position,contacts.size());
                            }
                        })
                        .setNegativeButton("no", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                builder.show();

                return true;
            }
        });
    }


    @Override
    public int getItemCount() {
        return contacts.size();
    }

    // ViewHolder class to hold the references to each row's views
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgContact;
        TextView txtName, txtNumber;
        Button btnUpdate;
        LinearLayout llrow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgContact = itemView.findViewById(R.id.imgContact);
            txtName = itemView.findViewById(R.id.txtName);
            txtNumber = itemView.findViewById(R.id.txtNumber);
            btnUpdate = itemView.findViewById(R.id.btnUpdate);
            llrow = itemView.findViewById(R.id.llrow);
        }
    }

    private void setAnimation(View viewToAnimate, int position){

        if (position>lastPosition){
            Animation slidein = AnimationUtils.loadAnimation(context,android.R.anim.slide_in_left);
            slidein.setDuration(200);
            viewToAnimate.startAnimation(slidein);
            lastPosition=position;
        }

    }
}
