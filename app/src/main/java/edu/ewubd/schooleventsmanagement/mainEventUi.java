package edu.ewubd.schooleventsmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class mainEventUi extends AppCompatActivity {


    FirebaseAuth mAuth;
    private ListView listView;
    DatabaseReference databaseReference;
    private List<DB> eventlist;
    private customadopter customadopter;

    private Button ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_event_ui);
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("event");
        eventlist = new ArrayList<>();
        customadopter = new customadopter(mainEventUi.this,eventlist);
        listView = findViewById(R.id.listviewId);
        ref = findViewById(R.id.ref);
        ref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainEventUi.this,mainEventUi.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onStart() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                eventlist.clear();
                for (DataSnapshot dataSnapshot1 :snapshot.getChildren())
                {
                    DB db = dataSnapshot1.getValue(DB.class);
                    eventlist.add(db);
                }


                listView.setAdapter(customadopter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        super.onStart();
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
        Intent intent = new Intent(mainEventUi.this,Login_page.class);
        startActivity(intent);
        }

        return super.onOptionsItemSelected(item);

    }
}
