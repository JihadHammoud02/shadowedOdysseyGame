public class Game {
    public static void main(String[] args) {
        Witch samira = new Witch("Samira", Diet.CARNIVOR, 120, 10, 30);
        //Player player1 = Player.getInstance();
        System.out.println(samira.speak());
        samira.trade();
        
        /*Knight jihad = new Knight("jihad", 5);
        System.out.println(jihad.speak());
        jihad.trade();*/
    }
}
