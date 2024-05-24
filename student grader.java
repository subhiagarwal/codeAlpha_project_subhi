import java.util.ArrayList;
import java.util.Scanner;

public class StudentGrader {
    private ArrayList<Double> grades;

    public StudentGrader() {
        grades = new ArrayList<>();
    }

    public void addGrade(double grade) {
        grades.add(grade);
    }

    public double computeAverage() {
        if (grades.isEmpty()) {
            return 0.0;
        }
        double sum = 0.0;
        for (double grade : grades) {
            sum += grade;
        }
        return sum / grades.size();
    }

    public double findHighest() {
        if (grades.isEmpty()) {
            return Double.NEGATIVE_INFINITY;
        }
        double highest = grades.get(0);
        for (double grade : grades) {
            if (grade > highest) {
                highest = grade;
            }
        }
        return highest;
    }

    public double findLowest() {
        if (grades.isEmpty()) {
            return Double.POSITIVE_INFINITY;
        }
        double lowest = grades.get(0);
        for (double grade : grades) {
            if (grade < lowest) {
                lowest = grade;
            }
        }
        return lowest;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentGrader tracker = new StudentGrader();
        while (true) {
            System.out.println("\nStudent Grade Tracker Menu:");
            System.out.println("1. Add a grade");
            System.out.println("2. Compute average grade");
            System.out.println("3. Find highest grade");
            System.out.println("4. Find lowest grade");
            System.out.println("5. Quit");

            System.out.print("Enter your choice (1-5): ");
            String choice = scanner.next();

            switch (choice) {
                case "1":
                    try {
                        System.out.print("Enter the grade: ");
                        double grade = scanner.nextDouble();
                        tracker.addGrade(grade);
                        System.out.println("Grade " + grade + " added.");
                    } catch (Exception e) {
                        System.out.println("Invalid grade. Please enter a numeric value.");
                        scanner.next();  // Clear the invalid input
                    }
                    break;
                case "2":
                    double average = tracker.computeAverage();
                    System.out.printf("Average grade: %.2f%n", average);
                    break;
                case "3":
                    double highest = tracker.findHighest();
                    System.out.printf("Highest grade: %.2f%n", highest);
                    break;
                case "4":
                    double lowest = tracker.findLowest();
                    System.out.printf("Lowest grade: %.2f%n", lowest);
                    break;
                case "5":
                    System.out.println("Exiting the program. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                    break;
            }
        }
    }
}
