package com.example.nestedlistviews;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ListView mCategoryList;
    private String[] mCategories = new String[]{"Education", "Entertainment", "Health", "Provisions"};
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCategoryList = (ListView) findViewById(R.id.category_list);
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mCategories);
        mCategoryList.setAdapter(mAdapter);

        mCategoryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String category = mCategories[position];
                showSubCategories(category);
            }
        });
    }

    private void showSubCategories(String category) {
        String[] subCategories = null;
        switch (category) {
            case "Education":
                subCategories = new String[]{"Schools", "Colleges", "Universities"};
                break;
            case "Entertainment":
                subCategories = new String[]{"Movies", "Music", "Theater"};
                break;
            case "Health":
                subCategories = new String[]{"Hospitals", "Clinics", "Pharmacies"};
                break;
            case "Provisions":
                subCategories = new String[]{"Grocery stores", "Supermarkets", "Convenience stores"};
                break;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String[] finalSubCategories = subCategories;
        builder.setTitle("Sub-Categories")
                .setItems(subCategories, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showDetails(finalSubCategories[which]);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    private void showDetails(String subCategory) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Details")
                .setMessage(subCategory + " details");
    }
}

