# 🧠 JDM Exercise Game – *MyoMove*

A Java-based, gamified command-line prototype that supports children with Juvenile Dermatomyositis (JDM) in performing and tracking their CMAS exercises. The system rewards participation with virtual items and stores all progress in a local SQLite database. Developed as part of the *Introduction to Software Engineering* course at Zuyd University.

---

## 📌 Project Summary

Children diagnosed with JDM are required to complete a set of 14 physical exercises, evaluated using the Children’s Myositis Assessment Scale (CMAS). In-person sessions can be exhausting and infrequent. This project proposes a **remote, gamified digital solution** where:
- Sessions are completed at home
- Feedback and progress are saved in a database
- Children unlock monsters, outfits, and customize their character

---

## 👨‍👩‍👧‍👦 Team

Developed by Team *Bambino*  
- Ana Grigore, Jaroslav Sloup, Tijmen Fauser, Luc Gerards  
- Zuyd University of Applied Sciences  
- April 2025

---

## 🗂 Features

### ✅ Core Functionality
- Start a CMAS session with 14 exercises
- Input feedback (difficulty level) per exercise
- Earn coins for completing exercises
- Coins can be used to buy monsters, outfits, or gamble for a surprise
- All data stored in SQLite and restored on future logins

### 👕 Character Customization
- Equip unlocked outfits and buddy monsters
- Visual overview of your inventory

### 🛍 Shop
- Purchase items using earned currency
- Prevents duplicate purchases
- “Chest” gambling option rewards random items

---

## 🏗️ Project Structure

```
src/
├── GameApp.java                # Main menu and app logic
├── User.java                   # Handles user profile and ID
├── Character.java              # Customization logic
├── Inventory.java              # Tracks coins and owned items
├── Item.java (abstract)        # Parent class for Outfit & Monster
├── Outfit.java / Monster.java  # Cosmetic & collectible items
├── Wardrobe.java / Monsterdex.java  # Collection of items
├── Shop.java                   # Shop logic and gambling
├── SessionManager.java         # Session creation
├── SessionLogic.java           # Session execution logic
├── InventorySQL.java           # Inventory persistence via SQLite
├── CoinSQL.java                # Coin persistence
├── UserManager.java            # Fetching/creating users
├── DatabaseConnector.java      # SQLite DB connection
├── ItemCollection.java         # Abstract base for Monsterdex/Wardrobe
```

---

## 🧱 Database Schema (SQLite)

| Table | Description |
|-------|-------------|
| `User` | Stores user ID, name, and age |
| `Session` | Tracks sessions (ID, user, score) |
| `Session_Exercise` | Stores feedback for each of the 14 exercises |
| `UserInventory` | Stores which user owns which item (by name/type) |
| `UserCoins` | Stores how many coins each user has |

---

## 💻 Technologies Used

- **Java** – Object-oriented logic & control flow
- **SQLite** – Lightweight, embedded database
- **IntelliJ IDEA** – Development environment
- **Git** – Version control

---

## ▶️ How to Run

1. Ensure you have Java 17 or higher.
2. Add the SQLite JDBC driver (`sqlite-jdbc-3.42.0.0.jar`) to your project.
3. Compile the program:

```bash
javac src/*.java
```

4. Run the application:

```bash
java -cp .:sqlite-jdbc-3.42.0.0.jar src/GameApp
```

*(On Windows, use `;` instead of `:` for classpath separator)*

---

## 🧪 Example Flow

1. User starts the program → Enters name and age.
2. Session begins with 14 exercises.
3. User marks completion and gives feedback.
4. At the end → User receives coins.
5. Coins can be spent in shop or used to gamble.
6. Unlocked outfits/monsters are stored and visible next time the user logs in.

---

## 📊 Testing

Manual testing confirmed:
- Feedback is correctly stored
- Skipped exercises are saved with `-1` feedback
- Inventory and coins persist across sessions
- Invalid inputs are handled gracefully

---

## 📉 Limitations & Future Work

### 🔧 Known Limitations
- Command-line only (no GUI)
- SQLite not suitable for large-scale deployment
- No doctor dashboard implemented (yet)

### 🚀 Planned Features
- GUI version using JavaFX or Swing
- Doctor dashboard for remote monitoring
- Login system and password support
- Automatic daily reminders / streak tracker
- Research data export (CSV/Excel)

---

## 🧑‍⚕️ Use Case Scenarios

| Use Case | Description |
|----------|-------------|
| CMAS Session | Perform and record 14 exercises |
| Open Shop | Buy monsters, outfits, or chests |
| Customize Character | Equip buddy and outfit |
| View Monsterdex/Wardrobe | See all owned and locked items |

---

## 📥 Database Setup

The application uses a local SQLite DB. If `JDM_An_Unexpected_Journey.db` doesn't exist, it will be created with tables automatically.

To reset all data:

```sql
DELETE FROM Session_Exercise;
DELETE FROM Session;
DELETE FROM UserInventory;
DELETE FROM UserCoins;
DELETE FROM User;
```

---

## 🙌 Special Thanks

Big thanks to our mentors and peers for feedback and inspiration!
