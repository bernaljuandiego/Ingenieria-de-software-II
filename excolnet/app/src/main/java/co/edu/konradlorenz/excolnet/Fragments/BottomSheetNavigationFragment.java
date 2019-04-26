package co.edu.konradlorenz.excolnet.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.facebook.login.LoginManager;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import co.edu.konradlorenz.excolnet.Activities.LoginActivity;
import co.edu.konradlorenz.excolnet.R;

import static com.facebook.FacebookSdk.getApplicationContext;


public class BottomSheetNavigationFragment extends BottomSheetDialogFragment {

    private View view;
    private NavigationView navigationView;
    private FirebaseUser user;
    private ImageView userPicture;
    private TextView userName;
    private TextView userEmail;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.bottom_navigation_drawer, container, false);

        firebaseLoadData();
        findMaterialElements();
        userInfoSetUpLayout();
        menuItemsHandler();

        return view;
    }

    private void findMaterialElements(){
        navigationView = view.findViewById(R.id.navigation_view);
        userPicture = view.findViewById(R.id.profile_image_bottom_sheet);
        userName = view.findViewById(R.id.user_name_bottom_sheet);
        userEmail = view.findViewById(R.id.user_email_bottom_sheet);
    }

    private void userInfoSetUpLayout(){
        userName.setText(user.getDisplayName());
        userEmail.setText(user.getEmail());
        Glide.with(getApplicationContext()).load(user.getPhotoUrl()).into(userPicture);
    }

    private void firebaseLoadData(){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
    }

    private void menuItemsHandler(){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch(menuItem.getItemId()){
                    case R.id.nav_chat_option:
                        //Toast.makeText(getContext(), "Chat Option Selected", Toast.LENGTH_SHORT).show();
                        FriendsFragment friendsFragment =  new FriendsFragment();
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.contenido , friendsFragment)
                                .commit();

                        BottomSheetNavigationFragment.super.dismiss();



                        return true;
                    case R.id.nav_invitation_option:
                        Toast.makeText(getContext(), "Invitations Option Selected", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.nav_tourism_agencies_option:
                        Toast.makeText(getContext(), "Tourism agencies Option Selected", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.nav_night_life_option:
                        Toast.makeText(getContext(), "Night Life Option Selected", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.nav_living_costs_option:
                        Toast.makeText(getContext(), "Living Costs Option Selected", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.nav_housing_option:
                        Toast.makeText(getContext(), "Housing Option Selected", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.nav_tourist_sites_option:
                        Toast.makeText(getContext(), "Tourist Sites Option Selected", Toast.LENGTH_SHORT).show();

                        Fragment fragment = new TouristSitesFragment();
                        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.contenido,fragment);
                        ft.addToBackStack(null);
                        ft.commit();
                        BottomSheetNavigationFragment.super.dismiss();
                    case R.id.nav_settings_option:
                        Toast.makeText(getContext(), "Settings Option Selected", Toast.LENGTH_SHORT).show();
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
}
