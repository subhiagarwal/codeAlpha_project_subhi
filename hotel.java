import java.util.ArrayList;
import java.util.Scanner;

class Destination {
    private String name;
    private String startDate;
    private String endDate;
    private String preferences;
    private double budget;

    public Destination(String name, String startDate, String endDate, String preferences, double budget) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.preferences = preferences;
        this.budget = budget;
    }

    public String getName() {
        return name;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getPreferences() {
        return preferences;
    }

    public double getBudget() {
        return budget;
    }

    @Override
    public String toString() {
        return "Destination: " + name +
                "\nStart Date: " + startDate +
                "\nEnd Date: " + endDate +
                "\nPreferences: " + preferences +
                "\nBudget: " + budget + "\n";
    }
}

class WeatherService {
    public static String getWeather(String destination) {
        // Mock implementation for weather information
        return "Sunny";
    }
}

class MapService {
    public static String getMap(String destination) {
        // Mock implementation for map information
        return "Map of " + destination;
    }
}

public class TravelPlanner {
    private ArrayList<Destination> itinerary;

    public TravelPlanner() {
        itinerary = new ArrayList<>();
    }

    public void addDestination(String name, String startDate, String endDate, String preferences, double budget) {
        Destination destination = new Destination(name, startDate, endDate, preferences, budget);
        itinerary.add(destination);
    }

    public void generateItinerary() {
        if (itinerary.isEmpty()) {
            System.out.println("No destinations in the itinerary.");
            return;
        }

        for (Destination destination : itinerary) {
            System.out.println(destination);
            System.out.println("Weather: " + WeatherService.getWeather(destination.getName()));
            System.out.println("Map: " + MapService.getMap(destination.getName()));
            System.out.println();
        }
    }

    public double calculateTotalBudget() {
        double totalBudget = 0.0;
        for (Destination destination : itinerary) {
            totalBudget += destination.getBudget();
        }
        return totalBudget;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TravelPlanner planner = new TravelPlanner();

        while (true) {
            System.out.println("\nTravel Planner Menu:");
            System.out.println("1. Add a destination");
            System.out.println("2. Generate itinerary");
            System.out.println("3. Calculate total budget");
            System.out.println("4. Quit");

            System.out.print("Enter your choice (1-4): ");
            String choice = scanner.next();

            switch (choice) {
                case "1":
                    System.out.print("Enter destination name: ");
                    String name = scanner.next();
                    System.out.print("Enter start date (YYYY-MM-DD): ");
                    String startDate = scanner.next();
                    System.out.print("Enter end date (YYYY-MM-DD): ");
                    String endDate = scanner.next();
                    System.out.print("Enter preferences: ");
                    String preferences = scanner.next();
                    System.out.print("Enter budget: ");
                    double budget = scanner.nextDouble();

                    planner.addDestination(name, startDate, endDate, preferences, budget);
                    System.out.println("Destination added.");
                    break;

                case "2":
                    planner.generateItinerary();
                    break;

                case "3":
                    double totalBudget = planner.calculateTotalBudget();
                    System.out.printf("Total budget: %.2f%n", totalBudget);
                    break;

                case "4":
                    System.out.println("Exiting the program. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                    break;
            }
        }
    }
}
