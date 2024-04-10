package View;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;

import Model.Personnage;
import Model.Player;

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


    // Function responsible of spawning Player with key bindings listener


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
        Player pl = player.getComponent(Player.class);
        onKey(KeyCode.LEFT , ()->{
            if(player.getX() > 0 ) {
                if(pl.getCanMove()) {
                    player.translateX(-2);
                } else {
                    player.translateX(5);
                    pl.setCanMove(true);
                }
            }
            

        });

        onKey(KeyCode.RIGHT , ()->{
            if(player.getX() < 420) {
                if(pl.getCanMove()) {
                    player.translateX(2);
                } else {
                    player.translateX(-5);
                    pl.setCanMove(true);
                }
                
            }
        }

        );
        onKey(KeyCode.UP , ()->{
            if(player.getY() >0 ) {
                if(pl.getCanMove()) {
                    player.translateY(-1);
                } else {
                    player.translateY(5);
                    pl.setCanMove(true);
                }
            }
        });
        onKey(KeyCode.DOWN , ()->{
            if(player.getY() < 418 ) {
                if(pl.getCanMove()) {
                    player.translateY(1);
                } else {
                    player.translateY(-5);
                    pl.setCanMove(true);
                }
            }
        });

    }

    
    // Function responsible of spawning NPCs and associate with them the attribute pers that links graphic and logic entity
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
                .at((x+4)*90, (y * 90 + 20))
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
