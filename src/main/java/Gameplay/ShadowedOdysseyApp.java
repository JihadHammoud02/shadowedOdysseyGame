package Gameplay;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.entity.Entity;


import static com.almasb.fxgl.dsl.FXGL.onCollisionBegin;
import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

public class ShadowedOdysseyApp extends GameApplication {
    private final GameEntityFactory ShadowedOdysseyFactory = new GameEntityFactory();
    private Carte map;
    private Player player = Player.getInstance();

    @Override
    protected  void initGame(){
        getGameWorld().addEntityFactory(ShadowedOdysseyFactory);
        ShadowedOdysseyFactory.spawnPlayer(player);
        GameScenario mygame = new GameScenario();
        Carte mymap = mygame.returnMap();
        int counter=1;
        for(int j=1; j<=mymap.returnChemin().size();j++){

            Chemin cheminAct = mymap.returnChemin().get(j-1);
            int n =cheminAct.getLength()-cheminAct.getHabitats().size();
            int i=0;

            if(counter==1 && j!= cheminAct.getLength()){
                i=i+1;
                n=n+1;
            }
            while(i<n){
                if(i<cheminAct.getHabitats().size()) {
                    
                    HabEntity habitat = new HabEntity(cheminAct.getHabitats().get(i));
                    PersEntity personne = new PersEntity(cheminAct.getHabitats().get(i).getHabitant());
                    
                    
                    if (cheminAct.getHabitats().get(i).getType().equals("w")) {
                        // Spanning Witch Habitat
                        habitat.spawnHab(i,j);
                        personne.spawn(i,j, GameEntityFactory.EntityType.WITCH);
                        ShadowedOdysseyFactory.spawnRoad(94 * (i + 2), j * 90);
                    }
                    else if (cheminAct.getHabitats().get(i).getType().equals("k")) {
                        // Spanning Knight Habitat
                        habitat.spawnHab(i, j);
                        personne.spawn(i, j, GameEntityFactory.EntityType.KNIGHT);
                        ShadowedOdysseyFactory.spawnRoad(94 * i,  j * 90);
                        ShadowedOdysseyFactory.spawnRoad(94 * (i+4),  j * 90);
                    }
                }
                else{
                    ShadowedOdysseyFactory.spawnRoad(94 * i, j * 90);
                }
                i++;
            }
            counter*=-1;
        }

    }

    @Override
    protected  void initSettings(GameSettings settings){

        settings.setTitle("ShadowedOdyssey");
        settings.setWidth(470);
        settings.setHeight(480);
    }

    @Override
    protected void initPhysics() {
        onCollisionBegin(GameEntityFactory.EntityType.PLAYER, GameEntityFactory.EntityType.WITCH, (Entity player, Entity witch) -> {
            Witch thisWitch = witch.getComponent(Witch.class);
            System.out.println(thisWitch.speak());
            thisWitch.trade();
        });

        onCollisionBegin(GameEntityFactory.EntityType.PLAYER, GameEntityFactory.EntityType.KNIGHT, (Entity player, Entity knight) -> {
            Knight thisKnight = knight.getComponent(Knight.class);
            System.out.println(thisKnight.speak());
            thisKnight.trade();
        });
    }



    public static void main(String[] args) {
        launch(args);
    }




}
