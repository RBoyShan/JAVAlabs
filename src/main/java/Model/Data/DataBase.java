package Model.Data;

import Model.Actors.User;
import Model.Data.Entity.Product;

import java.util.ArrayList;
import java.util.HashMap;

public interface DataBase {
    HashMap<Product, Integer> readDataBase();
    ArrayList<User> readUsers();

    boolean writeDataBase();
}
