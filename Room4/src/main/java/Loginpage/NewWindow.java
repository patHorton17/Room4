package Loginpage;
import java.awt.*;
import javax.swing.*;
import javax.swing.ImageIcon;
public class NewWindow {

 JFrame frame = new JFrame();
 JLabel label = new JLabel("Hello!");
 JLabel gifLabel = new JLabel();
 
 NewWindow(){
  
  label.setBounds(0,0,100,50);
  label.setFont(new Font(null,Font.PLAIN,25));
  
//Load the GIF image from file
  ImageIcon icon = new ImageIcon("/Images/delta-airplane.gif");
  gifLabel.setIcon(icon);
  gifLabel.setBounds(100, 50, icon.getIconWidth(), icon.getIconHeight());

  // Add both labels to the frame
  frame.add(gifLabel);
  frame.add(label);
  
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  frame.setSize(1000,700);
  frame.setLayout(null);
  frame.setVisible(true);
 }

public void setVisible(boolean b) {
	// TODO Auto-generated method stub
	
}
}