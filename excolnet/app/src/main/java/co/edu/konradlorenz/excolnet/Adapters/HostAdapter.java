package co.edu.konradlorenz.excolnet.Adapters;

import android.content.Context;
import android.content.Intent;
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

import co.edu.konradlorenz.excolnet.Activities.SitesActivity;
import co.edu.konradlorenz.excolnet.Entities.Host;
import co.edu.konradlorenz.excolnet.R;

public class HostAdapter extends RecyclerView.Adapter<HostAdapter.HostHolder> {

    private ArrayList<Host> hosts;
    private CardView cardViewHosts;
    private Context context;
    private String ACTIVITY_NAME = "HostAdapter";
    private View view;


    public HostAdapter(Context context, ArrayList<Host> hosts) {
        this.hosts = hosts;
        this.context = context;
    }


    @NonNull
    @Override
    public HostAdapter.HostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_housting, parent, false);
        findMaterialElements();
        HostAdapter.HostHolder hth = new HostAdapter.HostHolder(view);
        return hth;
    }


    @Override
    public void onBindViewHolder(@NonNull final HostAdapter.HostHolder holder, final int position) {
        holder.nombreHost.setText(hosts.get(position).getNombreHost());
        holder.descripcionHost.setText(hosts.get(position).getDescripcionHost());
        holder.precioHost.setText(hosts.get(position).getPrecioHost());
        Glide.with(context).load(hosts.get(position).getImagenHost()).into(holder.imagenHost);
        cardViewHosts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), SitesActivity.class);
                intent.putExtra("id", hosts.get(position).getId());
                intent.putExtra("nameActivity", ACTIVITY_NAME);

                intent.putExtra("latitud", hosts.get(position).getLatitud());
                intent.putExtra("longitud", hosts.get(position).getLongitud());
                intent.putExtra("titulo", hosts.get(position).getNombreHost());

                view.getContext().startActivity(intent);
            }
        });
    }

    private void findMaterialElements() {
        cardViewHosts = view.findViewById(R.id.host_card);

    }

    @Override
    public int getItemCount() {
        return hosts.size();
    }

    public ArrayList<Host> getHosts() {
        return hosts;
    }

    public void setHosts(ArrayList<Host> hosts) {
        this.hosts = hosts;
    }


    public static class HostHolder extends RecyclerView.ViewHolder {
        ImageView imagenHost;
        TextView nombreHost;
        TextView descripcionHost;
        TextView precioHost;

        public HostHolder(@NonNull View itemView) {
            super(itemView);
            imagenHost = itemView.findViewById(R.id.imagen_host);
            nombreHost = itemView.findViewById(R.id.titulo_host);
            descripcionHost = itemView.findViewById(R.id.descripcion_host);
            precioHost = itemView.findViewById(R.id.precio_host);
        }
    }
}
