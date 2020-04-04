package com.hospital.core;

import com.hospital.core.interfaces.Controller;
import com.hospital.entities.Patient;

import javax.persistence.EntityManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

public class ControllerImpl implements Controller {

    private EntityManager entityManager;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public ControllerImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public String readLine() throws IOException {
        this.reader.readLine();
        return null;
    }

    @Override
    public void createPatient() throws IOException {

        Patient patient = new Patient();

        System.out.println("Enter patient first name: ");
        String firstName = reader.readLine();
        patient.setFirstName(firstName);

        System.out.println("Enter patient last name: ");
        String lastName = reader.readLine();
        patient.setLastName(lastName);

        System.out.println("Enter patient address: ");
        String address = reader.readLine();
        patient.setAddress(address);

        System.out.println("Enter patient email: ");
        String email = reader.readLine();
        patient.setEmail(email);

//        System.out.println("Enter patient date of birth: ");
//        String date = reader.readLine();
//        patient.setDateOfBirth(LocalDateTime.parse(date));

        entityManager.getTransaction().begin();
        entityManager.persist(patient);
        entityManager.getTransaction().commit();

        // picture -> optional so you can set it as NULL with if statement

    }

    @Override
    public void createVisitation() throws IOException {

//        Visitation visitation = new Visitation();
//
//        System.out.println("Enter date of visitation: ");
//        String date = reader.readLine();
//        visitation.setDate(LocalDateTime.parse(date));
//
//        System.out.println("Add comment: ex. primary visit, profligacy visit etc.");
//        String comment = reader.readLine();
//        visitation.setComment(comment);


    }

    @Override
    public void addDiagnose() {

    }

    @Override
    public void addMedicament() {

    }
}
