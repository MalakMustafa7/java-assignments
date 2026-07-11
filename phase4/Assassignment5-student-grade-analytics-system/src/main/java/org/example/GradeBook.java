package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class GradeBook {
    Map<String, Student> students = new HashMap<>();
    Map<String, Subject> subjects = new HashMap<>();
    private Map<String,List<Grade>> studentGrades= new HashMap<>();
    private Map<String,List<Grade>> subjectGrades = new HashMap<>();
    Map<String, Double> studentGPA = new HashMap<>();

    TreeSet<StudentGPA> ranking = new TreeSet<>();

    public void addStudent(Student student){
        students.put(student.getStudentId(),student);
    }

    public void addGrade(Grade grade){
       studentGrades.computeIfAbsent(grade.getStudentId(),k->new ArrayList<>())
                                     .add(grade);
        subjectGrades.computeIfAbsent(grade.getSubjectCode(),k->new ArrayList<>())
                                       .add(grade);

        updateGPA(grade.getStudentId());

    }

    public List<Grade> getStudentGrades(String studentId){
        return studentGrades.getOrDefault(studentId,new ArrayList<>());
    }

    public List<Grade>getSubjectGrades(String subjectCode){
        return subjectGrades.getOrDefault(subjectCode,new ArrayList<>());
    }
    public List<Student>getTopStudents(int n){
        List<String>studentsId= ranking.stream()
                .limit(n)
                .map(StudentGPA::getStudentId)
                .toList();
        return studentsId.stream()
                .filter(students::containsKey)
                .map(students::get)
                .toList();
    }

//    public List<Student>getTopStudents(int n){
//        List<String>studentsId= studentGrades.values().stream()
//                .flatMap(Collection::stream)
//                .collect(Collectors.groupingBy(Grade::getStudentId,
//                        Collectors.summingDouble(Grade::getScores)))
//                .entrySet().stream()
//                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
//                .map(Map.Entry::getKey)
//                .toList();
//        return studentsId.stream()
//                .filter(students::containsKey)
//                .map(students::get)
//                .toList();
//    }

    public double getClassAverage(String subjectCode,String semester){
        return subjectGrades
                .getOrDefault(subjectCode, Collections.emptyList())
                .stream()
                .filter(g->g.getSemesters().equals(semester))
                .mapToDouble(Grade::getScores)
                .average()
                .orElse(0.0);
    }
    public int getStudentRank(String studentId){
        int rank=1;
        for(StudentGPA gpa:ranking){
            if(gpa.getStudentId().equals(studentId)){
                return rank;
            }
            rank++;
        }
        return -1;
    }

    private void updateGPA(String studentId) {
        List<Grade> grades = studentGrades.get(studentId);
        double avg = grades.stream()
                .mapToDouble(Grade::getScores)
                .average()
                .orElse(0.0);

        ranking.remove(new StudentGPA(studentId,studentGPA.getOrDefault(studentId,0.0)));
        studentGPA.put(studentId,avg);
        ranking.add(new StudentGPA(studentId,avg));

    }

}
