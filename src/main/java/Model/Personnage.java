package Model;


import com.almasb.fxgl.entity.component.Component;

public abstract  class  Personnage  extends Component {
    private String name;

    public Personnage(String name){
        this.name=name;
    }

    public abstract String getImageName();
    
    public String getName(){ return name;}
    
    public String toString() {
        return "My name is "+name;
    }

    public String speak() {
        return "";
    }

    // method to be overriden by each npc , specifying the trade logic
    public abstract boolean trade();


    }

