package Model;
import java.util.Scanner;

public class Knight extends Personnage{
    private int prixDemande; 
    private String imageName;

    public Knight(String name, int prixDemande, String imageName){
        super(name);
        this.prixDemande = prixDemande;
        this.imageName = imageName;
    }

    @Override
    public String getImageName(){
        return imageName;
    }

    public int getPrixDemande(){
        return prixDemande;
    }

    @Override
    public String toString(){
        return super.toString() + " and I am a knight";
    }

    @Override
    public String speak(){
        return 
        "|              Welcome to the borders.       |\n"+
        "|    In order to proceed to the next map,    |\n"+
        "|      you will need to pay me " + prixDemande + " coins.     |\n";
    }

    @Override
    public boolean trade() {
        Player player = Player.getInstance();
        Scanner playerInput = new Scanner(System.in);
        String playerAnswer;
        System.out.println("Do you want to go to the next map? (Y/n)");
        playerAnswer = playerInput.nextLine();
        if (playerAnswer.equals("Y")) { 
            boolean paid = player.pay(prixDemande);
            if (paid) {
                System.out.println("The money has been transferred. Good luck on your quest!");
                return true;
            }
            System.out.println("You don't have enough coins to go through the borders.");
        }
        return false;
    }
}

