package edu.uncc.assignment06;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import edu.uncc.assignment06.databinding.FragmentCreateTaskBinding;

public class CreateTaskFragment extends Fragment {

    public CreateTaskFragment() {
        // Required empty public constructor
    }

    private String date = "N/A";
    public static final String DATE_ARG = "DATE_ARG";

    public void setDate(String date){
        this.date = date;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    FragmentCreateTaskBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCreateTaskBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Create Task");

        if(getArguments() != null && getArguments().getString(DATE_ARG) != null){
            date = getArguments().getString(DATE_ARG);
        }

        binding.textViewDate.setText(date);

        binding.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.goBackToTaskMenu(null);
            }
        });

        binding.buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.editTextTaskName.getText().toString();
                String date = binding.textViewDate.getText().toString();
                String priority = "";

                if(binding.radioGroup.getCheckedRadioButtonId() == R.id.radioButtonLow){
                    priority = "Low";
                } else if (binding.radioGroup.getCheckedRadioButtonId() == R.id.radioButtonMedium) {
                    priority = "Medium";
                } else if (binding.radioGroup.getCheckedRadioButtonId() == R.id.radioButtonHigh){
                    priority = "High";
                }

                if (name.isEmpty()){
                    Toast.makeText(getActivity(), "Enter Valid Name", Toast.LENGTH_SHORT).show();
                } else if (date.equalsIgnoreCase("N/A")) {
                    Toast.makeText(getActivity(), "Enter Valid Date", Toast.LENGTH_SHORT).show();
                } else if (binding.radioGroup.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getActivity(), "Select Priority", Toast.LENGTH_SHORT).show();
                } else{
                    mListener.goBackToTaskMenu(new Task(name, date, priority));
                }

            }
        });

        binding.buttonSetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.gotoSetDate();
            }
        });
    }

    CreateTaskListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (CreateTaskListener) context;
    }

    interface CreateTaskListener{
        void goBackToTaskMenu(Task task);
        void gotoSetDate();
    }
}