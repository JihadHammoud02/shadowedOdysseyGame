public class Game {
    public static void main(String[] args) {
        Witch Samira = new Witch("Samira", Diet.CARNIVOR, 120, 10, 30);
        Player player1 = new Player("player1");
        System.out.println(Samira.speak());
        Samira.trade(player1);
    }
}
