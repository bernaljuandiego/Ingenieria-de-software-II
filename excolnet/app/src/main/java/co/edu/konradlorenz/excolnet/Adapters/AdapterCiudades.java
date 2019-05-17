package co.edu.konradlorenz.excolnet.Adapters;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterCiudades {
    private Context context;
    private ArrayList<String> ciudades;

    public AdapterCiudades(Context context, ArrayList<String> ciudades) {
        this.context = context;
        this.ciudades = ciudades;
    }

    public class CiudadesHolder extends RecyclerView.ViewHolder{
        public CiudadesHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
