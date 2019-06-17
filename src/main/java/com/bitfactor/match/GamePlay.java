package com.bitfactor.match;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class GamePlay implements Score {
	private final String player1;
	private final String player2;
	private final Map<String, Points> gameScore = new HashMap<>();
	private final Map<String, Boolean> advantage = new HashMap<>();

	private String winner;

	public GamePlay(String p1, String p2) {
		this.player1 = p1;
		this.player2 = p2;
		this.gameScore.put(this.player1, Points.LOVE);
		this.gameScore.put(this.player2, Points.LOVE);
		this.advantage.put(this.player1, false);
		this.advantage.put(this.player2, false);

		log.info("==== {} vs {}", this.player1, this.player2);
	}

	public boolean isCompleted() {
		return this.winner != null;
	}

	public String getWinner() {
		return this.winner;
	}

	@Override
	public void point(String winningPlayer) {
		if (this.winner != null) {
			throw new InvalidGamePointException("This game already has a winner!");
		}

		if (!this.gameScore.containsKey(winningPlayer)) {
			throw new InvalidGamePointException("Player `" + winningPlayer + "` is not in the game yet");
		}

		String loserPlayer = this.getOtherPlayerName(winningPlayer);

		Points winnerPlayerPoints = this.gameScore.get(winningPlayer);
		Points loserPlayerPoints = this.gameScore.get(loserPlayer);

		if (winnerPlayerPoints.equals(Points.FORTY) && loserPlayerPoints.equals(Points.FORTY)) {
			if (this.advantage.get(winningPlayer)) {
				// win game
				this.gameWonBy(winningPlayer);
			} else if (this.advantage.get(loserPlayer)){
				this.advantage.put(loserPlayer, false);
			} else {
				this.advantage.put(winningPlayer, true);
			}
		} else if (winnerPlayerPoints.equals(Points.FORTY)) {
			this.gameWonBy(winningPlayer);
		} else {
			this.gameScore.put(winningPlayer, this.gameScore.get(winningPlayer).next());
		}

		log.info("\tpoint won by {} - {}", winningPlayer, this.buildGameScore());
	}

	private void gameWonBy(String winningPlayer) {
		winner = winningPlayer;
	}

	public String buildGameScore() {
		Points playert1Points = this.gameScore.get(player1);
		Points playert2Points = this.gameScore.get(player2);

		if (winner != null) {
			return "Game won by " + winner;
		}

		if (playert1Points.equals(Points.FORTY) && playert2Points.equals(Points.FORTY)) {
			if (this.advantage.get(player1)) {
				// win game
				return "Advantage player " + player1;
			} else if (this.advantage.get(player2)){
				return "Advantage player " + player2;
			} else {
				return "Deuce";
			}
		} else if (playert1Points.equals(playert2Points)) {
			return String.format("%s ALL", playert1Points);
		} else {
			return String.format("%s - %s", playert1Points, playert2Points);
		}
	}

	private String getOtherPlayerName(String crtPlayerName) {
		if (crtPlayerName.equals(this.player1)) {
			return this.player2;
		}

		return this.player1;
	}
}
