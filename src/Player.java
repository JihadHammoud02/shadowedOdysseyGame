public class Player extends Personnage {
    private static Player instance;
    private int nbrePommes=0;
    private int monnaie=0;
    private int nbreChauveSouris=0;



    public Player(String name){
        super(name);
    }

    public static Player getInstance() {
        if (instance == null) {
            instance = new Player("villageois");
        }
        return instance;
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

    public void sellPommes(int pommes){
        nbrePommes-=pommes;
    }
    
    
}
