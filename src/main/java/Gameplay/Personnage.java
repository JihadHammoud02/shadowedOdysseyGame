package Gameplay;


import com.almasb.fxgl.entity.component.Component;

public abstract  class  Personnage  extends Component {
    private String name;
    private String imageName;

    public Personnage(String name){
        this.name=name;
    }

    public String getImageName(){ return imageName;}
    public String getName(){ return name;}
    public String toString() {
        return "My name is "+name;
    }

    public String speak() {
        return "";
    }
    public void trade(){ /* to be overriden */}


    }

