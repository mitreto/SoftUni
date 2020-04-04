import com.hospital.core.ControllerImpl;
import com.hospital.core.EngineImpl;
import com.hospital.core.interfaces.Controller;
import com.hospital.core.interfaces.Engine;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hospital_app");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Controller controller = new ControllerImpl(entityManager);

        Engine engine = new EngineImpl(controller);

        engine.run();
    }
}
