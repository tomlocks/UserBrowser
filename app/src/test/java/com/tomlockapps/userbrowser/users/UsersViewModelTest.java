package com.tomlockapps.userbrowser.users;

import com.tomlockapps.userbrowser.base.schedulers.ImmediateSchedulerProvider;
import com.tomlockapps.userbrowser.data.IUserModel;
import com.tomlockapps.userbrowser.data.source.UserDataRepository;
import com.tomlockapps.userbrowser.data.source.UserDataSource;
import com.tomlockapps.userbrowser.users.mock.MockedCacheDataSource;
import com.tomlockapps.userbrowser.users.mock.MockedUser;
import com.tomlockapps.userbrowser.users.mock.MockedUserDataSource;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by tomlo on 07/03/2018.
 */
public class UsersViewModelTest {

    private final List<IUserModel> mockedUsers = Arrays.asList(new MockedUser("Test1", "avatarUrl1", UserDataSource.DAILY_MOTION),
            new MockedUser("Test2", "avatarUrl2", UserDataSource.GITHUB),
            new MockedUser("Test3", "avatarUrl3", UserDataSource.GITHUB)
    );

    private final List<IUserModel> mockedUsersCache = Arrays.asList(new MockedUser("TestCache1", "avatarUrlCache1", UserDataSource.DAILY_MOTION),
            new MockedUser("TestCache2", "avatarUrlCache2", UserDataSource.GITHUB),
            new MockedUser("TestCache3", "avatarUrlCache3", UserDataSource.GITHUB)
    );

    @Test
    public void fetchTasksWithoutCache() throws Exception {
        UsersViewModel viewModel = new UsersViewModel(UserDataRepository.createWithoutCacheSupport(new MockedUserDataSource(mockedUsers)),
                new ImmediateSchedulerProvider(), resId -> "Mock");

        viewModel.fetchTasks(true);

        Assert.assertEquals(viewModel.models.stream().filter(userModel -> userModel.getSourceType() == UserDataSource.DAILY_MOTION).count(), 1);
        Assert.assertEquals(viewModel.models.stream().filter(userModel -> userModel.getSourceType() == UserDataSource.GITHUB).count(), 2);
    }

    @Test
    public void fetchTasksWithCache() throws Exception {
        UsersViewModel viewModel = new UsersViewModel(UserDataRepository.createWithCacheSupport(new MockedCacheDataSource(mockedUsersCache), new MockedUserDataSource(mockedUsers)),
                new ImmediateSchedulerProvider(), resId -> "Mock");

        viewModel.fetchTasks(true);

        Assert.assertEquals(viewModel.models.stream().filter(userModel -> userModel.getName().contains("Cache")).count(), 3);
        Assert.assertEquals(viewModel.models.stream().filter(userModel -> !userModel.getName().contains("Cache")).count(), 0);
    }

    @Test
    public void fetchFreshTasksAndSave() throws Exception {
        List<IUserModel> savedModels = new ArrayList<>();

        UsersViewModel viewModel = new UsersViewModel(UserDataRepository.createWithCacheSupport(new MockedCacheDataSource(mockedUsersCache) {
                                                                                                    @Override
                                                                                                    public void insert(IUserModel modelList) {
                                                                                                        super.insert(modelList);

                                                                                                        savedModels.add(modelList);
                                                                                                    }
                                                                                                }
                , new MockedUserDataSource(mockedUsers)),
                new ImmediateSchedulerProvider(), resId -> "Mock");

        viewModel.fetchTasks(false);

        Assert.assertEquals(viewModel.models.stream().filter(userModel -> userModel.getName().contains("Cache")).count(), 0);
        Assert.assertEquals(viewModel.models.stream().filter(userModel -> !userModel.getName().contains("Cache")).count(), 3);

        Assert.assertEquals(savedModels.stream().filter(userModel -> !userModel.getName().contains("Cache")).count(), 3);
    }

    @Test
    public void onItemClick() throws Exception {
        int expectedPosition = 1;

        UsersViewModel viewModel = new UsersViewModel(UserDataRepository.createWithoutCacheSupport(new MockedUserDataSource(mockedUsers)),
                new ImmediateSchedulerProvider(), resId -> "Mock");

        UsersNavigator usersNavigator = (userModel, position) -> {
            Assert.assertEquals(expectedPosition, position);
            Assert.assertEquals(userModel, mockedUsers.get(expectedPosition));
        };

        viewModel.setUsersNavigator(usersNavigator);

        viewModel.fetchTasks(true);

        viewModel.onItemClick(mockedUsers.get(expectedPosition));
    }

}