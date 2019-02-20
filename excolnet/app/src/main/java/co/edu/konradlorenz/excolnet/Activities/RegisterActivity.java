package co.edu.konradlorenz.excolnet.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;

import co.edu.konradlorenz.excolnet.R;

public class RegisterActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 100;
    private ImageView imageRegisterInput;
    private TextInputEditText usernameTextInput;
    private TextInputEditText lastnameTextInput;
    private TextInputEditText emailTextInput;
    private TextInputEditText passwordTextInput;
    private Button selectImageButton;
    private Button signInButton;
    private Uri selectedImage;
    private ProgressBar registration_progressbar;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getLayoutComponents();
        buttonsController();

        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void addUser(){

        String emailAdress = emailTextInput.getText().toString().trim();
        String password = passwordTextInput.getText().toString().trim();

        registration_progressbar.setVisibility(View.VISIBLE);
        firebaseAuth.createUserWithEmailAndPassword(emailAdress, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                registration_progressbar.setVisibility(View.GONE);
                if(task.isSuccessful()){
                    Toast.makeText(RegisterActivity.this, "User Created", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    
    public void buttonsController(){
        selectImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageChooser();
            }
        });
        
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();
            }
        });
    }

    public void imageChooser(){
        Intent newIntent = new Intent(Intent.ACTION_GET_CONTENT);
        newIntent.setType("image/*");
        startActivityForResult(newIntent, PICK_IMAGE_REQUEST);
    }

    public void getLayoutComponents(){
        usernameTextInput = findViewById(R.id.username_text_input);
        lastnameTextInput = findViewById(R.id.lastname_text_input);
        emailTextInput = findViewById(R.id.email_text_input);
        passwordTextInput = findViewById(R.id.password_text_input);
        imageRegisterInput = findViewById(R.id.image_selected_view);
        selectImageButton = findViewById(R.id.select_image_button);
        signInButton = findViewById(R.id.sign_in_button);
        registration_progressbar = findViewById(R.id.user_registration_progressbar);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode){
            case PICK_IMAGE_REQUEST:
                if(resultCode == RESULT_OK){
                    selectedImage = data.getData();

                    try{
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                        imageRegisterInput.setImageBitmap(bitmap);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                }
                break;
        }
    }
}
