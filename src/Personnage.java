public abstract  class  Personnage{
    private String name;

    public Personnage(String name){
        this.name=name;
    }

    public String toString() {
        return "My name is "+name;
    }

}