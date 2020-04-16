package robotService.models.procedures;

import robotService.models.robots.interfaces.Robot;

public class Work extends BaseProcedure {

    private static final int REMOVE_ENERGY = 6;
    private static final int ADD_HAPPINESS = 12;

    @Override
    public void doService(Robot robot, int procedureTime) {

        super.doService(robot, procedureTime);

        robot.setEnergy(robot.getEnergy() - REMOVE_ENERGY);

        robot.setHappiness(robot.getHappiness() + ADD_HAPPINESS);

        this.robots.add(robot);
    }
}
