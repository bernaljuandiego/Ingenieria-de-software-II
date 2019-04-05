package co.edu.konradlorenz.excolnet.Activities;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import co.edu.konradlorenz.excolnet.Fragments.PublicationsFragment;
import co.edu.konradlorenz.excolnet.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    private TextView userName;
    private CircleImageView userImage;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseLoadData();
        findMaterialElements();
        userInfoSetUpLayout();
        loadPublications();
    }

    private void loadPublications(){
        Fragment fragment = new PublicationsFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragmento, fragment);
        ft.commit();
    }

    private void firebaseLoadData(){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
    }

    private void userInfoSetUpLayout(){
        userName.setText(user.getDisplayName());
        Glide.with(getApplicationContext()).load(user.getPhotoUrl()).into(userImage);
    }

    private void findMaterialElements(){
        userName = findViewById(R.id.name_profile);
        userImage = findViewById(R.id.avatar);
    }
}
