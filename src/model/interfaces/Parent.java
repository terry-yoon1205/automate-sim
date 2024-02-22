package model.interfaces;

import model.Stmt;

import java.util.ArrayList;
import java.util.List;

public interface Parent {
    List<Stmt> children = new ArrayList<>();

    default void add(Stmt s) {
        children.add(s);
    }
}
