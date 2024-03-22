public class Player extends Personnage {
    private String name;
    private int nbrePommes;
    private int Monnaie;
    private int nbreChauveSouris=1;
    public Player(String name){
        super(name);
    }

    public int getnbreChauveSouris(){
        return nbreChauveSouris;
    }

    public void echangerChauveSouris(){
        nbreChauveSouris-=1;
    }


    public boolean pay(int cost){
        if(cost<=Monnaie){
            Monnaie-=cost;
            return  true;
        }
        return false;
    }
    
}
