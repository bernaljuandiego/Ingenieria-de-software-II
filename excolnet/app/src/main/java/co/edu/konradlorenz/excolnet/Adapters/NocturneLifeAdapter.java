package co.edu.konradlorenz.excolnet.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import co.edu.konradlorenz.excolnet.Activities.SitesActivity;
import co.edu.konradlorenz.excolnet.Entities.Interes;
import co.edu.konradlorenz.excolnet.R;
import de.hdodenhof.circleimageview.CircleImageView;

import static java.lang.Double.parseDouble;

public class NocturneLifeAdapter extends RecyclerView.Adapter<NocturneLifeAdapter.TopicHolder> {

    public View view;
    public CardView commonsCardView;
    private ArrayList<Interes> currentInterests;
    private Context myContext;

    public NocturneLifeAdapter(ArrayList<Interes> interest, Context context){
            this.currentInterests = interest;
            this.myContext = context;

    }

    @NonNull
    @Override
    public TopicHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.commoncard, parent, false);
        commonsCardView = (CardView) view.findViewById(R.id.common_card);

        NocturneLifeAdapter.TopicHolder topicHolder = new NocturneLifeAdapter.TopicHolder(view);

        return topicHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TopicHolder holder, int position) {
        Double currLat =  currentInterests.get(position).getLatitude();
        Double currLong = currentInterests.get(position).getLongitude();
        holder.setLatitude(currLat);
        holder.setLongitude(currLong);
        holder.getTitle().setText(this.currentInterests.get(position).getTopicName());
        holder.getDescTextView().setText(this.currentInterests.get(position).getDescription());
        Glide.with(myContext).load(currentInterests.get(position).getPhotoUrl()).error(R.drawable.com_facebook_profile_picture_blank_square).into(holder.getImageVC());
    }

    @Override
    public int getItemCount() {
        return currentInterests.size();
    }

    public static  class TopicHolder extends  RecyclerView.ViewHolder {

        private CircleImageView imageVC;
        private TextView descTextView;
        private Button locationButton;
        private TextView title;


        //For get Location
        private Double latitude;
        private Double longitude;




        public TopicHolder(@NonNull View itemView) {
            super(itemView);
            this.imageVC = itemView.findViewById(R.id.circlenImage);
            this.descTextView = itemView.findViewById(R.id.textDesc);
            this.locationButton = itemView.findViewById(R.id.locationButton);
            this.title = itemView.findViewById(R.id.topicTitle);

            initializeLocationListener();
        }

        public void initializeLocationListener(){

            locationButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // Toast.makeText(locationButton.getContext() , "hola" , Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(itemView.getContext() , SitesActivity.class);
                    intent.putExtra("latitud" ,  latitude);
                    intent.putExtra("longitud", longitude);
                    intent.putExtra("titulo" , title.getText());
                    intent.putExtra("nameActivity" , "NocturneLife");
                    itemView.getContext().startActivity(intent);

                }
            });
        }

        public CircleImageView getImageVC() {
            return imageVC;
        }

        public void setImageVC(CircleImageView imageVC) {
            this.imageVC = imageVC;
        }

        public TextView getDescTextView() {
            return descTextView;
        }

        public void setDescTextView(TextView descTextView) {
            this.descTextView = descTextView;
        }

        public Button getLocationButton() {
            return locationButton;
        }

        public void setLocationButton(Button locationButton) {
            this.locationButton = locationButton;
        }

        public Double getLatitude() {
            return latitude;
        }

        public void setLatitude(Double latitude) {
            this.latitude = latitude;
        }

        public Double getLongitude() {
            return longitude;
        }

        public void setLongitude(Double longitude) {
            this.longitude = longitude;
        }

        public TextView getTitle() {
            return title;
        }

        public void setTitle(TextView title) {
            this.title = title;
        }
    }
}
