package model;

import model.interfaces.Observer;

import java.util.ArrayList;
import java.util.List;

public class Action extends Observer {
    private final List<Stmt> stmts;

    public Action() {
        stmts = new ArrayList<>();
    }

    public void add(Stmt s) {
        stmts.add(s);
    }

    @Override
    public void update() {
        for (Stmt s : stmts) {
            s.execute();
        }
    }
}
