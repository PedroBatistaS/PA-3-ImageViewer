package persistance;

import ui.ImagePanel;
import control.Command;
import control.NextImageCommand;
import control.PreviousImageCommand;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import ui.ImageDisplay;


public class Application extends JFrame{
    private Map<String, Command> commands = new HashMap<>();
    private ImageDisplay imageDisplay;

    public static void main(String[] args) {
        new Application().setVisible(true);
    }
    
    
    private JPanel toolbar() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(previousButton());
        panel.add(nextButton());
        return panel;
    }   
    
    
    public Application() {
        deployUI();
        createCommands();
    }
    
    private void createCommands() {
        commands.put("Next", new NextImageCommand(imageDisplay));
        commands.put("Previous", new PreviousImageCommand(imageDisplay));
    }
    
    private ImagePanel imagePanel() {
        ImagePanel imagePanel = new ImagePanel(firstImage());
        this.imageDisplay = imagePanel;    
        return imagePanel;
    }

    private FileImageReader firstImage() {
        FileImageReader fileReader = new FileImageReader("/Users/Pedro/Desktop/iaresultado");
        return fileReader;
    }
    
    private void deployUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().add(components());
        this.setPreferredSize(new Dimension(500, 500));
        this.setLocationRelativeTo(null);
        pack();    
    }
    
    private JButton nextButton() {
        JButton nextButton = new JButton(">");
        nextButton.addActionListener(doCommand("Next"));
        return nextButton;
    }
    
    private JButton previousButton() {
        JButton previousButton = new JButton("<");
        previousButton.addActionListener(doCommand("Previous"));
        return previousButton;
    }
    
    
    
    private ActionListener doCommand(String command) {
        return (ActionEvent e) -> {
            commands.get(command).execute();            
        };
    }
    
    
    private JPanel components() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(toolbar(), BorderLayout.SOUTH);
        panel.add(imagePanel(), BorderLayout.CENTER);
        return panel;
    }
}
