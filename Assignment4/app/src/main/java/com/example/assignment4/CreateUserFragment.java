package com.example.assignment4;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class CreateUserFragment extends Fragment {

    Button btnSubmit;

    EditText etName, etEmail;

    RadioGroup rgRole;

    public CreateUserFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etName = view.findViewById(R.id.create_etNameUI);
        etEmail = view.findViewById(R.id.create_etEmailUI);
        rgRole = view.findViewById(R.id.create_rgRole);
        btnSubmit = view.findViewById(R.id.create_buttonNext);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = etName.getText().toString();

                if(name.isEmpty()){
                    Toast.makeText(getActivity(), "Enter a name please.", Toast.LENGTH_SHORT).show();
                    return;
                }

                String email = etEmail.getText().toString();

                if(email.isEmpty()){
                    Toast.makeText(getActivity(), "Enter a email please.", Toast.LENGTH_SHORT).show();
                    return;
                }

                int roleId = rgRole.getCheckedRadioButtonId();

                if(roleId == -1){
                    Toast.makeText(getActivity(), "Select a role please.", Toast.LENGTH_SHORT).show();
                    return;
                }

                String role = "[ERROR]";

                if(roleId == R.id.create_rbStudent){
                    role = "Student";
                } else if(roleId == R.id.create_rbEmployee){
                    role = "Employee";
                } else if (roleId == R.id.create_rbOther){
                    role = "Other";
                }

                User usr = new User(name, email, role);

                // send user back to main activity
                mListener.sendUser(usr);
                mListener.gotoProfile();

            }
        });

    }

    CreateUserListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (CreateUserListener) context;
    }

    public interface CreateUserListener {
        void sendUser(User usr);
        void gotoProfile();
    }
}