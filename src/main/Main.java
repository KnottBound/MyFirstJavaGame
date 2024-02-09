package main;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
		JFrame window = new JFrame(); // Creating Window
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Creating Close Operation
		window.setResizable(false);
		window.setTitle("First Java Game");
		
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		
		window.pack();
		
		window.setLocationRelativeTo(null); // Window center of screen
		window.setVisible(true);
		
		gamePanel.setupGame();
		gamePanel.startGameThread();

	}

}
