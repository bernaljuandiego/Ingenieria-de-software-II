package co.edu.konradlorenz.excolnet.Activities;

import androidx.appcompat.app.AppCompatActivity;
import co.edu.konradlorenz.excolnet.Entities.Usuario;
import co.edu.konradlorenz.excolnet.R;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DetailPublicationActivity extends AppCompatActivity {


    private TextView userName;
    private Usuario publicationUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_publication);

        getSupportActionBar().setTitle("@username");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findMaterialElements();

        publicationUser = (Usuario) getIntent().getSerializableExtra("user");

        setUpLayoutFirebaseData();
    }

    private void setUpLayoutFirebaseData(){
        userName.setText(publicationUser.getDisplayName());
    }

    private void findMaterialElements(){
        userName = findViewById(R.id.detail_publication_title);
    }


}
