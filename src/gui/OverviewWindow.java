package gui;

import database.Data;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.io.Serial;
import java.text.NumberFormat;
import java.util.Objects;

public class OverviewWindow extends JFrame {
    @Serial
    private static final long serialVersionUID = 1L;

    private final JPanel panel;

    public OverviewWindow() {
        super("Lerngruppe - Chat");
        this.setSize(1000, 800);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        this.add(panel);

        NumberFormat format = NumberFormat.getInstance();
        format.setGroupingUsed(false);
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setAllowsInvalid(false);

        this.setVisible(true);
    }

    public void reload(Data[] data) {
        panel.removeAll();
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