package Model;


public class GameLevel {

    // Creating components of  the level
    private Witch witch1;
    private Knight knight;

    private AppleBuyer buyer;

    private Habitat habitat1;
    private Habitat habitat2;
    private Habitat habitat3;
    

    private Chemin chemin1;
    private Chemin chemin2;
    private Chemin chemin3;
    private Chemin chemin4;
    private Chemin chemin5;

    private Carte map;

    
    public Carte returnMapLevel1(){
        witch1= new Witch("Semoua", Diet.CARNIVOR,120,10,30,"witch.png");
        knight= new Knight("Samir", 10, "knight.png");
        buyer = new AppleBuyer("bob", 15, 5,"idle.gif");

        habitat1 = new Habitat("Semoua's store",witch1,"w");
        habitat2 = new Habitat("bob's store",buyer,"b");
        habitat3 = new Habitat("Samir's borders", knight,"k");

        chemin1= new Chemin(0,0);
        chemin2= new Chemin(0,0);
        chemin3= new Chemin(1,0);
        chemin4= new Chemin(0,0);
        chemin5= new Chemin(0,8);
        
        chemin1.addHabitats(habitat3);
        chemin2.addHabitats(habitat1);
        chemin4.addHabitats(habitat2);

        


        map =  new Carte("lvl1");

        map.addChemin(chemin1);
        map.addChemin(chemin2);
        map.addChemin(chemin3);
        map.addChemin(chemin4);
        map.addChemin(chemin5);

        return map;
    }

}
