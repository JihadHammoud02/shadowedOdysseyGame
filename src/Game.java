public class Game {
    public static void main(String[] args) {
        Witch Samira = new Witch("Samira", Diet.CARNIVOR, 120, 10, 30);
        //Player player1 = Player.getInstance();
        System.out.println(Samira.speak());
        Samira.trade();
    }
}
