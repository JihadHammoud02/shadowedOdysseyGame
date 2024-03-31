package Gameplay;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;


public class PersEntity extends Entity{

    private Personnage pers;

    public PersEntity(Personnage pers){
        this.pers=pers;
    }

    public Personnage getPers(){
        return pers;
    }

    public void spawn(int x, int y){
        Entity graphical_witch = FXGL.entityBuilder()
                .type(GameEntityFactory.EntityType.WITCH)
                .at( x + 30, y - 40)
                .viewWithBBox(pers.getImageName())
                .collidable()
                .with(pers)
                .buildAndAttach();

    }



}
