package Gameplay;



import java.util.ArrayList;

public class Chemin {
    private static int numeroChemin;
    private int nbrePommes;
    private int nbreChauveSouris;
    private int length;
    private ArrayList<Habitat> habitats;

    public Chemin(int nbreChauveSouris, int nbrePommes){
        this.nbreChauveSouris=nbreChauveSouris;
        this.nbrePommes=nbrePommes;
        this.habitats= new ArrayList<Habitat>();
        this.length=5;
        numeroChemin++;

    }

    public ArrayList<Habitat> getHabitats(){
        return this.habitats;
    }

    public void addHabitats(Habitat hab){
        habitats.add(hab);
    }

    public int getLength(){
        return length;
    }

    public int getNbrePommes(){
        return nbrePommes;
    }
    public int getNbreChauveSouris(){
        return nbreChauveSouris;
    }


}
