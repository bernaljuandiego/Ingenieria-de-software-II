package co.edu.konradlorenz.excolnet.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import co.edu.konradlorenz.excolnet.Activities.ChatActivity;
import co.edu.konradlorenz.excolnet.Entities.Usuario;
import co.edu.konradlorenz.excolnet.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.FriendHolder> {

    private ArrayList<Usuario> friends;
    private CardView friendCardView;
    private Context context;
    private View view;

    public FriendsAdapter(ArrayList<Usuario> friends, Context context) {
        this.friends = friends;
        this.context = context;
    }


    public ArrayList<Usuario> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<Usuario> friends) {
        this.friends = friends;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    @NonNull
    @Override
    public FriendHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.friend_cardview, parent, false);
        this.friendCardView = (CardView) view.findViewById(R.id.FriendCard);

        FriendHolder friendHolder = new FriendHolder(view);

        return friendHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FriendHolder holder, final int position) {
        holder.getFriendName().setText(friends.get(position).getDisplayName());
        Glide.with(context).load(friends.get(position).getPhotoUrl()).placeholder(R.drawable.ic_profile).error(R.drawable.com_facebook_profile_picture_blank_square).into(holder.getFriendImage());
        friendCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chatIntent = new Intent(view.getContext(), ChatActivity.class);
                chatIntent.putExtra("UserChat", friends.get(position));
                view.getContext().startActivity(chatIntent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return friends.size();
    }

    public static class FriendHolder extends RecyclerView.ViewHolder {

        private CircleImageView friendImage;
        private TextView friendName;

        public FriendHolder(@NonNull View itemView) {
            super(itemView);

            this.friendImage = (CircleImageView) itemView.findViewById(R.id.friend_Image);
            this.friendName = (TextView) itemView.findViewById(R.id.friend_name);
        }

        public CircleImageView getFriendImage() {
            return friendImage;
        }

        public void setFriendImage(CircleImageView friendImage) {
            this.friendImage = friendImage;
        }

        public TextView getFriendName() {
            return friendName;
        }

        public void setFriendName(TextView friendName) {
            this.friendName = friendName;
        }
    }
}
