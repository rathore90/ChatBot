import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * I learned socket programming from a Youtube channel "thenewboston". It is a open and free source for learning.
 * MLA Citation:
 * “Intermediate Java Tutorial - 38 -Awesome Instant Messaging Program with Streams and Sockets.” 31 May. 2012. YouTube.	Web. 8	Mar. 2019.
 */
public class Server extends JFrame {
	private JTextField userText;
	private JTextArea chatWindow;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private ServerSocket server;
	private Socket connection;
	// constructor
	public Server() {
		userText = new JTextField();
		userText.setEditable(false);
		userText.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						sendMessage(event.getActionCommand());
						userText.setText("");
					}
				}
		);
		add(userText, BorderLayout.NORTH);
		chatWindow = new JTextArea();
		add(new JScrollPane(chatWindow));
		setSize(300, 150); // Sets the window size
		setVisible(true);
	}
	/**
	 * MLA Citation:
	 * “Intermediate Java Tutorial - 40 - Setting Up the Server.” 31 May. 2012. YouTube.	Web. 9	Mar. 2019.
	 */
	//Setup and run server
	public void startRunning() {
		try {
			//Server is a computer that is used by many people.
			server = new ServerSocket(6789, 2); // 6789 is a dummy port for testing, this can be changed. 
			while (true) {
				try {
					// Trying to connect and have conversation
					waitForConnection();
					//Setstreams for inout and output.
					setupStreams();
					//this allows us to chat back and forth
					whileChatting();
				} catch (EOFException eofException) {
					showMessage("\n Server ended the connection! ");
				} finally {
					closeConnection(); 
				}
			}
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}
	/**
	 * MLA Citation:
	 * “Intermediate Java Tutorial - 41 - Waiting for a Connection.” 31 May. 2012. YouTube.	Web. 10	Mar. 2019.
	 */
	// waiting for connection, then displaying connection information
	private void waitForConnection() throws IOException {
		showMessage("Looking for connection... \n");
		//when the connection has been established between this machine to another machine, connection variable is created.
		connection = server.accept();
		showMessage("You are connected to " + connection.getInetAddress().getHostName());
	}
	/**
	 * MLA Citation:
	 * “Intermediate Java Tutorial - 42 -  Setting Up the Streams.” 31 May. 2012. YouTube.	Web. 11	Mar. 2019.
	 */	
	//A stream is a way to communicate with other computer.
	// get stream to send and receive data
	private void setupStreams() throws IOException {
		//providing ouput to other computer
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		input = new ObjectInputStream(connection.getInputStream());
		showMessage("\n Streams are now setup \n");
	}
	// during the chat conversation
	private void whileChatting() throws IOException {
		String message = " You are now connected! ";
		sendMessage(message);
		ableToType(true);
		do {
			try {
				message = (String) input.readObject();
				showMessage("\n" + message);
			} catch (ClassNotFoundException classNotFoundException) {
				showMessage("The user has sent an unknown object!");
			}
		} while (!message.equals("CLIENT - END"));
	}
	public void closeConnection() {
		showMessage("\n Closing Connections... \n");
		ableToType(false);
		try {
			output.close(); // Closes the output path to the client
			input.close(); // Closes the input path to the server, from the client.
			connection.close(); // Closes the connection between you can the client
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}
	// Send a mesage to the client
	private void sendMessage(String message) {
		try {
			output.writeObject("SERVER - " + message);
			output.flush();
			showMessage("\nSERVER -" + message);
		} catch (IOException ioException) {
			chatWindow.append("\n ERROR: CANNOT SEND MESSAGE, PLEASE RETRY");
		}
	}
	// update chatWindow
	private void showMessage(final String text) {
		SwingUtilities.invokeLater(
				new Runnable() {
					public void run() {
						chatWindow.append(text);
					}
				}
		);
	}
	private void ableToType(final boolean tof) {
		SwingUtilities.invokeLater(
				new Runnable() {
					public void run() {
						userText.setEditable(tof);
					}
				}
		);
	}
}