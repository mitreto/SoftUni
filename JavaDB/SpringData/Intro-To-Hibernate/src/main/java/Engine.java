import entities.Address;
import entities.Employee;
import entities.Project;
import entities.Town;

import javax.crypto.spec.PSource;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

public class Engine implements Runnable {

    private final EntityManager entityManager;
    private final BufferedReader reader;

    public Engine(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run() {

        // Ex 2 Remove Objects
        // executeUpdateEx1();
        // this.removeObjectsEx();

        // Ex 3 Contains Employee
//        try {
//            this.containsEmployeeEx();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        // Ex 4 Employees with Salary Over 50 000
        // this.getEmployeesWithSalaryOver50000();

        // Ex 5 Employees from Department
        // this.getEmployeesFromDepartment();

        // Ex 6 Adding a New Address and Updating Employee
//        try {
//            this.addNewAddressAndUpdateEmployeeEx6();
//        System.out.println("New entity created and new value set to employee");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        // Ex 7 Addresses with Employee Count
        // this.getAddressesWithEmployeeCountEx7();

        // Ex 8 Get Employee with Project
//        try {
//            this.getEmployeeWithProjectEx8();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        // EX 9 Find Latest 10 Projects
        // this.getLatest10ProjectsEx9();

        // Ex 10 Increase Salaries
        // this.increaseSalariesEx10();

        // Ex 11 Remove Towns


        // Ex 12 Find Employees by First Name
//        try {
//            this.findEmployeesByFirstNameEx12();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        // Ex 13 Employees Maximum Salaries
        this.getEmployeesMaximumSalariesByDepartmentEx13();

    }

    private void executeUpdateEx2() {
        this.entityManager.getTransaction().begin();

        this.entityManager.
                createQuery(
                        "UPDATE Town AS t SET t.name = LOWER(t.name) WHERE length(t.name) > 5").executeUpdate();

        this.entityManager.getTransaction().commit();
    }

    private void removeObjectsEx2() {
        List<Town> towns = this.entityManager
                .createQuery("SELECT t FROM Town AS t", Town.class)
                .getResultList();

        this.entityManager.getTransaction().begin();

        for (Town town : towns) {
            if (town.getName().length() > 5) {
                this.entityManager.detach(town);
            }
        }

        towns
                .forEach(town -> town.setName(town.getName().toLowerCase()));

//        this.entityManager.
//                createQuery(
//                        "UPDATE Town AS t SET t.name = UPPER(t.name) WHERE length(t.name) > 5",
//                        Town.class).executeUpdate();


//        towns
//                .forEach(this.entityManager::detach);

//        towns = this.entityManager
//                .createQuery("SELECT t FROM Town AS t " + "WHERE length(t.name) > 5", Town.class)
//                .getResultList();


//        for (Town town : towns) {
//            town.setName(town.getName().toUpperCase());
//        }

//        towns.forEach(this.entityManager::merge);

//        this.entityManager.flush();
        this.entityManager.getTransaction().commit();

    }

    private void containsEmployeeEx3() throws IOException {
        System.out.println("Enter employee full name: ");
        String fullName = this.reader.readLine();

        try {
            Employee employee = this.entityManager
                    .createQuery("SELECT e FROM Employee AS e " +
                            "WHERE concat(e.firstName, ' ', e.lastName) = :name", Employee.class)
                    .setParameter("name", fullName)
                    .getSingleResult();

            System.out.println("Yes");
        } catch (NoResultException nre) {
            System.out.println("No");
        }
    }

    private void getEmployeesWithSalaryOver50000Ex4() {

//        List<Employee> employees = this.entityManager
//                .createQuery("SELECT e FROM Employee AS e " +
//                        "WHERE e.salary > 50000", Employee.class)
//                .getResultList();
//
//        employees.forEach(e -> System.out.println(e.getFirstName()));

        this.entityManager
                .createQuery("SELECT e FROM Employee AS e " +
                        "WHERE e.salary > 50000", Employee.class)
                .getResultStream()
                .forEach(e -> System.out.println(e.getFirstName()));


    }

    private void getEmployeesFromDepartmentEx5() {

        this.entityManager
                .createQuery("SELECT e FROM Employee AS e " +
                        "WHERE e.department.name = 'Research and Development' " +
                        "ORDER BY e.salary, e.id", Employee.class)
                .getResultStream()
                .forEach(e -> System.out.printf("%s %s from %s - $%.2f%n",
                        e.getFirstName(),
                        e.getLastName(),
                        e.getDepartment().getName(),
                        e.getSalary()));

    }

    private void addNewAddressAndUpdateEmployeeEx6() throws IOException {

        System.out.println("Enter employee last name:");
        String lastName = this.reader.readLine();
        Employee employee = null;

        try {
            employee = this.entityManager.createQuery(
                    "SELECT e FROM Employee AS e WHERE e.lastName = :name", Employee.class)
                    .setParameter("name", lastName)
                    .setMaxResults(1)
                    .getSingleResult();

            Address address = this.createNewAddressPartOfEx6();

            this.entityManager.getTransaction().begin();

            this.entityManager.detach(employee);
            employee.setAddress(address);
            this.entityManager.merge(employee);

            this.entityManager.flush();
            this.entityManager.getTransaction().commit();

        } catch (NoResultException nre) {
            System.out.printf("No records found for %s in the database%n", lastName);
        }
    }

    private Address createNewAddressPartOfEx6() {
        Address address = new Address();
        address.setText("Vitoshka 15");

        this.entityManager.getTransaction().begin();
        this.entityManager.persist(address);
        this.entityManager.getTransaction().commit();

        return address;
    }

    private void getAddressesWithEmployeeCountEx7() {

        this.entityManager.createQuery(
                "SELECT a FROM Address AS a ORDER BY a.employees.size DESC", Address.class)
                .getResultStream()
                .limit(10)
                .forEach(a -> System.out.printf("%s, %s - %d employees%n",
                        a.getText(),
                        a.getTown().getName(),
                        a.getEmployees().size()));

    }

    private void getEmployeeWithProjectEx8() throws IOException {
        System.out.println("Enter employee id:");
        int idInput = Integer.parseInt(reader.readLine());

        Employee employee = this.entityManager.createQuery(
                "SELECT e FROM Employee AS e WHERE e.id = :id", Employee.class)
                .setParameter("id", idInput)
                .getSingleResult();

        System.out.printf("%s %s - %s%n",
                employee.getFirstName(),
                employee.getLastName(),
                employee.getJobTitle());

        employee
                .getProjects()
                .stream()
                .sorted(Comparator.comparing(Project::getName))
                .forEach(p -> System.out.printf("      %s%n", p.getName()));
    }

    private void getLatest10ProjectsEx9() {

        this.entityManager.createQuery(
                "SELECT p FROM Project AS p " +
                        "ORDER BY p.startDate DESC", Project.class)
                .getResultStream()
                .limit(10)
                .sorted(Comparator.comparing(Project::getName))
                .forEach(p ->
                        System.out.printf("Project name: %s%n" +
                                        "        Project description: %s%n" +
                                        "        Project start Date: %s%n" +
                                        "        Project End Date: %s%n",
                                p.getName(),
                                p.getDescription(),
                                p.getStartDate(),
                                p.getEndDate()));
    }

    private void increaseSalariesEx10() {

        this.entityManager.getTransaction().begin();

        this.entityManager.createQuery(
                "UPDATE Employee AS e SET e.salary = e.salary * 1.12 " +
                        "WHERE e.department.id IN (1, 2, 4, 11)")
                .executeUpdate();

        this.entityManager.getTransaction().commit();

        this.entityManager.createQuery("SELECT e FROM Employee AS e " +
                "WHERE e.department.name IN ('Engineering', 'Tool Design', 'Marketing', 'Information Services')", Employee.class)
                .getResultStream()
                .forEach(e -> System.out.printf("%s %s ($%.2f)%n",
                        e.getFirstName(),
                        e.getLastName(),
                        e.getSalary()));
    }

    private void removeTownAndAllAddressesEx11() {
    }

    private void findEmployeesByFirstNameEx12() throws IOException {
        System.out.println("Enter first letters of the employee name: ");
        String lineInput = reader.readLine();

        this.entityManager.createQuery(
                "SELECT e FROM Employee AS e WHERE e.firstName LIKE CONCAT(:name, '%')", Employee.class)
                .setParameter("name", lineInput)
                .getResultStream()
                .forEach(e -> System.out.printf("%s %s - %s - ($%.2f)%n",
                        e.getFirstName(),
                        e.getLastName(),
                        e.getJobTitle(),
                        e.getSalary()));
    }

    private void getEmployeesMaximumSalariesByDepartmentEx13() {

//        List<Employee> resultList = this.entityManager.createQuery(
//                "SELECT e " +
//                        "FROM Employee AS e " +
//                        "WHERE e.salary NOT BETWEEN 30000 AND 70000" +
//                        "GROUP BY e.department ORDER BY e.salary DESC", Employee.class)
//                .getResultList();

        this.entityManager.createQuery(
                "SELECT e FROM Employee AS e " +
                        "GROUP BY e.department.name " +
                        "HAVING e.salary NOT BETWEEN 30000 AND 70000 ORDER BY e.department.name, e.salary DESC",
                Employee.class)
                .getResultStream()
                .forEach(e -> System.out.printf("%s %.2f%n", e.getDepartment().getName(), e.getSalary()));


//        resultList.forEach(e -> System.out.printf("%s %.2f%n", e.getDepartment().getName(), e.getSalary()));

    }
}
