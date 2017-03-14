import java.util.ArrayList
import java.util.List


interface IBowlingBall {
    void setId(int id);
    int getId();
}

interface IProShopBowlingBall extends IBowlingBall {};   // marker interface
interface IBowlerBowlingBall extends IBowlingBall {};    // ditto

class BowlingBallFactory {

    private class BowlingBall implements IBowlingBall{
        private int id;

        BowlingBall() {
            this.id = 0;
        }

        void setId(int newId) {
            this.id = newId;
        }

        int getId() {return this.id}
    }

    class BowlerBowlingBall extends BowlingBall implements IBowlerBowlingBall {
    }

    class ProShopBowlingBall extends BowlingBall implements IProShopBowlingBall {
    }

    IBowlerBowlingBall createBowlingBall(Bowler b) {
        return new BowlerBowlingBall();
    }

    IProShopBowlingBall createBowlingBall(ProShop p) {
        return new ProShopBowlingBall();
    }

}

class Bowler {
    private IBowlerBowlingBall bowlingBall;

    void setBowlingBall(IBowlerBowlingBall b) {
        bowlingBall = b;
    }

    IBowlerBowlingBall getBowlingBall() {
        return bowlingBall;
    }
}

class ProShop {
    private List<IProShopBowlingBall> bowlingBalls = new ArrayList<>();

    boolean addBowlingBall(IProShopBowlingBall b) {
        return this.bowlingBalls.add(b);
    }

    IProShopBowlingBall getAtBowlingBall(index) {
        try {
            return this.bowlingBalls[index];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }
}

// test
ProShop proShop = new ProShop();
Bowler bowler = new Bowler();
BowlingBallFactory bbf = new BowlingBallFactory();

IProShopBowlingBall proShopBall = bbf.createBowlingBall(proShop);
proShop.addBowlingBall(proShopBall);
bowler.setBowlingBall(bbf.createBowlingBall(bowler));

proShop.getAtBowlingBall(0).setId(1);
bowler.getBowlingBall().setId(2);

assert bowler.getBowlingBall().getId() == 2;
assert proShop.getAtBowlingBall(0).getId() == 1;

try {
    bowler.setBowlingBall(proShopBall);             //fails
} catch (groovy.lang.MissingMethodException e) {
    System.err << "Can't assign proShopBowlingBall to Bowler"
}
