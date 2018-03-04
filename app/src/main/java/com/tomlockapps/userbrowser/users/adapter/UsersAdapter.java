package com.tomlockapps.userbrowser.users.adapter;


import com.tomlockapps.userbrowser.R;
import com.tomlockapps.userbrowser.base.adapter.BindableRecyclerAdapter;
import com.tomlockapps.userbrowser.data.IUserModel;
import com.tomlockapps.userbrowser.databinding.ItemUserBinding;
import com.tomlockapps.userbrowser.users.UsersViewModel;

/**
 * Adapter responsible for showing user information(name, avatars).
 *
 * Created by tomlo on 25.10.2016.
 */

public class UsersAdapter extends BindableRecyclerAdapter<UsersAdapter.UsersAdapterViewHolder, IUserModel, ItemUserBinding> {

    private final UsersViewModel viewModel;

    public UsersAdapter(UsersViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    protected int getLayoutIdForPosition(int position) {
        return R.layout.item_user;
    }

    public class UsersAdapterViewHolder extends BindableRecyclerAdapter.ViewHolder<ItemUserBinding, IUserModel> {

        public UsersAdapterViewHolder(ItemUserBinding binding) {
            super(binding);
        }

        @Override
        protected void bind(ItemUserBinding binding, IUserModel object) {
            binding.setItemModel(object);
            binding.setViewmodel(viewModel);
        }
    }
}
