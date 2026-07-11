package org.example;

public class StudentGPA implements Comparable<StudentGPA>{
    private String studentId;
    double gpa;

    public StudentGPA(String studentId, double gpa) {
        this.studentId = studentId;
        this.gpa = gpa;
    }

    public String getStudentId() {
        return studentId;
    }

    @Override
    public int compareTo(StudentGPA o) {
        int cmp = Double.compare(o.gpa, this.gpa);
        if (cmp == 0) return this.studentId.compareTo(o.studentId);
        return cmp;
    }
}
