package com.edx.shell.android.rommiematic.login;

import com.edx.shell.android.rommiematic.libs.base.EventBus;
import com.edx.shell.android.rommiematic.login.events.LoginEvent;
import com.edx.shell.android.rommiematic.login.ui.LoginView;

import org.greenrobot.eventbus.Subscribe;

/**
 * @author Shell_Core
 */
public class LoginPresenterImpl implements LoginPresenter {

    // Servicios
    private EventBus eventBus;
    private LoginView view;
    private LoginInteractor interactor;

    /**
     * Constructor necesario para la inyección de dependencias
     * @param eventBus
     * @param view
     * @param interactor
     */
    public LoginPresenterImpl(EventBus eventBus, LoginView view, LoginInteractor interactor) {
        this.eventBus = eventBus;
        this.view = view;
        this.interactor = interactor;
    }

    /**
     * Función que manda a registrar la actividad a EventBus
     */
    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    /**
     * Función que manda a eliminar el registro de la actividad, en el EventBus. También libera la
     * memoria de la vista para que no exista un memory leak.
     */
    @Override
    public void onDestroy() {
        view = null;
        eventBus.unregister(this);
    }

    /**
     * Función que manda a realizar los eventos de la vista y llama al interactuador a ejecutar la
     * función de login
     * @param email
     * @param pass
     */
    @Override
    public void validateLogin(String email, String pass) {
        if (view != null) {
            view.hideInputs();
            view.showProgressbar();
        }

        interactor.doSignin(email, pass);
    }

    /**
     * Función que manda a realizar los eventos de la vista y llama al interactuador a ejecutar la
     * función de registro de nuevo usuario
     * @param email
     * @param pass
     */
    @Override
    public void registerNewUser(String email, String pass) {
        if (view != null) {
            view.hideInputs();
            view.showProgressbar();
        }
        interactor.doSignup(email, pass);
    }

    /**
     * Funcion que se llama a llamar cuando se lanza un evento del LoginEvent, por parte del
     * EventBus
     * @param event
     */
    @Override
    @Subscribe
    public void onEventMainThread(LoginEvent event) {
        switch (event.getEventType()) {
            case LoginEvent.ON_SIGNIN_ERROR :
                onSigninError(event.getError());
                break;
            case LoginEvent.ON_SIGNUP_ERROR :
                onSignupError(event.getError());
                break;
            case LoginEvent.ON_FAILED_TO_RECOVER_SESSION :
                onFailedToRecoverySession();
                break;
            case LoginEvent.ON_SIGNIN_SUCCESS :
                onSigninSuccess(event.getCurrentEmail());
                break;
            case LoginEvent.ON_SIGNUP_SUCCESS :
                onSignupSuccess();
                break;
            default:

                break;
        }
    }

    // Funciones necesarias para el manejador de eventos

    /**
     * En caso de que la función de login falle, manda un mensaje de error.
     * @param error
     */
    private void onSigninError(String error) {
        if (view != null) {
            view.hideProgressbar();
            view.showInputs();
            view.loginError(error);
        }
    }

    /**
     * En caso de que la función de creación de cuenta falle, manda un mensaje de error.
     * @param error
     */
    private void onSignupError(String error) {
        if (view != null) {
            view.hideProgressbar();
            view.showInputs();
            view.newUserError(error);
        }
    }

    /**
     * Cuando no se puede recuperar la sesión activa del usuario, inicializa los elementos para el
     * login o la creación de cuenta.
     */
    private void onFailedToRecoverySession() {
        if (view != null) {
            view.hideProgressbar();
            view.showInputs();
        }
    }

    /**
     * Cuando el inicio de sesión se realiza correctamente, se realiza el proceso de cambio de
     * actividad a la principal.
     * @param email
     */
    private void onSigninSuccess(String email) {
        if (view != null) {
            view.setUserEmail(email);
            view.navigateToMainScreen();
        }
    }

    /**
     * Cuando se genera un usuario satisfactoriamente, se realiza el proceso necesario en la
     * actividad.
     */
    private void onSignupSuccess() {
        if (view != null) {
            view.newUserSuccess();
        }
    }

}
