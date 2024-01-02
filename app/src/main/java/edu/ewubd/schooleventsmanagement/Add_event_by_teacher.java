package edu.ewubd.schooleventsmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
// Import the necessary Firebase database classes
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Add_event_by_teacher extends AppCompatActivity {
    FirebaseAuth mAuth;
    private RadioButton radiooff,radioon;
    private EditText eteventname,eventdate;
    private Button btnupdate;
    private DatabaseReference databaseReference;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event_by_teacher);

        //databaseReference = FirebaseDatabase.getInstance().getReference("event");
        databaseReference = database.getInstance().getReference("event");
        mAuth = FirebaseAuth.getInstance();

        radiooff = findViewById(R.id.radiooff);
        radioon = findViewById(R.id.radioon);
        eteventname = findViewById(R.id.eteventname);
        eventdate = findViewById(R.id.eventdate);
        btnupdate = findViewById(R.id.btnupdate);

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean allcjak = true;
                String evtname = eteventname.getText().toString().trim();
                if (evtname.isEmpty()){
                    allcjak = false;
                    eteventname.setError("Event name missing");
                }
                String evdate = eventdate.getText().toString().trim();
                if (evtname.isEmpty()){
                    allcjak = false;
                    eventdate.setError("Event date missing");
                }
                String rad = "";
                if (radiooff.isChecked()){
                    rad = "off";
                }
                else if (radioon.isChecked()){
                    rad = "on";
                }
                else {
                    rad ="";
                }
                if (allcjak==true){
                    savedata(rad);
                }
            }
        });

    }
    public void savedata(String rad){
        String evtname = eteventname.getText().toString().trim();
        String evdate = eventdate.getText().toString().trim();
        String key = databaseReference.push().getKey();

        DB db = new DB(evtname,evdate,rad);
        //databaseReference.setValue("pohela boisakh");
        //
        //
        databaseReference.child(key).setValue(db);
        Toast.makeText(getApplicationContext(),"Add to server",Toast.LENGTH_SHORT).show();


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.nav_logout){
            FirebaseAuth.getInstance().signOut();
            finish();
            Intent intent = new Intent(Add_event_by_teacher.this,Login_page.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);

    }
}