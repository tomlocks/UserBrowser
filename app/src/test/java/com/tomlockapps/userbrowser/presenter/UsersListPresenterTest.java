package com.tomlockapps.userbrowser.presenter;

import com.tomlockapps.userbrowser.action.IUsersListActions;
import com.tomlockapps.userbrowser.interactor.IUsersInteractor;
import com.tomlockapps.userbrowser.presenter.mock.MockedImmediateUsersInteractor;
import com.tomlockapps.userbrowser.presenter.mock.MockedNetworkUsersInteractor;
import com.tomlockapps.userbrowser.presenter.mock.MockedUser;
import com.tomlockapps.userbrowser.view.IUserView;
import com.tomlockapps.userbrowser.view.IUsersListView;
import com.tomlockapps.userbrowser.viewmodel.IUserModel;
import com.tomlockapps.userbrowser.viewmodel.UserColor;
import com.tomlockapps.userbrowser.viewmodel.UserViewModel;
import com.tomlockapps.userbrowser.viewmodel.UsersViewModel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;

/**
 * Created by tomlo on 27.10.2016.
 */
public class UsersListPresenterTest {

    private UsersListPresenter presenter;

    private IUsersListActions mockActions;
    private IUsersInteractor mockInteractor;
    private IUsersListView mockView;

    @Captor
    ArgumentCaptor<UsersViewModel> viewModelCaptor;

    @Captor
    ArgumentCaptor<IUserModel> userModelCaptor;

    @Before
    public void setUp() throws Exception {
        viewModelCaptor = ArgumentCaptor.forClass(UsersViewModel.class);
        userModelCaptor = ArgumentCaptor.forClass(IUserModel.class);

        mockActions = Mockito.mock(IUsersListActions.class);

        mockView = Mockito.mock(IUsersListView.class);
    }

    @Test
    public void fetchUsersNetworkSuccess() throws Exception {
        mockInteractor = new MockedNetworkUsersInteractor(true);
        presenter = new UsersListPresenter(mockActions, mockInteractor);
        presenter.attach(mockView);

        presenter.fetchUsers();

        Mockito.verify(mockView,times(1)).showProgress(true);

        Mockito.verify(mockView, timeout(2000).times(1)).showUsers(viewModelCaptor.capture());

        assertEquals(2, viewModelCaptor.getValue().getiUserModelList().size());

        assertEquals("Adam", viewModelCaptor.getValue().getiUserModelList().get(0).getName());
        assertEquals("www.example.org", viewModelCaptor.getValue().getiUserModelList().get(0).getAvatarUrl());
        assertEquals(UserColor.BLUE, viewModelCaptor.getValue().getiUserModelList().get(0).getBackgroundColor());


        Mockito.verify(mockView, times(1)).showProgress(false);
        Mockito.verify(mockView, times(0)).showFailMessage();
    }

    @Test
    public void fetchUsersNetworkFail() throws Exception {
        mockInteractor = new MockedNetworkUsersInteractor(false);
        presenter = new UsersListPresenter(mockActions, mockInteractor);
        presenter.attach(mockView);

        presenter.fetchUsers();
        Mockito.verify(mockView, timeout(2000).times(1)).showFailMessage();


        Mockito.verify(mockView,times(1)).showProgress(true);
        Mockito.verify(mockView,times(1)).showProgress(false);
    }


    @Test
    public void fetchUsersImmediate() throws Exception {
        mockInteractor = new MockedImmediateUsersInteractor();
        presenter = new UsersListPresenter(mockActions, mockInteractor);
        presenter.attach(mockView);

        presenter.fetchUsers();

        Mockito.verify(mockView).showUsers(viewModelCaptor.capture());

        assertEquals(2, viewModelCaptor.getValue().getiUserModelList().size());

        assertEquals("Adam", viewModelCaptor.getValue().getiUserModelList().get(0).getName());
        assertEquals("www.example.org", viewModelCaptor.getValue().getiUserModelList().get(0).getAvatarUrl());
        assertEquals(UserColor.BLUE, viewModelCaptor.getValue().getiUserModelList().get(0).getBackgroundColor());

        Mockito.verify(mockView, times(0)).showProgress(true);
        Mockito.verify(mockView, times(1)).showProgress(false);
        Mockito.verify(mockView, times(0)).showFailMessage();
    }

    @Test
    public void onUserClick() throws Exception {
        mockInteractor = new MockedImmediateUsersInteractor();
        presenter = new UsersListPresenter(mockActions, mockInteractor);
        presenter.onUserClick(new MockedUser("Test", "www", UserColor.LIME));

        Mockito.verify(mockActions).showUserDetails(userModelCaptor.capture());

        assertEquals(userModelCaptor.getValue().getName(), "Test");
        assertEquals(userModelCaptor.getValue().getAvatarUrl(), "www");
        assertEquals(userModelCaptor.getValue().getBackgroundColor(), UserColor.LIME);
    }

}