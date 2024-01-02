package edu.ewubd.schooleventsmanagement;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_page extends AppCompatActivity {

    private EditText username,password,schoolcode;

    private Button loginButton;
    private TextView signupText,callinfo;
    private FirebaseAuth mAuth;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        mAuth = FirebaseAuth.getInstance();
        callinfo = findViewById(R.id.callinfo);
        schoolcode = findViewById(R.id.schoolcode);
        username = findViewById(R.id.username);
        signupText = findViewById(R.id.signupText);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean allchake = true;
                String userna = username.getText().toString();
                if (userna.isEmpty()){
                    username.setError("Email is empty");
                    allchake = false;
                }
                String pass = password.getText().toString();
                if (userna.isEmpty()){
                    password.setError("password is empty");
                    allchake = false;
                }
                String code1 ="ewucse2020";
                String code = schoolcode.getText().toString();
                Boolean allchake2 = false;
                if (code1.equals(code)){
                allchake2 = true;
                }
                if (allchake.equals(false)){
                    Toast.makeText(getApplicationContext(),"Something is missing",Toast.LENGTH_SHORT).show();
                }else {
                    if (allchake2==true){
                        userLoginteacher();
                    }
                    else{
                        userLoginStudent();
                    }
                }


            }
        });
        signupText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_page.this,StudentOrTeacher.class);
                startActivity(intent);
            }
        });

        callinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Login_page.this);
                builder.setMessage("Call to this number?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String phoneNumber = "0171234567";
                        Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
                        startActivity(dialIntent);
                    }
                });
                builder.setNegativeButton("No", null);
                builder.show();
            }
        });

    }
    private void userLoginStudent(){
        String email = username.getText().toString();
        String pass = password.getText().toString();
        mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Intent intent = new Intent(Login_page.this,AllStudentoption.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(),"Invalid email or password",Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
    private void userLoginteacher(){
        String email = username.getText().toString();
        String pass = password.getText().toString();
        mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Intent intent = new Intent(Login_page.this,Add_event_by_teacher.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(),"Invalid email or password or code",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}