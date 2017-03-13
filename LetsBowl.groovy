import java.util.ArrayList
import java.util.List

class BowlingBall {
    public int id;

    BowlingBall(int id) {
        this.id = id;
    }
}

class Bowler {
    private BowlingBall bowlingBall;

    void setBowlingBall(b) {
        bowlingBall = b;
    }

    BowlingBall getBowlingBall() {
        return bowlingBall;
    }
}

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

// test
BowlingBall bowlingBall = new BowlingBall(1);

ProShop proShop = new ProShop();
Bowler bowler = new Bowler();

proShop.addBowlingBall(bowlingBall);
bowler.setBowlingBall(bowlingBall);

bowler.getBowlingBall().id = 2;

assert bowler.getBowlingBall().id == 2;
assert proShop.getAtBowlingBall(0).id == 1; //fail!
