package co.edu.konradlorenz.excolnet.Activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.palette.graphics.Palette;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import co.edu.konradlorenz.excolnet.Entities.Usuario;
import co.edu.konradlorenz.excolnet.Fragments.PublicationsFragment;
import co.edu.konradlorenz.excolnet.R;

public class ProfileActivity extends AppCompatActivity {

    private AppBarLayout profileHeader;
    private boolean appBarExpanded = true;
    private Menu collapsedMenu;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbar;
    private de.hdodenhof.circleimageview.CircleImageView circleImageView;
    private TextView userName;
    private FirebaseUser user;
    private LinearLayout userDataLinearLayout;
    private LinearLayout buttonOptionsLinearLayout;
    private ImageButton addUserButton;
    private Usuario userPrincipalPublication;
    private DatabaseReference mDatabase;
    private DatabaseReference mUserDatabase;
    private String mcurrentState;
    private DatabaseReference mFriendREqDatabase;
    private String user_id;
    private TextView maddUserText;
    private DatabaseReference mFriendDatabase;
    private ValueEventListener valueEventListener;
    private ArrayList<Usuario> listaUsuarios;
    private boolean frien = false;
    // private TextView numberFriends;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseLoadData();
        findMaterialComponents();
        setUpToolbar();
        setUpToolbarLayout();
        setUpHeaderColor();
        setUpAppBarLayout();
        setUpAddUser();

        activityWhoCalledThis();

    }

    private void activityWhoCalledThis() {

        String activityCalled = getIntent().getStringExtra("ACTIVITY_CALLED_NAME");

        switch (activityCalled) {
            case "PrincipalActivity":
                userPrincipalPublication = (Usuario) getIntent().getSerializableExtra("USER");
                setUpUserData(activityCalled);
                loadPublications(userPrincipalPublication);
                break;
            case "PublicationsAdapter":
                userPrincipalPublication = (Usuario) getIntent().getSerializableExtra("USER");
                setUpUserData(activityCalled);
                loadPublications(userPrincipalPublication);
                break;
            case "AdapterSearch":
                userPrincipalPublication = (Usuario) getIntent().getSerializableExtra("USER");
                setUpUserData(activityCalled);
                loadPublications(userPrincipalPublication);
        }

    }


    private void setUpAddUser() {

        user_id = getIntent().getStringExtra("USER_ID");
        isFriend();
        if (user_id == null || user_id.equals(user.getUid())) {
            user_id = user.getUid();
            addUserButton.setEnabled(false);
            addUserButton.setVisibility(View.INVISIBLE);
            maddUserText.setText("");
        }


        System.out.print(frien);
        mUserDatabase = mDatabase.child("Users").child(user_id);


        addUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUserButton.setEnabled(false);

                // -----Not Friends state

                Toast.makeText(ProfileActivity.this, "Add User clicked", Toast.LENGTH_SHORT).show();
                if (mcurrentState.equals("not_friends")) {

                    mFriendREqDatabase.child(user.getUid()).child(user_id).child("request_type").setValue("sent").addOnCompleteListener(
                            new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        mFriendREqDatabase.child(user_id).child(user.getUid()).child("request_type")
                                                .setValue("received").addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {


                                                mcurrentState = "req_sent";
                                                maddUserText.setText("Cancel Friend Request");
                                                Toast.makeText(ProfileActivity.this, "Request Sent Succesfully", Toast.LENGTH_SHORT).show();

                                            }
                                        });

                                    } else {
                                        Toast.makeText(ProfileActivity.this, "Failed Sending Request", Toast.LENGTH_SHORT).show();
                                    }
                                    addUserButton.setEnabled(true);
                                }
                            }

                    );

                }


                //----------Cancel Request

                if (mcurrentState.equals("req_sent")) {
                    mFriendREqDatabase.child(user.getUid()).child(user_id).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            mFriendREqDatabase.child(user_id).child(user.getUid()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {

                                    addUserButton.setEnabled(true);
                                    mcurrentState = "not_friends";
                                    maddUserText.setText("Add user");

                                }
                            });
                        }
                    });

                }

                //--------------REQ received state

                if (mcurrentState.equals("req_received")) {
                    final String currentDate = DateFormat.getDateTimeInstance().format(new Date());
                    mFriendDatabase.child(user.getUid()).child(user_id).setValue(currentDate).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            mFriendDatabase.child(user_id).child(user.getUid()).setValue(currentDate)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {


                                            mFriendREqDatabase.child(user.getUid()).child(user_id).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    mFriendREqDatabase.child(user_id).child(user.getUid()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {

                                                            addUserButton.setEnabled(true);
                                                            mcurrentState = "friends";
                                                            maddUserText.setText("Unfriend");

                                                        }
                                                    });
                                                }
                                            });


                                        }
                                    });
                        }
                    });

                }
            }
        });
    }

    private void isFriend() {

        this.valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                for (DataSnapshot dataSnap : dataSnapshot.getChildren()) {
                    String friendUserUID = dataSnap.getKey();

                    if (friendUserUID.equals(user_id)) {
                        frien = true;
                        addUserButton.setEnabled(false);
                        addUserButton.setVisibility(View.INVISIBLE);
                        maddUserText.setText("");

                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("FriendsFragment.error: ", databaseError.getMessage() + " details : " + databaseError.getDetails());
            }
        };

        mDatabase.child("Friends").child(user.getUid()).addValueEventListener(valueEventListener);

    }


    private void firebaseLoadData() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

    }

    private void setUpUserData(String activityCalled) {


        Glide.with(getApplicationContext()).load(user.getPhotoUrl()).into(circleImageView);
        userName.setText(user.getDisplayName());


        Glide.with(getApplicationContext()).load(userPrincipalPublication.getPhotoUrl()).into(circleImageView);
        userName.setText(userPrincipalPublication.getDisplayName());

        mFriendREqDatabase.child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(user_id)) {
                    String req_type = dataSnapshot.child(user_id).child("request_type").getValue().toString();
                    if (req_type.equals("received")) {
                        mcurrentState = "req_received";
                        maddUserText.setText("Accept Friend Request");

                    } else if (req_type.equals("sent")) {
                        mcurrentState = "req_sent";
                        maddUserText.setText("Cancel Friend Request");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void setUpToolbarLayout() {
        collapsingToolbar.setTitleEnabled(false);
    }

    private void loadPublications() {
        Fragment fragment = new PublicationsFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.contenido, fragment);
        ft.commit();
    }

    private void loadPublications(Usuario user) {
        Fragment fragment = new PublicationsFragment(user);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();


        ft.replace(R.id.contenido, fragment);
        ft.commit();
    }

    public void setUpHeaderColor() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.header);
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @SuppressWarnings("ResourceType")
            @Override
            public void onGenerated(@Nullable Palette palette) {
                int vibrantColor = palette.getVibrantColor(R.color.primary_500);
                collapsingToolbar.setContentScrimColor(vibrantColor);
                collapsingToolbar.setStatusBarScrimColor(R.color.black_trans80);
            }
        });
    }

    public void setUpToolbar() {
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    public void findMaterialComponents() {
        profileHeader = findViewById(R.id.profile_appbar);
        toolbar = findViewById(R.id.anim_toolbar);
        collapsingToolbar = findViewById(R.id.collapsing_toolbar);
        circleImageView = findViewById(R.id.circle_image);
        userName = findViewById(R.id.username_text_profile);
        userDataLinearLayout = findViewById(R.id.user_data_linear_layout);
        buttonOptionsLinearLayout = findViewById(R.id.button_options_linear_layout);
        addUserButton = findViewById(R.id.add_friend_button);
        maddUserText = findViewById(R.id.add_friend_text);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("BaseDatos");
        mcurrentState = "not_friends";
        mFriendREqDatabase = FirebaseDatabase.getInstance().getReference().child("BaseDatos").child("FriendReq");
        mFriendDatabase = FirebaseDatabase.getInstance().getReference().child("BaseDatos").child("Friends");
        //numberFriends =findViewById(R.id.number_friends);
    }

    public void setUpAppBarLayout() {

        profileHeader.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Log.d(ProfileActivity.class.getSimpleName(), "onOffsetChanged: verticalOffset: " + verticalOffset);

                //vertical offset == 0 indicates appBar is fully expanded.
                if (Math.abs(verticalOffset) > 650) {
                    appBarExpanded = false;
                    invalidateOptionsMenu();
                    toolbar.setTitle(user.getDisplayName());
                    //circleImageView.setVisibility(View.GONE);
                    userDataLinearLayout.setVisibility(View.GONE);
                    buttonOptionsLinearLayout.setVisibility(View.GONE);
                } else {
                    appBarExpanded = true;
                    invalidateOptionsMenu();
                    toolbar.setTitle("");
                    //circleImageView.setVisibility(View.VISIBLE);
                    userDataLinearLayout.setVisibility(View.VISIBLE);
                    buttonOptionsLinearLayout.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        if (collapsedMenu != null && (!appBarExpanded || collapsedMenu.size() != 1)) {
            //collapsed
            collapsedMenu.add("Add").setIcon(R.drawable.ic_action_edit).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        } else {
            //expanded
        }

        return super.onPrepareOptionsMenu(collapsedMenu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        collapsedMenu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_settings:
                return true;
        }
        if (item.getTitle() == "Add") {
            Toast.makeText(this, "clicked add", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }


}
