package com.hospital.core.interfaces;

import java.io.IOException;

public interface Controller {


    void createVisitation() throws IOException;

    void createPatient() throws IOException;

    void addDiagnose();

    void addMedicament();

    String readLine() throws IOException;
}
