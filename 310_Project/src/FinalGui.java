import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class FinalGui extends JFrame implements KeyListener {
	public handleInput inputHandler = new handleInput();
	public determineOutput outputDeterminer = new determineOutput();
	public Personality p = new Personality();
	public questionAsker questionAsker = new questionAsker();
	public boolean genderchosen = false;
	public boolean turn = false;
	public boolean nameknown = false;
	public boolean profasker = false;
	public boolean profResponse = false;
	public boolean bye = false;
	public String username = "";
	public String chatbotname = "CHATBOTNAME";
	public String botoutput = "";
	public String data = "";
	public String qdata = "";
	public String qresponse[] = new String[2];
	public String question[] = new String[2];
	public String userinput = "";
	public boolean askName = false;
	public String result;
	String POS = "";
	String word = "";
	String nlpInput = "";
	String nameEntity = "";
	String userinput1 = "";
	POSandNER nlp = new POSandNER();

	JPanel panel = new JPanel();
	// Typing Area:
	private JTextField txtEnter = new JTextField();

	// Instruction Area
	private JTextArea instruction = new JTextArea();
	private JTextArea instruction2 = new JTextArea();

	// Chat Area:
	public JTextArea txtChat = new JTextArea();

	/*
	 * JScrollPane sp = new JScrollPane( txtChat,
	 * JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
	 * JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED ); // scrolling private JLabel
	 * background; private ImageIcon image;
	 */
	public FinalGui() {
		// Frame Attributes:

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1460, 1080);
		this.setVisible(true);
		this.setResizable(true);
		this.setLayout(null);
		this.setTitle("DateBot");

		// panel.add(sp);
		// instructions Attributes:
		instruction.setLocation(1400, 5);
		instruction.setSize(30, 900);
		instruction2.setLocation(15, 910);
		instruction2.setSize(134, 30);

		// txtEnter Attributes:
		txtEnter.setLocation(150, 910);
		txtEnter.setSize(1245, 30);

		// txtEnter Action Event:
		txtEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String userinput = txtEnter.getText();

				txtChat.append(" User: " + userinput + "\n");
				// Determine desired gender from user
				if (genderchosen == false) {
					userinput = txtEnter.getText();
					String gender = inputHandler.checkGender(userinput);
					p = new Personality(gender);
					chatbotname = p.getName();
					txtChat.append("You are now on a date with a " + gender + " named " + chatbotname
							+ ". (Press Enter To Continue)" + "\n");
					genderchosen = true;
				} else if (nameknown == false) {
					txtChat.append("\n" + chatbotname + ": Hi! What's your name?" + "\n");
					userinput = txtEnter.getText();
					nameknown = true;

				}

				else if (profasker == false) {
					int random = (int) (Math.random() * 2 + 1);
					if (random == 1)
						txtChat.append(chatbotname + ": It's nice to meet you, " + userinput
								+ ". What do you do for a living?" + "\n");
					else
						txtChat.append(chatbotname + ": That's a lovely name, " + userinput
								+ ". So, what do you do for a living?" + "\n");
					profasker = true;
				}

				else if (profResponse == false) {
					userinput = txtEnter.getText();
					txtChat.append(chatbotname + ": "
							+ outputDeterminer.occupation(inputHandler.checkOccupation(userinput)) + "\n");
					profResponse = true;
				}

				else if (genderchosen == true && nameknown == true) {
					data = inputHandler.parseInput(userinput);
					if ((data.equals("invalid") || (data.equals("nothing"))) && (!data.equals("bye"))) {
						nlpInput = inputHandler.processInput(userinput);

						// System.out.println(nlpInput);

						POS = nlp.returnPOS(nlpInput);
						nameEntity = nlp.returnNER(nlpInput);
						if (POS.equals("NN")) {
							word = nlp.returnWord(nlpInput);
							txtChat.append(chatbotname + ": " + "I love " + word + "." + "\n");
						} else if (nameEntity.equals("LOCATION")) {
							word = nlp.returnWord(nlpInput);
							txtChat.append(chatbotname + ": "  + word +  ". Indeed. Its is a nice place " + "\n");
						} else if (nameEntity.equals("PERSON")) {
							word = nlp.returnWord(nlpInput);
							txtChat.append(chatbotname + ": " + "I dont know " + word + ". lets talk about ourselves."+ "\n");
						} else if (POS.equals(".")) {
							word = nlp.returnNoun(nlpInput);
							if(word.equals("?"))
								txtChat.append("No never.");
							else
							txtChat.append(chatbotname + ": " + "What.....? what about " + word + "\n");
						} else if (POS.equals("NNP")) {
							word = nlp.returnNoun(nlpInput);
							txtChat.append(chatbotname + ": " + "Really, " + word + "\n");
						} else if (POS.equals("JJ")) {
							word = nlp.returnWord(nlpInput);
							txtChat.append(chatbotname + ": " + "You are " + word + " too" + "\n");
						} else if (POS.equals("VBD")) {
							word = nlp.returnVerbPast(nlpInput);
							txtChat.append(chatbotname + ": " + "You " + word + "? Good to know." + "\n");
						} else if (POS.equals("VB")) {
							word = nlp.returnVerb(nlpInput);
							txtChat.append(chatbotname + ": " + "You " + word + "." + "\n");
						}else if (POS.equals("UH")) {
							word = nlp.returnWord(nlpInput);
							txtChat.append(chatbotname + ": "  + word + "." + "\n");
						}else if (POS.equals("NN")) {
							word = nlp.returnNoun(nlpInput);
							txtChat.append(chatbotname + ": " + "You are " + word + "\n");
						}

						txtEnter.setText("");
						
						if (!data.equals("invalid") && userinput.endsWith("?")) {
							botoutput = outputDeterminer.respond(inputHandler.keywordConvert(qdata), p);
							txtChat.append(chatbotname + ":" + botoutput);
						}
						botoutput = questionAsker.afterAsk(qresponse, qdata);
					} else {
						botoutput = outputDeterminer.respond(data, p) + "\n";
					}
					txtChat.append(chatbotname + ": " + botoutput + "\n");
				}
				txtEnter.setText("");
			}
		});
		// txtChat and instruction Attributes:
		txtChat.setLocation(15, 5);
		txtChat.setSize(1380, 900);
		txtChat.setEditable(false);

		// Add Items To Frame:
		this.add(txtEnter);
		this.add(txtChat);
		this.add(instruction);
		this.add(instruction2);

		instruction.setText(
				"\n\n\n\n\n\n\n\n\n\n\n\n\n   H\n   I\n   T\n\n   E\n   N\n   T\n   E\n   R\n\n   T\n   O\n\n   S\n   E\n   N\n   D\n\n   M\n   E\n   S\n   S\n   A\n   G\n   E");
		instruction2.setText("Please Provide Input ->:");
		txtChat.setText("You are on a blind date. Would you like to date a man or woman?" + "\n");
	}

	public void botSay(String s) {
		txtChat.append("ChatBot: " + s + "\n");

	}

	public static void main(String[] args) {
		FinalGui bot = new FinalGui();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}