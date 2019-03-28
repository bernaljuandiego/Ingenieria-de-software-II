package co.edu.konradlorenz.excolnet.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.facebook.login.LoginManager;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import co.edu.konradlorenz.excolnet.Activities.LoginActivity;
import co.edu.konradlorenz.excolnet.R;


public class BottomSheetNavigationFragment extends BottomSheetDialogFragment {

    private View view;
    private NavigationView navigationView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.bottom_navigation_drawer, container, false);

        findMaterialElements();
        menuItemsHandler();

        return view;
    }

    private void menuItemsHandler(){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch(menuItem.getItemId()){
                    case R.id.nav_chat_option:
                        Toast.makeText(getContext(), "Chat Option Selected", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.nav_signout_option:
                        FirebaseAuth mAuth = FirebaseAuth.getInstance();
                        mAuth.signOut();
                        LoginManager.getInstance().logOut();
                        Intent newintent = new Intent(getContext(), LoginActivity.class);
                        newintent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(newintent);
                        return true;
                }

                return true;
            }
        });
    }

    private void findMaterialElements(){
        navigationView = view.findViewById(R.id.navigation_view);
    }
}
