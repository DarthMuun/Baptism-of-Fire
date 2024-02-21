package main;

import javax.swing.JFrame;

public class Main {
	
	JFrame myFrame;
	GamePanel gp;

	public static void main(String[] args) {

		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("BoF Demo");
		
		GamePanel gamePanel = new GamePanel(window);
		window.add(gamePanel);
		
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		gamePanel.setupGame();
		gamePanel.startGameThread();
		
	}

}