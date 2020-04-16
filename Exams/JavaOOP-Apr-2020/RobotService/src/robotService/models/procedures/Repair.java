package robotService.models.procedures;

import robotService.models.robots.interfaces.Robot;

import static robotService.common.ExceptionMessages.*;

public class Repair extends BaseProcedure {

    private static final int REMOVE_HAPPINESS = 5;

    @Override
    public void doService(Robot robot, int procedureTime) {

        super.doService(robot, procedureTime);

        robot.setHappiness(robot.getHappiness() - REMOVE_HAPPINESS);

        if (robot.isRepaired()) {
            throw new IllegalArgumentException(
                    String.format(ALREADY_REPAIRED, robot.getName()));

        } else {
            robot.setRepaired(true);
        }

        this.robots.add(robot);
    }
}
