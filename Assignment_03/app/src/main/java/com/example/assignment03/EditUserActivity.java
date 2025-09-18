package com.example.assignment03;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EditUserActivity extends AppCompatActivity {

    EditText etNameUI, etEmailUI;
    RadioGroup radioGroup;

    User usr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_user);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etNameUI = findViewById(R.id.etNameEUI);
        etEmailUI = findViewById(R.id.etEmailEUI);
        radioGroup = findViewById(R.id.rg);

        if(getIntent() != null && getIntent().hasExtra(User.KEY)){
            usr = (User) getIntent().getSerializableExtra(User.KEY);
        } else {
            usr = new User("ERROR", "ERROR", "ERROR");
        }

        etNameUI.setText(usr.getName());
        etEmailUI.setText(usr.getEmail());

        if(usr.getRole().compareTo("Student") == 0){
            radioGroup.check(R.id.radioButton);
        } else if(usr.getRole().compareTo("Employee") == 0){
            radioGroup.check(R.id.radioButton2);
        } else if(usr.getRole().compareTo("Other") == 0){
            radioGroup.check(R.id.radioButton3);
        }

        findViewById(R.id.buttonCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra(User.KEY, usr);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });

        findViewById(R.id.buttonSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etNameUI.getText().toString();

                if(name.isEmpty()){
                    Toast.makeText(EditUserActivity.this,"Enter a name please.", Toast.LENGTH_SHORT).show();
                    return;
                }

                String email = etEmailUI.getText().toString();

                if(email.isEmpty()){
                    Toast.makeText(EditUserActivity.this,"Enter a email please.", Toast.LENGTH_SHORT).show();
                    return;
                }

                int radioID = radioGroup.getCheckedRadioButtonId();

                if(radioID == -1){
                    Toast.makeText(EditUserActivity.this,"Select a role please.", Toast.LENGTH_SHORT).show();
                    return;
                }

                String role = "ROLE";

                if(radioID == R.id.radioButton){
                    role = "Student";
                } else if(radioID == R.id.radioButton2){
                    role = "Employee";
                } else if(radioID == R.id.radioButton3){
                    role = "Other";
                }

                User modifiedUsr = new User(name, email, role);

                Intent intent = new Intent();
                intent.putExtra(User.KEY, modifiedUsr);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }
}