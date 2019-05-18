package co.edu.konradlorenz.excolnet.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.libraries.places.internal.ag;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import co.edu.konradlorenz.excolnet.Entities.Agencia;
import co.edu.konradlorenz.excolnet.Entities.Plan;
import co.edu.konradlorenz.excolnet.R;

public class agenciesAdapter extends RecyclerView.Adapter<agenciesAdapter.agenciesHolder>{

    private Context context;

    private ArrayList<Agencia> agencies;

    private View view;

    public agenciesAdapter(Context context , ArrayList<Agencia> agencias){
        this.context = context;
        this.agencies =  agencias;
    }

    @Override
    public void onBindViewHolder(@NonNull agenciesHolder holder, int position) {
            holder.getTitle().setText(agencies.get(position).getNombre());
            holder.getPhoneNumber().setText(agencies.get(position).getTelefono() +"");
        Glide.with(context).load(agencies.get(position).getPhotoLogo()).error(R.drawable.com_facebook_profile_picture_blank_square).into(holder.getAgencieImg());

    }

    @NonNull
    @Override
    public agenciesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.agencie_card, parent, false);
        agenciesHolder agenciesH = new agenciesHolder(view);

        return agenciesH;

    }

    @Override
    public int getItemCount() {
        return agencies.size();
    }

    public static class agenciesHolder extends RecyclerView.ViewHolder{

        private ImageView agencieImg;

        private TextView title;
        private TextView phoneNumber;

        private Button plansButton;


        public agenciesHolder(@NonNull View itemView) {
            super(itemView);

            this.agencieImg =  itemView.findViewById(R.id.agencie_image);
            this.title = itemView.findViewById(R.id.agencie_name);
            this.phoneNumber =  itemView.findViewById(R.id.agencie_phone);
            this.plansButton = itemView.findViewById(R.id.plans_button);
            initializeBtnListener();
        }



        public void initializeBtnListener(){
                plansButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(plansButton.getContext() , "message", Toast.LENGTH_LONG).show();

                }

        });

        }

        public ImageView getAgencieImg() {
            return agencieImg;
        }

        public void setAgencieImg(ImageView agencieImg) {
            this.agencieImg = agencieImg;
        }

        public TextView getTitle() {
            return title;
        }

        public void setTitle(TextView title) {
            this.title = title;
        }

        public TextView getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(TextView phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public Button getPlansButton() {
            return plansButton;
        }

        public void setPlansButton(Button plansButton) {
            this.plansButton = plansButton;
        }
    }

}

