package com.sushant.ball;

import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private static final String TAG = "MainActivity";
    int width;
    int height;
    ImageView b;
    int timestamp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b= (ImageView) findViewById(R.id.ball);
        timestamp=0;
        Display display =    getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        width = size.x;
        height = size.y;

        Log.d("123", "onCreate: width="+width+" height= "+height);

        SensorManager sensorManager= (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor accel=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this,accel,SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(timestamp==0){
            timestamp= SystemClock.uptimeMillis()
        }
       if(event.values[0]>2||event.values[0]<-2||event.values[1]>2||event.values[1]<-2) {
            float x, y;
            x = (((event.values[0] - 10) / (-20)) * width);
            y = (((event.values[1] ) / 20) * height);
            Log.d(TAG, "onSensorChanged: x="+x);
            Log.d(TAG, "onSensorChanged: y="+y);
            Log.d(TAG, "onSensorChanged: original y="+event.values[1]);
            b.setX(x);
             b.setY(y);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}
