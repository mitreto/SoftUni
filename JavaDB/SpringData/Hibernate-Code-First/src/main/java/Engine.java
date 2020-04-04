import entities.university_system.Course;
import entities.university_system.Person;
import entities.university_system.Student;
import entities.university_system.Teacher;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.HashSet;

public class Engine implements Runnable {

    private final EntityManager entityManager;

    public Engine(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public void run() {

        Teacher teacher = new Teacher();

        teacher.setFirstName("Ivan");
        teacher.setLastName("Petrov");
        teacher.setPhoneNumber("0837986388");
        teacher.setEmail("ssas@gmo.com");
        teacher.setSalary(new BigDecimal(3300));
        teacher.setCourses(new HashSet<>());

        Course course = new Course();
        course.setName("Java Web Development");

        entityManager.getTransaction().begin();
        entityManager.persist(course);
        entityManager.persist(teacher);
        entityManager.getTransaction().commit();
    }
}
