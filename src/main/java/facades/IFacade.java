package facades;

import entities.Semester;
import entities.Student;
import entities.Teacher;
import java.util.List;

public interface IFacade {

    List<Student> getAllStudents();

    List<Student> getAllStudensWithFirstName(String firstName);

    Student addStudent(Student st);

    Student AddStudentToSemester(Student st, Semester sm);

    List<Student> getAllStudentsWithLastName(String lastName);

    long getNumberOfStudentsInSemester(String semesterName);

    long getNumberOfAllStudent();

    Teacher findMostActiveTeacher();
    
    Semester getSemester(int id);

}
