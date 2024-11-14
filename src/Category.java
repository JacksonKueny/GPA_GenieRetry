import java.util.Scanner;

public class Category {
    private String categoryName;
    private double categoryWeight;
    private int numAssignments;
    private double[] assignmentGrades;

    public void inputCategoryData(Scanner scanner) {
        System.out.print("Enter category name: ");
        this.categoryName = scanner.next();

        this.categoryWeight = Main.getDoubleInput(scanner, "Enter category weight (0-100): ", 0, 100);
        this.numAssignments = Main.getIntInput(scanner, "How many assignments in this category: ", 1, 10);

        this.assignmentGrades = new double[numAssignments];
        for (int i = 0; i < numAssignments; i++) {
            assignmentGrades[i] = Main.getDoubleInput(scanner, "Enter grade for assignment " + (i + 1) + ": ", 0, 100);
        }
    }

    public double calculateCategoryGrade() {
        double total = 0;
        for (double grade : assignmentGrades) {
            total += grade;
        }
        return total / numAssignments;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public double getCategoryWeight() {
        return categoryWeight;
    }

    public int getNumAssignments() {
        return numAssignments;
    }

    // Setter for category name
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    // Setter for category weight
    public void setCategoryWeight(double categoryWeight) {
        this.categoryWeight = categoryWeight;
    }

    // Setter for assignment grades
    public void setAssignmentGrades(double[] assignmentGrades) {
        this.assignmentGrades = assignmentGrades;
        this.numAssignments = assignmentGrades.length;  // Update the number of assignments
    }

    // Getter for assignment grades
    public double[] getAssignmentGrades() {
        return assignmentGrades;
    }

    public void printCategoryDetails() {
        System.out.println(" - " + categoryName + " (Weight: " + categoryWeight + "%)");
        System.out.println("   Assignment Grades:");
        for (int i = 0; i < assignmentGrades.length; i++) {
            System.out.println("     Assignment " + (i + 1) + ": " + assignmentGrades[i]);
        }
    }
}
