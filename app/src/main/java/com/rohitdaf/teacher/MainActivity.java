package com.rohitdaf.teacher;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.robertlevonyan.views.customfloatingactionbutton.FloatingActionButton;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    //   static FirebaseApp firebaseApp;
    RecyclerView mainIterate;
    DatabaseReference databaseReference;
    TextView namefetch, rollFetch, reasonFetch;
    FloatingActionButton b1;
    TextView hintText;

    Button accept, decline;
    public String name;
    public String roll;
    public String codename;
    public String request;
    public String reason;
    public String time;
    public String lectruAssigned;
    public Boolean acceptance;

    String emailFetchs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);
        b1 = findViewById(R.id.button);
        namefetch = findViewById(R.id.nameFetch);
        rollFetch = findViewById(R.id.rollFetch);
        reasonFetch = findViewById(R.id.reasonFetch);
        hintText = findViewById(R.id.hintText);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Requests Super");
        databaseReference.keepSynced(true);
        mainIterate = findViewById(R.id.mainIterate);
        mainIterate.setHasFixedSize(true);
        mainIterate.setLayoutManager(new LinearLayoutManager(this));
        decline = findViewById(R.id.btnDecline);
        accept = findViewById(R.id.btnAccept);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insaneFetch();
            }
        });


    }

    public void insaneFetch() {
        FirebaseRecyclerAdapter<DataRefFetch, DataRefFetchViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<DataRefFetch, DataRefFetchViewHolder>(DataRefFetch.class, R.layout.single_item, DataRefFetchViewHolder.class, databaseReference) {


            @Override
            protected void populateViewHolder(DataRefFetchViewHolder dataRefFetchViewHolder, DataRefFetch dataRefFetch, int i) {
                hintText.setVisibility(View.GONE);
                dataRefFetchViewHolder.setName(dataRefFetch.getName());
                dataRefFetchViewHolder.setRoll(dataRefFetch.getRoll());
                dataRefFetchViewHolder.setReason(dataRefFetch.getReason());
                String formated = String.format(dataRefFetch.getTime(), 2, ":");
                dataRefFetchViewHolder.setTime(formated);
                dataRefFetchViewHolder.setCodeName(dataRefFetch.getCodename());
                dataRefFetchViewHolder.setAcceptance(dataRefFetch.getAcceptance());
                dataRefFetchViewHolder.setEmail(dataRefFetch.getEmail());


                //dataRefFetchViewHolder.

                //to send again
                acceptance = dataRefFetch.getAcceptance();
                name = dataRefFetch.getName();
                roll = dataRefFetch.getRoll();
                reason = dataRefFetch.getReason();
                time = dataRefFetch.getTime();
                lectruAssigned = dataRefFetch.getLectruAssigned();
                codename = dataRefFetch.getCodename();
                emailFetchs = dataRefFetch.getEmail();

            }


        };


        mainIterate.setAdapter(firebaseRecyclerAdapter);
    }


    public static class DataRefFetchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        Button acceptBtn, declineBtn;
        Context appContext;
        View mView;


        public DataRefFetchViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            acceptBtn = itemView.findViewById(R.id.btnAccept);
            declineBtn = itemView.findViewById(R.id.btnDecline);

            acceptBtn.setOnClickListener(this);
            declineBtn.setOnClickListener(this);

        }

        public void setName(String name) {
            TextView namefetch = mView.findViewById(R.id.nameFetch);
            namefetch.setText(name);
        }

        public void setRoll(String roll) {
            TextView rollFetch = mView.findViewById(R.id.rollFetch);
            rollFetch.setText(roll);
        }

        public void setReason(String reason) {
            TextView reasonFetch = mView.findViewById(R.id.reasonFetch);
            reasonFetch.setText(reason);
        }

        public void setTime(String time) {
            TextView timeFetch = mView.findViewById(R.id.timeFetch);
            timeFetch.setText(time);
        }

        public void setCodeName(String codeName) {
            TextView codeFetch = mView.findViewById(R.id.codeFetch);
            codeFetch.setText(codeName);

        }

        @SuppressLint("SetTextI18n")
        public Boolean setAcceptance(Boolean acceptance) {
            TextView acceptTextView = mView.findViewById(R.id.acceptanceA);
            if (!acceptance) {

                acceptTextView.setText("Not Accepted Till Now");
            } else {
                acceptTextView.setText("Accepted Already");
            }
            TextView acceptanceFetch = mView.findViewById(R.id.acceptanceFetch);
            acceptanceFetch.setText(acceptance.toString());
            return acceptance;
        }

        public void setEmail(String email){
            TextView emailset = mView.findViewById(R.id.emailFetch);
            emailset.setText(email);
        }


        @RequiresApi(api = Build.VERSION_CODES.O)
        @SuppressLint("CutPasteId")
        @Override
        public void onClick(View view) {
                TextView nameFinal,rollFinal,codeFinal,emailFinal,timeFinal,reasonFinal,acceptanceFinal;
                DatabaseReference updatedata = FirebaseDatabase.getInstance().getReference();


                nameFinal = mView.findViewById(R.id.nameFetch);
                rollFinal =    mView.findViewById(R.id.rollFetch);
                codeFinal =  mView.findViewById(R.id.codeFetch);
                reasonFinal = mView.findViewById(R.id.reasonFetch);
                emailFinal = mView.findViewById(R.id.emailFetch);
                timeFinal = mView.findViewById(R.id.timeFetch);

                String emailFinalCode = encodeUserEmail(emailFinal.getText().toString());
                String name = (nameFinal.getText().toString());
                String roll = (rollFinal.getText().toString());
                String code = (codeFinal.getText().toString());
                String reason = (reasonFinal.getText().toString());
                String time = (timeFinal.getText().toString());
                Vibrator vibrator = (Vibrator) mView.getContext().getSystemService(VIBRATOR_SERVICE);


                DataRefFetch dataRefFetchTrue = new DataRefFetch(name,roll,code,reason,time,true,emailFinalCode);


            switch (view.getId()){

                case  R.id.btnAccept:
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        vibrator.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
                    } else {
                        vibrator.vibrate(50);
                    }
                    updatedata.child("Students").child(emailFinalCode).child("Request").setValue(dataRefFetchTrue);
                    updatedata.child("Requests Super").child(emailFinalCode).removeValue();
                    Toast.makeText(mView.getContext(),"Request Accepted",Toast.LENGTH_SHORT).show();

                    break;

                case R.id.btnDecline:
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE));
                    } else {
                        vibrator.vibrate(200);
                    }
                    updatedata.child("Requests Super").child(emailFinalCode).removeValue();
                    Toast.makeText(mView.getContext(),"Request Declined",Toast.LENGTH_SHORT).show();
                    break;

            }


            }

        }

    static String encodeUserEmail(String userEmail) {
        return userEmail.replace(".", ",");
    }

    static String decodeUserEmail(String userEmail) {
        return userEmail.replace(",", ".");
    }
}
