package com.khadeeja.sapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


public class Resetpassword extends AppCompatActivity {

    private EditText resetEmail;
    private Button resetDone;
    private Button resetBack;
    private ProgressBar resetProgress;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpassword);

        resetEmail = findViewById(R.id.reset_email);
        resetDone = findViewById(R.id.reset_done);
        resetBack = findViewById(R.id.reset_back);
        resetProgress = findViewById(R.id.reset_progress);

        mAuth = FirebaseAuth.getInstance();

        resetBack.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {

                Intent resetIntent = new Intent(Resetpassword.this, loginActivity.class);
                startActivity(resetIntent);
                finish();
            }
        });


        resetDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = resetEmail.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplication(), "Enter your registered email", Toast.LENGTH_LONG).show();

                    return;

                }

                resetProgress.setVisibility(View.VISIBLE);

                mAuth.sendPasswordResetEmail(email)

                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if (task.isSuccessful()) {
                                    Toast.makeText(Resetpassword.this, "A link has been sent to reset your email", Toast.LENGTH_LONG).show();

                                    finish();

                                } else {
                                    Toast.makeText(Resetpassword.this, "Failed to send reset email", Toast.LENGTH_LONG).show();
                                }

                                resetProgress.setVisibility(View.INVISIBLE);
                            }
                        });
                    }
            });



        }
}
