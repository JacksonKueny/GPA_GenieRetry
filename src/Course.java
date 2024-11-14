import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Course {
    private String name;
    private int num_of_categories;
    private Category[] courseCategories;
    private double courseGrade;

    public void setCourseName(String courseName) {
        this.name = courseName;
    }

    public void setNumOfCategories(int numOfCategories) {
        this.num_of_categories = numOfCategories;
        this.courseCategories = new Category[numOfCategories]; // Initialize the array with the number of categories
    }

    public double calculateCourseGrade() {
        for(int i = 0; i < num_of_categories; i++) {
            if(courseCategories[i] != null) {
                courseGrade += courseCategories[i].calculateCategoryGrade() * (courseCategories[i].getCategoryWeight() / 100);
            }
        }
        return courseGrade;
    }

    public String getCourseName() {
        return name;
    }

    public int getNumOfCategories() {
        return num_of_categories;
    }

    public Category[] getCourseCategories() {
        return courseCategories;
    }

    public void addCourseCategory(Category category, int index) {
        courseCategories[index] = category;
    }

    public void printCourse() {
        System.out.println("Course Name: " + name);
        System.out.println("Course Categories: ");
        for(int i = 0; i < courseCategories.length; i++) {
            if (courseCategories[i] != null) {
                courseCategories[i].printCategory();
            }
        }
    }

    public void predictFutureGrade(String courseName) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Predict Future Grade for Course: " + getCourseName());
        double predictedCourseGrade = 0.0;
    }

    public void editCourseInfo(String courseName) {
        Scanner scanner = new Scanner(System.in);

        // Display editing options
        System.out.println("Edit Course: " + getCourseName());
        System.out.println("1. Edit Course Name");
        System.out.println("2. Edit Categories");
        System.out.println("3. Edit Assignment Grades");
        int option = Main.getIntInput(scanner, "Choose an option (1-3): ", 1, 3);

        if(option == 1) {
            System.out.print("Enter new course name: ");
            setCourseName(scanner.nextLine());
            System.out.println("Course name updated.");
        }
        else if(option == 2) {
            System.out.println("Select category to edit:");
            for (int i = 0; i < num_of_categories; i++) {
                System.out.println((i+1) + ". " + courseCategories[i].getCategoryName());
            }
            int categoryIndex = Main.getIntInput(scanner, "Enter category number: ", 1, num_of_categories) - 1;

            Category category = courseCategories[categoryIndex];
            System.out.println("Editing Category: " + category.getCategoryName());

            System.out.print("Enter new category name: ");
            category.setCategoryName(scanner.nextLine());
            double newWeight = Main.getDoubleInput(scanner, "Enter new category weight (0-100): ", 0, 100);
            category.setCategoryWeight(newWeight);
            System.out.println("Category updated.");
        }
        else if (option == 3) {
            System.out.println("Select category to edit assignment grades:");
            for (int i = 0; i < num_of_categories; i++) {
                System.out.println((i+1) + ". " + courseCategories[i].getCategoryName());
            }
            int catIndex = Main.getIntInput(scanner, "Enter category number: ", 1, num_of_categories) - 1;
            Category chosenCategory = courseCategories[catIndex];

            for (int i = 0; i < chosenCategory.getNum_of_assignments(); i++) {
                double newGrade = Main.getDoubleInput(scanner, "Enter new grade for assignment " + (i+1) + ": ", 0, 100);
                chosenCategory.getAssignmentGrades()[i] = newGrade;
            }
            System.out.println("Assignment grades updated.");
        }
        else {
            System.out.println("Invalid option.");
        }
    }

    public void saveCourseToCSV() {
        String fileName = "courses.csv";
        try (FileWriter writer = new FileWriter(fileName, true)) { // true to append
            // Write course name and number of categories
            writer.write("Course Name: " + name + "\n");

            // Write each category's details
            for (Category category : courseCategories) {
                if (category != null) {
                    writer.write("Category Name: " + category.getCategoryName() + ", " +
                            "Category Weight: " + category.getCategoryWeight() + ", " +
                            "Number of Assignments: " + category.getNum_of_assignments() + ", ");

                    // Write assignment grades as comma-separated values
                    double[] grades = category.getAssignmentGrades();
                    for (int i = 0; i < grades.length; i++) {
                        writer.write("Assignment " + (i+1) + " grade: " + Double.toString(grades[i]));
                        if (i < grades.length - 1){
                            writer.write(", "); // Add comma between grades
                        }
                    }
                    writer.write("\n"); // New line after each category
                }
            }
            writer.write("\n"); // Separate courses with a newline

            System.out.println("Course saved");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the course to a CSV file.");
            e.printStackTrace();
        }
    }

    // New static method to read and display saved courses
    public void displaySavedCourses() {
        String fileName = "courses.csv";
        File file = new File(fileName);

        if (!file.exists()) {
            System.out.println("No saved courses found.");
            return;
        }

        try (Scanner scanner = new Scanner(file)) {
            System.out.println("Saved Courses:");
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the saved courses.");
            e.printStackTrace();
        }
    }
}
