package manualtests;

import entities.Semester;
import entities.Student;
import facades.Facade;
import java.util.List;
import javax.persistence.Persistence;

public class manualtest {

    public static void main(String[] args)
    {
        Facade f = new Facade(Persistence.createEntityManagerFactory("devpu"));
        List<Student> students = f.getAllStudents();
        for (Student student : students)
        {
            System.out.println(student);
        }
        System.out.println("Find students all with firstname of Anders");
        students = f.getAllStudensWithFirstName("Anders");
        for (Student student : students)
        {
            System.out.println(student);
        }

        System.out.println("Add new Student");
        Student stu = new Student("Knud", "And");
        stu = f.addStudent(stu);
        System.out.println("added student:" + stu);

        System.out.println("Add student to semester");
        Semester sem = f.getSemester(1);
        stu.setCurrentsemesterId(sem);
        stu = f.AddStudentToSemester(stu, sem);
        System.out.println("added student to semester" + stu);

        System.out.println("find all students with last name and");
        students = f.getAllStudentsWithLastName("And");
        for (Student student : students)
        {
            System.out.println(student);
        }

        long stuInSem = f.getNumberOfStudentsInSemester("CLcos-v14e");
        System.out.println("Number of studens in cos: " + stuInSem);

        long numStu = f.getNumberOfAllStudent();
        System.out.println("All students in semesters: " + numStu);
    }

}
