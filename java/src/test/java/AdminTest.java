package users;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class AdminTest {
    Connection TestConnection = null;
    Statement TestStatement = null;
    void makeConnetion() throws ClassNotFoundException, SQLException {
        String TestURL = "jdbc:postgresql://localhost:5433/";
        String TestUserName = "postgres";
        String TestPassword = "dbms";
        Class.forName("org.postgresql.Driver");
        TestConnection = DriverManager.getConnection(TestURL, TestUserName, TestPassword);
        TestStatement = TestConnection.createStatement();
    }

    @Test
    void createAccountStudent() throws SQLException, IOException, ClassNotFoundException {

        Admin admin = new Admin ("Admin","Admin","admin",2008);

        String r1 = admin.createAccount("TS1","student","iit",2020);
        assertEquals("User with ID = TS1 registered successfully.",r1);

        String r2 = admin.createAccount("TS1","student","iit",2020);
        assertEquals("User Already Registered.",r2);

        String s1 = "Drop table student_TS1;";
        String s2 = "Delete from users where userid = 'TS1';";

        makeConnetion();
        TestStatement.executeUpdate(s1);
        TestStatement.executeUpdate(s2);
        TestStatement.close();
        TestConnection.close();
    }

    @Test
    void createAccountFaculty() throws SQLException, IOException, ClassNotFoundException {

        Admin admin = new Admin ("Admin","Admin","admin",2008);

        String r1 = admin.createAccount("TF1","faculty","iit",2020);
        assertEquals("User with ID = TF1 registered successfully.",r1);

        String s1 = "Drop table faculty_TF1;";
        String s2 = "Delete from users where userid = 'TF1';";

        makeConnetion();
        TestStatement.executeUpdate(s1);
        TestStatement.executeUpdate(s2);

        TestStatement.close();
        TestConnection.close();

    }

    @Test
    void createAccountForInValidUser() throws SQLException, IOException, ClassNotFoundException {
        Admin admin = new Admin ("Admin","Admin","admin",2008);

        String r1 = admin.createAccount("TS1", "svd", "admin",2022);
        assertEquals("Not a Valid User Info.",r1);

    }

    @Test
    void addcourse() throws SQLException, IOException, ClassNotFoundException {
        Admin admin = new Admin ("Admin","Admin","admin",2008);
        String r1 = admin.addcourse("TC101","3-0-0","TC102","cs,mc","ee,me");
        assertEquals("Course Added Successfully",r1);
        String r2 = admin.addcourse("TC101","3-0-0","TC102","cs,mc","ee,me");
        assertEquals("The course already exist.",r2);
        admin.deletecourse("TC101");
    }

    @Test
    void deletecourse() throws SQLException, IOException, ClassNotFoundException {
        Admin admin = new Admin ("Admin","Admin","admin",2008);
        String r1 = admin.deletecourse("TC101");
        assertEquals("The Course does not exist. Please choose a valid course.",r1);
        admin.addcourse("TC101","3-0-0","TC102","cs,mc","ee,me");
        String r2 = admin.deletecourse("TC101");
        assertEquals("Course Deleted Successfully",r2);
    }
    @Test
    void editCourseCatalogForInvalidChoice() throws SQLException, IOException, ClassNotFoundException {
        Admin admin = new Admin ("Admin","Admin","admin",2008);
        String r1 = admin.editcourse("TC101","cs,ee");
        assertEquals("The Course does not exist. Please choose a valid course.",r1);
        admin.addcourse("TC101","3-0-0","TC102","cs,mc","ee,me");
        String r2 = admin.editcourse("TC101","cs,ep");
        assertEquals("Course Updated Successfully.",r2);
        admin.deletecourse("TC101");
    }
    @Test
    void changeAcademicYearSem() throws SQLException, ClassNotFoundException {
        Admin admin = new Admin ("Admin","Admin","admin",2008);
        String s1 = admin.changeAcademicYearSem(2022,1,0);
        assertEquals("Updated",s1);
        admin.changeAcademicYearSem(2022,2,1);
    }

    @Test
    void viewgradesstudent() throws SQLException, IOException, ClassNotFoundException {
        makeConnetion();
        Admin admin = new Admin ("Admin","Admin","admin",2008);
        admin.changeAcademicYearSem(2022,1,1);
        admin.addcourse("TC1","3-0-0","-","cs,mc","ee,me");
        admin.addcourse("TC2","4-0-0","-","cs,mc","ee,me");
        admin.addcourse("TC3","3-0-0","-","cs,mc","ee,me");
        admin.createAccount("TF1","faculty","iit",2020);
        admin.createAccount("TS1","student","iit",2022);

        Instructor instructor = new Instructor ("TF1","faculty","iit",2020);
        Student student = new Student("TS1","student","iit",2022);

        instructor.floatCourse("TC1",6f,"2022");
        student.courseRegister("TC1");
        instructor.floatCourse("TC2",6.5f,"2022");
        student.courseRegister("TC2");
        instructor.floatCourse("TC3",7f,"2022");
        student.courseRegister("TC3");

        String s5 = "Update student_TS1 set grade = 8 where courseid = 'TC1' and year = 2022 and sem = 1;";
        String s6 = "Update student_TS1 set grade = 7 where courseid = 'TC2' and year = 2022 and sem = 1;";
        TestStatement.executeUpdate(s5);
        TestStatement.executeUpdate(s6);

        Integer r1 = admin.viewGradesStudent("TS1");
        assertEquals(1,r1);

        Integer r2 = admin.viewGradesStudent("TS2");
        assertEquals(0,r2);

        student.courseWithdraw("TC3");
        student.courseWithdraw("TC2");
        student.courseWithdraw("TC1");
        instructor.deFloatCourse("TC1",2022,1);
        instructor.deFloatCourse("TC2",2022,1);
        instructor.deFloatCourse("TC3",2022,1);
        admin.deletecourse("TC1");
        admin.deletecourse("TC2");
        admin.deletecourse("TC3");
        String s1 = "Drop table faculty_TF1;";
        String s2 = "Delete from users where userid = 'TF1';";
        String s3 = "Drop table student_TS1;";
        String s4 = "Delete from users where userid = 'TS1';";

        TestStatement.executeUpdate(s1);
        TestStatement.executeUpdate(s2);
        TestStatement.executeUpdate(s3);
        TestStatement.executeUpdate(s4);

        TestStatement.close();
        TestConnection.close();
    }

    @Test
    void viewgradescourse() throws SQLException, ClassNotFoundException, IOException {
        makeConnetion();
        Admin admin = new Admin ("Admin","Admin","admin",2008);
        admin.changeAcademicYearSem(2022,1,1);
        admin.addcourse("TC1","3-0-0","-","cs,mc","ee,me");
        admin.createAccount("TF1","faculty","iit",2020);
        admin.createAccount("TS1","student","iit",2022);
        admin.createAccount("TS2","student","iit",2022);
        admin.createAccount("TS3","student","iit",2022);

        Instructor instructor = new Instructor ("TF1","faculty","iit",2020);
        Student student1 = new Student("TS1","student","iit",2022);
        Student student2 = new Student("TS2","student","iit",2022);
        Student student3 = new Student("TS3","student","iit",2022);

        instructor.floatCourse("TC1",6f,"2022");
        student1.courseRegister("TC1");
        student2.courseRegister("TC1");
        student3.courseRegister("TC1");

        String s5 = "Update TC1_2022_1 set grade = 8;";
        TestStatement.executeUpdate(s5);

        Integer r1 = admin.viewGradesCourse("TC1",2022,1);
        assertEquals(1,r1);
        Integer r2 = admin.viewGradesCourse("TC2",2022,1);
        assertEquals(0,r2);

        String s1 = "Drop table student_TS1, student_TS2, student_TS3;";
        TestStatement.executeUpdate(s1);
        instructor.deFloatCourse("TC1",2022,1);
        admin.deletecourse("TC1");
        String s2 = "Drop table faculty_TF1;";
        TestStatement.executeUpdate(s2);
        String s3 = "Delete from users where userid = 'TF1' or userid = 'TS1' or userid = 'TS2' or userid = 'TS3';";
        TestStatement.executeUpdate(s3);

        TestStatement.close();
        TestConnection.close();
    }

    @Test
    void coverCatalogandOfferings() throws SQLException, ClassNotFoundException, IOException {
        makeConnetion();
        Admin admin = new Admin ("Admin","Admin","admin",2008);
        admin.viewCourseOffering();
        admin.viewCourseCatalog();
        TestStatement.close();
        TestConnection.close();
    }
}