package com.edx.shell.android.rommiematic.roomies.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.edx.shell.android.rommiematic.R;
import com.edx.shell.android.rommiematic.domain.Util;
import com.edx.shell.android.rommiematic.entities.User;
import com.edx.shell.android.rommiematic.libs.base.ImageLoader;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author Shell_Core
 */
public class RoomiesAdapter extends RecyclerView.Adapter<RoomiesAdapter.ViewHolder> {

    private List<User> roomies;
    private ImageLoader imageLoader;
    private OnItemClickListener clickListener;

    public RoomiesAdapter(List<User> roomies, ImageLoader imageLoader, OnItemClickListener clickListener) {
        this.roomies = roomies;
        this.imageLoader = imageLoader;
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_contact, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User user = roomies.get(position);
        holder.setClickListener(user, clickListener);

        holder.txtUser.setText(user.getEmail());
        imageLoader.load(holder.imgAvatar, Util.getAvatarUrl(user.getEmail()));
    }

    @Override
    public int getItemCount() {
        return roomies.size();
    }

    public void add(User user) {
        if (!roomies.contains(user)) {
            roomies.add(user);
            notifyDataSetChanged();
        }
    }

    public void remove(User user) {
        if (roomies.contains(user)) {
            roomies.remove(user);
            notifyDataSetChanged();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private View view;

        @Bind(R.id.img_avatar)
        CircleImageView imgAvatar;
        @Bind(R.id.txt_user)
        TextView txtUser;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.view = itemView;
        }

        private void setClickListener(final User user, final OnItemClickListener clickListener) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onItemClick(user);
                }
            });
        }
    }
}
