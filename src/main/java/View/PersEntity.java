package View;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;

import Model.Personnage;

import static com.almasb.fxgl.dsl.FXGL.onKey;
import javafx.scene.input.KeyCode;

public class PersEntity {

    private Personnage pers;

    public PersEntity(Personnage pers){
        this.pers=pers;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return pers.toString();
    }

    public Personnage getPers(){
        return pers;
    }



    public void spawnPlayer(int x, int y){
        Entity player = FXGL.entityBuilder()
                .type(ItemsEntity.EntityType.PLAYER)
                .at(x,y)
                .with(pers)
                .collidable()
                .view("playerIdle.png")
                .bbox(new HitBox(BoundingShape.box(30,20)))
                .buildAndAttach();

        // Key binding logic
        onKey(KeyCode.LEFT , ()->{
            if(player.getX() > 0 ) {
                player.translateX(-2);
            }
        });

        onKey(KeyCode.RIGHT , ()->{
            if(player.getX() < 368) {
                player.translateX(2);
            }
        }

        );
        onKey(KeyCode.UP , ()->{
            player.translateY(-1);
        });
        onKey(KeyCode.DOWN , ()->{
            if(player.getY() < 416)player.translateY(1);
        });

    }

    

    public void spawn(int x, int y, ItemsEntity.EntityType EntityType){
        if (EntityType == ItemsEntity.EntityType.WITCH){
           FXGL.entityBuilder()
            .type(EntityType)
            .at( x + 30, y*90  - 40)
            .viewWithBBox(pers.getImageName())
            .collidable()
            .with(pers)
            .buildAndAttach();
        }
        else if (EntityType == ItemsEntity.EntityType.KNIGHT){
            FXGL.entityBuilder()
                .type(EntityType)
                .at((x+4)*90, (y * 90)+30)
                .viewWithBBox(pers.getImageName())
                .collidable()
                .with(pers)
                .buildAndAttach();
        }
        else if (EntityType == ItemsEntity.EntityType.APPLEBUYER){
           FXGL.entityBuilder()
                    .type(EntityType)
                    .at(x+4, (y*90)+60)
                    .viewWithBBox(pers.getImageName())
                    .collidable()
                    .with(pers)
                    .buildAndAttach();

        }
        else{
            if(EntityType == ItemsEntity.EntityType.PLAYER){
                spawnPlayer(x, y);
            }
        }
        

    }



}
