# Cinema Booking System – HashMap & HashSet

## Project Overview
This project is a simple Cinema Booking System implemented in Java using `HashMap` and `HashSet`.

The main goal is to understand how to use custom objects as keys in a `HashMap` and how to correctly override the `equals()` and `hashCode()` methods.

## Technologies Used
- Java
- HashMap
- HashSet
- Object-Oriented Programming (OOP)

## Project Structure
```text
Task 1/
│
├── Task_CinemaBooking.java
└── README.md
```

## System Requirements

The cinema has:
- 5 rows: A, B, C, D, E.
- 6 seats in each row.
- Each seat is represented by a `Seat` object containing:
  - `row`
  - `number`

The system uses:

- `HashMap<Seat, String>` to store seat bookings, where the key is the seat and the value is the customer's name.
- `HashSet<Seat>` to store broken seats.

## Implemented Features

### Step 1: Basic HashMap Operations

1. **put()** – Add four bookings:
   - A1 → Ali
   - A2 → Sara
   - B5 → Omar
   - C3 → Mona

2. **get()** – Retrieve the customer who booked seat C3.

3. **containsKey()** – Check whether seats A2 and E6 are booked.

4. **putIfAbsent()** – Check whether Youssef can book seat B5 without replacing the existing booking.

5. **remove() and put()** – Move Ali's booking from A1 to D2.

6. **HashSet Operations** – Mark E1 and E2 as broken and prevent Hana from booking E1.

7. **size()** – Display the total number of booked seats.

### Step 2: equals() and hashCode()

The `Seat` class overrides:

- **equals(Object o):** Compares two seats based on their row and seat number.
- **hashCode():** Generates a hash code based on the row and seat number.

These methods ensure that different `Seat` objects representing the same seat are treated as equal in `HashMap` and `HashSet`.

### Step 3: Immutability

The `row` and `number` fields are declared as `final` to prevent changing a seat's identity after it has been used as a key.

## Expected Output

```text
Who booked C3: Mona
Is A2 taken: true
Is E6 taken: false
Youssef -> B5: refused, taken by Omar
Hana -> E1: refused, seat is broken
Seats booked: 4
```

The seating chart displays:
- `X` = Booked seat
- `#` = Broken seat
- `.` = Available seat

## Questions and Answers

**Q1: Why do we need to override equals() and hashCode()?**

Because HashMap and HashSet use these methods to identify equal objects. `equals()` compares seat values, while `hashCode()` helps locate the correct bucket.

**Q2: What happens if we delete hashCode() but keep equals()?**

The default `hashCode()` from the Object class is used. Equal seats may have different hash codes, which can cause HashMap and HashSet lookups to fail.

**Q3: Why are row and number declared as final?**

To prevent modifying the seat identity after inserting it into a HashMap or HashSet. Changing these values may make the object difficult to find or remove.

**Q4: What happens if equals(Seat o) is used instead of equals(Object o)?**

It overloads the method instead of overriding it. Java collections use `equals(Object o)`, so the custom comparison will not work as intended.

**Bonus: What happens if we use a Java record instead of the Seat class?**

A record automatically generates `equals()`, `hashCode()`, and `toString()` methods. Therefore, it can be used as a key in HashMap without manually overriding these methods.

## How to Run

1. Open the project in VS Code.
2. Make sure Java is installed.
3. Open `Task_CinemaBooking.java`.
4. Run the Java file.

## Conclusion

This task demonstrates how HashMap and HashSet work with custom objects, why `equals()` and `hashCode()` are important, and how immutable objects help maintain correct collection behavior.