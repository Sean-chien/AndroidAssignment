package com.derrick.park.assignment3_contacts.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.derrick.park.assignment3_contacts.R;
import com.derrick.park.assignment3_contacts.models.Contact;
import com.derrick.park.assignment3_contacts.models.ContactList;
import com.derrick.park.assignment3_contacts.network.ContactClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MListApapter mListApapter;
    private ArrayList<Contact> mContactList;
    private Context context = this;
    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Call<ContactList> call = ContactClient.getContacts(10);

        call.enqueue(new Callback<ContactList>() {
            @Override
            public void onResponse(Call<ContactList> call, Response<ContactList> response) {
                if (response.isSuccessful()) {
                     mContactList = response.body().getContactList();
                     for(Contact contact: mContactList) {
                         Log.d(TAG, "onResponse: " + contact.getName());
                         Log.d(TAG, "onResponse: " + contact.getCell());  // cell = phone
                     }

                }
                mListApapter = new MListApapter(mContactList, context);
                recyclerView = findViewById(R.id.recyclerview);
                recyclerView.setAdapter(mListApapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            }

            @Override
            public void onFailure(Call<ContactList> call, Throwable t) {
                // Error Handling

            }
        });
    }
}
