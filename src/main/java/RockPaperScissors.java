import java.util.HashMap;
import java.util.List;

public final class RockPaperScissors {
    public static HashMap<String, Integer> points = new HashMap<>() {{
        put("ROCK", 1);
        put("PAPER", 2);
        put("SCISSORS", 3);
    }};
    public static HashMap<String, String> elfHand = new HashMap<>() {{
        put("A", "ROCK");
        put("B", "PAPER");
        put("C", "SCISSORS");
    }};
    public static HashMap<String, String> myHand = new HashMap<>() {{
        put("X", "ROCK");
        put("Y", "PAPER");
        put("Z", "SCISSORS");
    }};
    public static HashMap<String, String> recommendedPlay = new HashMap<>() {{
        put("X", "LOSE");
        put("Y", "DRAW");
        put("Z", "WIN");
    }};

    public static int calculateScore(List<String> match) {
        if (myHand.get(match.get(1)).equals("ROCK")) {
            if (elfHand.get(match.get(0)).equals("ROCK")) {
                return 3 + points.get("ROCK");
            } else if (elfHand.get(match.get(0)).equals("PAPER")) {
                return points.get("ROCK");
            } else if (elfHand.get(match.get(0)).equals("SCISSORS")) {
                return 6 + points.get("ROCK");
            }
        } else if (myHand.get(match.get(1)).equals("PAPER")) {
            if (elfHand.get(match.get(0)).equals("ROCK")) {
                return 6 + points.get("PAPER");
            } else if (elfHand.get(match.get(0)).equals("PAPER")) {
                return 3 + points.get("PAPER");
            } else if (elfHand.get(match.get(0)).equals("SCISSORS")) {
                return points.get("PAPER");
            }
        } else if (myHand.get(match.get(1)).equals("SCISSORS")) {
            if (elfHand.get(match.get(0)).equals("ROCK")) {
                return points.get("SCISSORS");
            } else if (elfHand.get(match.get(0)).equals("PAPER")) {
                return 6 + points.get("SCISSORS");
            } else if (elfHand.get(match.get(0)).equals("SCISSORS")) {
                return 3 + points.get("SCISSORS");
            }
        }
        return 0;
    }

    public static int playAsRecommended(List<String> match) {
        if (recommendedPlay.get(match.get(1)).equals("LOSE")) {
            if (elfHand.get(match.get(0)).equals("ROCK")) {
                return points.get("SCISSORS");
            } else if (elfHand.get(match.get(0)).equals("PAPER")) {
                return points.get("ROCK");
            } else if (elfHand.get(match.get(0)).equals("SCISSORS")) {
                return points.get("PAPER");
            }
        }
        if (recommendedPlay.get(match.get(1)).equals("DRAW")) {
            if (elfHand.get(match.get(0)).equals("ROCK")) {
                return 3 + points.get("ROCK");
            } else if (elfHand.get(match.get(0)).equals("PAPER")) {
                return 3 + points.get("PAPER");
            } else if (elfHand.get(match.get(0)).equals("SCISSORS")) {
                return 3 + points.get("SCISSORS");
            }
        }
        if (recommendedPlay.get(match.get(1)).equals("WIN")) {
            if (elfHand.get(match.get(0)).equals("ROCK")) {
                return 6 + points.get("PAPER");
            } else if (elfHand.get(match.get(0)).equals("PAPER")) {
                return 6 + points.get("SCISSORS");
            } else if (elfHand.get(match.get(0)).equals("SCISSORS")) {
                return 6 + points.get("ROCK");
            }
        }
        return 0;
    }
}
