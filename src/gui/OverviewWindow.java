package gui;

import database.Data;
import database.Database;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class OverviewWindow extends Window {
    private final String name;

    public OverviewWindow(Database database, Data[] data, String name) {
        super(database, 200, 800);
        this.name = name;

        //panel.add(new JScrollBar(JScrollBar.VERTICAL, 0, 0, 0, 1000));
        JScrollPane scrollPane = new JScrollPane(panel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.add(scrollPane);

        load(data);

        this.setVisible(true);
    }

    public void load (Data[] data) {
        String letzteFrage = null;

        //Nach frage sortiert: bei neuer Frage neuen Block
        for (Data dat : data) {
            if (!Objects.equals(dat.getFrage(), letzteFrage)) {//andere Frage -> neu anzeigen
                letzteFrage = dat.getFrage();

                String frage = dat.getFrage();
                int frage_nr = database.getFrageNr(name, frage);

                JLabel label = new JLabel("Frage " + (frage_nr >= 0 ? frage_nr : "(noch nicht eingegeben)"));
                label.setForeground(Color.red);
                panel.add(label);
                label = new JLabel(frage);
                label.setFont(label.getFont().deriveFont(label.getFont().getStyle() | ~Font.BOLD));
                panel.add(label);
            }

            JLabel label = new JLabel("<html>" + dat.getMitglied() + ": <b>" + dat.getAntwort() + "</b></html>");
            label.setFont(label.getFont().deriveFont(label.getFont().getStyle() | ~Font.BOLD));
            panel.add(label);
        }
    }
}