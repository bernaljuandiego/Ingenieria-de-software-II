package co.edu.konradlorenz.excolnet.Activities;

import androidx.appcompat.app.AppCompatActivity;
import co.edu.konradlorenz.excolnet.Entities.Usuario;
import co.edu.konradlorenz.excolnet.R;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static com.facebook.FacebookSdk.getApplicationContext;

public class DetailPublicationActivity extends AppCompatActivity {


    private TextView userName;
    private Usuario publicationUser;
    private ImageView userImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_publication);

        getSupportActionBar().setTitle("@username");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findMaterialElements();

        setUpLayoutData();
    }

    private void setUpLayoutData(){
        publicationUser = (Usuario) getIntent().getSerializableExtra("user");
        userName.setText(publicationUser.getDisplayName());
        Glide.with(getApplicationContext()).load(publicationUser.getPhotoUrl()).into(userImage);
    }

    private void findMaterialElements(){
        userName = findViewById(R.id.username_detail_publication);
        userImage = findViewById(R.id.user_image_detail_publication);
    }


}
