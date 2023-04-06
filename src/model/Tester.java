package model;

public class Tester {
    public static void main(String[] args) {
        Customer customer = new Customer("Anees", "Ahmad", "anees.ahmad1107@gmail.com");
        System.out.println(customer);

        IRoom room1 = new Room("101", 50.0, RoomType.SINGLE);
        IRoom room2 = new Room("201", 80.0, RoomType.DOUBLE);
        IRoom room3 = new FreeRoom("301", RoomType.DOUBLE);


    }
}
