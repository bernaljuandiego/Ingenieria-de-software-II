package co.edu.konradlorenz.excolnet.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private ConstraintLayout register_layout;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getLayoutComponents();
        buttonsController();

        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void addUser() {


        usernameTextInput.setError(null);
        lastnameTextInput.setError(null);
        emailTextInput.setError(null);
        passwordTextInput.setError(null);

        String usermame = usernameTextInput.getText().toString().trim();
        String lastname = lastnameTextInput.getText().toString().trim();
        String emailAdress = emailTextInput.getText().toString().trim();
        String password = passwordTextInput.getText().toString().trim();


        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(usermame)) {
            usernameTextInput.setError(getString(R.string.error_field_required));
            focusView = usernameTextInput;
            cancel = true;
        }

        if (TextUtils.isEmpty(lastname)) {
            lastnameTextInput.setError(getString(R.string.error_field_required));
            focusView = lastnameTextInput;
            cancel = true;
        }


        if (TextUtils.isEmpty(emailAdress)) {
            emailTextInput.setError(getString(R.string.error_field_required));
            focusView = emailTextInput;
            cancel = true;
        } else if (!isEmailValid(emailAdress)) {
            emailTextInput.setError(getString(R.string.error_invalid_email));
            focusView = emailTextInput;
            cancel = true;
        }


        if (TextUtils.isEmpty(password)) {
            passwordTextInput.setError(getString(R.string.error_field_required));
            focusView = passwordTextInput;
            cancel = true;
        } else if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            passwordTextInput.setError(getString(R.string.error_invalid_password));
            focusView = passwordTextInput;
            cancel = true;
        }

        //Validate if user upload and image
        boolean notSelected = false;
        ImageView defaultImage = new ImageView(this);
        defaultImage.setImageResource(R.drawable.ic_select_photo);

        if (imageRegisterInput.getDrawable().getConstantState() == defaultImage.getDrawable().getConstantState()) {
            cancel = true;
            notSelected = true;
        }


        final String userCompleteName = usernameTextInput.getText().toString().trim() + " " + lastnameTextInput.getText().toString().trim();

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            if (notSelected) {
                alertNullImage();
            } else {
                focusView.requestFocus();
            }
        } else {

            registration_progressbar.setVisibility(View.VISIBLE);
            firebaseAuth.createUserWithEmailAndPassword(emailAdress, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    registration_progressbar.setVisibility(View.GONE);
                    if (task.isSuccessful()) {

                        FirebaseUser user = firebaseAuth.getCurrentUser();

                        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                .setDisplayName(userCompleteName)
                                .setPhotoUri(selectedImage)
                                .build();

                        user.updateProfile(profileUpdates)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Snackbar.make(register_layout, "User Created", Snackbar.LENGTH_SHORT).show();
                                        }
                                    }
                                });


                    } else {
                        Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }


    }

    public void buttonsController() {
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

    public void imageChooser() {
        Intent newIntent = new Intent(Intent.ACTION_GET_CONTENT);
        newIntent.setType("image/*");
        startActivityForResult(newIntent, PICK_IMAGE_REQUEST);
    }

    public void getLayoutComponents() {
        usernameTextInput = findViewById(R.id.username_text_input);
        lastnameTextInput = findViewById(R.id.lastname_text_input);
        emailTextInput = findViewById(R.id.email_text_input);
        passwordTextInput = findViewById(R.id.password_text_input);
        imageRegisterInput = findViewById(R.id.image_selected_view);
        selectImageButton = findViewById(R.id.select_image_button);
        signInButton = findViewById(R.id.sign_in_button);
        registration_progressbar = findViewById(R.id.user_registration_progressbar);
        register_layout = findViewById(R.id.register_layout);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case PICK_IMAGE_REQUEST:
                if (resultCode == RESULT_OK) {
                    selectedImage = data.getData();

                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                        imageRegisterInput.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    protected boolean isPasswordValid(String password) {

        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=\\S+$).{6,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();
    }

    protected boolean isEmailValid(String email) {
        if (email.contains("@") && email.contains(".")) {
            return true;
        } else {
            return false;
        }

    }


    private void alertNullImage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.image_required)
                .setTitle("Error");

        builder.setPositiveButton("Ok", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
