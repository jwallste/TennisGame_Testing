import static org.junit.Assert.*;

import org.junit.Test;

public class TennisGameTest {
	
// Here is the format of the scores: "player1Score - player2Score"
// "love - love"
// "15 - 15"
// "30 - 30"
// "deuce"
// "15 - love", "love - 15"
// "30 - love", "love - 30"
// "40 - love", "love - 40"
// "30 - 15", "15 - 30"
// "40 - 15", "15 - 40"
// "player1 has advantage"
// "player2 has advantage"
// "player1 wins"
// "player2 wins"
	@Test
	public void testTennisGame_Start() {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		String score = game.getScore() ;
		// Assert
		assertEquals("Initial score incorrect", "love - love", score);		
	}
	
	@Test
	public void testTennisGame_EahcPlayerWin4Points_Score_Deuce() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		
		game.player1Scored();
		game.player2Scored();
		//Act
		String score = game.getScore() ;
		// Assert
		assertEquals("Tie score incorrect", "deuce", score);		
	}
	
	@Test (expected = TennisGameException.class)
	public void testTennisGame_Player1WinsPointAfterGameEnded_ResultsException() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		//Act
		// This statement should cause an exception
		game.player1Scored();			
	}		
	
	@Test
	public void testTennisGame_Player1Wins() throws TennisGameException {
	    // Arrange
	    TennisGame game = new TennisGame();
	    // Act
	    game.player1Scored();
	    game.player1Scored();
	    game.player1Scored();
	    game.player1Scored();
	    String score = game.getScore();
	    // Assert
	    assertEquals("player1 wins", score);
	}
	@Test
	public void testTennisGame_Player2Wins() throws TennisGameException {
	    // Arrange
	    TennisGame game = new TennisGame();
	    // Act
	    game.player2Scored();
	    game.player2Scored();
	    game.player2Scored();
	    game.player2Scored();
	    String score = game.getScore();
	    // Assert
	    assertEquals("player2 wins", score);
	}

	@Test
	public void testPlayer1HasAdvantage() throws TennisGameException {
	    TennisGame game = new TennisGame();

	    // Both players reach deuce
	    game.player1Scored(); // 15 - love
	    game.player1Scored(); // 30 - love
	    game.player1Scored(); // 40 - love
	    game.player2Scored(); // 40 - 15
	    game.player2Scored(); // 40 - 30
	    game.player2Scored(); // deuce

	    // Player 1 scores to get an advantage
	    game.player1Scored();
	    assertEquals("player1 has advantage", game.getScore());
	}

	@Test
	public void testPlayer2HasAdvantage() throws TennisGameException {
	    TennisGame game = new TennisGame();

	    // Both players reach deuce
	    game.player1Scored(); // 15 - love
	    game.player1Scored(); // 30 - love
	    game.player1Scored(); // 40 - love
	    game.player2Scored(); // 40 - 15
	    game.player2Scored(); // 40 - 30
	    game.player2Scored(); // deuce

	    // Player 2 scores to get an advantage
	    game.player2Scored();
	    assertEquals("player2 has advantage", game.getScore());
	}

	
	@Test(expected = TennisGameException.class)
	public void testTennisGame_ScoreAfterGameEnded_ThrowsException() throws TennisGameException {
	    // Arrange
	    TennisGame game = new TennisGame();
	    game.player1Scored();
	    game.player1Scored();
	    game.player1Scored();
	    game.player1Scored(); 
	    // Act
	    game.player2Scored(); // This should throw an exception
	}
	
	@Test
	public void testTennisGame_GetScore() throws TennisGameException {
	    // Arrange
	    TennisGame game = new TennisGame();

	    // Act
	    // Initial score
	    String initialScore = game.getScore();

	    // Player 1 scores once
	    game.player1Scored();
	    String scoreAfterPlayer1 = game.getScore();

	    // Player 2 scores twice
	    game.player2Scored();
	    game.player2Scored();
	    String scoreAfterPlayer2 = game.getScore();

	    // Player 1 scores twice more
	    game.player1Scored();
	    game.player1Scored();
	    String finalScore = game.getScore();

	    // Assert
	    assertEquals("love - love", initialScore);
	    assertEquals("15 - love", scoreAfterPlayer1);
	    assertEquals("15 - 30", scoreAfterPlayer2);
	    assertEquals("40 - 30", finalScore);
	}

}
