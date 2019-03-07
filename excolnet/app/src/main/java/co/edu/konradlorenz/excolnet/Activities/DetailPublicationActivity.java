package co.edu.konradlorenz.excolnet.Activities;

import androidx.appcompat.app.AppCompatActivity;
import co.edu.konradlorenz.excolnet.R;

import android.os.Bundle;

public class DetailPublicationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_publication);

        getSupportActionBar().setTitle("@username");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
