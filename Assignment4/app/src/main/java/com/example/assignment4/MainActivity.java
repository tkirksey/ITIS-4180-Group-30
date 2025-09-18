package com.example.assignment4;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements
        MainFragment.MainFragmentListener,
        DisplayUserFragment.DisplayUserFragmentListener,
        CreateUserFragment.CreateUserListener,
        UpdateUserFragment.UpdateUserListener
{

    User usr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.main, new MainFragment())
                .commit();
    }

    @Override
    public void gotoCreateUser() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main, new CreateUserFragment())
                .commit();
    }

    @Override
    public void gotoUpdateUser() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main, UpdateUserFragment.newInstance(usr))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void sendUser(User usr) {
        this.usr = usr;
    }

    @Override
    public void backProfile() {
        DisplayUserFragment fragment = (DisplayUserFragment) getSupportFragmentManager().findFragmentByTag("profile");

        if(fragment != null){
            fragment.setUser(this.usr);
            getSupportFragmentManager().popBackStack();
        }

    }

    @Override
    public void gotoProfile() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main, DisplayUserFragment.newInstance(usr), "profile")
                .commit();
    }
}