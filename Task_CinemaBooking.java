
import java.util.*;

class Task_CinemaBooking {

    // Seat Class
    static class Seat {

        final char row;
        final int number;

        Seat(char row, int number) {
            this.row = row;
            this.number = number;
        }

        // Display seat as A1, B5, etc.
        @Override
        public String toString() {
            return "" + row + number;
        }

        // STEP 3 - Override equals()
        @Override
        public boolean equals(Object o) {

            if (this == o) {
                return true;
            }

            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Seat seat = (Seat) o;

            return row == seat.row &&
                   number == seat.number;
        }

        // STEP 3 - Override hashCode()
        @Override
        public int hashCode() {
            return Objects.hash(row, number);
        }
    }

    public static void main(String[] args) {

        // Key = Seat, Value = Customer Name
        HashMap<Seat, String> bookings = new HashMap<>();
        HashSet<Seat> broken = new HashSet<>();

        // Part A: Book seats using put()

        bookings.put(new Seat('A', 1), "Ali");
        bookings.put(new Seat('A', 2), "Sara");
        bookings.put(new Seat('B', 5), "Omar");
        bookings.put(new Seat('C', 3), "Mona");

        System.out.println("Bookings: " + bookings);


        // Part B: Get customer name using get()

        System.out.println("Who booked C3: " +
                bookings.get(new Seat('C', 3)));


        // Part C: Check seats using containsKey()

        System.out.println("Is A2 taken: " +
                bookings.containsKey(new Seat('A', 2)));

        System.out.println("Is E6 taken: " +
                bookings.containsKey(new Seat('E', 6)));


        // Part D: Use putIfAbsent()

        String result = bookings.putIfAbsent(
                new Seat('B', 5), "Youssef");

        if (result == null) {
            System.out.println("Youssef -> B5: booked");
        } else {
            System.out.println("Youssef -> B5: refused, taken by " + result);
        }


        // Part E: Ali moves from A1 to D2

        bookings.remove(new Seat('A', 1));

        bookings.put(new Seat('D', 2), "Ali");

        System.out.println("After Ali moved: " + bookings);


        // Part F: Broken seats using HashSet

        broken.add(new Seat('E', 1));
        broken.add(new Seat('E', 2));

        if (broken.contains(new Seat('E', 1))) {

            System.out.println("Hana -> E1: refused, seat is broken");

        } else {

            bookings.put(new Seat('E', 1), "Hana");

            System.out.println("Hana -> E1: booked");
        }


        // Part G: Count booked seats

        System.out.println("Seats booked: " + bookings.size());


        // GIVEN - Do not change printChart()

        System.out.println();

        printChart(bookings, broken);
    }


    // GIVEN - Do not change this method

    static void printChart(HashMap<Seat, String> bookings,
                           HashSet<Seat> broken) {

        System.out.println("     1  2  3  4  5  6");

        for (char row = 'A'; row <= 'E'; row++) {

            System.out.print(row + "  ");

            for (int number = 1; number <= 6; number++) {

                Seat seat = new Seat(row, number);

                if (bookings.containsKey(seat)) {

                    System.out.print("  X");

                } else if (broken.contains(seat)) {

                    System.out.print("  #");

                } else {

                    System.out.print("  .");
                }
            }

            System.out.println();
        }
    }
}

/*
====================================================
STEP 2: RUN BEFORE equals() AND hashCode()
====================================================

Expected observations:

Bookings: 4 seats added successfully.
Who booked C3: null
Is A2 taken: false
Is E6 taken: false
Youssef -> B5: booked
After Ali moved: 6 bookings
Hana -> E1: booked
Seats booked: 7

The seating chart shows all seats as free (.).

Reason:
Java compares objects using their references by default.
New Seat objects are different objects, even if their
row and number are the same.

====================================================
STEP 3: AFTER equals() AND hashCode()
====================================================

The program produces the correct expected output.
HashMap and HashSet can recognize equal Seat objects.

====================================================
Q1. Why did we have to override hashCode() and equals()?
====================================================

Because Seat is a custom class used as a key in HashMap
and an element in HashSet.

equals() compares objects based on their data
(row and number).

hashCode() generates a hash value based on the same fields.
Equal objects must have the same hash code.

====================================================
Q2. Delete hashCode() but keep equals(). What happens?
====================================================

The program may give incorrect results because equal
objects can have different hash codes.

HashMap and HashSet use hashCode() to locate objects,
so they may fail to find equal objects.

====================================================
Q3. Why are row and number final?
====================================================

Because a seat's row and number should not change
after creating the object.

Changing these values after inserting a seat into a
HashMap may change its hash code, making the object
difficult to find or remove.

====================================================
Q4. Change equals(Object o) to equals(Seat o).
What happens? Then remove @Override.
====================================================

First:
Keeping @Override causes a compilation error because
equals(Seat o) does not override equals(Object o).

Second:
After removing @Override, the program compiles,
but equals(Seat o) becomes an overloaded method,
not an overridden method.

HashMap and HashSet still use equals(Object o),
so the default object comparison is used.

====================================================
BONUS
====================================================

Replace the Seat class with:

record Seat(char row, int number) {}

Java automatically generates:
- equals()
- hashCode()
- toString()
- Constructor
- Accessor methods

The program works correctly, but the printed Seat
format changes to:
Seat[row=A, number=1]

====================================================
END OF TASK
====================================================

*/