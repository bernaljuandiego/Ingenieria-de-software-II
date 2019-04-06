package co.edu.konradlorenz.excolnet.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import co.edu.konradlorenz.excolnet.Entities.Usuario;
import co.edu.konradlorenz.excolnet.R;

public class AdapterSearch extends RecyclerView.Adapter<AdapterSearch.MyViewHolder> {
    ArrayList<Usuario> list;
    private Context context;

    public AdapterSearch(Context context, ArrayList<Usuario> list){
        this.context=context;
        this.list=list;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_search,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(list.get(position).getDisplayName());
        Glide.with(context).load(list.get(position).getPhotoUrl()).apply(RequestOptions.circleCropTransform()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        ImageView image;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.user_name_search);
            image = itemView.findViewById(R.id.user_image_search);

        }
    }



}
