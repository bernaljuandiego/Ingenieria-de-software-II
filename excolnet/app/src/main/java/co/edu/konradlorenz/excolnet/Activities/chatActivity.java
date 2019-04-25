package co.edu.konradlorenz.excolnet.Activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import co.edu.konradlorenz.excolnet.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class chatActivity extends AppCompatActivity  {

    private CircleImageView avatarImage;
    private TextView userChatText;
    private RecyclerView messageList;
    private EditText messageInput;
    private Button sendButton;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        getLayoutComponents();
    }




    public void getLayoutComponents(){
        this.avatarImage = (CircleImageView) findViewById(R.id.circleAvatarImage);
        this.userChatText = (TextView) findViewById(R.id.UserChatName);
        this.messageList = (RecyclerView) findViewById(R.id.MessageList);
        this.messageInput = (EditText) findViewById(R.id.message_input);
        this.sendButton = (Button)  findViewById(R.id.send_button);
    }
}
