import java.util.ArrayList;
import java.util.Scanner;

abstract class Employee {
    private String name;
    private int id;

    public Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public abstract double calculateSalary();

    @Override
    public String toString() {
        return "Employee[name=" + name + ", id= " + id + ", salary=" + calculateSalary() + "]";
    }
}

class FullTimeEmployee extends Employee {
    private double monthlySalary;

    public FullTimeEmployee(String name, int id, double monthlySalary) {
        super(name, id);
        this.monthlySalary = monthlySalary;
    }

    @Override
    public double calculateSalary() {
        return monthlySalary;
    }
}

class PartTimeEmployee extends Employee {
    private int hoursWorked;
    private double hourlyRate;

    public PartTimeEmployee(String name, int id, int hoursWorked, double hourlyRate) {
        super(name, id);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calculateSalary() {
        return hourlyRate * hoursWorked;
    }
}

class PayrollSystem {
    private ArrayList<Employee> employeeList;

    public PayrollSystem() {
        employeeList = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employeeList.add(employee);
    }

    public void removeEmployee(int id) {
        Employee toRemove = null;
        for (Employee emp : employeeList) {
            if (emp.getId() == id) {
                toRemove = emp;
            }
        }
        if (toRemove != null) {
            employeeList.remove(toRemove);
            System.out.println("Employee removed.");
        } else {
            System.out.println("Employee not found.");
        }
    }

    public void displayEmployee() {
        if (employeeList.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }
        for (Employee emp : employeeList) {
            System.out.println(emp);
        }
    }

    public void searchEmployee(int id) {
        for (Employee emp : employeeList) {
            if (emp.getId() == id) {
                System.out.println("Employee Found: " + emp);
                return;
            }
        }
        System.out.println("Employee with ID " + id + " not found.");
    }
}

public class Main {
    public static void main(String[] args) {
        PayrollSystem payrollSystem = new PayrollSystem();
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n===== Payroll Menu =====");
            System.out.println("1. Add Full-Time Employee");
            System.out.println("2. Add Part-Time Employee");
            System.out.println("3. Display All Employees");
            System.out.println("4. Remove Employee");
            System.out.println("5. Search Employee");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            String choiceStr = sc.nextLine();
            int choice = Integer.parseInt(choiceStr);

            switch (choice) {
                case 1:
                    System.out.print("Name: ");
                    String ftName = sc.nextLine();
                    System.out.print("ID: ");
                    int ftId = Integer.parseInt(sc.nextLine());
                    System.out.print("Monthly Salary: ");
                    double ftSalary = Double.parseDouble(sc.nextLine());
                    payrollSystem.addEmployee(new FullTimeEmployee(ftName, ftId, ftSalary));
                    break;

                case 2:
                    System.out.print("Name: ");
                    String ptName = sc.nextLine();
                    System.out.print("ID: ");
                    int ptId = Integer.parseInt(sc.nextLine());
                    System.out.print("Hours Worked: ");
                    int hours = Integer.parseInt(sc.nextLine());
                    System.out.print("Hourly Rate: ");
                    double rate = Double.parseDouble(sc.nextLine());
                    payrollSystem.addEmployee(new PartTimeEmployee(ptName, ptId, hours, rate));
                    break;

                case 3:
                    System.out.println("\n Employee List:");
                    payrollSystem.displayEmployee();
                    break;

                case 4:
                    System.out.print("Enter ID to remove: ");
                    int removeId = Integer.parseInt(sc.nextLine());
                    payrollSystem.removeEmployee(removeId);
                    break;

                case 5:
                    System.out.print("Enter ID to search: ");
                    int searchId = Integer.parseInt(sc.nextLine());
                    payrollSystem.searchEmployee(searchId);
                    break;

                case 6:
                    System.out.println("Exiting program...");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }

        sc.close();
    }
}
