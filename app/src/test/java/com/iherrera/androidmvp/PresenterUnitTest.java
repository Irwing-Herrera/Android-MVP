package com.iherrera.androidmvp;

import com.iherrera.androidmvp.login.LoginActivityMVP;
import com.iherrera.androidmvp.login.model.User;
import com.iherrera.androidmvp.login.presenter.LoginActivityPresenter;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

/**
 * Unit Test de LoginActivityPresenter
 */
public class PresenterUnitTest {

    LoginActivityPresenter presenter;
    User user;

    // Variables simuladas
    LoginActivityMVP.Model mockedModel;
    LoginActivityMVP.View mockedView;

    // Se encarga de limpiar sus valores antes de ejecutar cada una de las pruebas
    @Before
    public void initialization() {
        mockedModel = mock(LoginActivityMVP.Model.class);
        mockedView = mock(LoginActivityMVP.View.class);

        user = new User("Antonio", "Banderas");

        // Implementacion concreta
        presenter = new LoginActivityPresenter(mockedModel);
        presenter.setView(mockedView);
    }

    @Test
    public void noExistInteractionWithView() {
        presenter.getCurrentUser();
        // Flujo de usuario no inicializado
        verify(mockedView, times(1)).showUserNotAvailable();
    }

    @Test
    public void loadUserFromTheRepoWhenValidUserIsPresenter() {
        // Simular metodos de la interfaz de Modelo
        when(mockedModel.getUser()).thenReturn(user);

        presenter.getCurrentUser();

        // Comprobamos la interactuacion con el modelo de datos
        verify(mockedModel, times(1)).getUser();

        // Comprobamos la interactuacion con la vista
        verify(mockedView, times(1)).setFirstName("Antonio");
        verify(mockedView, times(1)).setLastName("Banderas");
        verify(mockedView, never()).showUserNotAvailable();
    }

    @Test
    public void showErrorMessageWhenUserIsNull() {
        when(mockedModel.getUser()).thenReturn(null);

        presenter.getCurrentUser();

        // Comprobamos la interactuacion con el modelo de datos
        verify(mockedModel, times(1)).getUser();

        // Comprobamos la interactuacion con la vista
        verify(mockedView, never()).setFirstName("Antonio");
        verify(mockedView, never()).setLastName("Banderas");
        verify(mockedView, times(1)).showUserNotAvailable();
    }

    @Test
    public void createErrorMessageIfAnyFieldIsEmpty() {
        // Primer prueba
        when(mockedView.getFirstName()).thenReturn("");

        presenter.loginButtonClicked();

        verify(mockedView, times(1)).getFirstName();
        verify(mockedView, never()).getLastName();
        verify(mockedView, times(1)).showInputError();

        // Segunda Prueba
        when(mockedView.getFirstName()).thenReturn("Antonio");
        when(mockedView.getLastName()).thenReturn("");

        presenter.loginButtonClicked();

        verify(mockedView, times(2)).getFirstName();
        verify(mockedView, times(1)).getLastName();
        verify(mockedView, times(2)).showInputError();
    }

    @Test
    public void saveValidUser() {
        when(mockedView.getFirstName()).thenReturn("Antonio");
        when(mockedView.getLastName()).thenReturn("Banderas");

        presenter.loginButtonClicked();

        verify(mockedView, times(2)).getFirstName();
        verify(mockedView, times(2)).getLastName();

        // Comprobamos la interactuacion con el modelo de datos
        verify(mockedModel, times(1)).createUser("Antonio", "Banderas");
        // Comprobamos la interactuacion con la Vista
        verify(mockedView, times(1)).showUserSaved();
    }
}
