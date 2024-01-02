package edu.ewubd.schooleventsmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class StudentOrTeacher extends AppCompatActivity {
    ImageView teacher,student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_or_teacher);
        teacher = findViewById(R.id.teacher);
        student = findViewById(R.id.student);

       student.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(StudentOrTeacher.this,Signup_page.class);
               startActivity(intent);
           }
       });
        teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(StudentOrTeacher.this,Signup_teacher_page.class);
                startActivity(intent2);
            }
        });
    }
}