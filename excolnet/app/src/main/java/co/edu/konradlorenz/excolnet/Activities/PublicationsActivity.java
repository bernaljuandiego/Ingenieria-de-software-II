package co.edu.konradlorenz.excolnet.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import co.edu.konradlorenz.excolnet.Fragments.BottomSheetNavigationFragment;
import co.edu.konradlorenz.excolnet.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class PublicationsActivity extends AppCompatActivity {


    BottomAppBar bottomAppBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publications);
        findLayoutElements();
        setUpBottomBar();



    }

    public void findLayoutElements(){
        bottomAppBar = findViewById(R.id.app_bar_publications);
    }

    public void setUpBottomBar(){

        setSupportActionBar(bottomAppBar);

        bottomAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.app_bar_home:
                        Toast.makeText(PublicationsActivity.this, "Home Icon Pressed", Toast.LENGTH_SHORT).show();
                        Intent newIntent = new Intent(PublicationsActivity.this, DetailPublicationActivity.class);
                        startActivity(newIntent);
                        break;
                    case R.id.app_bar_notifications:
                        Toast.makeText(PublicationsActivity.this, "Notifications Icon Pressed", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.app_bar_profile:
                        Toast.makeText(PublicationsActivity.this, "Profile Icon Pressed", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });

        bottomAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialogFragment bottomSheetDialogFragment = BottomSheetNavigationFragment.newInstance();
                bottomSheetDialogFragment.show(getSupportFragmentManager(), "Bottom Sheet Dialog Fragment");
            }
        });
    }

    public void toggleFabMode(View view) {
        //check the fab alignment mode and toggle accordingly
        if (bottomAppBar.getFabAlignmentMode() == BottomAppBar.FAB_ALIGNMENT_MODE_END) {
            bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);
        } else {
            bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_END);
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_bar_menu, menu);
        return true;
    }

}
