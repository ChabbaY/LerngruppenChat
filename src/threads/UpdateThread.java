package threads;

import database.Data;
import database.Database;
import gui.OverviewWindow;

import java.util.Arrays;

public class UpdateThread extends Thread {
    private final Database database;
    OverviewWindow overviewWindow;

    public UpdateThread(Database database, OverviewWindow overviewWindow) {
        this.database = database;
        this.overviewWindow = overviewWindow;
    }

    @Override
    public void run() {
        Data[] lastData = null;
        while (true) {
            Data[] data = database.getDetails();
            if (!Arrays.equals(data, lastData)) {
                overviewWindow = new OverviewWindow();
                overviewWindow.reload(data);
                lastData = data;
            }
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}