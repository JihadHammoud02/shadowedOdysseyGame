import java.util.Scanner; 
public class Witch extends Personnage{
    private Diet diet;
    private int maxXp;
    private int prixXp;
    private int chauveSourisXp;

    public Witch(String name,Diet diet,int maxXp,int prixXp,int chauveSourisXp){
        super(name);
        this.diet=diet;
        this.maxXp=maxXp;
        this.prixXp=prixXp;
        this.chauveSourisXp=chauveSourisXp;
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

    public String speak(){
        String text;
        text = "Welcome to my store, here is what I offer: \n10xp --> "+getPrixXp()+" coins \nI have "+getmaxXp()+"xp to give";
        if(diet == Diet.VEGAN){
            text+="\nI am Vegan so I don't accept bats like my fellow Witches";
        }else{
            text+="\nIf you have a bat I will consider it rather than the money";
        }
        return text;
        
    }


    public void trade(){
        Player player = Player.getInstance();
        Scanner playerInput = new Scanner(System.in);
        String playerAnswer;
        if(player.getnbreChauveSouris() > 0 && diet==Diet.CARNIVOR){
            System.out.println("I see you have a bat. I will take it for "+getchauveSourisXp()+"xp, I will not accept money (Y/n)");
            playerAnswer = playerInput.nextLine();
            if(playerAnswer.equals("Y")){
                player.echangerChauveSouris();
                this.maxXp -= chauveSourisXp;
                System.out.println("bat<---\n--->"+chauveSourisXp+"xp");
            }
        }
        else{
            System.out.println("Please choose how much xp you want me to give you");
            playerAnswer = playerInput.nextLine();
            int xp=0;

            try{
                xp = Integer.valueOf(playerAnswer);
            }
            catch(NumberFormatException e){
                System.out.print("Invalid input. Please enter a valid integer: ");
            }

            int bill = xp * prixXp;
            System.out.println("This will cost you "+bill+" coins (Y/n)");
            playerAnswer = playerInput.nextLine();
            if( playerAnswer.equals("Y")){
                if(player.pay(bill)){
                    maxXp-=xp;
                    System.out.println("Successfully bought");
                }
                else{
                    System.out.println("Sorry you don't have enough money");
                }
            }    
        }
        playerInput.close();
    }

}







