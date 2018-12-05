package com.example.tinashe.savinginstance;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private EditText etName, etPersonName, etPersonGender;
    private TextView tvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //binding views
        etName = findViewById(R.id.etName);
        tvName = findViewById(R.id.tvName);
        etPersonName = findViewById(R.id.etPersonName);
        etPersonGender = findViewById(R.id.etPersonGender);
        Log.d(TAG, "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String data = tvName.getText().toString();
        outState.putString("data", data);

        Log.d(TAG, "onSaveInstanceState: ");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        tvName.setText(savedInstanceState.getString("data"));
        Log.d(TAG, "onRestoreInstanceState: "+ tvName.getText().toString());
    }

    public void doSomething(View view) {
        switch (view.getId()) {
            case R.id.btnChangeText:
                String name = etName.getText().toString();
                tvName.setText(name);
                break;

            case R.id.goToSecond:
                List<Person> personList = new ArrayList<>();
                personList.add(new Person(etPersonName.getText().toString(),
                        etPersonGender.getText().toString()));
                personList.add(new Person(getMessageDigest(etPersonName.getText().toString())));
                        getMessageDigest(etPersonGender.getText().toString());
                Intent intent = new Intent( this, SecondActivity.class);
                Intent.setAction("goToSecond");
                Intent.putParcelableArrayListExtra( "person", (ArrayList<? extends Parcelable>) personList);
                startActivity(intent);
                break;
        }

    }

    private String getMessageDigest(String string) {
        MessageDigest messageDigest = MessageDigest.getInstance("Sha-256");
        messageDigest.reset();
        messageDigest.update(String.getBytes(Charset.forName("UTF-8")));
        StringBuilder hexString = new StringBuilder();
        byte[] messageDigestArray = messageDigest.digest();
        for (int i =0; i < messageDigestArray.length; i++) {
            hexString.append(Intent.toHexString(i:0xFF & messageDigestArray[i]));
        }
        return hexString.toString();
    }
}
