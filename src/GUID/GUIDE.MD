# 🍽 Restaurant Management System — Step-by-Step Guide
## BSc CSE Student | Java + IntelliJ IDEA

---

## 📦 FILES YOU WILL CREATE (in order)

| Step | File | What it represents |
|------|------|--------------------|
| 3 | Fooditem.java | Abstract class (ABSTRACTION) |
| 4 | Dessert.java | Inherits Fooditem (INHERITANCE + POLYMORPHISM) |
| 5 | MainCourse.java | Inherits Fooditem (INHERITANCE + POLYMORPHISM) |
| 6 | Order.java | Stores order total (ENCAPSULATION) |
| 7 | Waiter.java | Takes orders (ENCAPSULATION) |
| 8 | Chef.java | Prepares food (ENCAPSULATION) |
| 9 | Restaurant.java | Manages sales (ENCAPSULATION) |
| 10 | RestaurantGUI.java | Main GUI file — RUN THIS |

---

## 🖥 STEP 1 — Open IntelliJ IDEA

1. Open **IntelliJ IDEA**
2. Click **"New Project"**
3. Select **"Java"** (not Maven or Gradle — just plain Java)
4. Make sure **JDK is selected** (JDK 8 or higher is fine)
5. Click **Next** → give project name: `RestaurantApp`
6. Click **Finish**

---

## 📁 STEP 2 — Create the src folder structure

IntelliJ will auto-create a `src` folder for you.
You will put **ALL .java files inside `src`**.

---

## 📝 STEP 3 to 10 — Create each Java file

**For EACH file:**
1. Right-click on the `src` folder in the left panel
2. Click **New → Java Class**
3. Type the exact class name (e.g. `Fooditem` – note lowercase `i`)
4. Click **OK**
5. **Delete everything** in the file
6. **Paste the code** from the provided file

Repeat this for all 8 files.

---

## ▶ HOW TO RUN THE PROJECT

1. Open `RestaurantGUI.java`
2. Look for the `main` method at the bottom
3. Click the **green ▶ play button** next to `main`
4. OR press **Shift + F10**

The restaurant window will open! 🎉

---

## 🎮 HOW TO USE THE APP

1. **Select item type** — Main Course or Dessert (dropdown)
2. **Type item name** — e.g. "Chicken Biryani"
3. **Type price** — e.g. `8.50` (price includes service charge for Main Course)
4. **Type service charge** — only for Main Course (will be added to price)
5. Click **➕ Add Item** — item appears in the order list
6. Keep adding more items
7. Click **✔ Submit Order** — waiter takes the order, bill shown
8. Click **✖ Clear Order** — start fresh

---

## 🧠 OOP CONCEPTS USED (for your exam!)

### 1. ENCAPSULATION
All fields are `private`. You must use **getter methods** to access them.
```java
// In Restaurant.java
private String name;        // ← private = hidden
public String getName() {   // ← public getter = controlled access
    return name;
}