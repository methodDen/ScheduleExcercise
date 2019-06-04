package Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Objects;

@DatabaseTable (tableName = "Tutors")
public class Tutors {
    @DatabaseField (columnName = "TutorId")
    private long tutorId;
    @DatabaseField (columnName = "FirstName")
    private String firstName;
    @DatabaseField (columnName = "LastName")
    private String lastName;
    @DatabaseField (columnName = "DateOfBirth")
    private String dateOfBirth; // change and parse on SimpleDateFormat
    @DatabaseField (columnName = "PhoneNumber")
    private String phoneNumber;

    public long getTutorId() {
        return tutorId;
    }

    public void setTutorId(long tutorId) {
        this.tutorId = tutorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tutors tutors = (Tutors) o;
        return tutorId == tutors.tutorId &&
                Objects.equals(firstName, tutors.firstName) &&
                Objects.equals(lastName, tutors.lastName) &&
                Objects.equals(dateOfBirth, tutors.dateOfBirth) &&
                Objects.equals(phoneNumber, tutors.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tutorId, firstName, lastName, dateOfBirth, phoneNumber);
    }

    public Tutors() {
    }
}