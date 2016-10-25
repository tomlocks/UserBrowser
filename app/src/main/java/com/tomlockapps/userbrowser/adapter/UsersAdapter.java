package com.tomlockapps.userbrowser.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tomlockapps.userbrowser.R;
import com.tomlockapps.userbrowser.viewmodel.IUserModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tomlo on 25.10.2016.
 */

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder> {

    private List<IUserModel> userModelList;
    private OnItemClickListener onItemClickListener;

    public UsersAdapter(List<IUserModel> userModelList) {
        this.userModelList = userModelList;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user, parent, false);

        UserViewHolder viewHolder = new UserViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        final IUserModel userModel = userModelList.get(position);

        holder.text.setText(userModel.getName());
        Picasso.with(holder.avatar.getContext()).load(userModel.getAvatarUrl()).into(holder.avatar);

        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener != null)
                    onItemClickListener.onItemClick(userModel);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userModelList.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {

        final ImageView avatar;
        final TextView text;

        public UserViewHolder(View itemView) {
            super(itemView);

            avatar = (ImageView) itemView.findViewById(R.id.item_user_image);
            text =  (TextView) itemView.findViewById(R.id.item_user_name);
        }

        public void setOnClickListener(View.OnClickListener onClickListener) {
            itemView.setOnClickListener(onClickListener);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(IUserModel userModel);
    }

}
