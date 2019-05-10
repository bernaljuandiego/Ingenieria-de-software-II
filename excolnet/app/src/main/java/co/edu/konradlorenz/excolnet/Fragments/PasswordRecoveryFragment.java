package co.edu.konradlorenz.excolnet.Fragments;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

import co.edu.konradlorenz.excolnet.R;

public class PasswordRecoveryFragment extends Fragment {

    private EditText email_text;
    private ConstraintLayout back_button;
    private Button reset_password_button;
    private LinearLayout mProgressView;
    private ScrollView form_password;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_password_recovery, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        obtenerElementosLayout();
        crearLiseners();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        form_password.setVisibility(show ? View.GONE : View.VISIBLE);
        if (show) {
            back_button.setOnClickListener(null);
        } else {
            crearLiseners();
        }
    }

    private void obtenerElementosLayout() {
        email_text = (EditText) getView().findViewById(R.id.email_text);
        back_button = (ConstraintLayout) getView().findViewById(R.id.back_button);
        reset_password_button = (Button) getView().findViewById(R.id.reset_password_button);
        mProgressView = getView().findViewById(R.id.login_progress);
        form_password = getView().findViewById(R.id.form_password);
        email_text.requestFocus();
    }

    private void closePasswordRecoveryWindow() {
        FragmentManager manager = ((Fragment) PasswordRecoveryFragment.this).getFragmentManager();
        FragmentTransaction trans = manager.beginTransaction();
        trans.remove((Fragment) PasswordRecoveryFragment.this);
        trans.commit();
    }

    private void crearLiseners() {
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closePasswordRecoveryWindow();
            }
        });
        reset_password_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth auth = FirebaseAuth.getInstance();
                String emailAddress = email_text.getText().toString();
                if (TextUtils.isEmpty(emailAddress)) {
                    email_text.setError(getString(R.string.error_field_required));
                } else {
                    if (emailAddress.contains("@")) {
                        showProgress(true);
                        auth.sendPasswordResetEmail(emailAddress)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            showProgress(false);
                                            Snackbar.make(back_button, "An password recovery email will send to tou.", Snackbar.LENGTH_SHORT).show();
                                            closePasswordRecoveryWindow();
                                        }
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                showProgress(false);
                                Snackbar.make(back_button, "Email doesn't exists.", Snackbar.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        Snackbar.make(back_button, "Enter a valid email.", Snackbar.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


}
