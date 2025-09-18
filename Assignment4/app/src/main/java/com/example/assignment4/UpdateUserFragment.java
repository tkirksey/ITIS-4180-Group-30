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

public class UpdateUserFragment extends Fragment {

    static final public String ARG_USER = "ARG_USER";

    private User mUser;

    Button btnSubmit, btnCancel;
    EditText etName, etEmail;
    RadioGroup rgRole;

    public static UpdateUserFragment newInstance(User usr){
        UpdateUserFragment fragment = new UpdateUserFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_USER, usr);
        fragment.setArguments(args);
        return fragment;
    }

    public UpdateUserFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            mUser = (User) getArguments().getSerializable(ARG_USER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etName = view.findViewById(R.id.update_etNameUI);
        etEmail = view.findViewById(R.id.update_etEmailUI);
        rgRole = view.findViewById(R.id.update_rgRole);

        btnSubmit = view.findViewById(R.id.update_buttonSubmit);
        btnCancel = view.findViewById(R.id.update_buttonCancel);

        etName.setText(mUser.getName());
        etEmail.setText(mUser.getEmail());

        switch (mUser.getRole()) {
            case "Student":
                rgRole.check(R.id.update_rbStudent);
                break;
            case "Employee":
                rgRole.check(R.id.update_rbEmployee);
                break;
            case "Other":
                rgRole.check(R.id.update_rbOther);
                break;
        }

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // return user back to main activity
                mListener.sendUser(mUser);
                mListener.backProfile();

            }
        });

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

                String role = "[Role]";

                if(roleId == R.id.update_rbStudent){
                    role = "Student";
                } else if(roleId == R.id.update_rbEmployee){
                    role = "Employee";
                } else if(roleId == R.id.update_rbOther){
                    role = "Other";
                }

                mUser.setEmail(email);
                mUser.setName(name);
                mUser.setRole(role);

                // send user back to main activity
                mListener.sendUser(mUser);
                mListener.backProfile();

            }
        });

    }

    UpdateUserListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (UpdateUserListener) context;
    }

    public interface UpdateUserListener {
        void sendUser(User usr);
        void backProfile();
    }
}