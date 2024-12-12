# Culla-Clark-Cedric-C.-JAVA
# Leastahan

Leastahan is a simple Java-based console application that allows users to manage personal lists of items. Users can add, view, mark, and delete items, all while being able to switch between different user profiles.

## Features

- **User Management**: Each user has their own personalized list of items.
- **Item Management**:
  - Add new items to your list.
  - View and mark items as completed.
  - Delete items from your list.
- **Switch Users**: Seamlessly switch between different user accounts.
- **Persistence During Session**: Items for each user are managed separately during the program's runtime.

---

## Project Structure

1. **`User` (Abstract Class)**:
   - Represents a general user with a `username`.
   - Contains an abstract method `displayWelcomeMessage()` that must be implemented by subclasses.

2. **`RegularUser` (Subclass of `User`)**:
   - Represents a regular user with a personalized welcome message.

3. **`Item`**:
   - Represents an item in the user's list.
   - Attributes:
     - `name`: Name of the item.
     - `checked`: Boolean indicating whether the item is marked as completed.

4. **`ItemManager`**:
   - Manages user-specific lists of items using a `Map<String, List<Item>>`.
   - Provides methods to:
     - Add items.
     - Retrieve a user's list of items.
     - Delete items.

5. **`Leastahan` (Main Class)**:
   - Entry point of the application.
   - Handles user input and interacts with `ItemManager` and `RegularUser` to perform actions.
   - Main menu options include:
     - Add Item
     - View Items
     - Delete Item
     - Switch User
     - Exit

---

## How to Use

1. **Compile and Run**:
   - Use the following commands to compile and run the application:
     ```bash
     javac Leastahan.java
     java Leastahan
     ```

2. **Login**:
   - Enter a username to begin. This creates or switches to the user's account.

3. **Main Menu**:
   - Options are displayed to manage items or switch users.

4. **Add Item**:
   - Enter the name of the item to add it to the current user's list.

5. **View Items**:
   - See all items in the current user's list.
   - Optionally, mark items as completed.

6. **Delete Item**:
   - Remove an item by specifying its index.

7. **Switch User**:
   - Log in with a different username to manage another user's list.

8. **Exit**:
   - Quit the application.

---

## Example Usage

