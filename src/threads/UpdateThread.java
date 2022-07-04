package threads;

import database.Data;
import database.Database;
import gui.OverviewWindow;

import java.util.Arrays;

public class UpdateThread extends Thread {
    private final Database database;
    private final String name;

    public UpdateThread(Database database, String name) {
        this.database = database;
        this.name = name;
    }

    @Override
    public void run() {
        Data[] lastData = null;
        OverviewWindow overviewWindow = null;
        while (true) {
            Data[] data = database.getDetails();
            if (!Arrays.equals(data, lastData)) {//bei Änderung min. eines Objekts
                if (overviewWindow != null) overviewWindow.dispose();
                overviewWindow = new OverviewWindow(database, data, name);
                lastData = data;
            }
            try {
                sleep(1000);//Überprüfung einmal pro Sekunde
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}