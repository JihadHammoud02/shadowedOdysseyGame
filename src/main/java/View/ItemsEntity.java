package View;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.EntityFactory;



public class ItemsEntity implements EntityFactory {

    public enum EntityType {
        PLAYER,ROAD,WITCHZONE,WITCH,WITCHBARRIER,APPLE,BAT,KNIGHT,KNIGHTFENCE,APPLEBUYERZONE,APPLEBUYER
    }


    public void spawnRoad(int x, int y){
         FXGL.entityBuilder()
                .type(EntityType.ROAD)
                .at(x ,y-2)
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
                .viewWithBBox("bat.png")
                .collidable()
                .buildAndAttach();
    }


}
