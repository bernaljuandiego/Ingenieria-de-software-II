package co.edu.konradlorenz.excolnet.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import co.edu.konradlorenz.excolnet.Activities.PrincipalActivity;
import co.edu.konradlorenz.excolnet.Adapters.FriendsAdapter;
import co.edu.konradlorenz.excolnet.Entities.Usuario;
import co.edu.konradlorenz.excolnet.R;


public class FriendsFragment extends Fragment {

    private RecyclerView friendsList;
    private RecyclerView.Adapter friendsAdapter;
    private RecyclerView.LayoutManager lManager;
    private DatabaseReference databaseReference;
    private ArrayList<Usuario> friendsArray;
    private ValueEventListener valueEventListener;
    private FirebaseUser currentUser;

    public FriendsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_friends, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeAtributtes();

    }


    public void initializeAtributtes() {
        this.databaseReference = FirebaseDatabase.getInstance().getReference("BaseDatos");
        this.friendsList = (RecyclerView) getView().findViewById(R.id.friends_list);
        this.friendsArray = new ArrayList<>();
        this.lManager = new LinearLayoutManager(getContext());

        FirebaseAuth auth = FirebaseAuth.getInstance();

        this.currentUser = auth.getCurrentUser();
    }


    @Override
    public void onResume() {
        super.onResume();

        this.valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                friendsArray.clear();


                for (DataSnapshot dataSnap : dataSnapshot.getChildren()) {
                    String friendUserUID = dataSnap.getKey();

                    Usuario friend = getFriend(friendUserUID);

                    if (friend != null && !friend.getUid().isEmpty()) {
                        friendsArray.add(friend);
                    } else {
                        Log.e("FriendsFragment.error: ", "friend Is empty " + friend);
                    }
                    friendsList.setHasFixedSize(true);
                    friendsList.setLayoutManager(lManager);
                    friendsAdapter = new FriendsAdapter(friendsArray, getContext());
                    friendsList.setAdapter(friendsAdapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("FriendsFragment.error: ", databaseError.getMessage() + " details : " + databaseError.getDetails());
            }
        };

        databaseReference.child("Friends").child(this.currentUser.getUid()).addValueEventListener(valueEventListener);
    }

    @Override
    public void onPause() {
        super.onPause();
        databaseReference.child("Friends").child(this.currentUser.getUid()).removeEventListener(valueEventListener);
    }

    public Usuario getFriend(String UserUUID) {
        Usuario returnUser = null;
        PrincipalActivity principalActivity = (PrincipalActivity) getActivity();
        ArrayList<Usuario> allUsers = principalActivity.getListaUsuarios();

        if (allUsers != null && allUsers.size() > 0) {
            for (Usuario currentUser : allUsers) {
                if (currentUser.getUid().equals(UserUUID)) {
                    returnUser = currentUser;

                    return returnUser;
                }
            }
        }


        return returnUser;

    }
}
