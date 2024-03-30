package Gameplay;
import java.util.Scanner;

public class Knight extends Personnage{
    private int prixDemande; 

    public Knight(String name, int prixDemande){
        super(name);
        this.prixDemande = prixDemande;
    }

    public int getPrixDemande(){
        return prixDemande;
    }

    @Override
    public String toString(){
        return super.toString() + " and I am a knight";
    }

    public String speak(){
        return "Welcome to the borders. In order to proceed to the next map, you will need to pay me " + prixDemande + " coins.";
    }

    
    public void trade() {
        Player player = Player.getInstance();
        Scanner playerInput = new Scanner(System.in);
        String playerAnswer;
        System.out.println("Do you want to go to the next map? (Y/n)");
        playerAnswer = playerInput.nextLine();
        if (playerAnswer.equals("Y")) { 
            boolean paid = player.pay(prixDemande);
            if (paid) {
                System.out.println("The money has been transferred. Good luck on your quest!");
            } else {
                System.out.println("You don't have enough coins to go through the borders.");
            }
        }
        // playerInput.close();
    }
}

