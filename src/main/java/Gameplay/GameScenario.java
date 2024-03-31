package Gameplay;


public class GameScenario {
    private Witch witch1;
    private Witch witch2;
    private Witch witch3;
    private Witch witch4;
    private Knight knight;

    private AppleBuyer buyer;

    private Habitat habitat1;
    private Habitat habitat2;
    private Habitat habitat3;
    private Habitat habitat5;
    private Habitat habitat4;
    

    private Chemin chemin1;
    private Chemin chemin2;
    private Chemin chemin3;
    private Chemin chemin4;
    private Chemin chemin5;

    private Carte map;
    private Player player;

    public Carte returnMap(){
        witch1= new Witch("Semoua", Diet.CARNIVOR,120,10,30,"witch.png");
        witch2= new Witch("Jezz", Diet.VEGAN,90,20,0,"witch.png");
        witch3= new Witch("Marta", Diet.VEGAN, 20, 15, 0, "witch.png");
        witch4= new Witch("Lola", Diet.CARNIVOR, 35, 18, 25, "witch.png");
        knight= new Knight("Samir", 10, "knight.png");

        habitat1 = new Habitat("Semoua's store",witch1,"w");
        habitat2 = new Habitat("Jezz's store",witch2,"w");
        habitat3 = new Habitat("Marta's store",witch3,"w");
        habitat4 = new Habitat("Lola's store",witch4,"w");
        habitat5 = new Habitat("Samir's borders", knight,"k");

        chemin1= new Chemin(0,0);
        chemin2= new Chemin(0,0);
        chemin3= new Chemin(0,0);
        chemin4= new Chemin(0,0);
        chemin5= new Chemin(0,0);
        
        chemin1.addHabitats(habitat5);
        chemin2.addHabitats(habitat1);
        chemin2.addHabitats(habitat3);
        chemin4.addHabitats(habitat2);
        


        map =  new Carte("lvl1");

        map.addChemin(chemin1);
        map.addChemin(chemin2);
        map.addChemin(chemin3);
        map.addChemin(chemin4);
        map.addChemin(chemin5);

        return map;
    }

    public Player getPlayer(){
        return player;
    }
}
