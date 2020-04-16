package robotService.models.procedures;

import robotService.models.robots.interfaces.Robot;

public class Charge extends BaseProcedure {

    private static final int ADD_HAPPINESS = 12;
    private static final int ADD_ENERGY = 10;

    @Override
    public void doService(Robot robot, int procedureTime) {

        super.doService(robot, procedureTime);

        robot.setHappiness(robot.getHappiness() + ADD_HAPPINESS);

        robot.setEnergy(robot.getEnergy() + ADD_ENERGY);

        this.robots.add(robot);

    }
}
