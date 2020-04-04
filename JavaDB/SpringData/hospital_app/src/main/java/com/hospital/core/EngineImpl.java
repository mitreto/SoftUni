package com.hospital.core;

import com.hospital.core.interfaces.Controller;
import com.hospital.core.interfaces.Engine;

import java.io.IOException;

public class EngineImpl implements Engine {

private Controller controller;


    public EngineImpl(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void run() {

        System.out.println("Enter your name: ");
        try {
            String name = controller.readLine();
            System.out.printf("Hello dr. %s%n", name);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Select an action by typing the number and press enter: ");
        System.out.println("1. Create Patient");
        System.out.println("2. Create Visitation");
        System.out.println("3. Create Diagnose");
        System.out.println("4. Prescribe  Medicament");
        try {
            String command = controller.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            controller.createPatient();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
