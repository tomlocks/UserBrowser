package com.tomlockapps.userbrowser.presenter;

import com.tomlockapps.userbrowser.interactor.IUsersInteractor;
import com.tomlockapps.userbrowser.presenter.mock.MockedImmediateUsersInteractor;
import com.tomlockapps.userbrowser.presenter.mock.MockedNetworkUsersInteractor;
import com.tomlockapps.userbrowser.presenter.mock.MockedUser;
import com.tomlockapps.userbrowser.view.IUsersListView;
import com.tomlockapps.userbrowser.viewmodel.IUserModel;
import com.tomlockapps.userbrowser.viewmodel.UserColor;
import com.tomlockapps.userbrowser.viewmodel.UsersViewModel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;

/**
 * Created by tomlo on 27.10.2016.
 */
public class UsersListPresenterTest {

    private UsersListPresenter presenter;

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
        mockView = Mockito.mock(IUsersListView.class);
    }

    @Test
    public void fetchUsersNetworkSuccess() throws Exception {
        mockInteractor = new MockedNetworkUsersInteractor(true);
        presenter = new UsersListPresenter(mockInteractor);
        presenter.attach(mockView);

        presenter.fetchUsers();

        Mockito.verify(mockView,times(1)).showProgress(true);

        Mockito.verify(mockView, timeout(2000).times(1)).showUsers(viewModelCaptor.capture());

        assertEquals(2, viewModelCaptor.getValue().getiUserModelList().size());

        assertEquals("Adam", viewModelCaptor.getValue().getiUserModelList().get(0).getName());
        assertEquals("www.example.org", viewModelCaptor.getValue().getiUserModelList().get(0).getAvatarUrl());
        assertEquals(UserColor.BLUE, viewModelCaptor.getValue().getiUserModelList().get(0).getBackgroundColor());


        Mockito.verify(mockView, times(1)).showProgress(false);
        Mockito.verify(mockView, times(0)).showFetchFailMessage();
    }

    @Test
    public void fetchUsersNetworkFail() throws Exception {
        mockInteractor = new MockedNetworkUsersInteractor(false);
        presenter = new UsersListPresenter(mockInteractor);
        presenter.attach(mockView);

        presenter.fetchUsers();
        Mockito.verify(mockView, timeout(2000).times(1)).showFetchFailMessage();


        Mockito.verify(mockView,times(1)).showProgress(true);
        Mockito.verify(mockView,times(1)).showProgress(false);
    }


    @Test
    public void fetchUsersImmediate() throws Exception {
        mockInteractor = new MockedImmediateUsersInteractor();
        presenter = new UsersListPresenter(mockInteractor);
        presenter.attach(mockView);

        presenter.fetchUsers();

        Mockito.verify(mockView).showUsers(viewModelCaptor.capture());

        assertEquals(2, viewModelCaptor.getValue().getiUserModelList().size());

        assertEquals("Adam", viewModelCaptor.getValue().getiUserModelList().get(0).getName());
        assertEquals("www.example.org", viewModelCaptor.getValue().getiUserModelList().get(0).getAvatarUrl());
        assertEquals(UserColor.BLUE, viewModelCaptor.getValue().getiUserModelList().get(0).getBackgroundColor());

        Mockito.verify(mockView, times(0)).showProgress(true);
        Mockito.verify(mockView, times(1)).showProgress(false);
        Mockito.verify(mockView, times(0)).showFetchFailMessage();
    }

    @Test
    public void onUserClick() throws Exception {
        mockInteractor = new MockedImmediateUsersInteractor();
        presenter = new UsersListPresenter(mockInteractor);
        presenter.attach(mockView);

        presenter.onUserClick(new MockedUser("Test", "www", UserColor.LIME));

        Mockito.verify(mockView).showUserDetails(userModelCaptor.capture());

        assertEquals(userModelCaptor.getValue().getName(), "Test");
        assertEquals(userModelCaptor.getValue().getAvatarUrl(), "www");
        assertEquals(userModelCaptor.getValue().getBackgroundColor(), UserColor.LIME);
    }

}