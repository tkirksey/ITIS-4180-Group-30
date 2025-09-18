package com.example.assignment03;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProfileActivity extends AppCompatActivity {

    TextView tvNamePr, tvEmailPr, tvRolePr;
    User usr = null;

    private ActivityResultLauncher<Intent> startForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getData() != null && result.getResultCode() == RESULT_OK){
                usr = (User) result.getData().getSerializableExtra(User.KEY);
                updateFields();
            } else {
                usr = new User("ERROR", "ERROR", "ERROR");
            }
        }
    }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvNamePr = findViewById(R.id.tvNamePr);
        tvEmailPr = findViewById(R.id.tvEmailPr);
        tvRolePr = findViewById(R.id.tvRolePr);

        if(getIntent() != null && getIntent().hasExtra(User.KEY)){
            usr = (User) getIntent().getSerializableExtra(User.KEY);

            updateFields();

        }

        findViewById(R.id.buttonUpdate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextActivity = new Intent(ProfileActivity.this, EditUserActivity.class);
                nextActivity.putExtra(User.KEY, usr);
                startForResult.launch(nextActivity);
            }
        });

    }

    private void updateFields(){
        tvNamePr.setText(usr.getName());
        tvEmailPr.setText(usr.getEmail());
        tvRolePr.setText(usr.getRole());
    }
}