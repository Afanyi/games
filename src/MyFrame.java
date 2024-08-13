import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyFrame extends JFrame {

    MyFrame() {
        super("My Custom JFrame");
        setSize(400, 300);

        // Set the background color of the content pane
        getContentPane().setBackground(new Color(25, 25, 100));

        // Create a custom panel that draws two rectangles and a ball
        CustomPanel panel = new CustomPanel();
        panel.setOpaque(false); // Make the panel transparent to keep the background color visible
        add(panel);

        // Add key listener to handle keyboard events
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                panel.moveRectangles(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });

        // Set the focus to the frame to ensure it receives key events
        setFocusable(true);
        requestFocusInWindow();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        // Create an instance of MyFrame, which displays the frame
        new MyFrame();
    }
}

// Custom panel class to handle drawing and moving the rectangles and the ball
class CustomPanel extends JPanel {
    // Position and size of the first rectangle
    private int x1 = 10;
    private int y1 = 10;
    private final int RECT_WIDTH = 10;
    private final int RECT_HEIGHT = 100;

    // Position of the second rectangle
    private int x2 = 380; // Positioned on the other side of the frame initially
    private int y2 = 10;

    // Position and size of the ball
    private int x3 = 200; // Initial x position of the ball
    private int y3 = 150; // Initial y position of the ball
    int dx = 10;
    int dy = 10;
    private final int BALL_DIAMETER = 15;

    CustomPanel() {
        // Timer to move the ball every second
        Timer timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveBall();
                repaint(); // Repaint the panel to update the ball's position
            }
        });
        timer.start();
    }

    // Method to move the ball and check boundaries
    private void moveBall() {
        x3 += dx;
        y3 += dy;
        if(y3 <= 0|| y3 + BALL_DIAMETER >= getHeight()){
            dy = -dy;
        }
        if(x3 <= RECT_WIDTH +x1 || x3>= x2-20){
            if((y3>=y1 && y3<=y1+RECT_HEIGHT) || (y3>=y2 && y3<=y2+RECT_HEIGHT))
                dx = -dx;
        }

        // Check if the ball has hit the edges of the panel
        if (x3 <= 0 || x3 + BALL_DIAMETER >= getWidth()) {

            // Show option dialog for quitting or replaying
            int choice = JOptionPane.showOptionDialog(this,
                    "You lose! Would you like to play again or quit?",
                    "Game Over",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    new String[]{"Replay", "Quit"},
                    "Replay");

            if (choice == JOptionPane.YES_OPTION) {
                // Replay: Reset ball position
                x3 = 200;
                y3 = 150;
            } else {
                // Quit the game
                System.exit(0);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the first rectangle (controlled by arrow keys)
        g.setColor(Color.RED);
        g.fillRect(x1, y1, RECT_WIDTH, RECT_HEIGHT);

        // Draw the second rectangle (controlled by WASD keys)
        g.setColor(Color.GREEN);
        g.fillRect(x2, y2, RECT_WIDTH, RECT_HEIGHT);

        // Draw the ball
        g.setColor(Color.BLUE);
        g.fillOval(x3, y3, BALL_DIAMETER, BALL_DIAMETER);
    }

    // Method to move the rectangles based on key input
    public void moveRectangles(KeyEvent e) {
        switch (e.getKeyCode()) {
            // Arrow keys control the first rectangle's vertical movement
            case KeyEvent.VK_UP:
                y1 = Math.max(y1 - 10, 0);
                break;
            case KeyEvent.VK_DOWN:
                y1 = Math.min(y1 + 10, getHeight() - RECT_HEIGHT);
                break;

            // WASD keys control the second rectangle's vertical movement
            case KeyEvent.VK_W:
                y2 = Math.max(y2 - 10, 0);
                break;
            case KeyEvent.VK_S:
                y2 = Math.min(y2 + 10, getHeight() - RECT_HEIGHT);
                break;
        }
        repaint(); // Repaint the panel to update the rectangles' positions
    }
}

