package gui;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.io.Serial;
import java.text.NumberFormat;

public class Window extends JFrame {
    @Serial
    private static final long serialVersionUID = 1L;

    protected JPanel panel;
    protected final NumberFormatter formatter;

    public Window(String title, int width, int height) {
        super(title);
        this.setSize(width, height);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        this.add(panel);

        NumberFormat format = NumberFormat.getInstance();
        format.setGroupingUsed(false);
        formatter = new NumberFormatter(format);
        formatter.setAllowsInvalid(false);
    }
}