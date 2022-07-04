import database.Database;
import gui.MenuWindow;

public class Main {
    public static void main(String[] args) {
        Database database = new Database();

        MenuWindow menu = new MenuWindow(database);
    }
}