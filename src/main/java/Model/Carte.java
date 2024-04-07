package Model;



import java.util.ArrayList;

public class Carte {
    private String nom;
    private ArrayList<Chemin> chemins;

    public Carte(String nom){
        this.nom=nom;
        this.chemins= new ArrayList<Chemin>();

    }

    public void addChemin(Chemin ch){
        chemins.add(ch);
    }

    public ArrayList<Chemin> returnChemin(){
        return chemins;
    }

    @Override
    public String toString() {
        return nom;
    }
}

