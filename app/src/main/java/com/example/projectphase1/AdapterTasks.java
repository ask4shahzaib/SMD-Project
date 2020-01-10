package com.example.projectphase1;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.content.Context.CONNECTIVITY_SERVICE;

public class AdapterTasks extends RecyclerView.Adapter<AdapterTasks.MyViewHolder> {


    Context mcontext;
    List<ClassTasks> jobsClassList;
    EditText text;
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("tasks");
    EditText editText;
    Dialog dialog;
    ProgressBar progressBar;
    private final String CHANNEL_ID = "Personal Notification";
    private final int NOTIFICATION_ID = 1;
    ClassMyTasksFB classMyTasksFB;
    public AdapterTasks(Context mcontext, List<ClassTasks> jobsClassList,ProgressBar p) {
        this.mcontext = mcontext;
        this.jobsClassList = jobsClassList;
        progressBar=p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(mcontext).inflate(R.layout.item_worker_task,parent,false);
        final MyViewHolder myViewHolder=new MyViewHolder(view);

        dialog=new Dialog(mcontext);
        dialog.setContentView(R.layout.dialog_task);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;



        myViewHolder.linearLayout_Job_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //   TextView rID=(TextView) dialog.findViewById(R.id.rIDd);
                final TextView rName=(TextView) dialog.findViewById(R.id.dialog_task_name);
                ImageView rImage=(ImageView)dialog.findViewById(R.id.job_request_image);
                //   rID.setText(""+jobsClassList.get(myViewHolder.getAdapterPosition()).getJobId());

                rName.setText(""+jobsClassList.get(myViewHolder.getAdapterPosition()).getJobName());
                Picasso.with(mcontext).load(jobsClassList.get(myViewHolder.getAdapterPosition()).getJobPhoto()).into( rImage);
                dialog.show();
                text=dialog.findViewById(R.id.dialogue_task_detail);
                editText=dialog.findViewById(R.id.dialogue_Task_location);
                final Button dialogueButton=(Button) dialog.findViewById(R.id.dialog_task_btn);
                dialogueButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(TextUtils.isEmpty(editText.getText()) ||TextUtils.isEmpty(text.getText()))
                        {
                            Toast.makeText(mcontext,"Enter the proper required details",Toast.LENGTH_SHORT).show();
                        }
                        else {

                            if(isConnectedToInternet()) {

                                String id = databaseReference.push().getKey();
                                classMyTasksFB = new ClassMyTasksFB(jobsClassList.get(myViewHolder.getAdapterPosition()).getJobPhoto()
                                        , jobsClassList.get(myViewHolder.getAdapterPosition()).getJobId()
                                        , jobsClassList.get(myViewHolder.getAdapterPosition()).getJobName()
                                        , jobsClassList.get(myViewHolder.getAdapterPosition()).getJobammount()
                                        , id
                                        , text.getText().toString()
                                        , getDefaults("username",mcontext)
                                        , editText.getText().toString(), getDate());
                                databaseReference.child(id).setValue(classMyTasksFB);
                                
                                Toast.makeText(mcontext, "the request for task " + rName.getText() + " has sent", Toast.LENGTH_SHORT).show();
                                dialog.hide();
                                text.getText().clear();
                                editText.getText().clear();
                                show_notifiication();
                             //   displayNotify("Your Task has been Register. Worker will be at your place with in 24 Hours");

                            }
                            else
                            {
                                displayNotify("Request cancelled ....Check your INTERNET CONNECTION");
                            }
                        }
                    }
                });
            }
        });
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.job_ammount.setText(""+jobsClassList.get(position).getJobammount());
        holder.job_name.setText(jobsClassList.get(position).getJobName());
        Picasso.with(mcontext).load(jobsClassList.get(position).getJobPhoto()).into(  holder.job_image);
        progressBar.setVisibility(View.GONE);
    }

    public String getDate()
    {
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c);
        return formattedDate;
    }
    @Override
    public int getItemCount() {
        return jobsClassList.size();
    }

    public static String getDefaults(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        private LinearLayout linearLayout_Job_item;
        private TextView job_name;
        private ImageView job_image;
        private TextView job_ammount;
        public MyViewHolder( View itemView) {
            super(itemView);

            linearLayout_Job_item=(LinearLayout)itemView.findViewById(R.id.job_item_id);
            job_name= (TextView)itemView.findViewById(R.id.job_namee_id);
            job_image= (ImageView) itemView.findViewById(R.id.job_item_imagee);
            job_ammount= (TextView) itemView.findViewById(R.id.job_item_ammount);

        }
    }

    public boolean isConnectedToInternet(){
        boolean have_WIFI = false;
        boolean have_MData = false;

        ConnectivityManager connectivityManager =(ConnectivityManager) mcontext.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfos = connectivityManager.getAllNetworkInfo();

        for(NetworkInfo info:networkInfos)
        {
            if(info.getTypeName().equalsIgnoreCase("WIFI"))
                if(info.isConnected())
                    have_WIFI=true;
            if(info.getTypeName().equalsIgnoreCase("MOBILE"))
                if(info.isConnected())
                    have_MData=true;
        }
        return have_MData||have_WIFI;
    }
    public void displayNotify(String s)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Personal Notification";
            String description = "NOtification";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            notificationChannel.setDescription(description);

            NotificationManager notificationManager = (NotificationManager) mcontext.getSystemService(mcontext.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder b = new NotificationCompat.Builder(mcontext, CHANNEL_ID);
        b.setContentTitle("E-Worker");
        b.setSmallIcon(R.drawable.app_icon);
        b.setContentText(s);
        b.setSound(alarmSound);
        b.setPriority(NotificationCompat.PRIORITY_MAX
        );

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(mcontext);
        notificationManagerCompat.notify(NOTIFICATION_ID, b.build());
    }

    public void show_notifiication()
    {
        AlarmManager alarmManager = (AlarmManager)mcontext.getSystemService(Context.ALARM_SERVICE);
        Intent notificationIntent = new Intent(mcontext, AlarmReceiver.class);
        PendingIntent broadcast = PendingIntent.getBroadcast(mcontext, 100, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, 5);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), broadcast);
    }

}
