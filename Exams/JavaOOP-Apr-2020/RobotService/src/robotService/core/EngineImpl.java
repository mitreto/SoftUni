package robotService.core;

import robotService.common.Command;
import robotService.common.OutputMessages;
import robotService.core.interfaces.Controller;
import robotService.core.interfaces.Engine;
import robotService.io.ConsoleReader;
import robotService.io.ConsoleWriter;
import robotService.io.interfaces.InputReader;
import robotService.io.interfaces.OutputWriter;

import java.io.IOException;
import java.util.Arrays;

import static robotService.common.OutputMessages.*;

public class EngineImpl implements Engine {
    private InputReader reader;
    private OutputWriter writer;
    private Controller controller;

    public EngineImpl() {
        this.controller = new ControllerImpl();
        this.reader = new ConsoleReader();
        this.writer = new ConsoleWriter();
    }

    @Override
    public void run() {
        while (true) {
            String result = null;
            try {
                result = processInput();

                if ("Exit".equals(result)) {
                    break;
                }

            } catch (IOException | IllegalArgumentException | NullPointerException e) {
                result = e.getMessage();
            }

            this.writer.writeLine(result);
        }
    }

    private String processInput() throws IOException {
        String input = this.reader.readLine();
        String[] tokens = input.split("\\s");

        Command command = Command.valueOf(tokens[0]);
        String[] data = Arrays.stream(tokens).skip(1).toArray(String[]::new);

        String result = null;

        switch (command) {
            case Manufacture:
                result = manufacture(data);
                break;
            case Repair:
                result = repair(data);
                break;
            case Work:
                result = work(data);
                break;
            case Charge:
                result = charge(data);
                break;
            case Sell:
                result = sell(data);
                break;
            case History:
                result = history(data);
                break;
            case Exit:
                result = "Exit";
                break;
        }

        return result;
    }

    private String manufacture(String[] data) {

        this.controller.manufacture(
                data[0], data[1],
                Integer.parseInt(data[2]),
                Integer.parseInt(data[3]),
                Integer.parseInt(data[4]));

        return String.format(ROBOT_MANUFACTURED, data[1]);
    }

    private String repair(String[] data) {

        this.controller.repair(data[0], Integer.parseInt(data[1]));

        return String.format(REPAIR_PROCEDURE, data[0]);
    }

    private String work(String[] data) {

        this.controller.work(data[0], Integer.parseInt(data[1]));

        return String.format(WORK_PROCEDURE,
                data[0], Integer.parseInt(data[1]));
    }

    private String charge(String[] data) {

        this.controller.charge(data[0], Integer.parseInt(data[1]));

        return String.format(CHARGE_PROCEDURE, data[0]);
    }

    private String sell(String[] data) {

        this.controller.sell(data[0], data[1]);

        return String.format(SELL_ROBOT, data[1], data[0]);
    }

    private String history(String[] data) {

        return this.controller.history(data[0]);
    }
}
