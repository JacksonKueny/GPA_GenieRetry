import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Course {
    private String name;
    private ArrayList<Category> categories = new ArrayList<>();

    public void setCourseName(String name) {
        this.name = name;
    }

    public String getCourseName() {
        return name;
    }

    public void inputCourseData(Scanner scanner) {
        System.out.print("Enter course name: ");
        this.name = scanner.next();

        int numCategories = Main.getIntInput(scanner, "How many categories: ", 1, 10);
        for (int i = 0; i < numCategories; i++) {
            Category category = new Category();
            category.inputCategoryData(scanner);
            categories.add(category);
        }
    }

    public double calculateCourseGrade() {
        double totalGrade = 0;
        for (Category category : categories) {
            totalGrade += category.calculateCategoryGrade() * (category.getCategoryWeight() / 100.0);
        }
        return totalGrade;
    }

    public void predictFutureGrade(Scanner scanner) {
        System.out.println("Predict Future Grade for Course: " + name);

        // Define the weight of quizzes for simulation purposes
        double quizCategoryWeight = 3.0;  // Quizzes contribute 3% of the final grade

        // Prompt the user for three quiz grades
        double[] quizGrades = new double[3];
        System.out.println("Please enter grades for three quizzes.");
        for (int i = 0; i < 3; i++) {
            quizGrades[i] = Main.getDoubleInput(scanner, "Enter grade for quiz " + (i + 1) + ": ", 0, 100);
        }

        // Simulate the overall grade in each scenario (using each quiz grade one by one)
        System.out.println("\nSimulated Overall Grades:");
        for (int i = 0; i < quizGrades.length; i++) {
            double quizGrade = quizGrades[i];

            // Calculate base course grade (without quiz)
            double baseGrade = calculateCourseGrade();

            // Add the quiz grade as the only score in the quiz category (weighted at 3%)
            double simulatedOverallGrade = baseGrade + (quizGrade * (quizCategoryWeight / 100.0));

            // Display the result for this scenario
            System.out.println("If Quiz Grade " + (i+1) + " is used " + quizGrade + ": Simulated Overall Grade = " + simulatedOverallGrade);
        }
    }

    public void saveCourseToCSV() {
        try (FileWriter writer = new FileWriter("courses.csv", true)) {
            writer.write("Course Name: " + name + "\n");
            for (Category category : categories) {
                writer.write("Category Name: " + category.getCategoryName() + ", Weight: " +
                        category.getCategoryWeight() + ", Assignments: " + category.getNumAssignments() + "\n");
            }
            writer.write("\n");
            System.out.println("Course saved to CSV.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the course.");
        }
    }

    public void printCourseDetails() {
        System.out.println("Course Name: " + name);
        //System.out.println("Categories:");
        for (Category category : categories) {
            category.printCategoryDetails(); // Call the method in Category to print its details
        }
    }

    public void editCourseInfo(Scanner scanner) {
        while (true) {
            System.out.println("\nEditing Course: " + name);
            System.out.println("1. Edit Course Name");
            System.out.println("2. Edit Categories");
            System.out.println("3. Edit Assignment Grades");
            System.out.println("4. Return to Previous Menu");

            int choice = Main.getIntInput(scanner, "Choose an option (1-4): ", 1, 4);

            switch (choice) {
                case 1:
                    editCourseName(scanner);
                    break;
                case 2:
                    editCategories(scanner);
                    break;
                case 3:
                    editAssignmentGrades(scanner);
                    break;
                case 4:
                    return; // Exit edit menu
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void editCourseName(Scanner scanner) {
        System.out.print("Enter new course name: ");
        this.name = scanner.nextLine();
        System.out.println("Course name updated to: " + this.name);
    }

    private void editCategories(Scanner scanner) {
        if (categories.isEmpty()) {
            System.out.println("No categories to edit.");
            return;
        }

        System.out.println("Select a category to edit:");
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i).getCategoryName());
        }

        int categoryIndex = Main.getIntInput(scanner, "Enter category number to edit: ", 1, categories.size()) - 1;
        Category category = categories.get(categoryIndex);

        System.out.println("Editing Category: " + category.getCategoryName());
        System.out.print("Enter new category name: ");
        category.setCategoryName(scanner.next());

        double newWeight = Main.getDoubleInput(scanner, "Enter new category weight (0-100): ", 0, 100);
        category.setCategoryWeight(newWeight);

        System.out.println("Category updated to: " + category.getCategoryName() + " with weight " + category.getCategoryWeight() + "%.");
    }

    private void editAssignmentGrades(Scanner scanner) {
        if (categories.isEmpty()) {
            System.out.println("No categories to edit assignment grades.");
            return;
        }

        System.out.println("Select a category to edit assignment grades:");
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i).getCategoryName());
        }

        int categoryIndex = Main.getIntInput(scanner, "Enter category number to edit assignments: ", 1, categories.size()) - 1;
        Category category = categories.get(categoryIndex);

        System.out.println("Editing assignment grades in Category: " + category.getCategoryName());
        double[] assignmentGrades = category.getAssignmentGrades();

        for (int i = 0; i < assignmentGrades.length; i++) {
            assignmentGrades[i] = Main.getDoubleInput(scanner, "Enter new grade for assignment " + (i + 1) + ": ", 0, 100);
        }

        category.setAssignmentGrades(assignmentGrades);
        System.out.println("Assignment grades updated for Category: " + category.getCategoryName());
    }
}