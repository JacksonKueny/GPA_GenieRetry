import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SavedCourses {
    private ArrayList<Course> courses = new ArrayList<>();

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void displaySavedCourses() {
        File file = new File("courses.csv");

        // Check if the file exists and is not empty
        if (!file.exists() || file.length() == 0) {
            System.out.println("No saved courses found in the CSV file.");
            return;
        }

        // Clear the in-memory course list before reading from the file
        courses.clear();

        System.out.println("Saved Courses:");

        try (Scanner scanner = new Scanner(file)) {
            Course course = null;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                // Identify the start of a new course
                if (line.startsWith("Course Name: ")) {
                    course = new Course();
                    course.setCourseName(line.substring(13).trim());
                    courses.add(course);  // Add the new course to the list
                    System.out.println(" - " + course.getCourseName());
                }
                // Additional lines for category data, if needed
                else if (line.startsWith("Category Name: ")) {
                    // Logic to parse category details can be added if necessary
                }
            }

        } catch (IOException e) {
            System.out.println("An error occurred while reading the saved courses from the CSV file.");
            e.printStackTrace();
        }
    }

    public Course selectCourseByName(Scanner scanner) {
        System.out.print("Enter the name of the course to select: ");
        String courseName = scanner.next();

        for (Course course : courses) {
            if (course.getCourseName().equalsIgnoreCase(courseName)) {
                System.out.println("Course '" + courseName + "' selected.");
                //course.printCourseDetails();
                return course;
            }
        }

        System.out.println("Course not found.");
        return null;
    }
}
