package model;

import model.interfaces.Observer;
import model.interfaces.Parent;

import java.util.ArrayList;
import java.util.List;

public class Action extends Observer implements Parent {
    public List<Stmt> getStmts() {
        return children;
    }

    @Override
    public void update() {
        for (Stmt s : children) {
            s.execute();
        }
    }
}
