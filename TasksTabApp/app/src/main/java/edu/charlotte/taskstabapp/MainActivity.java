package edu.charlotte.taskstabapp;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

import edu.charlotte.taskstabapp.models.DataStore;
import edu.charlotte.taskstabapp.models.Task;

public class MainActivity extends AppCompatActivity {
    //Tasks pre-populated from DataStore
    ArrayList<Task> mTasks = DataStore.getTasks();

    ViewPager2 viewPager;
    ViewPagerAdpater viewPagerAdpater;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Log.d("demo", "onCreate: " + DataStore.getTasks());

        viewPager = findViewById(R.id.viewPager);
        viewPagerAdpater = new ViewPagerAdpater(this);

        viewPager.setAdapter(viewPagerAdpater);

        tabLayout = findViewById(R.id.tabLayout);

        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int i) {
                String[] tabNames = {"LOW", "MEDIUM", "HIGH", "ALL"};
                tab.setText(tabNames[i]);
            }
        }).attach();

    }

    public class ViewPagerAdpater extends FragmentStateAdapter {

        public ViewPagerAdpater(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch(position){
                case 0:
                    return TasksFragment.newInstance(getFilteredList("LOW"));
                case 1:
                    return TasksFragment.newInstance(getFilteredList("MEDIUM"));
                case 2:
                    return TasksFragment.newInstance(getFilteredList("HIGH"));
                case 3:
                    return TasksFragment.newInstance(mTasks);
                default:
                    return new TasksFragment();
            }
        }

        @Override
        public int getItemCount() {
            return 4;
        }

    }

    private ArrayList<Task> getFilteredList(String priotity){
        ArrayList<Task> returnedList = new ArrayList<>();

        for(Task t : mTasks){
            if(t.getPriority().equals(priotity)){
                returnedList.add(t);
            }
        }

        return returnedList;
    }

}