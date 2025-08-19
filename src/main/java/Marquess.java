public class Marquess {
    public static void main(String[] args) {
        new Marquess().run();
    }

    public void run() {
        this.greet();
        this.exit();
    }

    public void greet() {
        System.out.println("Hello! I'm Marquess!");
        System.out.println("What can I do for you?");
    }

    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
