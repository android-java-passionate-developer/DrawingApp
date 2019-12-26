package com.example.pujarani.drawingapp;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by Puja.Rani on 26-12-2019.
 */

public class ReadWriteClass {

    static ReadWriteClass io = new ReadWriteClass();

    public static ReadWriteClass getInstance() {
        return io;
    }


    void writeToFile(String data, Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("coordinates.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    String readFromFile(Context context) {

        String ret = "";

        try {
            InputStream inputStream = context.openFileInput("coordinates.txt");

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        } catch (FileNotFoundException e) {
            Log.e("IO activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("IO activity", "Can not read file: " + e.toString());
        }

        return ret;
    }
}
