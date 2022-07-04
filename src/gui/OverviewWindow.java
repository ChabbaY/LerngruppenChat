package gui;

import database.Data;

import javax.swing.*;
import java.util.Objects;

public class OverviewWindow extends Window {

    public OverviewWindow(Data[] data) {
        super("Lerngruppe - Chat", 200, 800);

        load(data);

        this.setVisible(true);
    }

    public void load (Data[] data) {
        String letzteFrage = null;

        for (Data dat : data) {
            if (!Objects.equals(dat.getFrage(), letzteFrage)) {//andere Frage -> neu anzeigen
                letzteFrage = dat.getFrage();

                panel.add(new JLabel("Frage " + dat.getFrageNr()));
                panel.add(new JLabel(dat.getFrage()));
            }

            panel.add(new JLabel(dat.getMitglied() + ": " + dat.getAntwort()));
        }
    }
}