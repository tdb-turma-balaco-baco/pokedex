package br.ufpr.tads.mobile.pokedex.service;

import javax.security.auth.login.LoginException;

import br.ufpr.tads.mobile.pokedex.model.Login;

public interface LoginService {
    void efetuarLogin(Login login) throws LoginException;
    void efetuarLogout();
}
