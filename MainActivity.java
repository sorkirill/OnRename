package com.example.onrename;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.onrename.notifications.NotificationsManager;


public class MainActivity extends AppCompatActivity {



    Button startJob, buttonBase;
    private int mId;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent resultIntent = new Intent(this, MainActivity.class);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 0, resultIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);



        //КНОПКА
        //-----------------------------------------
        startJob = findViewById(R.id.startJob);
        buttonBase = findViewById(R.id.button_base);

        buttonBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,BaseActivity.class);
                startActivity(intent);

               NotificationsManager.showNotification(getApplicationContext(),1,"sdfsf","sdfsdfsd");
            }
        });



        startJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v ) {
            Intent intent = new Intent(MainActivity.this,BaseActivity.class);
            startActivity(intent);


                NotificationCompat.Builder builder =
                        new NotificationCompat.Builder(v.getContext())
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setContentTitle("Title")
                                .setContentText("Notification text");

                Notification notification = builder.build();

                NotificationManager notificationManager =
                        (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(1, notification);
            }
        });
    }


    //кнопка перехода на новую активность
    public void OnClickGoToRecycleList(View view) {
        //перейдем на новую активность
        Intent intent = new Intent(this, RecycleListActivity.class);
        startActivity(intent);
    }

    //меню

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int id = item.getItemId();
        // Операции для выбранного пункта меню
        switch (id) {
            case R.id.settings:
                Toast.makeText(this, "Setting выбраны", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }





}
