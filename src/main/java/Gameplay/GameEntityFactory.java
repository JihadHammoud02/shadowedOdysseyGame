package Gameplay;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;

import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import javafx.scene.input.KeyCode;

import java.util.HashMap;
import java.util.Map;

import static com.almasb.fxgl.dsl.FXGL.onKey;

public class GameEntityFactory implements EntityFactory {

    public enum EntityType {
        PLAYER,ROAD,WITCHZONE,WITCH,WITCHBARRIER,APPLE,BAT
    }
    public Map<Entity, Personnage> binding = new HashMap<Entity,Personnage>();

    // add a function that takes an Entity as a parameter and build the right player
    // Function that create the Player
    public void spawnPlayer(Player ply){
        Entity player = FXGL.entityBuilder()
                .type(EntityType.PLAYER)
                .at(0,416)
                .with(new AnimationComponent())
                .with(ply)
                .collidable()
                .bbox(new HitBox(BoundingShape.box(30,20)))
                .buildAndAttach();

        // Key binding logic
        onKey(KeyCode.LEFT , ()->{
            if(player.getX() > 0 ) {
                player.translateX(-2);
                player.getComponent(AnimationComponent.class).moveLeft();
            }
        });

        onKey(KeyCode.RIGHT , ()->{
            if(player.getX() < 368) {
                player.translateX(2);
                player.getComponent(AnimationComponent.class).moveRight();
            }
        }
        );
        onKey(KeyCode.UP , ()->{
            player.translateY(-1);
        });
        onKey(KeyCode.DOWN , ()->{
            if(player.getY() < 416)player.translateY(1);
        });

        onKey(KeyCode.A,()->{
            System.out.println(ply.getNbrePommes());
        });
    }

    public void spawnRoad(int x, int y){
         FXGL.entityBuilder()
                .type(EntityType.ROAD)
                .at(x,y)
                .viewWithBBox("road.png")
                .collidable()
                .buildAndAttach();
    }

    public void spawnApple(int x, int y){
        FXGL.entityBuilder()
                .type(EntityType.APPLE)
                .at(x,y)
                .viewWithBBox("Apple.png")
                .collidable()
                .buildAndAttach();
    }

    public void spawnBat(int x, int y){
        FXGL.entityBuilder()
                .type(EntityType.BAT)
                .at(x,y)
                .viewWithBBox("Bat.png")
                .collidable()
                .buildAndAttach();
    }


}
