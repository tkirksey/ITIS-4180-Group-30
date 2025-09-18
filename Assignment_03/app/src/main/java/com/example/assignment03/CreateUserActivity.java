package com.example.assignment03;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.Serializable;

public class CreateUserActivity extends AppCompatActivity {

    EditText etNameUI, etEmailUI;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_user);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etNameUI = findViewById(R.id.etNameUI);
        etEmailUI = findViewById(R.id.etEmailUI);
        radioGroup = findViewById(R.id.radioGroup);

        findViewById(R.id.buttonNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = etNameUI.getText().toString();

                if(name.isEmpty()){
                    Toast.makeText(CreateUserActivity.this,"Enter a name please.", Toast.LENGTH_SHORT).show();
                    return;
                }

                String email = etEmailUI.getText().toString();

                if(email.isEmpty()){
                    Toast.makeText(CreateUserActivity.this,"Enter a email please.", Toast.LENGTH_SHORT).show();
                    return;
                }

                int radioID = radioGroup.getCheckedRadioButtonId();

                if(radioID == -1){
                    Toast.makeText(CreateUserActivity.this,"Select a role please.", Toast.LENGTH_SHORT).show();
                    return;
                }

                String role = "ROLE";

                if(radioID == R.id.rbEmployee){
                    role = "Employee";
                } else if(radioID == R.id.rbStudent){
                    role = "Student";
                } else if(radioID == R.id.rbOther){
                    role = "Other";
                }

                User usr = new User(name, email, role);

                Intent nextActivity = new Intent(CreateUserActivity.this, ProfileActivity.class);
                nextActivity.putExtra(User.KEY, usr);
                startActivity(nextActivity);

            }
        });

    }
}