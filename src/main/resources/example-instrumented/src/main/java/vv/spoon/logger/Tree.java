package vv.spoon.logger;

import java.util.ArrayList;
import java.util.List;


public class Tree {

    private String nom;
    private ArrayList<Tree> fils = new ArrayList();
    private Tree pere;

    public void addFils(Tree treeNode) {
        this.fils.add(treeNode);
    }

    public Tree getPere() {
        return pere;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPere(Tree pere) {
        this.pere = pere;
    }

    public String getNom() {
        return nom;
    }

    public List<Tree> getFils() {
        return fils;
    }
}
