package Controller;

import Auth.Authorization;
import Auth.AuthorizationException;
import Model.Actors.User;
import Model.Data.Model;
import View.View;

public class ControllerInternetShop {
    private View view;
    private Model model;
    private Authorization auth;
    private User user;

    public ControllerInternetShop(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public Model getModel() {
        return model;
    }

    public View getView() {
        return view;
    }

}
