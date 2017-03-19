import java.util.ArrayList
import java.util.List

interface IBowlingBall {
    String getBrand();
    String getSerialNumber();
}

abstract class BowlingBall implements IBowlingBall {
    private String brand, serialNumber;

    BowlingBall(String brand, String serialNumber) {
        this.brand = brand;
        this.serialNumber = serialNumber;
    }

    String getBrand() { return this.brand };
    String getSerialNumber() {return this.serialNumber};
}

interface IBowlerBowlingBall extends IBowlingBall {
    String getPetName();
}

interface IProShopBowlingBall extends IBowlingBall {}

class BowlingBallFactory {
    static private class BowlerBowlingBall extends BowlingBall implements IBowlerBowlingBall {
        private String petName;

        BowlerBowlingBall(String brand, String serialNumber) {
            super(brand, serialNumber);
        }

        private void setPetName(String petName) {
            this.petName = petName;
        }

        String getPetName() {
            return this.petName;
        }
    }

    static private class ProShopBowlingBall extends BowlingBall implements IProShopBowlingBall {
        ProShopBowlingBall(String brand, String serialNumber) {
            super(brand, serialNumber);
        }
    }

    void visitBowler(IBowlerBowlingBall bowlingBall) {
        bowlingBall = new BowlerBowlingBall('Acme Whizbang', '#3000-13333');
    }

    void visitProShop(List<IProShopBowlingBall> bowlingBalls) {
        bowlingBalls.add(new ProShopBowlingBall('Dynamo Destructor', '#22-233-XY'));
    }

    IBowlingBall createBowlingBallFor(Bowler b) {
        b.accept(this);
    }

    IBowlingBall createBowlingBallFor(ProShop p) {
        p.accept(this);
    }

}

class Bowler {
    private List<IBowlerBowlingBall> bowlingBalls= Arrays.asList(new IBowlerBowlingBall[1]);
    private IBowlingBall bowlingBall = bowlingBalls.get(0);

    Bowler() {}

    void setPetName(String petName) {
        if (petName ==~ /[A-Z][a-z\s]*/) {
            bowlingBall.setPetName(petName);
        } else {
            throw Exception("only letters, please")
        }
    }

    String getPetName() {
        return bowlingBall.getPetName();
    }

    void accept(BowlingBallFactory bbf) {
        bbf.visitBowler(bowlingBall);
    }
}

class ProShop {
    private List<IProShopBowlingBall> bowlingBalls = new ArrayList<IProShopBowlingBall>();

    ProShop() { }

    void addBowlingBall(IProShopBowlingBall b) {
        bowlingBalls.add(b);
    }

    IProShopBowlingBall getBowlingBallAt(index) {
        return bowlingBalls.getAt(index);
    }

    void accept(BowlingBallFactory bbf) {
        bbf.visitProShop(bowlingBalls);
    }
}

// test
ProShop proShop = new ProShop();
Bowler bowler = new Bowler();
BowlingBallFactory bbf = new BowlingBallFactory();

bbf.createBowlingBallFor(proShop);
bbf.createBowlingBallFor(bowler);

bowler.setPetName("Bunky");

print bowler.getPetName();
print proShop.getBowlingBallAt(0).getBrand();
