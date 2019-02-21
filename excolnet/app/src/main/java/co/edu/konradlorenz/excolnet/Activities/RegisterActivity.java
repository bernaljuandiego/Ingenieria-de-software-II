package co.edu.konradlorenz.excolnet.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import co.edu.konradlorenz.excolnet.R;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
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
