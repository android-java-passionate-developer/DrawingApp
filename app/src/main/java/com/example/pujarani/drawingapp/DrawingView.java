package com.example.pujarani.drawingapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Puja.Rani on 26-12-2019.
 */

public class DrawingView extends View {

    Point p1 = null, p2 = null;
    double x = 0.0;
    double y = 0.0;
    public Paint paint;
    Context context;
    ReadWriteClass io;


    public DrawingView(Context context) {
        super(context);
        this.context = context;

        setFocusable(true);
        setFocusableInTouchMode(true);
        setUpDrawPaint();
        io = ReadWriteClass.getInstance();

    }

    private void setUpDrawPaint() {
        paint = new Paint();
        paint.setColor(context.getResources().getColor(R.color.colorPrimary));
        paint.setAntiAlias(true);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x = event.getX();
        y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                p1 = new Point((int) x, (int) y);
                return true;
            case MotionEvent.ACTION_UP:
                p2 = new Point((int) x, (int) y);
                invalidate();
                break;
            default:
                return false;

        }

        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (p1 != null && p2 != null) {
            getCoordinates(p1.x, p1.y, p2.x, p2.y);
            canvas.drawRect(p1.x, p1.y, p2.x, p2.y, paint);
        }
    }

    public void getCoordinates(int p1x, int p1y, int p2x, int p2y) {
        try {


            JSONObject obj = new JSONObject();
            JSONArray array = new JSONArray();

            JSONArray finaldata = new JSONArray();

            array.put("(" + p1x + "," + p1y + ")");
            array.put("(" + p1x + "," + p2y + ")");
            array.put("(" + p2x + "," + p1y + ")");
            array.put("(" + p2x + "," + p2y + ")");

            obj.put("Coordinates:", array.toString());

            String str = io.readFromFile(context);

            finaldata = new JSONArray(str);
            finaldata.put(obj);

            io.writeToFile(finaldata.toString(), getContext());

            Log.d("Puja", "data stored now:" + str);

        } catch (
                Exception e) {
            e.printStackTrace();
        }
    }
}
