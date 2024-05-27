import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

enum RoomType {
    SINGLE, DOUBLE, SUITE
}

class Room {
    private int roomNumber;
    private RoomType roomType;
    private double price;
    private boolean available;

    public Room(int roomNumber, RoomType roomType, double price) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.price = price;
        this.available = true;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Room " + roomNumber + " [" + roomType + ", $" + price + ", Available: " + available + "]";
    }
}

class Reservation {
    private User user;
    private Room room;

    public Reservation(User user, Room room) {
        this.user = user;
        this.room = room;
    }

    public User getUser() {
        return user;
    }

    public Room getRoom() {
        return room;
    }

    @Override
    public String toString() {
        return "Reservation [User: " + user.getName() + ", Room: " + room.getRoomNumber() + "]";
    }
}

class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Payment {
    public static boolean processPayment(double amount) {
        // Simulate payment processing
        System.out.println("Processing payment of $" + amount);
        return true;
    }
}

class Hotel {
    private List<Room> rooms;
    private List<Reservation> reservations;

    public Hotel() {
        this.rooms = new ArrayList<>();
        this.reservations = new ArrayList<>();
        initializeRooms();
    }

    private void initializeRooms() {
        // Add rooms to the hotel (for simplicity, add some sample rooms)
        rooms.add(new Room(101, RoomType.SINGLE, 100.0));
        rooms.add(new Room(102, RoomType.DOUBLE, 150.0));
        rooms.add(new Room(103, RoomType.SUITE, 250.0));
    }

    public List<Room> searchRooms(RoomType roomType) {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if ((roomType == null || room.getRoomType() == roomType) && room.isAvailable()) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public boolean makeReservation(User user, Room room) {
        if (room.isAvailable()) {
            Reservation reservation = new Reservation(user, room);
            reservations.add(reservation);
            room.setAvailable(false);
            System.out.println("Reservation successful for Room " + room.getRoomNumber());
            return true;
        } else {
            System.out.println("Room is not available.");
            return false;
        }
    }

    public void viewReservations() {
        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
        } else {
            for (Reservation reservation : reservations) {
                System.out.println(reservation);
            }
        }
    }
}

public class HotelSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hotel hotel = new Hotel();
        boolean running = true;

        while (running) {
            System.out.println("Welcome to the Hotel Reservation System");
            System.out.println("1. Search for Rooms");
            System.out.println("2. Make a Reservation");
            System.out.println("3. View Reservations");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Room Types: 1. SINGLE, 2. DOUBLE, 3. SUITE");
                    System.out.print("Enter room type: ");
                    int roomTypeChoice = scanner.nextInt();
                    RoomType roomType = RoomType.values()[roomTypeChoice - 1];
                    List<Room> availableRooms = hotel.searchRooms(roomType);
                    if (availableRooms.isEmpty()) {
                        System.out.println("No available rooms of type " + roomType);
                    } else {
                        System.out.println("Available Rooms:");
                        for (Room room : availableRooms) {
                            System.out.println(room);
                        }
                    }
                    break;
                case 2:
                    System.out.print("Enter your name: ");
                    scanner.nextLine(); // consume newline
                    String userName = scanner.nextLine();
                    User user = new User(userName);
                    System.out.print("Enter room number to reserve: ");
                    int roomNumber = scanner.nextInt();
                    Room roomToReserve = null;
                    for (Room room : hotel.searchRooms(null)) {
                        if (room.getRoomNumber() == roomNumber) {
                            roomToReserve = room;
                            break;
                        }
                    }
                    if (roomToReserve != null && roomToReserve.isAvailable()) {
                        System.out.print("Enter payment amount: ");
                        double paymentAmount = scanner.nextDouble();
                        if (Payment.processPayment(paymentAmount)) {
                            hotel.makeReservation(user, roomToReserve);
                        }
                    } else {
                        System.out.println("Invalid room number or room not available.");
                    }
                    break;
                case 3:
                    hotel.viewReservations();
                    break;
                case 4:
                    running = false;
                    System.out.println("Exiting the application.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();
        }

        scanner.close();
    }
}
