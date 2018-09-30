package com.example.onrename;



import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {


    private LayoutInflater inflater;
    private List<Elements> phones;
    private RecycleListActivity.OnItemClickListener listener;//1создаем листенер

    DataAdapter(Context context, List<Elements> phones, RecycleListActivity.OnItemClickListener listener)
    {
        this.listener = listener;
        this.phones = phones;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.my_list_item, parent, false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Elements phone = phones.get(position);
        holder.tovarView.setText(phone.getTovar());
        holder.categoryView.setText(phone.getCategory());
        holder.priceView.setText(phone.getPrice());

        //2 устанавливаем слушатель
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(phone);
            }
        });

    }


    @Override
    public int getItemCount() {
        return phones.size();
    }


    public class ViewHolder extends  RecyclerView.ViewHolder {

        final TextView tovarView, categoryView, priceView;
        ViewHolder(View view) {
            super(view);
            tovarView = view.findViewById(R.id.tovar);
            categoryView = view.findViewById(R.id.category);
            priceView = view.findViewById(R.id.price);

        }
    }


}
