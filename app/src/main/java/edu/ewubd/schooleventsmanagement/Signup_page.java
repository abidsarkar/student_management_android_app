package edu.ewubd.schooleventsmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class Signup_page extends AppCompatActivity {
    private TextView login;
    private Button btnregister;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    private EditText etname,etemail,etphone,etpassword,etrepassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);
        mAuth = FirebaseAuth.getInstance();

        login = findViewById(R.id.login);
        btnregister= findViewById(R.id.btnregister);
        etemail= findViewById(R.id.etemail);
        etname= findViewById(R.id.etname);
        etphone= findViewById(R.id.etphone);
        etpassword= findViewById(R.id.etpassword);
        etrepassword= findViewById(R.id.etrepassword);
        progressBar = findViewById(R.id.progressbarId);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent3 = new Intent(Signup_page.this, Login_page.class);
                startActivity(intent3);
            }
        });

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean allchake = true;
                String name = etname.getText().toString();
                if (name.isEmpty()){
                    etname.setError("Name is empty");
                    allchake = false;
                }
                String email = etemail.getText().toString();
                if (email.isEmpty()){
                    etemail.setError("email is empty");
                    allchake = false;
                }
                String phone = etphone.getText().toString();
                if (phone.isEmpty()){
                    etphone.setError("phone is empty");
                    allchake = false;
                }

                String password = etpassword.getText().toString();
                if (password.isEmpty()){
                    etpassword.setError("password is empty");
                    allchake = false;
                }
                String repassword = etrepassword.getText().toString();
                if (repassword.isEmpty()){
                    etrepassword.setError("re enter password is empty");
                    allchake = false;
                }
                if (!password.equals(repassword)){
                    allchake = false;
                    Toast.makeText(getApplicationContext(),"password did not match",Toast.LENGTH_SHORT).show();
                }
                if (allchake==false){
                    Toast.makeText(getApplicationContext(),"Something is missing",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(),"Everything is ok",Toast.LENGTH_SHORT).show();
                    registeacher2();
                }


            }
        });
    }
    private void registeacher2() {
        progressBar.setVisibility(View.VISIBLE);
        String email = etemail.getText().toString();
        String pass = etpassword.getText().toString();
        mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),"Register Successfully",Toast.LENGTH_SHORT).show();

                } else {
                    if (task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(getApplicationContext(),"User Already Register",Toast.LENGTH_SHORT).show();

                    }else {
                        Toast.makeText(getApplicationContext(),"Error:"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });

    }
}