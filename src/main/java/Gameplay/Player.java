package Gameplay;



public class Player extends Personnage {
    private static final Player instance = new Player("villageois");
    private int nbrePommes=0;
    private int monnaie=0;
    private int nbreChauveSouris=0;


    private Player(String name){
        super(name);
    }

    public static Player getInstance() {
        return instance;
    }

    @Override
    public String getImageName(){
        return "";
    }

    public void collectChauveSouris(){
        nbreChauveSouris++;
    }

    public int getnbreChauveSouris(){
        return nbreChauveSouris;
    }

    public void echangerChauveSouris(){
        nbreChauveSouris-=1;
    }

    public boolean pay(int cost){
        if(cost<=monnaie){
            monnaie-=cost;
            return true;
        }
        return false;
    }

    public void getPaid(int earnings){
        monnaie += earnings;
    }

    public void collectPomme(){
        nbrePommes++;
    }

    public int getNbrePommes(){
        return nbrePommes;
    }

    public int sellPommes(int pommes){
        if( pommes > nbrePommes) {
            System.out.println("player doesn't have this much of apples");
            return -1;
        }
        nbrePommes-=pommes;
        return 0;
    }


}
