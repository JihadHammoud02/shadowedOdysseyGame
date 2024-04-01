package Gameplay;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;

public class HabEntity{
    private Habitat habitat;
    public HabEntity(Habitat hab){
        habitat=hab;
    }

    public Habitat getHabitat(){
        return habitat;
    }

    public void spawnBlocks(int x, int y, String img, GameEntityFactory.EntityType EntityType){
        FXGL.entityBuilder()
                .type(EntityType)
                .at(x,y)
                .viewWithBBox(img)
                .collidable()
                .buildAndAttach();
    }

    public void spawnItem(int x, int y,String img, GameEntityFactory.EntityType EntityType){
        FXGL.entityBuilder()
                .type(EntityType)
                .at(x,y)
                .viewWithBBox(img)
                .collidable()
                .buildAndAttach();
    }

    public void spawnHab(int x, int y){
        if(habitat.getType().equals("w")){
            spawnBlocks( x, y ,"witchZone.png", GameEntityFactory.EntityType.WITCHZONE);
            spawnBlocks( x+46, y ,"witchZone.png", GameEntityFactory.EntityType.WITCHZONE);
            spawnItem( x, y- 40,"witch2.png", GameEntityFactory.EntityType.WITCHBARRIER);
        }else if(habitat.getType().equals("k")){
            spawnBlocks(94*(x+4), (y*90)+50, "knight_fence.png", GameEntityFactory.EntityType.KNIGHTFENCE);
        } else if(habitat.getType().equals("b")) {
            spawnBlocks( x-20, y-70 ,"house3.png", GameEntityFactory.EntityType.APPLEBUYERZONE);
            spawnItem( x, y- 40,"idle.gif", GameEntityFactory.EntityType.APPLEBUYER);

        }
        // Other condition if habitat was Apple buyer ( to show other entities instead)
    }
}
