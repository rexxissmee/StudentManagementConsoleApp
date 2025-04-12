# Student Management System - Java Console Application

This is a simple Java console application for managing student records. The system allows users to add, edit, delete, display, search, and sort student information. It is designed for basic command-line interaction with input validation and sorting/searching algorithms (Quick Sort and Binary Search).

## 📁 Package Structure

```
com.btec.assignment.hungqvp
├── Student.java
└── StudentManagement.java
```

## 🚀 Features

- Add new students with unique IDs
- Display all student records
- Edit existing student information
- Delete a student by ID
- Search students by:
  - ID (using HashMap)
  - Name (using Binary Search)
  - Mark (using Binary Search)
- Sort students by marks (descending order) using Quick Sort
- Rank students based on their marks:
  - < 5.0: Fail
  - 5.0 - 6.4: Medium
  - 6.5 - 7.4: Good
  - 7.5 - 8.9: Very Good
  - ≥ 9.0: Excellent

## 🧠 Algorithms Used

- **Binary Search** for fast searching by name and mark
- **Quick Sort** for sorting student list by mark or name
- **HashMap** for O(1) ID lookups

## 🛠 Requirements

- Java 8 or higher
- Any Java IDE or terminal

## 📸 Sample Menu

```
===== Student Management Menu =====
1. Input Student List
2. Display Student List
3. Edit Student
4. Delete Student
5. Search Student by ID
6. Search Student by Name
7. Search Student by Mark
8. Sort by Marks
9. Exit
```

## 📌 Notes

- Duplicate student IDs are not allowed.
- Marks must be in the range of 0 to 10.
- The program validates all user inputs and handles exceptions gracefully.

## 📄 License

This project is open-source and free to use for educational purposes.

---

**Author:** rexxissmee

**GitHub:** https://github.com/rexxissmee
