package co.edu.konradlorenz.excolnet.Activities;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

import co.edu.konradlorenz.excolnet.R;

public class SesionActivity extends AppCompatActivity{

    private ImageView photoGoogleLogin;
    private TextView nameGoogleLogin;
    private TextView emailGoogleLogin;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sesion);

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();

        photoGoogleLogin = (ImageView) findViewById(R.id.photoGoogleLogin);
        nameGoogleLogin = (TextView) findViewById(R.id.nameTextView);
        emailGoogleLogin = (TextView) findViewById(R.id.emailTextView);

        if (user != null) {
            for (UserInfo profile : user.getProviderData()) {
                nameGoogleLogin.setText(profile.getDisplayName());
                emailGoogleLogin.setText(profile.getEmail());
                Glide.with(this).load(profile.getPhotoUrl()).into(photoGoogleLogin);
            }
        }



    }

    public void goGoogleLoginOut(View v) {
        mAuth.signOut();
        LoginManager.getInstance().logOut();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}