import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CourseManager {
    private static final String FILE_NAME = "courses.csv";
    private ArrayList<Course> courses = new ArrayList<>();

    public CourseManager() {
        loadCoursesFromCSV();
    }

    // Load courses from the CSV file
    private void loadCoursesFromCSV() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("No saved courses found.");
            return;
        }

        try (Scanner scanner = new Scanner(file)) {
            Course course = null;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.startsWith("Course Name: ")) {
                    course = new Course();
                    course.setCourseName(line.substring(13).trim());
                    courses.add(course);
                } else if (line.startsWith("Category Name: ")) {
                    // Parse category details if needed for full course representation
                    // Here we can assume categories are parsed but simplify for brevity
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the saved courses.");
            e.printStackTrace();
        }
    }

    // Display all loaded courses and prompt user to select one by name
    public Course selectCourseByName(Scanner scanner) {
        if (courses.isEmpty()) {
            System.out.println("No courses available.");
            return null;
        }

        System.out.println("Available Courses:");
        for (int i = 0; i < courses.size(); i++) {
            System.out.println((i + 1) + ". " + courses.get(i).getCourseName());
        }

        System.out.print("Enter the course name you want to select: ");
        String courseName = scanner.nextLine().trim();

        for (Course course : courses) {
            if (course.getCourseName().equalsIgnoreCase(courseName)) {
                System.out.println("Course '" + courseName + "' selected.");
                return course;
            }
        }

        System.out.println("Course not found. Please try again.");
        return null;
    }
}
