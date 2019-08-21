package com.feel.horizontalscrollview;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Activity activity;
    private ArrayList<String> arrayList;

    public RecyclerAdapter(Activity activity, ArrayList<String> arrayList) {
        this.activity = activity;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = null;
        view = LayoutInflater.from(activity.getApplicationContext()).inflate(R.layout.listitem, viewGroup, false);
        return new BodyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        ((BodyViewHolder) viewHolder).onBind(arrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public static class BodyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvName;

        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tvName = itemView.findViewById(R.id.tv_name);

        }

        public void onBind(String item) {
            tvName.setText(item);
        }

        @Override
        public void onClick(View view) {
            Log.e("test", tvName.getText().toString());
        }
    }


}
