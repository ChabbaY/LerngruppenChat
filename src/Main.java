import database.Database;
import gui.InputWindow;
import gui.OverviewWindow;
import threads.UpdateThread;

public class Main {
    public static void main(String[] args) {
        Database database = new Database();
        InputWindow iw = new InputWindow(database);
        OverviewWindow ow = new OverviewWindow();

        UpdateThread updater = new UpdateThread(database, ow);
        updater.start();
    }
}