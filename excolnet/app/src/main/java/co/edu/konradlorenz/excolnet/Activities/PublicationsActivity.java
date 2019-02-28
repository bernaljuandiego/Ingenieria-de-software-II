package co.edu.konradlorenz.excolnet.Activities;

import androidx.appcompat.app.AppCompatActivity;
import co.edu.konradlorenz.excolnet.R;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomappbar.BottomAppBar;

public class PublicationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publications);

        BottomAppBar bottomAppBar = findViewById(R.id.app_bar_publications);
        setSupportActionBar(bottomAppBar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.app_bar_home:
                Toast.makeText(this, "Home Icon Pressed", Toast.LENGTH_SHORT).show();
                break;
            case R.id.app_bar_notifications:
                Toast.makeText(this, "Notifications Icon Pressed", Toast.LENGTH_SHORT).show();
                break;
            case R.id.app_bar_profile:
                Toast.makeText(this, "Profile Icon Pressed", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}
