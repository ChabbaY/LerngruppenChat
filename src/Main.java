import database.Database;
import gui.InputWindow;
import threads.UpdateThread;

public class Main {
    public static void main(String[] args) {
        Database database = new Database();
        InputWindow iw = new InputWindow(database);

        UpdateThread updater = new UpdateThread(database);
        updater.start();
    }
}