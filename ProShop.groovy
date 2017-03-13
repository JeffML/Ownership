import java.util.ArrayList
import java.util.List;

class ProShop {
    private List<BowlingBall> bowlingBalls = new ArrayList<BowlingBall>();

    boolean addBowlingBall(BowlingBall b) {
        return this.bowlingBalls.add(b);
    }

    BowlingBall getAtBowlingBall(index) {
        try {
            return this.bowlingBalls[index];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }
}
