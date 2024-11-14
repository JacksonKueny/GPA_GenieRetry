import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        System.out.println("Hello and welcome to GPA Genie!");
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("1. Enter a new course");
            System.out.println("2. View saved courses");
            System.out.println("3. Exit");

            int choice = getIntInput(scanner, "Enter your choice: ", 1, 3);

            if(choice == 1) {
                Course course = new Course();
                System.out.println("Enter course name: ");
                String courseName = scanner.next();
                course.setCourseName(courseName);

                int num_of_categories = getIntInput(scanner, "How many categories: ", 1, Integer.MAX_VALUE);
                course.setNumOfCategories(num_of_categories);

                for(int i = 0; i < num_of_categories; i++) {
                    // Create a new Category object for each category
                    Category category = new Category();

                    System.out.println("Enter " + (i+1) + " course category name: ");
                    String categoryName = scanner.next();
                    category.setCategoryName(categoryName);

                    int categoryWeight = getIntInput(scanner, "How much does the category weigh (0-100): ", 0, 100);
                    category.setCategoryWeight(categoryWeight);

                    int numAssignmentsCategory = getIntInput(scanner, "How many assignments in this category: ", 1, 10);
                    category.setNum_of_assignments(numAssignmentsCategory);

                    double[] assignmentGrades = new double[numAssignmentsCategory];
                    for(int j = 0; j < numAssignmentsCategory; j++) {
                        assignmentGrades[j] = getDoubleInput(scanner, (j + 1) + ". Enter assignment grade (0-100): ", 0, 100);
                    }
                    category.setAssignmentGrades(assignmentGrades);

                    // Add the category to the course
                    course.addCourseCategory(category, i);
                }

                course.printCourse();
                course.saveCourseToCSV();

            }
            else if (choice == 2) {
                Course course = new Course();
                course.displaySavedCourses();
                System.out.println("Current Grade: " + course.calculateCourseGrade());

                System.out.println("1. Edit course information");
                System.out.println("2. Predict future grade based off quizzes");
                System.out.println("3. Return to main menu");
                choice = getIntInput(scanner, "Enter your choice: ", 1, 3);

                if(choice == 1) {
                    System.out.println("Choose course name exactly: ");
                    String courseName = scanner.next();
                    course.editCourseInfo(courseName);
                }
                else if(choice == 2) {
                    System.out.println("Choose course name exactly: ");
                    String courseName = scanner.next();
                    course.predictFutureGrade(courseName);
                }
                else if(choice == 3) {
                    break;
                }
            }
            else if (choice == 3) {
                System.out.println("Exiting GPA Genie.");
                break;
            }
            else {
                System.out.println("Invalid choice. Please enter 1, 2, or 3.");
            }
        }
    }
    public static int getIntInput(Scanner scanner, String prompt, int min, int max) {
        int value;
        while (true) {
            System.out.print(prompt);
            try {
                value = Integer.parseInt(scanner.next());
                if (value >= min && value <= max) {
                    break;
                } else {
                    System.out.println("Please enter a number between " + min + " and " + max);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
        return value;
    }

    // Utility method for double input with range checking
    public static double getDoubleInput(Scanner scanner, String prompt, double min, double max) {
        double value;
        while (true) {
            System.out.print(prompt);
            try {
                value = Double.parseDouble(scanner.next());
                if (value >= min && value <= max) {
                    break;
                } else {
                    System.out.println("Please enter a number between " + min + " and " + max);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        return value;
    }
}