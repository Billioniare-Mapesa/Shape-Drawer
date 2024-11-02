import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShapeDrawer extends JFrame {
    
    private JPanel panel;
    private String shape;
    private int width, height, diameter;
    private Color color;

    public ShapeDrawer() {
        setTitle("Shape Drawer");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (shape != null) { 
                    g.setColor(color);
                    switch (shape) {
                        case "Rectangle":
                            g.fillRect(50, 50, width, height);
                            break;
                        case "Triangle":
                            int[] xPoints = {50, 50 + width / 2, 50 + width};
                            int[] yPoints = {50 + height, 50, 50 + height};
                            g.fillPolygon(xPoints, yPoints, 3);
                            break;
                        case "Circle":
                            g.fillOval(50, 50, diameter, diameter);
                            break;
                    }
                }
            }
        };
        add(panel, BorderLayout.CENTER);

        String[] shapes = {"Rectangle", "Triangle", "Circle"};
        shape = (String) JOptionPane.showInputDialog(
                this,
                "Choose a shape to draw:",
                "Shape Selector",
                JOptionPane.PLAIN_MESSAGE,
                null,
                shapes,
                shapes[0]);

        if (shape != null) {
            switch (shape) {
                case "Rectangle":
                    width = getDimension("Width", 1, 400);
                    height = getDimension("Height", 1, 200);
                    break;
                case "Triangle":
                    width = getDimension("Base", 1, 150);
                    height = getDimension("Height", 1, 300);
                    break;
                case "Circle":
                    diameter = getDimension("Diameter", 1, 400);
                    break;
            }
            color = chooseColor();
            panel.repaint();
        }
    }

    private int getDimension(String name, int min, int max) {
        int value = 0;
        while (value < min || value > max) {
            String input = JOptionPane.showInputDialog(
                    this,
                    String.format("Enter %s (%d-%d):", name, min, max),
                    String.format("%s Input", name),
                    JOptionPane.PLAIN_MESSAGE);
            try {
                value = Integer.parseInt(input);
                if (value < min || value > max) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid number within the range.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter a number.");
            }
        }
        return value;
    }

    private Color chooseColor() {
        String[] colors = {"Blue", "Yellow", "Red", "Green", "Pink", "Violet", "Purple", "Orange"};
        String selectedColor = (String) JOptionPane.showInputDialog(
                this,
                "Choose a color:",
                "Color Selector",
                JOptionPane.PLAIN_MESSAGE,
                null,
                colors,
                colors[0]);

        if (selectedColor != null) {
            switch (selectedColor) {
                case "Blue":
                    return Color.BLUE;
                case "Yellow":
                    return Color.YELLOW;
                case "Red":
                    return Color.RED;
                case "Green":
                    return Color.GREEN;
                case "Pink":
                    return Color.PINK;
                case "Violet":
                    return new Color(238, 130, 238); // Custom violet color
                case "Purple":
                    return new Color(128, 0, 128); // Custom purple color
                case "Orange":
                    return Color.ORANGE;
            }
        }
        return Color.BLACK; // Default color if none selected
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ShapeDrawer().setVisible(true);
        });
    }
}
