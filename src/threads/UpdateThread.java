package threads;

import database.Data;
import database.Database;
import gui.OverviewWindow;

import java.util.Arrays;

public class UpdateThread extends Thread {
    private final Database database;

    public UpdateThread(Database database) {
        this.database = database;
    }

    @Override
    public void run() {
        Data[] lastData = null;
        OverviewWindow overviewWindow = null;
        while (true) {
            Data[] data = database.getDetails();
            if (!Arrays.equals(data, lastData)) {//bei Änderung min. eines Objekts
                if (overviewWindow != null) overviewWindow.dispose();
                overviewWindow = new OverviewWindow(data);
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