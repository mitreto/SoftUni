package robotService.models.procedures;

import robotService.models.procedures.interfaces.Procedure;
import robotService.models.robots.interfaces.Robot;

import java.util.Collection;
import java.util.LinkedHashSet;

import static robotService.common.ExceptionMessages.INSUFFICIENT_PROCEDURE_TIME;

public abstract class BaseProcedure implements Procedure {

    protected Collection<Robot> robots;

    protected BaseProcedure() {
        this.robots = new LinkedHashSet<>();
    }

    @Override
    public String history() {

        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName())
                .append(System.lineSeparator());

        for (Robot robot : robots) {
            sb.append(robot.toString());
            sb.append(System.lineSeparator());
        }

        return sb.toString().trim();
    }

    @Override
    public void doService(Robot robot, int procedureTime) {

        if (robot.getProcedureTime() < procedureTime) {
            throw new IllegalArgumentException(INSUFFICIENT_PROCEDURE_TIME);

        } else {
            robot.setProcedureTime(robot.getProcedureTime() - procedureTime);
        }

    }
}
