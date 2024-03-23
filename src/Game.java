public class Game {
    public static void main(String[] args) {
        //Player player1 = Player.getInstance();
        
        Witch samira = new Witch("Samira", Diet.CARNIVOR, 120, 10, 30);
        System.out.println(samira.speak());
        samira.trade();
        
        Knight jihad = new Knight("jihad", 5);
        System.out.println(jihad.speak());
        jihad.trade();
    }
}
