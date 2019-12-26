package com.example.pujarani.drawingapp;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button button;

    FragmentManager manager;
    FragmentTransaction transaction;
    boolean viewCoordinates = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        nextFragment(1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!viewCoordinates) {
                    nextFragment(2);
                    viewCoordinates = true;
                    button.setText("Back");
                } else {
                    nextFragment(1);
                    viewCoordinates = false;
                    button.setText("Display Coordinates");
                }
            }
        });
    }

    public void nextFragment(int i) {
        manager = getFragmentManager();
        transaction = manager.beginTransaction();
        switch (i) {
            case 1:
                transaction.replace(R.id.frameLayout, new DrawFragment(), null);
                break;
            case 2:
                transaction.replace(R.id.frameLayout, new ListFragment(), null);
                break;
        }
        transaction.commit();

    }


}
