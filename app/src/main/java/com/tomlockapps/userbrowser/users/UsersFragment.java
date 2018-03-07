package com.tomlockapps.userbrowser.users;

import android.arch.lifecycle.ViewModelProviders;
import android.content.res.Configuration;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tomlockapps.userbrowser.R;
import com.tomlockapps.userbrowser.details.UserDetailActivity;
import com.tomlockapps.userbrowser.base.providers.impl.AndroidResourceProvider;
import com.tomlockapps.userbrowser.base.schedulers.SchedulerProvider;
import com.tomlockapps.userbrowser.data.RepositoryInjection;
import com.tomlockapps.userbrowser.databinding.FragmentUsersBinding;
import com.tomlockapps.userbrowser.users.adapter.UsersAdapter;
import com.tomlockapps.userbrowser.data.IUserModel;

public class UsersFragment extends Fragment implements UsersNavigator {

    private UsersViewModel viewModel;
    private RecyclerView recyclerView;

    public static UsersFragment newInstance() {
        UsersFragment fragment = new UsersFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel =  ViewModelProviders.of(this,
                new UsersViewModelFactory(RepositoryInjection.getUserDataRepository(),
                        SchedulerProvider.getInstance(), new AndroidResourceProvider(getActivity().getApplicationContext()))).get(UsersViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        viewModel.messageString.addOnPropertyChangedCallback(snackbarPropertyListener);
        viewModel.setUsersNavigator(this);

        FragmentUsersBinding binding = FragmentUsersBinding.inflate(inflater, container, false);
        binding.setViewmodel(viewModel);

        View root = binding.getRoot();

        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setAdapter(new UsersAdapter(viewModel));

        GridLayoutManager gridLayoutManager = new GridLayoutManager(recyclerView.getContext().getApplicationContext(),
                getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT ? 2 : 3);

        recyclerView.setLayoutManager(gridLayoutManager);

        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        if(savedInstanceState == null)
            viewModel.fetchTasks(true);
        else
            viewModel.onRestoreInstanceState(savedInstanceState);


        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        viewModel.messageString.removeOnPropertyChangedCallback(snackbarPropertyListener);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        viewModel.onSaveInstanceState(outState);
    }


    private Observable.OnPropertyChangedCallback snackbarPropertyListener = new Observable.OnPropertyChangedCallback() {
        @Override
        public void onPropertyChanged(Observable sender, int propertyId) {
            final String stringMessage = ((ObservableField<String>) sender).get();
            if(stringMessage != null) {
                Snackbar.make(getView(), stringMessage, Snackbar.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    public void showUserDetails(IUserModel userModel, int position) {
        View itemView = recyclerView.getLayoutManager().findViewByPosition(position);

        UserDetailActivity.startActivity(getActivity(), itemView.findViewById(R.id.item_user_image), userModel);
    }

}
