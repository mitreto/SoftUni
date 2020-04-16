package robotService.core;

import robotService.core.interfaces.Controller;
import robotService.models.garages.GarageImpl;
import robotService.models.garages.interfaces.Garage;
import robotService.models.procedures.Charge;
import robotService.models.procedures.Repair;
import robotService.models.procedures.Work;
import robotService.models.procedures.interfaces.Procedure;
import robotService.models.robots.Cleaner;
import robotService.models.robots.Housekeeper;
import robotService.models.robots.interfaces.Robot;

import java.util.Map;
import java.util.Objects;

import static robotService.common.ExceptionMessages.*;
import static robotService.common.OutputMessages.*;

public class ControllerImpl implements Controller {

    private Garage garage;
    private Procedure charge;
    private Procedure repair;
    private Procedure work;

    public ControllerImpl() {
        this.garage = new GarageImpl();
        this.charge = new Charge();
        this.repair = new Repair();
        this.work = new Work();
    }

    @Override
    public String manufacture(String robotType, String name, int energy, int happiness, int procedureTime) {

        if (robotType.equals("Cleaner")) {
            Robot robot = new Cleaner(
                    name, happiness, energy, procedureTime);

            this.garage.manufacture(robot);

        } else if (robotType.equals("Housekeeper")) {
            Robot robot = new Housekeeper(
                    name, happiness, energy, procedureTime);

            this.garage.manufacture(robot);

        } else {
            throw new IllegalArgumentException(String.format(INVALID_ROBOT_TYPE, robotType));
        }

        return String.format(ROBOT_MANUFACTURED, name);
    }

    @Override
    public String repair(String robotName, int procedureTime) {

        Robot robot = getRobot(robotName);

        if (robot == null) {
            throw new IllegalArgumentException(String.format(NON_EXISTING_ROBOT, robotName));
        }

        this.repair.doService(robot, procedureTime);

        return String.format(REPAIR_PROCEDURE, robotName);
    }

    @Override
    public String work(String robotName, int procedureTime) {

        Robot robot = getRobot(robotName);

        if (robot == null) {
            throw new IllegalArgumentException(String.format(NON_EXISTING_ROBOT, robotName));
        }

        this.work.doService(robot, procedureTime);

        return String.format(WORK_PROCEDURE, robotName, procedureTime);
    }

    @Override
    public String charge(String robotName, int procedureTime) {

        Robot robot = getRobot(robotName);

        if (robot == null) {
            throw new IllegalArgumentException(String.format(NON_EXISTING_ROBOT, robotName));
        }

        this.charge.doService(robot, procedureTime);

        return String.format(CHARGE_PROCEDURE, robotName);
    }

    @Override
    public String sell(String robotName, String ownerName) {

        Robot robot = getRobot(robotName);

        if (robot == null) {
            throw new IllegalArgumentException(String.format(NON_EXISTING_ROBOT, robotName));
        }

        this.garage.sell(robotName, ownerName);

        return String.format(SELL_ROBOT, ownerName, robotName);
    }

    private Robot getRobot(String robotName) {

        Robot robot = null;

        for (Map.Entry<String, Robot> robotEntry : this.garage.getRobots().entrySet()) {

            if (robotEntry.getKey().equals(robotName)){
                robot = robotEntry.getValue();
            }
        }

        return robot;
}

    @Override
    public String history(String procedureType) {

        String history = "";

        if (procedureType.equals("Repair")) {
            history = this.repair.history();

        } else if (procedureType.equals("Charge")) {
            history = this.charge.history();

        } else {
            this.work.history();
        }

        return history;
    }
}
