package com.example.thirdapplication;

// MainActivity.java
import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;




import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);



        final TextView selectDateTextView = findViewById(R.id.selectDateTextView);
        final TextView dateTextView = findViewById(R.id.DateTextView);
        selectDateTextView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                // Create a DatePickerDialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this);

                // Set a listener to get the selected date
                datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Update the DateTextView with the selected date
                        String selectedDate = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                        dateTextView.setText(selectedDate);
                    }
                });

                // Show the DatePickerDialog
                datePickerDialog.show();
            }
        });



        ListView listView = findViewById(R.id.list);


        // Create a list of items
        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item("Amandes", "F", R.mipmap.ic_launcher_round , "20"));
        itemList.add(new Item("Noides", "V", R.mipmap.ic_launcher, "13"));
        itemList.add(new Item("Cacao", "V", R.mipmap.ic_launcher, "30"));
        itemList.add(new Item("Cacao", "V", R.mipmap.ic_launcher, "30"));
        itemList.add(new Item("Cacao", "V", R.mipmap.ic_launcher, "30"));
        itemList.add(new Item("Cacao", "V", R.mipmap.ic_launcher, "30"));
        itemList.add(new Item("Cacao", "V", R.mipmap.ic_launcher, "30"));

        // Create the adapter
        ItemAdapter itemAdapter = new ItemAdapter(this, itemList);

        // Set the adapter to the ListView
        listView.setAdapter(itemAdapter);

    }
}
