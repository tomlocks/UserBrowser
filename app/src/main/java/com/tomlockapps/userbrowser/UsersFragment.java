package com.tomlockapps.userbrowser;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tomlockapps.userbrowser.action.FragmentUsersListActions;
import com.tomlockapps.userbrowser.adapter.UsersAdapter;
import com.tomlockapps.userbrowser.interactor.DailyMotionUsersInteractor;
import com.tomlockapps.userbrowser.interactor.GithubUsersInteractor;
import com.tomlockapps.userbrowser.interactor.MultiSourceUsersInteractor;
import com.tomlockapps.userbrowser.presenter.IUsersListPresenter;
import com.tomlockapps.userbrowser.presenter.UsersListPresenter;
import com.tomlockapps.userbrowser.sources.dailymotion.DailyMotionService;
import com.tomlockapps.userbrowser.sources.dailymotion.DailyMotionSource;
import com.tomlockapps.userbrowser.sources.github.GithubSource;
import com.tomlockapps.userbrowser.view.IUsersListView;
import com.tomlockapps.userbrowser.viewmodel.IUserModel;
import com.tomlockapps.userbrowser.viewmodel.UsersViewModel;

import java.util.ArrayList;

public class UsersFragment extends Fragment implements IUsersListView {

    private static final String EXTRA_USERS = "UsersFragment.UsersModelList";

    private IUsersListPresenter presenter;

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefresh;
    private UsersAdapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private final ArrayList<IUserModel> userModelList = new ArrayList<>();


    public static UsersFragment newInstance() {
        UsersFragment fragment = new UsersFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new UsersAdapter(userModelList);

        adapter.setOnItemClickListener(onItemClickListener);

        MultiSourceUsersInteractor multiSourceUsersInteractor = new MultiSourceUsersInteractor(new DailyMotionUsersInteractor(DailyMotionSource.getService()), new GithubUsersInteractor(GithubSource.getService()));

        presenter = new UsersListPresenter(this, new FragmentUsersListActions(getActivity()), multiSourceUsersInteractor);//new MultiSiteUsersInteractor(GithubSource.getService()));
        mLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 2);
    }

    @Override
    public void onStart() {
        super.onStart();

        presenter.init();
    }

    @Override
    public void onStop() {
        super.onStop();

        presenter.uninit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View inflated = inflater.inflate(R.layout.fragment_users, container, false);

        if(savedInstanceState != null) {
            ArrayList<IUserModel> list = savedInstanceState.getParcelableArrayList(EXTRA_USERS);

            userModelList.addAll(list);
        }

        swipeRefresh = (SwipeRefreshLayout) inflated.findViewById(R.id.swipeRefresh);
        swipeRefresh.setColorSchemeResources(
                R.color.colorAccent,
                R.color.colorAccent,
                R.color.colorAccent);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.fetchUsers();
            }
        });

        recyclerView = (RecyclerView) inflated.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);



        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);



        return inflated;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        if(userModelList.size() == 0) {
            presenter.fetchUsers();
        }

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void showUsers(UsersViewModel viewModel) {
        userModelList.clear();

        userModelList.addAll(viewModel.getiUserModelList());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showProgress(final boolean show) {
        swipeRefresh.post(new Runnable() {
            @Override
            public void run() {
                swipeRefresh.setRefreshing(show);
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelableArrayList(EXTRA_USERS, userModelList);
    }

    private UsersAdapter.OnItemClickListener onItemClickListener = new UsersAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(IUserModel userModel) {
            presenter.onUserClick(userModel);
        }
    };

    @Override
    public void showFailMessage() {
        Snackbar.make(getView(), R.string.fetching_error, Snackbar.LENGTH_SHORT).show();
    }
}
