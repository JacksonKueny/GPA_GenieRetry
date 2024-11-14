import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SavedCourses savedCourses = new SavedCourses();

        System.out.println("Hello and welcome to GPA Genie!");

        while (true) {
            System.out.println("1. Enter a new course");
            System.out.println("2. View saved courses");
            System.out.println("3. Exit");

            int choice = getIntInput(scanner, "Enter your choice: ", 1, 3);

            if (choice == 1) {
                Course course = new Course();
                course.inputCourseData(scanner);
                savedCourses.addCourse(course);
                course.saveCourseToCSV();
            } else if (choice == 2) {
                savedCourses.displaySavedCourses();
                Course selectedCourse = savedCourses.selectCourseByName(scanner);
                if (selectedCourse != null) {
                    manageCourseMenu(selectedCourse, scanner);
                }
            } else if (choice == 3) {
                System.out.println("Exiting GPA Genie.");
                break;
            }
        }
    }

    private static void manageCourseMenu(Course course, Scanner scanner) {
        while (true) {
            System.out.println("\n1. Edit course information");
            System.out.println("2. Predict future grade based on quizzes");
            System.out.println("3. Return to main menu");

            int choice = getIntInput(scanner, "Enter your choice: ", 1, 3);

            if (choice == 1) {
                course.editCourseInfo(scanner);
            } else if (choice == 2) {
                course.predictFutureGrade(scanner);
            } else if (choice == 3) {
                break;
            }
        }
    }

    public static int getIntInput(Scanner scanner, String prompt, int min, int max) {
        int value;
        while (true) {
            System.out.print(prompt);
            try {
                value = Integer.parseInt(scanner.next());
                if (value >= min && value <= max) break;
                else System.out.println("Please enter a number between " + min + " and " + max);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
        return value;
    }

    public static double getDoubleInput(Scanner scanner, String prompt, double min, double max) {
        double value;
        while (true) {
            System.out.print(prompt);
            try {
                value = Double.parseDouble(scanner.next());
                if (value >= min && value <= max) break;
                else System.out.println("Please enter a number between " + min + " and " + max);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        return value;
    }
}
