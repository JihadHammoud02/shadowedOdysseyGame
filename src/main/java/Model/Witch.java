package Model;



import java.util.Scanner;
public class Witch extends Personnage {
    private Diet diet;
    private int maxXp;
    private int prixXp;
    private int nbreChauveSouris;
    private int chauveSourisXp;
    private String imageName;

    public Witch(String name, Diet diet, int maxXp, int prixXp, int chauveSourisXp, String imageName){
        super(name);
        this.diet=diet;
        this.maxXp=maxXp;
        this.prixXp=prixXp;
        this.chauveSourisXp=chauveSourisXp;
        this.imageName=imageName;

    }

    @Override
    public String toString(){
        return super.toString() + " and I am a witch";
    }

    public Diet getDiet(){
        return diet;
    }

    public int getmaxXp(){
        return maxXp;
    }

    public int getPrixXp(){
        return prixXp;
    }

    public int getchauveSourisXp(){
        return chauveSourisXp;
    }

    public int getNbreChauveSouris(){
        return nbreChauveSouris;
    }

    @Override
    public String getImageName() { return imageName; }

    @Override
    public String speak() {
        StringBuilder sb = new StringBuilder();

        // Draw top border of the box
        sb.append("┌");
        for (int i = 0; i < 40; i++) {
            sb.append("─");
        }
        sb.append("┐\n");

        // Add text content
        sb.append("│ Welcome to my store, here is what I offer: │\n");
        sb.append("│ 1xp --> ").append(getPrixXp()).append(" coins               │\n");
        sb.append("│ I have ").append(getmaxXp()).append("xp to give                        │\n");

        // Add diet information
        if (diet == Diet.VEGAN) {
            sb.append("│ I am Vegan so I don't accept bats    │\n");
        } else {
            sb.append("│ If you have a bat I will consider it │\n");
        }

        // Draw bottom border of the box
        sb.append("└");
        for (int i = 0; i < 40; i++) {
            sb.append("─");
        }
        sb.append("┘");

        return sb.toString();
    }


    @Override
    public boolean trade() {
        Player player = Player.getInstance();
        Scanner playerInput = new Scanner(System.in);
        String playerAnswer;
        System.out.println("Do you want to Trade? (Y/n)");
        playerAnswer = playerInput.nextLine();
        if (playerAnswer.equals("Y")) {
            if (player.getnbreChauveSouris() > 0 && diet.equals(Diet.CARNIVOR)) {
                System.out.println("I see you have a bat. I will take it for " + getchauveSourisXp() + "xp, I will not accept money (Y/n)");
                playerAnswer = playerInput.nextLine();
                if (playerAnswer.equals("Y")) {
                    player.echangerChauveSouris();
                    this.maxXp -= chauveSourisXp;
                    nbreChauveSouris++;
                    player.incrWifeLife(chauveSourisXp);
                    System.out.println("bat<---\n--->" + chauveSourisXp + "xp");
                    return true;
                }
            } else {
                System.out.println("Please choose how much xp you want me to give you");
                playerAnswer = playerInput.nextLine();
                int xp = 0;

                try {
                    xp = Integer.valueOf(playerAnswer);
                } catch (NumberFormatException e) {
                    System.out.print("Invalid input. Please enter a valid integer: ");
                }

                int bill = xp * prixXp;
                System.out.println("This will cost you " + bill + " coins (Y/n)");
                playerAnswer = playerInput.nextLine();
                if (playerAnswer.equals("Y")) {
                    if (player.pay(bill)) {
                        maxXp -= xp;
                        player.incrWifeLife(xp);
                        System.out.println("Successfully bought");
                        return true;
                    } else {
                        System.out.println("Sorry you don't have enough money");
                    }
                }
                
            }
            
        }
        
        return false;
    }

}







