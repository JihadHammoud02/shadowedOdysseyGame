package Gameplay;
import javax.tools.Diagnostic.Kind;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.gameplay.GameDifficulty;


public class PersEntity extends Entity{

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

    public void spawn(int x, int y, GameEntityFactory.EntityType EntityType){
        if (EntityType == GameEntityFactory.EntityType.WITCH){
            Entity graphical_entity = FXGL.entityBuilder()
            .type(EntityType)
            .at( x + 30, y*90  - 40)
            .viewWithBBox(pers.getImageName())
            .collidable()
            .with(pers)
            .buildAndAttach();
        }
        else if (EntityType == GameEntityFactory.EntityType.KNIGHT){
            Entity graphical_entity = FXGL.entityBuilder()
                .type(EntityType)
                .at((x+4)*90, (y * 90)+30)
                .viewWithBBox(pers.getImageName())
                .collidable()
                .with(pers)
                .buildAndAttach();
        }
        else if (EntityType == GameEntityFactory.EntityType.APPLEBUYER){
            Entity graphical_entity = FXGL.entityBuilder()
                    .type(EntityType)
                    .at(x+4, (y*90)+60)
                    .viewWithBBox(pers.getImageName())
                    .collidable()
                    .with(pers)
                    .buildAndAttach();

        }
        

    }



}
