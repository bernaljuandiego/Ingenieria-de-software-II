package co.edu.konradlorenz.excolnet.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.alespero.expandablecardview.ExpandableCardView;

import java.util.ArrayList;
import java.util.List;

import co.edu.konradlorenz.excolnet.Entities.Precios;
import co.edu.konradlorenz.excolnet.R;

public class AdapterPrecios extends RecyclerView.Adapter<AdapterPrecios.PriceHolder> {
        private Context mContext;
        List<Precios> precios_items;
        private View view;
        CardView cardView;

    public AdapterPrecios(Context mContext, List<Precios> precios_items) {
        this.mContext = mContext;
        this.precios_items = precios_items;
    }

    @NonNull
    @Override
    public PriceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view=LayoutInflater.from(parent.getContext()).inflate(R.layout.livingcard_layout,parent,false);
        findMaterialElements();
        PriceHolder priceHolder = new PriceHolder(view);
        return priceHolder;
    }

    private void findMaterialElements() {
       cardView=view.findViewById(R.id.expand_card);
    }

    @Override
    public void onBindViewHolder(@NonNull PriceHolder holder, int position) {
        holder.textViewnombre.setText(precios_items.get(position).getItem_name());
        holder.textViewprecio.setText("$"+String.format("%.0f",precios_items.get(position).getAverage_price()));
    }

    @Override
    public int getItemCount() {
        return precios_items.size();
    }


    public class PriceHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView textViewnombre;
        TextView textViewprecio;

        public PriceHolder(@NonNull View itemView) {
            super(itemView);
            cardView=itemView.findViewById(R.id.expand_card);
            textViewnombre = itemView.findViewById(R.id.precios_text_n);
            textViewprecio = itemView.findViewById(R.id.precios_text);
        }
    }
}
