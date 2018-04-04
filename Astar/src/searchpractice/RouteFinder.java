package searchpractice;

import java.util.Random;

import robocode.control.*;
import robocode.control.events.*;

public class RouteFinder {

	public static void main(String[] args) {
		// Create the RobocodeEngine
		RobocodeEngine engine = new RobocodeEngine(new java.io.File("C:/Robocode"));
		// Run from C:/Robocode
		// Show the Robocode battle view
		engine.setVisible(true);
		// Create the battlefield
		int NumPixelRows = 832;
		int NumPixelCols = 640;
		BattlefieldSpecification battlefield = new BattlefieldSpecification(NumPixelRows, NumPixelCols);
		// 800x600
		// Setup battle parameters
		int numberOfRounds = 5;
		long inactivityTime = 10000000;
		double gunCoolingRate = 1.0;
		int sentryBorderSize = 50;
		boolean hideEnemyNames = false;
		/*
		 * Create obstacles and place them at random so that no pair of
		 * obstacles are at the same position
		 */
		int NumObstacles = Constants.NUMBER_OBSTACLES;
		RobotSpecification[] modelRobots = engine.getLocalRepository("sample.SittingDuck,fnl.FnlBot*");
		RobotSpecification[] existingRobots = new RobotSpecification[NumObstacles + 1];
		RobotSetup[] robotSetups = new RobotSetup[NumObstacles + 1];
		Random r= new Random();
		int[] x={1,2,5,8,3,6,4,5,7,1,3,4};
		int[] y={1,5,4,8,3,6,1,5,7,1,3,4};
		
		for (int NdxObstacle = 0; NdxObstacle < NumObstacles; NdxObstacle++) {

			//Double InitialObstacleRow = (double)(r.nextInt(Constants.NUMBER_ROWS + 1)) * Constants.SIZE_OF_TILES + 32;
			//Double InitialObstacleCol = (double)(r.nextInt(Constants.NUMBER_COLUMNS + 1)) * Constants.SIZE_OF_TILES + 32;
			Double InitialObstacleRow = (double) x[NdxObstacle] *64+32;
			Double InitialObstacleCol = (double) y[NdxObstacle]*64+32;
			existingRobots[NdxObstacle] = modelRobots[0];
			robotSetups[NdxObstacle] = new RobotSetup(InitialObstacleRow, InitialObstacleCol, 0.0);
			
		}
		
		/*
		 * Create the agent and place it in a random position without obstacle
		 */
		existingRobots[NumObstacles] = modelRobots[1];
		Double InitialAgentRow = null;
		Double InitialAgentCol = null;
		robotSetups[NumObstacles] = new RobotSetup(InitialAgentRow, InitialAgentCol, 0.0);
		/* Create and run the battle */
		BattleSpecification battleSpec = new BattleSpecification(battlefield, numberOfRounds, inactivityTime,
				gunCoolingRate, sentryBorderSize, hideEnemyNames, existingRobots, robotSetups);
		// Run our specified battle and let it run till it is over
		engine.runBattle(battleSpec, true); // waits till the battle finishes
		// Cleanup our RobocodeEngine
		engine.close();
		// Make sure that the Java VM is shut down properly
		System.exit(0);
	}
}