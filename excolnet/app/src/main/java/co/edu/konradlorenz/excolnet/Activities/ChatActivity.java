package co.edu.konradlorenz.excolnet.Activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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

import co.edu.konradlorenz.excolnet.Adapters.ChatAdapter;
import co.edu.konradlorenz.excolnet.Entities.Mensaje;
import co.edu.konradlorenz.excolnet.Entities.Usuario;
import co.edu.konradlorenz.excolnet.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class ChatActivity extends AppCompatActivity  {

    private CircleImageView userChatImage;
    private TextView userChatName;
    private RecyclerView messageList;
    private EditText messageInput;
    private Button sendButton;
    //External User
    private Usuario chatUser;
    //Current User
    private FirebaseUser currentUser;
    private DatabaseReference currentConversation;
    private DatabaseReference chatReference;
    private ArrayList<Mensaje> messages;
    private ValueEventListener valueEventListener;
    private RecyclerView.LayoutManager layoutManager;
    private ChatAdapter adapter;





    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        getLayoutComponents();
        getIntentUser();
        getFirebaseComponents();
        addMessageListener();

        this.messages = new ArrayList<>();


    }


    @Override
    protected void onStart() {
        super.onStart();
        changeChatLayoutValues();

    }

    public void getLayoutComponents(){
        this.userChatImage = (CircleImageView) findViewById(R.id.circleAvatarImage);
        this.userChatName = (TextView) findViewById(R.id.UserChatName);
        this.messageList = (RecyclerView) findViewById(R.id.MessageList);
        this.messageInput = (EditText) findViewById(R.id.message_input);
        this.sendButton = (Button)  findViewById(R.id.sendMessage_button);
        this.layoutManager = new LinearLayoutManager(getApplicationContext());
    }

    public void getIntentUser(){

        Usuario usuario = (Usuario) getIntent().getSerializableExtra("UserChat");

        if(usuario != null){
            this.chatUser = usuario;
        }


    }


    public void changeChatLayoutValues(){
         if(chatUser != null){
             Glide.with(getApplicationContext()).load(chatUser.getPhotoUrl()).placeholder(R.drawable.ic_profile).error(R.drawable.com_facebook_profile_picture_blank_square).into(userChatImage);
             userChatName.setText(chatUser.getDisplayName());
         }
    }

    public void getFirebaseComponents(){
        FirebaseAuth auth =  FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();
        chatReference = FirebaseDatabase.getInstance().getReference("BaseDatos").child("Chat");
        validatePrivateChatCollection(currentUser.getUid() + "_" + chatUser.getUid());

        if (currentConversation == null ){
            validatePrivateChatCollection(chatUser.getUid() + "_" + currentUser.getUid() );
        }

        if (currentConversation == null){
            currentConversation = chatReference.child(currentUser.getUid() + "_" + chatUser.getUid());
        }


        Log.i("ChatActivitygetFBcomps " , currentConversation.getKey());
    }


    public void validatePrivateChatCollection(final String pattern){
        chatReference.orderByKey().equalTo(pattern).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                currentConversation = chatReference.child(pattern);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("ChatActivityONr" , currentConversation.getKey());
        getChatData();
    }


    public void getChatData(){
        this.valueEventListener  =  new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                messages.clear();


                for(DataSnapshot dataSnap : dataSnapshot.getChildren()){
                    Mensaje  msg = dataSnap.getValue(Mensaje.class);
                    messages.add(msg);

                }
                Log.i("ChatActivity msgs" , messages.toString());
                Log.i("ChatActivity currConv" , currentConversation.getKey());
                adapter = new ChatAdapter(messages , getApplicationContext());
                messageList.setHasFixedSize(true);
                messageList.setLayoutManager(layoutManager);
                messageList.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("ChatActivity error: " , databaseError.getMessage() + " : "+databaseError.getDetails());
            }
        };

        currentConversation.addValueEventListener(valueEventListener);

    }


    @Override
    protected void onPause() {
        super.onPause();
        currentConversation.removeEventListener(valueEventListener);
    }

    public void addMessageListener(){

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String message =  messageInput.getText().toString();


                if(message != null && !message.isEmpty()){
                    Mensaje mensaje  =  new Mensaje();
                    mensaje.setSenderDisplayName(currentUser.getDisplayName());
                    mensaje.setSenderImage(currentUser.getPhotoUrl().toString());
                    mensaje.setSenderUID(currentUser.getUid());
                    mensaje.setMessage(message);
                    final String currentDate= DateFormat.getDateTimeInstance().format(new Date());
                    mensaje.setSenderTime(currentDate);

                    //adapter.addMessage(mensaje);


                    currentConversation.push().setValue(mensaje);

                    messageInput.setText("");
                }
            }
        });

    }
}
