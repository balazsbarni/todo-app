import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TodoApp {

    public static final String TODO_PATH = "assets/todolist.txt";

    public static void main(String[] args) {

        if (args.length == 0){
            clearScreen();
            System.out.println(usageInformation());
        } else if (args[0].equals("-l")) {
            clearScreen();
            System.out.println(getTodoList());
        } else if (args[0].equals("-a")) {
            clearScreen();
            System.out.println(checkTaskArgs(args));
        } else if (args[0].equals("-r")) {
            System.out.println(checkRemoveArgs(args));

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
        List<String> readLines = readFile(TODO_PATH);
        return checkTodo(readLines);
    }

    public static List<String> readFile(String todoPath) {
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

    public static String checkTodo(List<String> todoList) {
        String todoListed = "";
        if (todoList.size() == 0) {
            todoListed = "No todos for today! :)";
        } else {
            for (int i = 0; i < todoList.size(); i++) {
                todoListed += "\n" + (i + 1) + " - " + todoList.get(i);
            }
        }
        return todoListed;
    }

    public static String checkTaskArgs(String[] args) {
        String result = "";
        if (args.length  < 2) {
            result += "Unable to add: no task provided";
        } else {
            if (!addNewTask(args)) {
                result = "Unable to add: error happened";
            }
        }
        return result;
    }

    public static boolean addNewTask(String[] arg) {
        return writeFileNew(TODO_PATH, arg[1]);
    }

    public static boolean writeFileNew(String todoPath, String newTodo) {
        List<String> lines = readFile(TODO_PATH);
        lines.add(newTodo);
        return writeFileContent(todoPath, lines);
    }

    public static boolean writeFileContent(String todoPath, List<String> content) {
        try {
            Path filePath = Paths.get(todoPath);
            Files.write(filePath, content);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean removeTask(String[] args) {
        int taskNumber = getIntFromArg(args[1]);
        List<String> lines = readFile(TODO_PATH);
        lines.remove(taskNumber - 1);
        return writeFileContent(TODO_PATH, lines);
    }


    public static int getIntFromArg(String arg) {
        int num = 0;
        try {
            num = Integer.parseInt(arg);
            return num;
        }
        catch (NumberFormatException nfe) {
            return num;
        }
    }

    public static String checkRemoveArgs(String[] args) {
        String result = "";
        if (args.length  < 2) {
            result = "Unable to remove: no index provided";
        } else if (getIntFromArg(args[1]) < 1) {
            result = "Index not good";
        } else {
            if (!removeTask(args)) {
                result = "Unable to add: error happened";
            }
        }
        return result;
    }
}
