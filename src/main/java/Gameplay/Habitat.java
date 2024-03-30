package Gameplay;



public class Habitat {

    private String nom;
    private Personnage habitant;
    private String type;


    public Habitat(String nom , Personnage habitant,String type){
        this.nom=nom;
        this.habitant=habitant;
        this.type=type;

    }

    @Override
    public String toString(){
        return habitant.toString() + " Welcome to "+nom;
    }

    public String getType(){
        return  type;
    }

    public Personnage getHabitant(){
        return habitant;
    }

}
