public class TodoApp {

    public static void main(String[] args) {

        System.out.println(args);

        if (args.length == 0){
            clearScreen();
            System.out.println(usageInformation());
            }
    }

    public static String usageInformation() {
        return "Command Line Todo application\n" +
                "=============================\n" +
                "\n" +
                "Command line arguments:\n" +
                " -l   Lists all the tasks\n" +
                " -a   Adds a new task\n" +
                " -r   Removes an task\n" +
                " -c   Completes an task";
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
