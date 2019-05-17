package com.derrick.park.assignment3_contacts.activities;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.derrick.park.assignment3_contacts.R;
import com.derrick.park.assignment3_contacts.models.Contact;

import java.util.ArrayList;

public class MListApapter extends RecyclerView.Adapter<MListApapter.MViewHolder> {
    private ArrayList<Contact>mcontactlist;
    private LayoutInflater layoutInflater;
    private Context context;

    public MListApapter(ArrayList<Contact> mcontactlist, Context context) {
        this.mcontactlist = mcontactlist;
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public MListApapter.MViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.view_holder, viewGroup, false);
        return new MViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull MListApapter.MViewHolder mViewHolder, int i) { // bind = like connect
        Contact contact = mcontactlist.get(i);  //i = index

        mViewHolder.bind(contact);

    }

    @Override
    public int getItemCount() {

        return mcontactlist.size();
    }

    public class MViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView phone;
        private MListApapter mListApapter;

        public MViewHolder(@NonNull View itemView, MListApapter mListApapter) {
            super(itemView);
            this.name = itemView.findViewById(R.id.name);
            this.phone = itemView.findViewById(R.id.phone);
            this.mListApapter = mListApapter;

        }

        public void bind(Contact contact) {
            name.setText(contact.getName() + ""); // set = put -- verb
            phone.setText(contact.getCell() + "");
        }
    }

}
