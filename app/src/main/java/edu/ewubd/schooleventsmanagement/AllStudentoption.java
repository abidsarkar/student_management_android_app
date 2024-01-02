package edu.ewubd.schooleventsmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class AllStudentoption extends AppCompatActivity {
    private RelativeLayout rlevent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_studentoption);
        rlevent = findViewById(R.id.rlevent);
        rlevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllStudentoption.this,mainEventUi.class);
                startActivity(intent);
            }
        });
    }
}