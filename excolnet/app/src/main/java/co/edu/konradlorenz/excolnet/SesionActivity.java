package co.edu.konradlorenz.excolnet;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

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