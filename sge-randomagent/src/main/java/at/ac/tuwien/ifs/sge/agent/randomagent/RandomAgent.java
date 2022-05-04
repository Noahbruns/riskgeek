package at.ac.tuwien.ifs.sge.agent.randomagent;

import at.ac.tuwien.ifs.sge.agent.GameAgent;
import at.ac.tuwien.ifs.sge.engine.Logger;
import at.ac.tuwien.ifs.sge.game.Game;
import at.ac.tuwien.ifs.sge.util.Util;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class RandomAgent<G extends Game<A, ?>, A> implements GameAgent<G, A> {

  private static int INSTANCE_NR_COUNTER = 1;

  private final Logger log;
  private final Random random;
  private final int instanceNr;

  public RandomAgent() {
    this(new Random(), null);
  }

  public RandomAgent(Logger log) {
    this(new Random(), log);
  }

  public RandomAgent(Random random, Logger log) {
    this.random = random;
    this.log = log;
    this.instanceNr = INSTANCE_NR_COUNTER++;
  }

  @Override
  public A computeNextAction(G game, long computationTime, TimeUnit timeUnit) {
    return Util.selectRandom(game.getPossibleActions(), random);
  }

  @Override
  public String toString() {
    if (instanceNr > 1 || RandomAgent.INSTANCE_NR_COUNTER > 2) {
      return String.format("%s%d", "RandomAgent#", instanceNr);
    }
    return "RandomAgent";
  }
}
