package co.edu.konradlorenz.excolnet.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import co.edu.konradlorenz.excolnet.Entities.Comentario;
import co.edu.konradlorenz.excolnet.R;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentHolder> {
    private ArrayList<Comentario> items;
    private Context context;
    private View view;

    public CommentAdapter(ArrayList<Comentario> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public CommentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_adapter, parent, false);
        CommentAdapter.CommentHolder pvh = new CommentAdapter.CommentHolder(view);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentHolder holder, int position) {
        holder.nombreUsuario.setText(items.get(position).getUsuario().getDisplayName());
        holder.textoComentario.setText(items.get(position).getTextComment());
        Glide.with(context).load(items.get(position).getUsuario().getPhotoUrl()).placeholder(R.drawable.ic_profile).error(R.drawable.com_facebook_profile_picture_blank_square).fitCenter().apply(RequestOptions.circleCropTransform()).into(holder.fotoUsuario);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class CommentHolder extends RecyclerView.ViewHolder {
        ImageView fotoUsuario;
        TextView nombreUsuario;
        TextView textoComentario;

        CommentHolder(View itemView) {
            super(itemView);
            fotoUsuario = itemView.findViewById(R.id.user_image_comment);
            nombreUsuario = itemView.findViewById(R.id.user_name);
            textoComentario = itemView.findViewById(R.id.comment_text);
        }
    }
}
