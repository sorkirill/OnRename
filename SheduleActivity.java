package com.example.onrename;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import com.example.onrename.toast.MyToast;


import com.example.onrename.jobs.SheduleJobsService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;



public class SheduleActivity extends AppCompatActivity {

    public static final Object ExtraData = "";
    ;
    private TextView textViewInfoJob;
    private TextView textViewInfoJob2;
    private EditText editTextStart, editTextEnd;
    private Button buttonOk;
    private Calendar calendar = Calendar.getInstance();
    int year, month, dayOfMonth;


    private static final DateFormat EVENT_DATE = new SimpleDateFormat("dd MM yyyy HH:mm ", Locale.getDefault());

 //
 // TODO Мой метод
    private   void  writeInfo(String a, String b)
    {
        textViewInfoJob.setText(a);
        textViewInfoJob2.setText(b);
        Log.d("writeInfo start 1:= ", a+"; 2 - "+ b);
        MyToast.makeText(this, "writeInfo settext + String res = yearcal+\" \"+montcal+\" \"+daycal+\" \"+hourcal;", MyToast.LENGTH_SHORT).show();
    }///конец


    private TimePickerDialog.OnTimeSetListener startTimeSetListener = new TimePickerDialog.OnTimeSetListener() {

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            calendar.set(year,month,dayOfMonth,hourOfDay,minute,0);
            editTextStart.setText(EVENT_DATE.format(calendar.getTime()));//переведет в стринг по образцу выше dd MM YYYY и тд


        }
    };

    public TimePickerDialog.OnTimeSetListener endTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            calendar.set(year,month,dayOfMonth,hourOfDay,minute,0);
            editTextEnd.setText(EVENT_DATE.format(calendar.getTime()));//переведет в стринг по образцу выше dd MM YYYY и тд
        }
    };

    
    private DatePickerDialog.OnDateSetListener startDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            SheduleActivity.this.year = year;
            SheduleActivity.this.month = month;
            SheduleActivity.this.dayOfMonth = dayOfMonth;
            new TimePickerDialog(view.getContext(),
                    startTimeSetListener,
                    calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE), true).show();
        }
    };

    private DatePickerDialog.OnDateSetListener endDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            SheduleActivity.this.year = year;
            SheduleActivity.this.month = month;
            SheduleActivity.this.dayOfMonth = dayOfMonth;
            new TimePickerDialog(view.getContext(),
                    endTimeSetListener,
                    calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE), true).show();
        }
    };



//=============================================================================
//=============================================================================
//=============================================================================
//=============================================================================
//===========================ON CREATE================================================
//=============================================================================
//=============================================================================
//=============================================================================
@SuppressLint("ClickableViewAccessibility")
@Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_shedule);

    textViewInfoJob= findViewById(R.id.textViewInfoJob);
    textViewInfoJob2= findViewById(R.id.textViewInfoJob2);
    editTextStart = findViewById(R.id.editTextStart);
    editTextEnd = findViewById(R.id.editTextEnd);
    buttonOk = findViewById(R.id.buttonOk);


    editTextStart.setOnTouchListener(new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_UP) {
               calendar.setTime(new Date());
                new DatePickerDialog(v.getContext(), startDateSetListener,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();

            }
            return true;
        }
    });

    editTextEnd.setOnTouchListener(new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                calendar.setTime(new Date());
                new DatePickerDialog(v.getContext(), endDateSetListener,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();

            }
            return true;

        }
    });

//==========================================================
//==========================================================
//==========================BUTTON OK================================
//==========================================================
//==========================================================
//==========================================================
    buttonOk.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {  //чтобы не вылетел exeption
                calendar.setTime(new Date());

                Date startDate = EVENT_DATE.parse(editTextStart.getText().toString());
                Date endDate = EVENT_DATE.parse(editTextEnd.getText().toString());

                long startTimeMillis = startDate.getTime() - calendar.getTimeInMillis();
                long endTimeMiliis = endDate.getTime() - calendar.getTimeInMillis();

                String a = String.valueOf(DateFormat.getDateInstance((int) startTimeMillis));
                String b = String.valueOf(calendar.getTimeInMillis());




                JobScheduler jobScheduler = (JobScheduler) getApplicationContext().getSystemService(Context.JOB_SCHEDULER_SERVICE);
                ComponentName componentName = new ComponentName(v.getContext(), SheduleJobsService.class);//для системы

                JobInfo startJobInfo = new JobInfo.Builder(1, componentName).setMinimumLatency(startTimeMillis).build();
                JobInfo endJob = new JobInfo.Builder(2, componentName).setMinimumLatency(endTimeMiliis).build();


                int firstJob = jobScheduler.schedule(startJobInfo);
                int secondJob = jobScheduler.schedule(endJob);

                jobScheduler.schedule(startJobInfo);
                jobScheduler.schedule(endJob);


                if (firstJob == JobScheduler.RESULT_SUCCESS && secondJob == JobScheduler.RESULT_SUCCESS) {
                    MyToast.makeText(SheduleActivity.this, "Job Sheduled", MyToast.LENGTH_SHORT);
                    writeInfo("Job Sheduled","");
                }
                MyToast.makeText(SheduleActivity.this, "Не вылетело", MyToast.LENGTH_SHORT).show();
                writeInfo(a, b);

            }
                catch (Throwable e) {
                e.printStackTrace();
                String.valueOf(Log.d("TAG", "Throwable"));
                Log.d("TAG", "Throwable");
                writeInfo("Throwable",String.valueOf(Log.d("TAG", "Throwable")));
            }
                finally {
                Log.d("TAG", "Finnaly");
                writeInfo("Finnaly","");
            }
        }
     });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate( R.menu.shedule_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

//================================onOptionsItemSelected===============================
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.item_startjob_notify://1
              Toast.makeText(this, "Запустили нотификацию если включена зарядка", Toast.LENGTH_SHORT).show();
                try {
                    Date dateStart = EVENT_DATE.parse(editTextStart.getText().toString());
                    String time = dateStart.toString();
                    Log.d("start date ", time );

                    ComponentName component = new ComponentName(getApplicationContext(),SheduleJobsService.class);
                    JobInfo jobInfo = new JobInfo.Builder(4,component).setRequiresCharging(true).build();
                    JobScheduler jobScheduler = (JobScheduler) getApplicationContext().getSystemService(Context.JOB_SCHEDULER_SERVICE);
                    jobScheduler.schedule(jobInfo);

                } catch (ParseException e) {
                    e.printStackTrace();
                    Log.d("ParseException", String.valueOf(e));
                }
                break;
            case  R.id.item_startjob://2


            try {

                Date dateStart = EVENT_DATE.parse(editTextStart.getText().toString());
                long da = dateStart.getTime();
                String time = dateStart.toString();
                ComponentName component = new ComponentName(getApplicationContext(),SheduleJobsService.class);
                JobInfo jobInfo = new JobInfo.Builder(3,component).setRequiresCharging(true).build();
                JobScheduler jobScheduler = (JobScheduler) getApplicationContext().getSystemService(Context.JOB_SCHEDULER_SERVICE);
                jobScheduler.schedule(jobInfo);

            } catch (ParseException e) {
                e.printStackTrace();
                Log.d("ParseException", String.valueOf(e));

            }
        }
        return super.onOptionsItemSelected(item);
    }
//================================onOptionsItemSelected===============================
}

