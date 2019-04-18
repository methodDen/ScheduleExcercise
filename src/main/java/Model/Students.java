package Model;

import Utils.Role;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Objects;

@DatabaseTable (tableName = "Model.Students")
public class Students {
    @DatabaseField(columnName = "StudentId", generatedId = true, canBeNull = false)
    private long studentId;
    @DatabaseField(columnName = "FirstName")
    private String firstName;
    @DatabaseField(columnName = "LastName")
    private String lastName;
    @DatabaseField(columnName = "MobilePhone")
    private String mobilePhone;
    @DatabaseField(columnName = "GroupId")
    private long groupId;
    @DatabaseField(columnName = "Login")
    private String login;
    @DatabaseField(columnName = "PasswordOfStudent")
    private String passwordOfStudent;
    @DatabaseField(columnName = "RoleOfUser")
    private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Students(long studentId, String firstName, String lastName, String mobilePhone, long groupId) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobilePhone = mobilePhone;
        this.groupId = groupId;
    }

    public Students(long studentId, String firstName, String lastName, String mobilePhone, long groupId, String login, String passwordOfStudent, Role role) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobilePhone = mobilePhone;
        this.groupId = groupId;
        this.login = login;
        this.passwordOfStudent = passwordOfStudent;
        this.role = role;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswordOfStudent() {
        return passwordOfStudent;
    }

    public void setPasswordOfStudent(String passwordOfStudent) {
        this.passwordOfStudent = passwordOfStudent;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
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

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Students students = (Students) o;
        return studentId == students.studentId &&
                groupId == students.groupId &&
                Objects.equals(firstName, students.firstName) &&
                Objects.equals(lastName, students.lastName) &&
                Objects.equals(mobilePhone, students.mobilePhone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, firstName, lastName, mobilePhone, groupId);
    }

    public Students() {
    }

}