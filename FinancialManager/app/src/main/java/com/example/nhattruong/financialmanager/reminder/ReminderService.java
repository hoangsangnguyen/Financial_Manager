package com.example.nhattruong.financialmanager.reminder;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.JobIntentService;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.example.nhattruong.financialmanager.MainApplication;
import com.example.nhattruong.financialmanager.R;
import com.example.nhattruong.financialmanager.interactor.api.ApiManager;
import com.example.nhattruong.financialmanager.interactor.api.network.ApiCallback;
import com.example.nhattruong.financialmanager.interactor.api.network.RestError;
import com.example.nhattruong.financialmanager.interactor.api.response.TodoResponse;
import com.example.nhattruong.financialmanager.interactor.prefer.PreferManager;
import com.example.nhattruong.financialmanager.interactor.sqlite.SQLiteManager;
import com.example.nhattruong.financialmanager.model.Todo;
import com.example.nhattruong.financialmanager.mvp.home.HomeActivity;
import com.example.nhattruong.financialmanager.utils.DateUtils;

import java.util.List;

import javax.inject.Inject;

public class ReminderService extends JobIntentService {

    private static final int JOB_ID = 1000;

    public static final String ACTION_CLEAR_EVENT_REMINDER = "com.example.nhattruong.financialmanager.action.CLEAR_EVENT_REMINDER";
    public static final String ACTION_INIT_EVENT_REMINDER = "com.example.nhattruong.financialmanager.action.INIT_EVENT_REMINDER";

    @Inject
    SQLiteManager mSQLiteManager;
    @Inject
    ApiManager mApiManager;

    public ReminderService() {
        MainApplication.getAppComponent().inject(this);
    }

    public static void enqueueWork(Context context, Intent work) {
        enqueueWork(context, ReminderService.class, JOB_ID, work);
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        processMessage();
    }

    private void processMessage() {
        mApiManager.getTodoNext(mSQLiteManager.getUser().getId(), new ApiCallback<TodoResponse>() {
            @Override
            public void success(TodoResponse res) {
                sendNotification(res.getList());
            }

            @Override
            public void failure(RestError error) {
                Toast.makeText(ReminderService.this, "Get Todo Failed!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void sendNotification(Todo todo) {
        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationManager mNM = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("channel", "channel", NotificationManager.IMPORTANCE_DEFAULT);
            mNM.createNotificationChannel(channel);
        }

        Intent myIntent = new Intent(this , HomeActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, myIntent, 0);

        Notification notification = new NotificationCompat.Builder(this, "channel")
                    .setSmallIcon(android.R.drawable.ic_popup_reminder)
                    .setContentTitle(getString(R.string.app_name))
                    .setContentText(todo.getName() + " on " + DateUtils.formatFullDatePeriods(todo.getDate()))
                    .setContentIntent(contentIntent)
                    .setAutoCancel(true)
                    .setSound(sound)
                    .build();
            mNM.notify(10, notification);
    }

    private void sendNotification(List<Todo> listTodo) {

//        cancelAlarms(getApplicationContext());

        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationManager mNM = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("channel", "channel", NotificationManager.IMPORTANCE_DEFAULT);
            mNM.createNotificationChannel(channel);
        }

        Intent myIntent = new Intent(this , HomeActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, myIntent, 0);

        Notification notification;

        for (int i = 0; i<listTodo.size(); i++){
            notification = new NotificationCompat.Builder(this, "channel")
                    .setSmallIcon(android.R.drawable.ic_popup_reminder)
                    .setContentTitle(getString(R.string.app_name))
                    .setContentText(listTodo.get(i).getName() + " on " + DateUtils.formatFullDatePeriods(listTodo.get(i).getDate()))
                    .setContentIntent(contentIntent)
                    .setAutoCancel(true)
                    .setSound(sound)
                    .build();
            mNM.notify(i, notification);
        }
    }

   /* private void cancelAlarms(Context context) {
        PendingIntent alarmIntentFirst = createPendingIntent(context, alarms.get(i).getAlarmFirst());
        alarmMgr.cancel(alarmIntentFirst);
    }

    private static PendingIntent createPendingIntent(Context context, int alarmId) {
        Intent intent = new Intent(context, ReminderReceiver.class);
        intent.setAction(ReminderService.ACTION_FIRE_EVENT_REMINDER);
        return PendingIntent.getBroadcast(context, alarmId, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }*/
}
