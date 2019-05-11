package co.edu.konradlorenz.excolnet.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import co.edu.konradlorenz.excolnet.Entities.Mensaje;
import co.edu.konradlorenz.excolnet.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatHolder> {
    public static final String MYTAG = "ChatAdapter";
    private ArrayList<Mensaje> messages;
    private CardView messageCardView;
    private Context myContext;
    private View view;
    private String currentUserUID;

    public ChatAdapter(Context myContext, String userUUID) {
        this.messages = new ArrayList<Mensaje>();
        this.myContext = myContext;
        this.currentUserUID = userUUID;
    }

    @NonNull
    @Override
    public ChatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_cardview, parent, false);
        messageCardView = (CardView) view.findViewById(R.id.cardViewMessage);

        ChatHolder chatHolder = new ChatHolder(messageCardView);

        return chatHolder;
    }


    public void addMessage(Mensaje m) {
        messages.add(m);
        notifyItemInserted(messages.size());
    }

    @Override
    public void onBindViewHolder(@NonNull ChatHolder holder, int position) {

        holder.getSender().setText(messages.get(position).getSenderDisplayName());

        holder.getSenderTime().setText(messages.get(position).getSenderTime());

        holder.getMessageContent().setText(messages.get(position).getMessage());

        Glide.with(myContext).load(messages.get(position).getSenderImage()).placeholder(R.drawable.ic_profile).error(R.drawable.com_facebook_profile_picture_blank_square).into(holder.getCardImage());


        if (messages.get(position).getMessage_type().equals("2")) {
            Glide.with(myContext).load(messages.get(position).getPhotoUrl()).into(holder.getMessageFoto());
            holder.getMessageFoto().setVisibility(View.VISIBLE);
        } else {

            holder.getSender().setVisibility(View.VISIBLE);

        }

        if (!(messages.get(position).getSenderUID().equals(currentUserUID))) {

            holder.itemView.setBackgroundColor(myContext.getResources().getColor(R.color.white));

            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) holder.itemView.getLayoutParams();

            layoutParams.setMargins(0, 10, 200, 10);

            holder.itemView.setLayoutParams(layoutParams);


        } else {

            holder.itemView.setBackgroundColor(myContext.getResources().getColor(R.color.blue));

            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) holder.itemView.getLayoutParams();

            layoutParams.setMargins(200, 10, 0, 10);

            holder.itemView.setLayoutParams(layoutParams);


        }

    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public ArrayList<Mensaje> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Mensaje> messages) {
        this.messages = messages;
    }

    public static class ChatHolder extends RecyclerView.ViewHolder {

        private CircleImageView cardImage;
        private TextView sender;
        private TextView senderTime;
        private TextView messageContent;
        private ImageView messageFoto;


        public ChatHolder(@NonNull View itemView) {
            super(itemView);
            cardImage = (CircleImageView) itemView.findViewById(R.id.cardImage);
            sender = (TextView) itemView.findViewById(R.id.cardSender);
            senderTime = (TextView) itemView.findViewById(R.id.cardTime);
            messageContent = (TextView) itemView.findViewById(R.id.cardMessage);
            messageFoto = (ImageView) itemView.findViewById(R.id.messagePhoto);


        }

        public CircleImageView getCardImage() {
            return cardImage;
        }

        public void setCardImage(CircleImageView cardImage) {
            this.cardImage = cardImage;
        }


        public TextView getSender() {
            return sender;
        }

        public void setSender(TextView sender) {
            this.sender = sender;
        }

        public TextView getSenderTime() {
            return senderTime;
        }

        public void setSenderTime(TextView senderTime) {
            this.senderTime = senderTime;
        }

        public TextView getMessageContent() {
            return messageContent;
        }

        public void setMessageContent(TextView messageContent) {
            this.messageContent = messageContent;
        }

        public ImageView getMessageFoto() {
            return messageFoto;
        }

        public void setMessageFoto(ImageView messageFoto) {
            this.messageFoto = messageFoto;
        }
    }
}
