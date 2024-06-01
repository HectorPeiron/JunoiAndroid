package com.tfgjunio.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.tfgjunio.R;
import com.tfgjunio.domain.Animal;
import java.util.List;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder> {
    private Context context;
    private List<Animal> animalList;

    public AnimalAdapter(Context context, List<Animal> animalList) {
        this.context = context;
        this.animalList = animalList;
    }

    @NonNull
    @Override
    public AnimalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.animal_item, parent, false);
        return new AnimalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimalViewHolder holder, int position) {
        Animal animal = animalList.get(position);
        holder.fechaLlegada.setText("Fecha de llegada: " + animal.getFechaLlegada().toString());
        holder.numero.setText("Número: " + animal.getNumero());
        holder.sexo.setText("Sexo: " + animal.getSexo());
        holder.peso.setText("Peso: " + animal.getPeso() + " kg");
        holder.tipoAnimal.setText("Tipo: " + animal.getAnimalTipoAnimal().getNombre());
    }

    @Override
    public int getItemCount() {
        return animalList.size();
    }

    static class AnimalViewHolder extends RecyclerView.ViewHolder {
        TextView fechaLlegada, numero, sexo, peso, tipoAnimal;

        public AnimalViewHolder(View itemView) {
            super(itemView);
            fechaLlegada = itemView.findViewById(R.id.animalFechaLlegada);
            numero = itemView.findViewById(R.id.animalNumero);
            sexo = itemView.findViewById(R.id.animalSexo);
            peso = itemView.findViewById(R.id.animalPeso);
            tipoAnimal = itemView.findViewById(R.id.animalTipo);
        }
    }
}
