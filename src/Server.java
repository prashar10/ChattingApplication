import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.event.*;
import java.net.*;
import javax.swing.*;
import java.io.*;
public class Server extends JFrame implements ActionListener{
	
	JPanel p1; //global variable to use outside the constructor and everywhere else
	JTextField t1;	//the place where the person types
	JButton b1;		//the button to be clicked
	static JTextArea a1;	//to show the message boxes when sent
	
	static ServerSocket skt;	// used for socket programming.. connecting two different appllications together here client and server
	static Socket s;
	static DataInputStream din;
	static DataOutputStream dout;
	//creating a constructor of Server class for the box layout/frame
	//all the coding of frame will be inside this constructor only
	Server() {
		
		
		p1 = new JPanel();
		p1.setLayout(null);
		p1.setBackground(new Color(7, 94, 84)); // adding color similar to whatsapp
		p1.setBounds(0,0,450,70);
		add(p1);				//to add element on the frame
		
		ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("img/3.png"));
		Image i5 = i4.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		ImageIcon i6 = new ImageIcon(i5);
		JLabel l2= new JLabel(i6);
		l2.setBounds(5,17,30,30); // (x,y, imgsize,imagesize)
		p1.add(l2); 	// to add the icon(3.jpg) over the panel
		
		//now creating an option when clicking the back button the fram closes
		l2.addMouseListener(new MouseAdapter() {	
			public void mouseClicked(MouseEvent me) {
				System.exit(0);
			}
			
		});
		
		
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("img/messi.jpg"));
		Image i2 = i1.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel l1= new JLabel(i1);
		l1.setBounds(40,5,60,60); // (x,y, imgsize,imagesize)
		p1.add(l1); 
		
		JLabel l3= new JLabel("Messi"); 	// to write something on frame
		l3.setFont(new Font("SAN_SERIF",Font.BOLD,23)); // 23 is size of the font
		l3.setForeground(Color.WHITE);
		l3.setBounds(100,-20,100,120);
		p1.add(l3);
		
		JLabel l4= new JLabel("Online"); 	// to write something on frame
		l4.setFont(new Font("SAN_SERIF",Font.PLAIN,15)); // 23 is size of the font
		l4.setForeground(Color.WHITE);
		l4.setBounds(105,-2,100,120);
		p1.add(l4);
		
		a1 = new JTextArea();
		a1.setBounds(5,75,440,570);
		a1.setBackground(Color.white);
		a1.setFont(new Font("SAN_SERIF", Font.PLAIN,16));
		a1.setEditable(false);	// by default you can type in text area and we dont want that so disablibg it
		a1.setLineWrap(true);		//line wrapping
		a1.setWrapStyleWord(true);	//text wrapping
		add(a1);
		
		t1 = new JTextField();
		t1.setBounds(5,655,310,40);
		t1.setFont(new Font("SEN_SERIF", Font.PLAIN,15));
		add(t1);
		
		b1 = new JButton("Send");
		b1.setBounds(320,655,120,40); //send button dimensions
		b1.setBackground(new Color(7,94,84));	//rgb values
		b1.setForeground(Color.WHITE);
		b1.setFont(new Font("SAN_SERIF", Font.PLAIN,16));
		b1.addActionListener(this); 	//action listener goes to action performer and then the task is performed
		add(b1);
		
		//getContentPane().setBackground(Color.YELLOW);
		setLayout(null);
		setSize(450,700); 	//creates the layout or the border of the chat box
		setLocation(400,200); //setting xaxis and yaxis location
		setUndecorated(true);	// helps in removing the minimize and close bar
		setVisible(true); 	//IMPORTANT!!!! for the chatbox to be visible (default value is false). should always be placed at last
		
	}
	
	public void actionPerformed(ActionEvent me) {
		try {
		String out = t1.getText();
		a1.setText(a1.getText()+"\n\t\t\t"+out);	//setText- to set value dynamically
		dout.writeUTF(out);	// to display the message sent from here to the client side
		t1.setText("");
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void main(String[] args) {
		new Server().setVisible(true);
		String msginput="";
		try{
			skt= new ServerSocket(6001);
			s = skt.accept();
			din = new DataInputStream(s.getInputStream());	//data received
			dout = new DataOutputStream(s.getOutputStream());	//data sending
			
			msginput = din.readUTF();
			a1.setText(a1.getText()+"\n"+msginput);
			
			//closing connection
			skt.close();
			s.close();
			
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
