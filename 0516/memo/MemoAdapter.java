package com.example.memo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Map;

public class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onEdit(String key);
        void onDelete(String key);
    }

    private List<String> keys;
    private Map<String, String> memoMap;
    private OnItemClickListener listener;

    public MemoAdapter(List<String> keys, Map<String, String> memoMap, OnItemClickListener listener) {
        this.keys = keys;
        this.memoMap = memoMap;
        this.listener = listener;
    }

    public void updateList(List<String> newKeys) {
        this.keys = newKeys;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MemoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_memo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MemoAdapter.ViewHolder holder, int position) {
        String key = keys.get(position);
        String content = memoMap.get(key);

        if (content == null) content = "";

        String previewTitle = content.length() > 15 ? content.substring(0, 15) + "..." : content;

        holder.tvTitle.setText(previewTitle);
        holder.tvPreview.setText(content);
        holder.btnEdit.setOnClickListener(v -> listener.onEdit(key));
        holder.btnDelete.setOnClickListener(v -> listener.onDelete(key));
    }


    @Override
    public int getItemCount() {
        return keys.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        ImageButton btnEdit, btnDelete;
        TextView tvPreview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            tvPreview = itemView.findViewById(R.id.tvPreview);
        }
    }
}
