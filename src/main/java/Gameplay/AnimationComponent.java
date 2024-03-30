package Gameplay;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.geometry.Point2D;
import javafx.util.Duration;

public class AnimationComponent  extends Component {
    private AnimatedTexture texture;
    private AnimationChannel animIdle,animWalk;
    private int movingSpeed;

    public AnimationComponent(){
        animIdle=new AnimationChannel(FXGL.image("playerIdle.png"),1,45,46, Duration.seconds(1),0,0);
        animWalk = new AnimationChannel(FXGL.image("playerRun.png"),8,378/7,46,Duration.seconds(1),2,6);

        texture = new AnimatedTexture(animIdle);
    }
    @Override
    public void onAdded(){
        entity.getTransformComponent().setScaleOrigin(new Point2D(27,23));
        entity.getViewComponent().addChild(texture);
    }

    @Override
    // Called by the Game Engine on each frame
    public void onUpdate(double tpf){
        entity.translateX(movingSpeed * tpf); // distance = v * t

        if(movingSpeed != 0){
            if(texture.getAnimationChannel() == animIdle){
                texture.loopAnimationChannel(animWalk);
            }
            movingSpeed = (int) (movingSpeed * 0.858); // to decrease the speed by 30% when LEFT or RIGHT is not clicked
            if(FXGLMath.abs(movingSpeed) < 1){
                movingSpeed = 0;
                texture.loopAnimationChannel(animIdle);
            }
        }
    }

    public void moveRight(){

        movingSpeed = 50;
        entity.setScaleX(1);
    }

    public void moveLeft(){

        movingSpeed = -50;
        entity.setScaleX(-1);
    }




}
