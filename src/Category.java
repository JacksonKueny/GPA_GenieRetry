public class Category {
    private String categoryName;
    private double categoryWeight;
    private double categoryGrade;
    private int num_of_assignments;
    private double[] assignmentGrades;

    // Setter methods
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    public void setCategoryWeight(double categoryWeight) {
        this.categoryWeight = categoryWeight;
    }
    public void setNum_of_assignments(int num_of_assignments) {
        this.num_of_assignments = num_of_assignments;
    }
    public void setAssignmentGrades(double[] assignmentGrades) {
        this.assignmentGrades = assignmentGrades;
    }

    // Getter methods
    public String getCategoryName() {
        return categoryName;
    }
    public double getCategoryWeight() {
        return categoryWeight;
    }
    public int getNum_of_assignments() {
        return num_of_assignments;
    }
    public double[] getAssignmentGrades() {
        return assignmentGrades;
    }

    public double calculateCategoryGrade() {
        for(int i=0; i<num_of_assignments; i++) {
            categoryGrade += assignmentGrades[i];
        }
        categoryGrade = categoryGrade / num_of_assignments;
        return categoryGrade;
    }

    public void printCategory() {
        System.out.println("Name: " + getCategoryName() + " Weight: " + getCategoryWeight() +
                            " Number of assignments: " + getNum_of_assignments() + " Grade: " + calculateCategoryGrade());
    }
}
