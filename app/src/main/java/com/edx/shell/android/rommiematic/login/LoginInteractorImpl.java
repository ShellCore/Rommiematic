package com.edx.shell.android.rommiematic.login;

/**
 * @author Shell_Core
 */
public class LoginInteractorImpl implements LoginInteractor {

    // Servicios
    private LoginRepository repository;

    /**
     * Constructor necesario para la inyección de dependencias
     * @param repository
     */
    public LoginInteractorImpl(LoginRepository repository) {
        this.repository = repository;
    }

    /**
     * Se manda a llamar la función necesaria del repositorio para el servicio de inicio de sesión
     * @param user
     * @param pass
     */
    @Override
    public void doSignin(String user, String pass) {
        repository.signin(user, pass);
    }

    /**
     * Se manda a llamar la función necesaria del repositorio para el servicio de creación de nuevo
     * usuario.
     * @param user
     * @param pass
     */
    @Override
    public void doSignup(String user, String pass) {
        repository.signup(user, pass);
    }
}
