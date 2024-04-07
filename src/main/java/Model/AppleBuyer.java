package Model;

import java.util.Scanner;

public class AppleBuyer extends  Personnage {
    // Attributes
    private int maxQuantity;
    private int applePrice;
    private int currentQuantity;
    private int restQuantity;


    private String imageName;

    // Constructor
    public AppleBuyer(String name, int maxQuantity, int PricePerApple, String imageName) {
        super(name);
        this.maxQuantity = maxQuantity;
        this.applePrice = PricePerApple;
        this.imageName = imageName;
    }

    @Override
    public String toString() {
        return super.toString() + " and I am a AppleBuyer";
    }

    ;

    @Override
    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public int getApplePrice() {
        return applePrice;
    }

    public int getMaxQuantity() {
        return maxQuantity;
    }

    public String getName() {
        return super.getName();
    }

    public int MaxAppleQuantity() {
        return maxQuantity;
    }

    public int getCurrentAppleQuantity() {
        return currentQuantity;
    }

    public void setMaxQuantity(int maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public int getRestQuantity() {
        return maxQuantity - currentQuantity;
    }

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
        sb.append("│ Welcome to my store, \n");
        if (getRestQuantity() == 0) {
            sb.append("│ Sorry I can't serve you anymore \n");
        } else {
            sb.append("│ I can buy up to ").append(this.maxQuantity).append(" apples.\n");
            sb.append("│ My current quantity of apples is ").append(getCurrentAppleQuantity()).append(" \n");
            sb.append("│ I'm willing to buy apples at ").append(getApplePrice()).append("xp  per apple    \n");

        }
        // Draw bottom border of the box
        sb.append("└");
        for (int i = 0; i < 40; i++) {
            sb.append("─");
        }
        sb.append("┘");
        return sb.toString();
    }

    public boolean  trade() {
        Player player = Player.getInstance();
        Scanner playerInput = new Scanner(System.in);
        String playerAnswer;
        System.out.println("Do you want to Trade? (Y/n)");
        playerAnswer = playerInput.nextLine();
        if (playerAnswer.equals("Y")) {
            if (player.getNbrePommes() > 0 && this.getRestQuantity() != 0) {
                System.out.println("I see you have apples. I will buy therm  for " + getApplePrice() + " xp,(Y/n)");
                playerAnswer = playerInput.nextLine();
                if (playerAnswer.equals("Y")) {
                    System.out.println("I can buy " + getRestQuantity() + " apples .");
                    System.out.println("How much apples do you want to sell ?" + getApplePrice());
                    int nbr = -1;
                    try {
                        nbr = Integer.parseInt(playerInput.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.print("Invalid input. Please enter a valid integer: ");
                    }
                    if (nbr <= getRestQuantity() && nbr > -1) {
                        int ret = player.sellPommes(nbr,getApplePrice());
                        if (ret == 0) {
                            ret = buyApples(nbr);
                            if (ret == 0) {
                                System.out.println("Successfully bought");
                                return true;
                            } else {
                                System.out.println("Failed to complete the purchase.");
                            }
                        }
                    }
                }
            } else if(getRestQuantity() == 0) {
                System.out.println("Sorry I can't serve you anymore , I can't buy more apples");
            } else {
                System.out.println("Sorry I can't serve you anymore , you don't have any apple there");
            }
        } else if (playerAnswer.equals("n")) {
            System.out.println("Thank you for visiting our store. Please come again!");
        } else {
            System.out.println("Invalid input. Please enter 'Y' or 'n'.");
        }
        return false;
    }


    // Method to buy apples
    public int buyApples(int quantity) {
        if (quantity > 1) {
            if (currentQuantity + quantity <= maxQuantity) {
                this.currentQuantity += quantity;
                System.out.println(super.getName() + " bought " + quantity + " apples.");
                return 0;
            }
        }
        System.out.println( "invalid operation,");
        return -1;
    }

        // Method to display buyer information
        public void displayInfo() {
            System.out.println("Buyer name: " + super.getName());
            System.out.println("Max Quantity of apples to buy: " + this.maxQuantity);
            System.out.println("Current Quantity of apples : " + this.currentQuantity);
            System.out.println("Buying price per apple: " + this.applePrice);
        }



}
