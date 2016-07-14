package com.edx.shell.android.rommiematic.spends.ui.adapters;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.edx.shell.android.rommiematic.R;
import com.edx.shell.android.rommiematic.domain.Util;
import com.edx.shell.android.rommiematic.entities.Spend;
import com.edx.shell.android.rommiematic.libs.base.ImageLoader;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author Shell_Core
 */
public class SpendsAdapter extends RecyclerView.Adapter<SpendsAdapter.ViewHolder> {

    // Variables
    List<Spend> spends;


    // Servicios
    private ImageLoader imageLoader;
    private OnItemClickListener clickListener;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_spend, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Spend spend = spends.get(position);
        holder.setClickListener(spend, clickListener);
        holder.txtSpendDescription.setText(spend.getDescription());
        holder.txtSpendAmount.setText(Util.getCurrencyFromDouble(spend.getAmount()));
        holder.imgSpendIcon.setImageURI(Uri.parse(spend.getIcon()));
    }

    @Override
    public int getItemCount() {
        return spends.size();
    }

    public void add(Spend spend) {
        if (!spends.contains(spend)) {
            spends.add(spend);
            notifyDataSetChanged();
        }
    }

    public void remove(Spend spend) {
        if (spends.contains(spend)) {
            spends.remove(spend);
            notifyDataSetChanged();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // Variables
        private View view;

        // Componentes
        @Bind(R.id.img_spend_icon)
        CircleImageView imgSpendIcon;
        @Bind(R.id.txt_spend_description)
        TextView txtSpendDescription;
        @Bind(R.id.txt_spend_amount)
        TextView txtSpendAmount;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.view = itemView;
        }

        private void setClickListener(final Spend spend, final OnItemClickListener clickListener) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onItemClick(spend);
                }
            });
        }
    }
}
