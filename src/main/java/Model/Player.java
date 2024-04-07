package Model;


public class Player extends Personnage {
    private static final Player instance = new Player("villageois",60);
    private int nbrePommes=0;
    private int monnaie=0;
    private int nbreChauveSouris=0;
    private int wifeLife;
    private boolean canMove = true;

    public boolean getCanMove() {
        return this.canMove;
    }

    public void setCanMove(boolean bool) {
        this.canMove = bool;
    }


    private Player(String name,int wifeLife){
        super(name);
        this.wifeLife=wifeLife;
    }

    public static Player getInstance() {
        return instance;
    }

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

    public int sellPommes(int pommes,int price){
        if( pommes > nbrePommes) {
            System.out.println("player doesn't have this much of apples");
            return -1;
        }
        nbrePommes-=pommes;
        monnaie=monnaie+price*pommes;
        return 0;
    }

    public int getMoney(){
        return monnaie;
    }

    public int getWifeLife(){
        return wifeLife;
    }

    public void incrWifeLife(int xp){
        wifeLife+=xp;
    }

    public boolean trade(){
        return false;
    }



}
