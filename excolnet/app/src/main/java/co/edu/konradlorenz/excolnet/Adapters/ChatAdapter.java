package co.edu.konradlorenz.excolnet.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.edu.konradlorenz.excolnet.Entities.Mensaje;
import co.edu.konradlorenz.excolnet.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class ChatAdapter  extends RecyclerView.Adapter<ChatAdapter.ChatHolder> {
    private ArrayList<Mensaje> messages;
    private CardView messageCardView;
    private Context myContext;
    private View view;

    public static final String MYTAG="ChatAdapter";

    public ChatAdapter(ArrayList<Mensaje> messages, Context myContext) {
        this.messages = messages;
        this.myContext = myContext;
    }

    @NonNull
    @Override
    public ChatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_cardview , parent , false);
        messageCardView =  (CardView) view.findViewById(R.id.cardViewMessage);

        ChatHolder chatHolder =  new ChatHolder(messageCardView);

        return chatHolder;
    }


    public void addMessage(Mensaje m){
        messages.add(m);
        notifyItemInserted(messages.size());
    }

    @Override
    public void onBindViewHolder(@NonNull ChatHolder holder, int position) {


        holder.getSender().setText(messages.get(position).getSenderDisplayName());
        Log.i(MYTAG , messages.get(position).getSenderDisplayName());
        holder.getSenderTime().setText(messages.get(position).getSenderTime());
        Log.i(MYTAG , messages.get(position).getSenderTime());
        holder.getMessageContent().setText(messages.get(position).getMessage());
        Log.i(MYTAG , messages.get(position).getMessage());
        Glide.with(myContext).load(messages.get(position).getSenderImage()).placeholder(R.drawable.ic_profile).error(R.drawable.com_facebook_profile_picture_blank_square).into(holder.getCardImage());
        Log.i(MYTAG , messages.get(position).getSenderImage().toString());
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public static class ChatHolder extends RecyclerView.ViewHolder {

        private CircleImageView  cardImage;
        private TextView sender;
        private  TextView senderTime;
        private TextView messageContent;


        public ChatHolder(@NonNull View itemView) {
            super(itemView);
            cardImage = (CircleImageView) itemView.findViewById(R.id.cardImage);
            sender =  (TextView) itemView.findViewById(R.id.cardSender);
            senderTime = (TextView) itemView.findViewById(R.id.cardTime);
            messageContent = (TextView) itemView.findViewById(R.id.cardMessage);




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


    }
}
