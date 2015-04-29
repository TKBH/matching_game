import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class Board {
	
	private static Board board = null;
	private static JFrame boardPage;
	private static Properties properties;
	
	private static Deck deck = null;
	private static ArrayList<Card> cards = new ArrayList<Card>();
	private static ArrayList<JButton> display = new ArrayList<JButton>();
	private static JButton pressedButton;
	private static int selected = -1;
	
	private static boolean move = false;
	
	private Board(){
		boardPage = new JFrame("matching_game");
		boardPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		boardPage.setBounds(0, 0, 800, 600);
		ButtonHandler handler = new ButtonHandler();
		JLayeredPane panel = new JLayeredPane();
		panel.setOpaque(true);
		//panel.setPreferredSize(new Dimension(800, 600));
		ImageIcon background = new ImageIcon(properties.getBackground());
		JLabel backgroundLabel = new JLabel();
		backgroundLabel.setIcon(background);
		backgroundLabel.setOpaque(true);
		panel.add(backgroundLabel);
		
		System.out.println("ALSO HERE");
		loadCards();
		for(int x = 0; x < 4; x++){
			for(int y = 0; y < 13; y++){
				String path = properties.getCoverPath();
				ImageIcon icon = new ImageIcon(path);
				JButton card = new JButton();
				card.setIcon(icon);
				int xcoord = 5 + y * 55;
				int ycoord = x * (95 + 5);
				card.setBounds(xcoord, ycoord, 55, 95);
				card.setOpaque(true);
				card.addActionListener(handler);
				panel.add(card);
				panel.moveToFront(card);
				display.add(card);
			}
		}
		
		boardPage.add(panel);
		boardPage.pack();
		boardPage.setVisible(true);
	}
	
	private class ButtonHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			
			JButton newbutton = new JButton();
			Class button = newbutton.getClass();
			Class selected = event.getSource().getClass();
			if(button == selected){
				move = true;
				pressedButton = (JButton) event.getSource();
			}
		}
	}
	
	private static final void loadCards(){
		if(deck == null){
			deck = new Deck();
		}
		int count = 0;
		while(count < 52){
			int[] deckNumbers = deck.getDeck();
			String[] suits = deck.getSuits();
			
			int numNumbers = deckNumbers.length;
			int numSuits = suits.length;
			
			int number1 = (int) (numNumbers * Math.random());
			int number2 = (int) (numSuits * Math.random());
			
			if(deck.hasBeenDealt(deckNumbers[number1], suits[number2])){
				deck.deal(deckNumbers[number1], suits[number2]);
				cards.add(new Card(deckNumbers[number1], suits[number2], properties));
			}
		}
	}
	
	public static final void doMove(){
		if(move){
			for(int x = 0; x < display.size(); x++){
				JButton selectedButton = display.get(x);
				if(pressedButton == selectedButton){
					Card card = cards.get(x);
					String path = card.getPath();
					ImageIcon icon = new ImageIcon(path);
					selectedButton.setIcon(icon);
					display.set(x, selectedButton);
					
					if(selected == -1){
						selected = x;
						display.get(x).setEnabled(false);
					}else{
						Card card1 = cards.get(x);
						Card card2 = cards.get(selected);
						
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						if(card1.getNumber() == card2.getNumber()){
							display.get(x).setEnabled(false);
						}else{
							display.get(selected).setEnabled(true);
							icon = new ImageIcon(properties.getCoverPath());
							display.get(selected).setIcon(icon);
						}
					}
				}
			}
			move = false;
		}
	}
	
	public static final boolean initialized(){
		if(board == null){
			return false;
		}
		return true;
	}
	public static final void init(Properties p){
		properties = p;
		if(board == null){
			System.out.println("GOT HERE");
			board = new Board();
		}
	}
	public static final void show(){
		boardPage.setVisible(true);
	}
	public static final void hide(){
		boardPage.setVisible(false);
	}
}
