package com.example.a18pr_nastygl_22106;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity3 extends Activity {

    // имена атрибутов для Map
    final String ATTRIBUTE_NAME_TEXT = "text";
    final String ATTRIBUTE_NAME_PB = "pb";
    final String ATTRIBUTE_NAME_LL = "ll";
    Button bt;

    ListView lvSimple;

    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        bt = findViewById(R.id.button3);
        bt.setOnClickListener(this::OnClick);

        // массив данных
        int load[] = { 41, 48, 22, 35, 30, 67, 51, 88 };

        // упаковываем данные в понятную для адаптера структуру
        ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>(
                load.length);
        Map<String, Object> m;
        for (int i = 0; i < load.length; i++) {
            m = new HashMap<String, Object>();
            m.put(ATTRIBUTE_NAME_TEXT, "Day " + (i+1) + ". Load: " + load[i] + "%");
            m.put(ATTRIBUTE_NAME_PB, load[i]);
            m.put(ATTRIBUTE_NAME_LL, load[i]);
            data.add(m);
        }

        // массив имен атрибутов, из которых будут читаться данные
        String[] from = { ATTRIBUTE_NAME_TEXT, ATTRIBUTE_NAME_PB,
                ATTRIBUTE_NAME_LL };
        // массив ID View-компонентов, в которые будут вставлять данные
        int[] to = { R.id.tvLoad, R.id.pbLoad, R.id.llLoad };

        // создаем адаптер
        SimpleAdapter sAdapter = new SimpleAdapter(this, data, R.layout.activity_item3,
                from, to);
        // Указываем адаптеру свой биндер
        sAdapter.setViewBinder(new MyViewBinder());

        // определяем список и присваиваем ему адаптер
        lvSimple = (ListView) findViewById(R.id.lvSimple);
        lvSimple.setAdapter(sAdapter);
    }

    class MyViewBinder implements SimpleAdapter.ViewBinder {

        int red;
        int orange;
        int green;

        public MyViewBinder(){
            red = getResources().getColor(R.color.Red);
            orange = getResources().getColor(R.color.Orange);
            green = getResources().getColor(R.color.Green);
        }

        @Override
        public boolean setViewValue(View view, Object data, String textRepresentation) {
            int i = 0;
            if (view.getId() == R.id.llLoad) {
                i = ((Integer) data).intValue();
                if (i < 40) view.setBackgroundColor(green);
                else if (i < 70) view.setBackgroundColor(orange);
                else view.setBackgroundColor(red);
                return true;
            } else if (view.getId() == R.id.pbLoad) {
                i = ((Integer) data).intValue();
                ((ProgressBar)view).setProgress(i);
                return true;
            }
            return false;
        }

    }
    public void OnClick(View v){
        Intent intent = new Intent(this, MainActivity4.class);
        startActivity(intent);
    }
}