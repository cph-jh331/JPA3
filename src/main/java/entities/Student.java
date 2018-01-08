package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "student")
@NamedQueries(
        {
            @NamedQuery(name = "Student.findAll", query = "SELECT s FROM Student s")
            , @NamedQuery(name = "Student.findById", query = "SELECT s FROM Student s WHERE s.id = :id")
            , @NamedQuery(name = "Student.findByFirstname", query = "SELECT s FROM Student s WHERE s.firstname = :firstname")
            , @NamedQuery(name = "Student.findByLastname", query = "SELECT s FROM Student s WHERE s.lastname = :lastname")
            , @NamedQuery(name = "Student.countStudentsInSemester", query = "SELECT COUNT(s) FROM Student s WHERE s.currentsemesterId.name =:semesterName")
        })
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Size(max = 255)
    @Column(name = "FIRSTNAME")
    private String firstname;
    @Size(max = 255)
    @Column(name = "LASTNAME")
    private String lastname;
    @JoinColumn(name = "CURRENTSEMESTER_ID", referencedColumnName = "ID")
    @ManyToOne
    private Semester currentsemesterId;

    public Student()
    {
    }

    public Student(String firstname, String lastname)
    {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getFirstname()
    {
        return firstname;
    }

    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }

    public String getLastname()
    {
        return lastname;
    }

    public void setLastname(String lastname)
    {
        this.lastname = lastname;
    }

    public Semester getCurrentsemesterId()
    {
        return currentsemesterId;
    }

    public void setCurrentsemesterId(Semester currentsemesterId)
    {
        this.currentsemesterId = currentsemesterId;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Student))
        {
            return false;
        }
        Student other = (Student) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "Student{" + "id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", currentsemesterId=" + currentsemesterId + '}';
    }

}