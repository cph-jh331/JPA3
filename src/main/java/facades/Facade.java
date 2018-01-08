package facades;

import entities.Semester;
import entities.Student;
import entities.Teacher;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class Facade implements IFacade {

    private EntityManagerFactory emf;

    public Facade(EntityManagerFactory emf)
    {
        this.emf = emf;
    }

    private EntityManager getEntityManager()
    {
        return this.emf.createEntityManager();
    }

    //virker
    @Override
    public List<Student> getAllStudents()
    {
        EntityManager em = getEntityManager();
        try
        {
            Query q = em.createNamedQuery("Student.findAll");
            return q.getResultList();
        } finally
        {
            em.close();
        }
    }

    @Override
    public List<Student> getAllStudensWithFirstName(String firstName)
    {
        EntityManager em = getEntityManager();
        try
        {
            Query q = em.createNamedQuery("Student.findByFirstname");
            q.setParameter("firstname", firstName);
            return q.getResultList();
        } finally
        {
            em.close();
        }
    }

    @Override
    public Student addStudent(Student st)
    {
        EntityManager em = getEntityManager();
        try
        {
            em.getTransaction().begin();
            em.persist(st);
            em.getTransaction().commit();
            return st;
        } finally
        {
            em.close();
        }
    }

    @Override
    public Student AddStudentToSemester(Student st, Semester sm)
    {
        EntityManager em = getEntityManager();
        try
        {
            if (em.find(Student.class, st.getId()) == null)
            {
                return null;
            }
            if (em.find(Semester.class, sm.getId()) == null)
            {
                return null;
            }
            st.setCurrentsemesterId(sm);
            em.getTransaction().begin();
            em.merge(st);
            em.getTransaction().commit();
            return st;
        } finally
        {
            em.close();
        }
    }

    @Override
    public List<Student> getAllStudentsWithLastName(String lastName)
    {
        EntityManager em = getEntityManager();
        try
        {
            Query q = em.createNamedQuery("Student.findByLastname");
            q.setParameter("lastname", lastName);
            return q.getResultList();
        } finally
        {
            em.close();
        }
    }

    @Override
    public long getNumberOfStudentsInSemester(String semesterName)
    {
        EntityManager em = getEntityManager();
        try
        {
            Query q = em.createNamedQuery("Student.countStudentsInSemester");
            q.setParameter("semesterName", semesterName);
            return (long) q.getSingleResult();

        } catch (NoResultException ex)
        {
            return 0;
        } finally
        {
            em.close();
        }
    }

    @Override
    public long getNumberOfAllStudent()
    {
        EntityManager em = getEntityManager();
        try
        {
            Query q = em.createQuery("SELECT COUNT(s) FROM Student s WHERE s.currentsemesterId IS NOT NULL");
            return (long) q.getSingleResult();
        } catch (NoResultException ex)
        {
            return 0;
        } finally
        {
            em.close();
        }
    }

    @Override
    public Teacher findMostActiveTeacher()
    {
        EntityManager em = getEntityManager();
        try
        {
            //kan jeg sgu ikke overskue. 02.
            return null;
        } finally
        {
            em.close();
        }
    }

    @Override
    public Semester getSemester(int id)
    {
        long lid = id;
        EntityManager em = getEntityManager();
        try
        {
            return em.find(Semester.class, lid);
        } finally
        {
            em.close();
        }
    }

}
