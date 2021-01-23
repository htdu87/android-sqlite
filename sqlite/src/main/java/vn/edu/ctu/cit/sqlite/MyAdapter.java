package vn.edu.ctu.cit.sqlite;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<String> data;

    public MyAdapter(List<String> data) {
        this.data=data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String str=data.get(position);
        holder.textView.setText(str);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<String> data) {
        this.data=data;
        notifyDataSetChanged();
    }

    /**
     * MyViewHolder
     */
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        final TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(android.R.id.text1);
        }
    }
}
