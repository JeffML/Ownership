BowlingBall bowlingBall = new BowlingBall(1);

ProShop proShop = new ProShop();
Bowler bowler = new Bowler();

proShop.addBowlingBall(bowlingBall);
bowler.setBowlingBall(bowlingBall);

bowler.getBowlingBall().id = 2;

assert bowler.getBowlingBall().id == 2;
assert proShop.getAtBowlingBall(0).id == 1; //fail!
