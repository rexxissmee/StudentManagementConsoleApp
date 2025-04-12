package com.btec.assignment.hungqvp;

import java.util.*;

public class StudentManagement {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Student> students = new ArrayList<>();
    private static HashMap<String, Student> studentMap = new HashMap<>();

    // Input student information
    public static void inputStudentList() {
        System.out.print("Enter the number of students: ");
        int numStudents;
        try {
            numStudents = scanner.nextInt();
            scanner.nextLine();
            if (numStudents < 0) {
                System.out.println("Error: Number of students must be non-negative.");
                return;
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid input. Please enter a number.");
            scanner.nextLine();
            return;
        }

        for (int i = 0; i < numStudents; i++) {
            System.out.println("\nEnter information for student " + (i + 1) + ":");
            System.out.print("Enter ID: ");
            String id = scanner.nextLine();

            if (studentMap.containsKey(id)) {
                System.out.println("Error: ID already exists.");
                i--;
                continue;
            }

            System.out.print("Enter Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Marks (0-10): ");
            double mark;
            try {
                mark = scanner.nextDouble();
                scanner.nextLine();
                if (mark < 0 || mark > 10) {
                    System.out.println("Error: Marks must be between 0 and 10.");
                    i--;
                    continue;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid marks input. Please enter a number.");
                scanner.nextLine();
                i--;
                continue;
            }

            Student student = new Student(id, name, mark);
            students.add(student);
            studentMap.put(id, student);
        }
    }

    // Display student list
    public static void outputStudentList(List<Student> studentList) {
        if (studentList.isEmpty()) {
            System.out.println("No students in the list.");
            return;
        }
        System.out.println("\nList of Students:");
        for (Student student : studentList) {
            System.out.println(student);
        }
    }

    // Edit student information
    public static void editStudent() {
        System.out.print("\nEnter ID of student to edit: ");
        String id = scanner.nextLine();

        if (!studentMap.containsKey(id)) {
            System.out.println("Error: Student with ID " + id + " not found.");
            return;
        }

        System.out.print("Enter new Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter new Mark (0-10): ");
        double mark;
        try {
            mark = scanner.nextDouble();
            scanner.nextLine();
            if (mark < 0 || mark > 10) {
                System.out.println("Error: Mark must be between 0 and 10.");
                return;
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid mark input.");
            scanner.nextLine();
            return;
        }

        Student student = studentMap.get(id);
        student.setName(name);
        student.setMark(mark);
        System.out.println("Student updated successfully.");
    }

    // Delete student
    public static void deleteStudent() {
        System.out.print("\nEnter ID of student to delete: ");
        String id = scanner.nextLine();

        if (!studentMap.containsKey(id)) {
            System.out.println("Error: Student with ID " + id + " not found.");
            return;
        }

        Student student = studentMap.get(id);
        students.remove(student);
        studentMap.remove(id);
        System.out.println("Student deleted successfully.");
    }

    // Search student by ID (using HashMap)
    public static void searchStudentById() {
        System.out.print("\nEnter ID of student to search: ");
        String id = scanner.nextLine();

        Student student = studentMap.get(id);
        if (student != null) {
            System.out.println("Student found: " + student);
        } else {
            System.out.println("Student with ID " + id + " not found.");
        }
    }

    // Search student by mark (using Binary Search)
    public static void searchStudentByMark() {
        if (students.isEmpty()) {
            System.out.println("No students in the list to search.");
            return;
        }

        System.out.print("Enter mark to search: ");
        double searchMark;
        try {
            searchMark = scanner.nextDouble();
            scanner.nextLine();
            if (searchMark < 0 || searchMark > 10) {
                System.out.println("Error: Mark must be between 0 and 10.");
                return;
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid mark input.");
            scanner.nextLine();
            return;
        }

        // Sort by mark for Binary Search
        List<Student> sortedList = new ArrayList<>(students);
        quickSortByMark(sortedList, 0, sortedList.size() - 1);

        int left = 0, right = sortedList.size() - 1;
        boolean found = false;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            double midMark = sortedList.get(mid).getMark();

            if (Math.abs(midMark - searchMark) < 0.0001) {
                System.out.println("Student found: " + sortedList.get(mid));
                found = true;
                break;
            } else if (midMark < searchMark) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        if (!found) {
            System.out.println("No student found with mark " + searchMark);
        }
    }

    // Search student by name (using Binary Search)
    public static void searchStudentByName() {
        if (students.isEmpty()) {
            System.out.println("No students in the list to search.");
            return;
        }

        System.out.print("Enter name to search: ");
        String searchName = scanner.nextLine();

        // Sort by name for Binary Search
        List<Student> sortedList = new ArrayList<>(students);
        quickSortByName(sortedList, 0, sortedList.size() - 1);

        int left = 0, right = sortedList.size() - 1;
        boolean found = false;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            String midName = sortedList.get(mid).getName();
            int comparison = midName.compareToIgnoreCase(searchName);

            if (comparison == 0) {
                System.out.println("Student found: " + sortedList.get(mid));
                found = true;
                break;
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        if (!found) {
            System.out.println("No student found with name " + searchName);
        }
    }

    // Quick Sort by Mark
    public static void quickSortByMark(List<Student> list, int low, int high) {
        if (low < high) {
            int pi = partitionByMark(list, low, high);
            quickSortByMark(list, low, pi - 1);
            quickSortByMark(list, pi + 1, high);
        }
    }

    private static int partitionByMark(List<Student> list, int low, int high) {
        double pivot = list.get(high).getMark();
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (list.get(j).getMark() >= pivot) { // Descending order
                i++;
                Student temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
            }
        }
        Student temp = list.get(i + 1);
        list.set(i + 1, list.get(high));
        list.set(high, temp);
        return i + 1;
    }

    // Quick Sort by Name
    public static void quickSortByName(List<Student> list, int low, int high) {
        if (low < high) {
            int pi = partitionByName(list, low, high);
            quickSortByName(list, low, pi - 1);
            quickSortByName(list, pi + 1, high);
        }
    }

    private static int partitionByName(List<Student> list, int low, int high) {
        String pivot = list.get(high).getName();
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (list.get(j).getName().compareToIgnoreCase(pivot) <= 0) { // Ascending order
                i++;
                Student temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
            }
        }
        Student temp = list.get(i + 1);
        list.set(i + 1, list.get(high));
        list.set(high, temp);
        return i + 1;
    }

    // Main menu
    public static void mainMenu() {
        while (true) {
            System.out.println("\n===== Student Management Menu =====");
            System.out.println("1. Input Student List");
            System.out.println("2. Display Student List");
            System.out.println("3. Edit Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Search Student by ID");
            System.out.println("6. Search Student by Name");
            System.out.println("7. Search Student by Mark");
            System.out.println("8. Sort by Marks");
            System.out.println("9. Exit");
            System.out.print("Select an option (1-9): ");

            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter a valid number.");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1 -> inputStudentList();
                case 2 -> outputStudentList(students);
                case 3 -> editStudent();
                case 4 -> deleteStudent();
                case 5 -> searchStudentById();
                case 6 -> searchStudentByName();
                case 7 -> searchStudentByMark();
                case 8 -> {
                    List<Student> sortedList = new ArrayList<>(students);
                    quickSortByMark(sortedList, 0, sortedList.size() - 1);
                    System.out.println("Sorted by mark:");
                    outputStudentList(sortedList);
                }
                case 9 -> {
                    System.out.println("Exiting program...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Please enter a number from 1 to 9.");
            }
        }
    }

    public static void main(String[] args) {
        mainMenu();
    }
}