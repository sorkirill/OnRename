package com.example.onrename;



import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RecycleListActivity extends AppCompatActivity {

    interface OnItemClickListener {
        void onClick(Elements phone);
    }


    List<Elements> phones = new ArrayList<>();
    private OnItemClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_list);

        setInitialData();//загрузим данные
        RecyclerView recyclerView = findViewById(R.id.list);
        // создаем адаптер

        listener = new OnItemClickListener() {
            @Override
            public void onClick(Elements phone) {
                Intent intent = new Intent(RecycleListActivity.this, SheduleActivity.class);
                intent.putExtra("tovar", phone);
                startActivity(intent);
            }
        };

        DataAdapter adapter = new DataAdapter(this, phones, listener);
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(adapter);
    }


    private void setInitialData() {

        phones.add(new Elements("Huawei P10", "Huawei", "25 руб"));
        phones.add(new Elements("Elite z3", "HP", "21 руб"));
        phones.add(new Elements("Galaxy S8", "Samsung", "22 руб"));
        phones.add(new Elements("LG G 5", "LG", "23 руб"));
        phones.add(new Elements("Smartphone", "ASUS", "23 руб"));
        phones.add(new Elements("Smartphone", "Cat", "23 руб"));
        phones.add(new Elements("Smartphone", "dfh", "23 руб"));
        phones.add(new Elements("Smartphone", "dfhd", "23 руб"));
        phones.add(new Elements("Smartphone", "dfhd", "23 руб"));
        phones.add(new Elements("Smartphone", "Cat", "23 руб"));
        phones.add(new Elements("Smartphone", "dfhd", "23 руб"));
        phones.add(new Elements("Smartphone", "Cat", "23 руб"));
        phones.add(new Elements("Smartphone", "dhddfh", "23 руб"));
        phones.add(new Elements("Smartphone", "Cat", "23 руб"));
        phones.add(new Elements("Smartphone", "dhd", "23 руб"));
    }




}
