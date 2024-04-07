package View;
import com.almasb.fxgl.dsl.FXGL;

import Model.Habitat;

public class HabEntity{
    private Habitat habitat;
    public HabEntity(Habitat hab){
        habitat=hab;
    }

    public Habitat getHabitat(){
        return habitat;
    }

    public void spawnBlocks(int x, int y, String img, ItemsEntity.EntityType EntityType){
        FXGL.entityBuilder()
                .type(EntityType)
                .at(x,y)
                .viewWithBBox(img)
                .collidable()
                .buildAndAttach();
    }

    public void spawnItem(int x, int y,String img, ItemsEntity.EntityType EntityType){
        FXGL.entityBuilder()
                .type(EntityType)
                .at(x,y)
                .viewWithBBox(img)
                .collidable()
                .buildAndAttach();
    }

    public void spawnHab(int x, int y){
        if(habitat.getType().equals("w")){
            spawnBlocks( x, y*90 ,"witchZone.png", ItemsEntity.EntityType.WITCHZONE);
            spawnBlocks( x+46, y*90 ,"witchZone.png", ItemsEntity.EntityType.WITCHZONE);
            spawnItem( x, y*90- 40,"witch2.png", ItemsEntity.EntityType.WITCHBARRIER);
        }else if(habitat.getType().equals("k")){
            spawnBlocks(94*(x+4), (y*90)+50, "knight_fence.png", ItemsEntity.EntityType.KNIGHTFENCE);
        } else if(habitat.getType().equals("b")) {
            spawnBlocks( x-20, y*90+30 ,"house3.png", ItemsEntity.EntityType.APPLEBUYERZONE);

        }
    }
}
