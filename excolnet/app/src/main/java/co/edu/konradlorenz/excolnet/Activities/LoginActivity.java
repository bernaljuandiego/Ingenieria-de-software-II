package co.edu.konradlorenz.excolnet.Activities;

import java.util.List;

import android.net.Uri;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import android.view.KeyEvent;
import android.widget.Button;
import android.content.Loader;
import android.content.Intent;
import android.text.TextUtils;
import android.database.Cursor;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;

import android.widget.LinearLayout;
import android.widget.ArrayAdapter;
import android.content.CursorLoader;
import android.annotation.TargetApi;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.google.android.gms.tasks.Task;

import android.content.pm.PackageManager;
import android.view.View.OnClickListener;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.widget.AutoCompleteTextView;
import android.view.inputmethod.EditorInfo;

import com.google.firebase.auth.AuthResult;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.facebook.login.widget.LoginButton;

import android.support.design.widget.Snackbar;

import com.google.firebase.auth.AuthCredential;

import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import com.twitter.sdk.android.core.TwitterConfig;
import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.TwitterSession;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.TwitterAuthProvider;
import com.google.firebase.auth.FacebookAuthProvider;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;

import static android.Manifest.permission.READ_CONTACTS;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import co.edu.konradlorenz.excolnet.PasswordRecoveryFragment;
import co.edu.konradlorenz.excolnet.R;

public class LoginActivity extends AppCompatActivity implements LoaderCallbacks<Cursor> {

    // variables de clase ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    private FirebaseAuth mAuth;
    private EditText mPasswordView;
    private Button mEmailSignInButton;
    private Button googleSignInButton;
    private LinearLayout mProgressView;
    private LoginButton loginFacebookButton;
    private TextView password_text_link;
    private AutoCompleteTextView mEmailView;
    private CallbackManager mCallbackManager;
    private static final int RC_SIGN_IN = 101;
    private TwitterLoginButton loginTwitterButton;
    private GoogleSignInClient mGoogleSignInClient;
    private static final int REQUEST_READ_CONTACTS = 0;

    //metodos de autocompletado de textedit de correo electronico y pedida de permisos de acceso a contactos--------------------------------------------------------------------------------------------
    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };
        int ADDRESS = 0;
    }

    private void populateAutoComplete() {
        if (!mayRequestContacts()) {
            return;
        }
        getLoaderManager().initLoader(0, null, this);
    }

    private boolean mayRequestContacts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Snackbar.make(mEmailView, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
                        }
                    });
        } else {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        return false;
    }

    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(LoginActivity.this, android.R.layout.simple_dropdown_item_1line, emailAddressCollection);
        mEmailView.setAdapter(adapter);
    }

    // verificar el api level para mostrar el progressbar (cargando..) ---------------------------------------------------------------------------------------------------------------------------------
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    // metodos heredados de activity para el correcto funcionamiento de la aplicacion ------------------------------------------------------------------------------------------------------------------
    @Override
    public void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() != null) {
            logInSucceed();
        } else {
            showProgress(false);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        twitterSignInComponents();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getLayoutComponents();
        populateAutoComplete();
        googleFirebaseComponents();
        googleSignInComponents();
        facebookSignInComponents();
        createLiseners();
    }

    //metodos de autocompletado de textedit de correo electronico y pedida de permisos de acceso a contactos--------------------------------------------------------------------------------------------
    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,
                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},
                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }
        addEmailsToAutoComplete(emails);
    }

    //metodo que recibe el resultado de la tarea asincrona de inicio de sesion--------------------------------------------------------------------------------------------------------------------------
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        loginTwitterButton.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                logInError();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        /**
         * Callback received when a permissions request has been completed.
         */
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                populateAutoComplete();
            }
        }
    }

    //obtener los componentes para las distintas funcionalidades (el error que aparece es normal)-------------------------------------------------------------------------------------------------------
    private void getLayoutComponents() {
        mEmailSignInButton = findViewById(R.id.email_sign_in_button);
        mPasswordView = findViewById(R.id.password);
        mEmailView = findViewById(R.id.email);
        mProgressView = findViewById(R.id.login_progress);
        googleSignInButton = findViewById(R.id.google_login_button);
        loginFacebookButton = findViewById(R.id.facebook_login_button);
        loginTwitterButton = findViewById(R.id.twitter_login_button);
        password_text_link = findViewById(R.id.forgot_password_text);
    }

    private void googleSignInComponents() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void twitterSignInComponents() {
        Twitter.initialize(new TwitterConfig.Builder(this)
                .logger(new DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(new TwitterAuthConfig(getString(R.string.CONSUMER_KEY), getString(R.string.CONSUMER_SECRET)))
                .debug(true)
                .build());
    }

    private void facebookSignInComponents() {
        FacebookSdk.sdkInitialize(getApplicationContext());

        mCallbackManager = CallbackManager.Factory.create();
        loginFacebookButton.setReadPermissions("email", "public_profile");
    }

    private void googleFirebaseComponents() {
        mAuth = FirebaseAuth.getInstance();
    }

    //manejo de los distintos eventos obtenidos en la actividad-----------------------------------------------------------------------------------------------------------------------------------------
    private void googleSignIn() {
        showProgress(true);
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void createLiseners() {
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    firebaseAuthWithEmail();
                    return true;
                }
                return false;
            }
        });

        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuthWithEmail();
            }
        });

        googleSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                googleSignIn();
            }
        });

        loginFacebookButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                firebaseAuthWithFacebook(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                logInError();
            }

            @Override
            public void onError(FacebookException error) {
                logInConectionFailed();
            }
        });

        loginTwitterButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                firebaseAuthWithTwitter(result.data);
            }

            @Override
            public void failure(TwitterException exception) {
                logInError();
            }
        });

        password_text_link.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new PasswordRecoveryFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.main_layout, fragment);
                ft.commit();
            }

        });
    }

    public void onClickTwitter(View v) {
        showProgress(true);
        loginTwitterButton.performClick();
    }

    public void onClickFacebook(View v) {
        showProgress(true);
        loginFacebookButton.performClick();
    }

    private boolean isPasswordValid(String password) {
        if (password.length() < 6) {
            return false;
        }
        return true;
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    //validacion y persistencia de las cuentas en firebase----------------------------------------------------------------------------------------------------------------------------------------------
    private void firebaseAuthWithEmail() {
        /**
         * Attempts to sign in or register the account specified by the login form.
         * If there are form errors (invalid email, missing fields, etc.), the
         * errors are presented and no actual login attempt is made.
         */

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.error_field_required));
            focusView = mPasswordView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            showProgress(true);
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                logInSucceed();
                            } else {
                                logInError();
                                mPasswordView.setError("Incorrect Password.");
                            }
                        }
                    });
        }
    }

    private void firebaseAuthWithFacebook(AccessToken token) {
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        credentialFirebaseSingIn(credential);
    }

    private void firebaseAuthWithTwitter(TwitterSession session) {
        AuthCredential credential = TwitterAuthProvider.getCredential(
                session.getAuthToken().token,
                session.getAuthToken().secret);
        credentialFirebaseSingIn(credential);
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        credentialFirebaseSingIn(credential);
    }

    private void credentialFirebaseSingIn(AuthCredential credential) {
        mAuth.getCurrentUser().linkWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                           // Snackbar.make(findViewById(R.id.main_layout),Snackbar.LENGTH_SHORT,).setText("Account association succesful!").show();
                        } else {

                        }
                    }
                });

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            logInSucceed();
                        } else {
                            logInConectionFailed();
                        }
                    }
                });
    }

    //manejo de las distintas alternativas al hacer un inicio de sesion---------------------------------------------------------------------------------------------------------------------------------
    private void logInError() {
        showProgress(false);
        Snackbar.make(findViewById(R.id.main_layout), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
    }

    private void logInSucceed() {
        FirebaseUser user = mAuth.getCurrentUser();
        Intent i = new Intent(LoginActivity.this, SesionActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        showProgress(false);
        startActivity(i);
    }

    private void logInConectionFailed() {
        showProgress(false);
        Snackbar.make(findViewById(R.id.main_layout), "Connection Error.", Snackbar.LENGTH_SHORT).show();
    }
}

