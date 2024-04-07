package Controller;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;

import Model.AppleBuyer;
import Model.Carte;
import Model.Chemin;
import Model.GameScenario;
import Model.Habitat;
import Model.Knight;
import Model.Player;
import Model.Witch;
import View.GameEntityFactory;
import View.HabEntity;
import View.PersEntity;
import View.GameEntityFactory.EntityType;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


import java.util.ArrayList;
import java.util.Map;

import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.*;

public class ShadowedOdysseyApp extends GameApplication {
    private final GameEntityFactory ShadowedOdysseyFactory = new GameEntityFactory();
    private Carte map;
    private Player player = Player.getInstance();

    @Override
    protected  void initGame(){

        run(()->{
            FXGL.inc("SpouseLife", -1);
            player.incrWifeLife(-1);
            if(player.getWifeLife() == 0){
                FXGL.getDialogService().showMessageBox("Game Over",()->{FXGL.getGameController().exit();});
            }
    },Duration.seconds(1));


        getGameWorld().addEntityFactory(ShadowedOdysseyFactory);
        PersEntity playerEntity = new PersEntity(player);
        playerEntity.spawn(0, 416, EntityType.PLAYER);
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
                    habEntity.spawnHab(abssice, (i + 1));
                    pers.spawn(abssice, i + 1, GameEntityFactory.EntityType.WITCH);
                }
                if (hab.getType().equals("k")) {
                    HabEntity habEntity = new HabEntity(hab);
                    PersEntity pers = new PersEntity(hab.getHabitant());
                    habEntity.spawnHab(j, i);
                    pers.spawn(j, i , GameEntityFactory.EntityType.KNIGHT);
                    ShadowedOdysseyFactory.spawnRoad(94 * (i+4), (j+1) * 90);
                }
                if (hab.getType().equals("b")) {
                    HabEntity habEntity = new HabEntity(hab);
                    PersEntity pers = new PersEntity(hab.getHabitant());
                    habEntity.spawnHab(abssice, i);
                    pers.spawn(abssice,i, GameEntityFactory.EntityType.APPLEBUYER);                }

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
        settings.setMainMenuEnabled(true);
    }

    @Override
    protected void initPhysics() {
        onCollisionBegin(GameEntityFactory.EntityType.PLAYER, GameEntityFactory.EntityType.WITCH, (Entity player, Entity witch) -> {
            Witch thisWitch = witch.getComponent(Witch.class);
            System.out.println(thisWitch.speak());
            Player mainPlayer= player.getComponent(Player.class);
            int number_of_chauve_souris_before = mainPlayer.getnbreChauveSouris();
            int spouse_xp = mainPlayer.getWifeLife();
            thisWitch.trade();
            FXGL.inc("SpouseLife", mainPlayer.getWifeLife()-spouse_xp);
            if(thisWitch.getNbreChauveSouris() == 1 && number_of_chauve_souris_before == 1){
                FXGL.inc("Bat",-1);
            }
        });

    

        onCollisionBegin(GameEntityFactory.EntityType.PLAYER, GameEntityFactory.EntityType.KNIGHT, (Entity player, Entity knight) -> {
            Knight thisKnight = knight.getComponent(Knight.class);
            System.out.println(thisKnight.speak());
            boolean passed = thisKnight.trade();
            if(passed){
                FXGL.getDialogService().showMessageBox("Game Won",()->{FXGL.getGameController().exit();});
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


        onCollisionBegin(GameEntityFactory.EntityType.PLAYER, GameEntityFactory.EntityType.APPLEBUYER, (Entity player, Entity buyer) -> {
            AppleBuyer buy = buyer.getComponent(AppleBuyer.class);
            Player mainPlayer= player.getComponent(Player.class);
            int apples_before_trade = mainPlayer.getNbrePommes();
            int money_before_trade = mainPlayer.getMoney();
            boolean traded = buy.trade();
            FXGL.inc("Apple",mainPlayer.getNbrePommes()-apples_before_trade);
            FXGL.inc("Money", money_before_trade + (apples_before_trade-mainPlayer.getNbrePommes())*buy.getApplePrice());
        });
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("Apple", 0);
        vars.put("Bat",0);
        vars.put("Money",0);
        vars.put("SpouseLife",player.getWifeLife());
    }

    @Override
    protected void initUI() {
        Label appleLabel = new Label();
        Label batLabel = new Label();
        Label moneyLabel = new Label();
        Label spouseLife = new Label();
        appleLabel.setTextFill(Color.RED);
        batLabel.setTextFill(Color.GRAY);
        moneyLabel.setTextFill(Color.GREEN);
        spouseLife.setTextFill(Color.PINK);
        appleLabel.setFont(Font.font(20.0));
        batLabel.setFont(Font.font(20.0));
        moneyLabel.setFont(Font.font(20.0));
        spouseLife.setFont(Font.font(20.0));
        appleLabel.textProperty().bind(FXGL.getip("Apple").asString(" Apple: %d "));
        batLabel.textProperty().bind(FXGL.getip("Bat").asString(" Bat: %d "));
        moneyLabel.textProperty().bind(FXGL.getip("Money").asString(" Money: %d "));
        spouseLife.textProperty().bind(FXGL.getip("SpouseLife").asString(" Spouse life: %d "));
        FXGL.addUINode(appleLabel, 20, 10);
        FXGL.addUINode(batLabel,100,10);
        FXGL.addUINode(moneyLabel,160,10);
        FXGL.addUINode(spouseLife,270,10);
    }
        



    public static void main(String[] args) {
        launch(args);
    }




}
