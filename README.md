Topic Name: Restaurant-Management-System

Assignment: CSE282 Java Group Project 

Course: CSE 282.6 - PROGRAMMING LANGUAGE II LAB (JAVA)

Group Members:
| Student ID | Name |
|------------|------|
| 2024100000052 | Monira Haque |
| 2024100000427 | Marzia Maherin |
| 2024100000436 | Aysha Hossain Nila |
| 2024100000077 | Sadia Sikder Tonni |
| 2024100000423 | Samiul Tanvir Rabbi |




Project Overview:

The Restaurant Management System is a Java desktop application that simulates the daily operations of a restaurant.It allows a user (cashier/waiter) to: Add food items (Main Course or Dessert) with a price and optional service charge. Build a customer order interactively. Submit the order, which is then handled by a waiter and acknowledged by a chef.Record the total sales in the restaurant’s account.The system demonstrates core Object‑Oriented Programming (OOP) concepts: Encapsulation, Inheritance, Polymorphism, and Abstraction. A Swing‑based GUI provides a user‑friendly interface for restaurant staff.


Project Objectives:

Provide an interactive graphical interface to create and manage customer orders.  Apply OOP principles to model real‑world entities: Restaurant, Order, Food item, Waiter, Chef. Show inheritance by having Main Course and Dessert extend the abstract class Fooditem.
Enforce encapsulation by keeping all data fields private and accessing them via public getters.	Simulate the workflow: item selection → order creation → waiter action → chef acknowledgment → sales recording.
Offer buttons to add items, submit order.


System Theory (OOP Concepts):

In most restaurant management systems, order handling and sales tracking are done manually or with disconnected tools. This leads to calculation errors, lost order records, and no clear accountability. The Restaurant Management System (RMS) overcomes these issues through:
.Object‑Oriented Architecture: clear class relationships (Restaurant, Order, Fooditem, Waiter, Chef).
.Structured Data Flow: from user input → validation → order creation → sales update.


| PRINCIPLE  | IMPLEMENTATION IN RESTAURANT SYSTEM     |
|--------|----------|
| ENCAPSULATION | All fields in Restaurant (name, location, totalSales), Order (orderId, total), Waiter (name), Chef (name, specialty), and Fooditem (itemName, itemPrice) are private. Public getters (getName(), getTotalSales(), etc.) and controlled methods (addOrder(), addItem()) provide access. |
| ABSTRACTION | Fooditem is an abstract class. It declares the abstract method totalPrice() without implementation. Concrete subclasses (MainCourse, Dessert) must provide the body. The user only knows that every food item can calculate its total price – the internal logic is hidden.|
| INHERITANCE | MainCourse and Dessert extend Fooditem. They automatically inherit the itemName and itemPrice fields, as well as the getters getItemName() and getItemPrice(). This reuses code and establishes an “is‑a” relationship. |
| POLYMORPHISM | Both MainCourse and Dessert override the totalPrice() method. The same method call (item.totalPrice()) works for either type. At runtime, Java calls the correct version based on the actual object – this is runtime polymorphism. |





Object Model:

| Class | Description |
|---------|-------------|
| RestaurantGUI | The main driver class. Contains the main() method and builds the Swing window. Handles all user interactions, event listeners, and orchestrates the other classes. |
| Fooditem (Abstract) | Abstract base class defining shared fields: itemName, itemPrice. Declares abstract method totalPrice() which must be implemented by subclasses. |
| MainCourse | Extends Fooditem. Represents a main course dish. Implements totalPrice() and returns the stored price. |
| Dessert | Extends Fooditem. Represents a dessert item. Implements totalPrice() and returns the stored price. |
| Order | Stores a unique orderId and a total bill amount. Provides addItem(double price) to accumulate totals and getters for order details. |
| Restaurant | Holds the restaurant's name, location, and cumulative totalSales. Method addOrder(double amount) updates sales. Displays information via displayInfo(). |
| Waiter | Stores a waiter's name. Method takeOrder(String foodName) prints a message to the console to simulate taking an order. |
| Chef | Stores a chef's name and specialty. Method cook() prints a cooking message. Displays information via displayInfo(). |
| Additional Test Classes | AbstractionTest, Nila, Marzia, and Drive are separate test files demonstrating individual class features and are not part of the main GUI application. |


Conclusion:

The Restaurant Management System successfully implements a working food ordering application using pure Java and Swing.
Full application of OOP principles (abstraction, encapsulation, inheritance, polymorphism). 
Clean separation between GUI and business logic. Real‑time order building with immediate price feedback. Simulated staff roles (waiter, chef) that act on orders. Persistent sales tracking within a restaurant session.
