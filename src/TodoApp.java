import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TodoApp {

    public static void main(String[] args) {


        if (args.length == 0){
            clearScreen();
            System.out.println(usageInformation());
        } else if (args[0].equals("-l")) {
            clearScreen();
            System.out.println(getTodoList());
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

    public static String getTodoList() {
        String todoListed = "";
        List<String> readLines = readfile("assets/todolist.txt");
        for (int i = 0; i < readLines.size(); i++) {
            todoListed += readLines.get(i) + "\n";
        }
        return todoListed;
    }

    public static List<String> readfile(String todoPath) {
        List<String> lines = new ArrayList<>();
        try {
            Path filePath = Paths.get(todoPath);
            lines = Files.readAllLines(filePath);
            return lines;
        } catch (Exception e) {
            System.out.println("Uh-oh, could not read the file!");
            return lines;
        }
    }


}
