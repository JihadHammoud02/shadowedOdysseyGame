package Gameplay;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


import java.util.ArrayList;
import java.util.Map;

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
                ShadowedOdysseyFactory.spawnRoad(94 * i, j * 90);
                i++;
            }
            counter*=-1;
        }

        attachObjects(mymap.returnChemin(),188);



    }

    public void attachObjects(ArrayList<Chemin> chemins,int abssice ) {
        for (int i = 0; i < chemins.size(); i++) {
            Chemin cheminAct = chemins.get(i);
            for (int j = 0; j < cheminAct.getHabitats().size(); j++) {
                Habitat hab = cheminAct.getHabitats().get(j);
                if (hab.getType().equals("w")) {
                    HabEntity habEntity = new HabEntity(hab);
                    PersEntity pers = new PersEntity(hab.getHabitant());
                    habEntity.spawnHab(abssice, (i + 1) * 90);
                    pers.spawn(abssice, (i + 1) * 90);

                }

            }
            addItems(cheminAct,(i+1)*90 -21,"Apple");
            if(cheminAct.getNbreChauveSouris() > 0){
                addItems(cheminAct,(i+1)*90 -21,"Bat");
            }
        }
    }

    public void addItems(Chemin chemin , int y, String item){
        if(item.equals("Bat")){
            ShadowedOdysseyFactory.spawnBat(188,y-14);
        }
        else {
            for (int i = 0; i < chemin.getNbrePommes(); i++) {
                ShadowedOdysseyFactory.spawnApple(47 * (i + 1), y);
            }
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
            System.out.println(thisWitch.getNbreChauveSouris());
            if(thisWitch.getNbreChauveSouris() == 1){
                FXGL.inc("Bat",-1);
            }
        });

        onCollisionBegin(GameEntityFactory.EntityType.PLAYER, GameEntityFactory.EntityType.APPLE, (Entity player, Entity apple) -> {
            Player mainPlayer= player.getComponent(Player.class);
            apple.removeFromWorld();
            mainPlayer.collectPomme();
            FXGL.inc("Apple",1);
        });

        onCollisionBegin(GameEntityFactory.EntityType.PLAYER, GameEntityFactory.EntityType.BAT, (Entity player, Entity bat) -> {
            Player mainPlayer= player.getComponent(Player.class);
            bat.removeFromWorld();
            mainPlayer.collectChauveSouris();
            FXGL.inc("Bat",1);

        });
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("Apple", 0);
        vars.put("Bat",0);
        vars.put("Money",0);
    }

    @Override
    protected void initUI() {
        Label appleLabel = new Label();
        Label batLabel = new Label();
        Label moneyLabel = new Label();
        appleLabel.setTextFill(Color.RED);
        batLabel.setTextFill(Color.GRAY);
        moneyLabel.setTextFill(Color.GREEN);
        appleLabel.setFont(Font.font(20.0));
        batLabel.setFont(Font.font(20.0));
        moneyLabel.setFont(Font.font(20.0));
        appleLabel.textProperty().bind(FXGL.getip("Apple").asString("Apple: %d"));
        batLabel.textProperty().bind(FXGL.getip("Bat").asString("Bat: %d"));
        moneyLabel.textProperty().bind(FXGL.getip("Money").asString("Money: %d"));
        FXGL.addUINode(appleLabel, 20, 10);
        FXGL.addUINode(batLabel,100,10);
        FXGL.addUINode(moneyLabel,160,10);
    }
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
