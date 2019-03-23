package co.edu.konradlorenz.excolnet.Activities;

import android.os.Bundle;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.appcompat.app.AppCompatActivity;
import co.edu.konradlorenz.excolnet.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        TextView nombre = findViewById(R.id.name_profile);
        nombre.setText(user.getDisplayName());
        CircleImageView imagen = findViewById(R.id.avatar);
        Glide.with(getApplicationContext()).load(user.getPhotoUrl()).into(imagen);
    }
}
