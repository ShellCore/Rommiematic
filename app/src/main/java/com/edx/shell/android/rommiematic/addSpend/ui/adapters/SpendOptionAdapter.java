package com.edx.shell.android.rommiematic.addSpend.ui.adapters;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.edx.shell.android.rommiematic.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author Shell_Core
 */
public class SpendOptionAdapter extends RecyclerView.Adapter<SpendOptionAdapter.ViewHolder> {

    private List<String> images;
    private OnImageOptionClickListener clickListener;


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_spend_image, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String image = images.get(position);
        holder.setOnClickListener(image, clickListener);

        holder.imgSpendOption.setImageURI(Uri.parse(image));
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // Variables
        private View view;

        // Componentes
        @Bind(R.id.img_spend_option)
        ImageView imgSpendOption;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.view = itemView;
        }

        private void setOnClickListener(final String imagePath, final OnImageOptionClickListener clickListener) {
            imgSpendOption.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onClick(imagePath);
                }
            });
        }
    }
}
