package com.tomlockapps.userbrowser.presenter;

import com.tomlockapps.userbrowser.presenter.mock.MockedUser;
import com.tomlockapps.userbrowser.view.IUserView;
import com.tomlockapps.userbrowser.viewmodel.IUserModel;
import com.tomlockapps.userbrowser.viewmodel.UserColor;
import com.tomlockapps.userbrowser.viewmodel.UserViewModel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.junit.Assert.*;

/**
 * Created by tomlo on 27.10.2016.
 */
public class UserPresenterTest {

    private ArgumentCaptor<UserViewModel> captorUserViewModel;

    private UserPresenter userPresenter;
    private IUserView mockedView;

    @Before
    public void setUp() throws Exception {
        captorUserViewModel = ArgumentCaptor.forClass(UserViewModel.class);

        mockedView = Mockito.mock(IUserView.class);

        userPresenter = new UserPresenter();
        userPresenter.attach(mockedView);
    }

    @Test
    public void initWithModel() throws Exception {
        MockedUser mockedUser = new MockedUser("name", "url", UserColor.BLUE);
        userPresenter.initWithModel(mockedUser);

        Mockito.verify(mockedView).showUserDetail(captorUserViewModel.capture());

        assertEquals(mockedUser.getName(), captorUserViewModel.getValue().getName());
        assertEquals(mockedUser.getAvatarUrl(), captorUserViewModel.getValue().getAvatarUrl());
        assertEquals(mockedUser.getBackgroundColor().getColorResId(), captorUserViewModel.getValue().getBackgroundColorResId());
    }

}