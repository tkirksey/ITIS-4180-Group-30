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

    }

    public class ViewPagerAdpater extends FragmentStateAdapter {

        public ViewPagerAdpater(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {

            ArrayList<Task> filteredSet = new ArrayList<>();

            for(Task t : mTasks){

                String priority = t.getPriority();

                if(position == 0 && priority.equals("LOW")){
                    filteredSet.add(t);
                } else if(position == 1 && priority.equals("MEDIUM")){
                    filteredSet.add(t);
                } else if(position == 2 && priority.equals("HIGH")){
                    filteredSet.add(t);
                }

            }

            return TasksFragment.newInstance(filteredSet);
        }

        @Override
        public int getItemCount() {
            return 4;
        }

    }

}