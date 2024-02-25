package model.interfaces;

import model.Stmt;

import java.util.ArrayList;
import java.util.List;

public abstract class Parent {
    protected List<Stmt> children;

    public void add(Stmt s) {
        children.add(s);
    }

    public List<Stmt> getChildren() {
        return children;
    }
 }
