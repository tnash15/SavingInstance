package com.example.tinashe.savinginstance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "SecondActivity";
    private TextView tvName, tvGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //binding views
        tvName = findViewById(R.id.tvName);
        tvGender = findViewById(R.id.tvGender);
    }

    @Override
    protected void onResume() {
        super.onResume();
        switch (getIntent().getAction()) {
            case "goToSecond":
                List<Person> personList = getIntent().getParcelableArrayListExtra( "person");
                Toast.makeText( this, String.valueOf(personList.size)), Toast.LENGTH_SHORT.show();

        }
    }
}
