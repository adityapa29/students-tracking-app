package com.aditya.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class StudentDbUtil {

	private DataSource dataSource;

	public StudentDbUtil(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<Student> getStudent() throws SQLException {
		List<Student> students = new ArrayList<>();

		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			con = dataSource.getConnection();
			String sql = "SELECT * FROM students ORDER BY last_name";
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");
				Student tempStudent = new Student(id, firstName, lastName, email);
				students.add(tempStudent);
			}

		} finally {
			close(con, stmt, rs);
		}

		return students;
	}

	private void close(Connection con, Statement stmt, ResultSet rs) {

		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void addStudent(Student theStudent) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;

		try {
			con = dataSource.getConnection();
			String sql = "INSERT INTO students (first_name,last_name,email) VALUES (?,?,?)";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, theStudent.getFirstName());
			stmt.setString(2, theStudent.getLastName());
			stmt.setString(3, theStudent.getEmail());

			stmt.execute();
		} finally {
			close(con, stmt, null);
		}
	}

	public Student getStudent(String theStudentId) throws Exception {
		Student theStudent = null;
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int studentId;

		try {
			studentId = Integer.parseInt(theStudentId);
			con = dataSource.getConnection();
			String sql = "SELECT * FROM students WHERE id=?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, studentId);
			rs = stmt.executeQuery();
			if (rs.next()) {
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");

				theStudent = new Student(studentId, firstName, lastName, email);
			} else {
				throw new Exception("Could not find student id: " + studentId);
			}
		} finally {
			close(con, stmt, rs);
		}

		return theStudent;
	}

	public void updateStudent(Student theStudent) throws Exception {

		Connection con = null;
		PreparedStatement stmt = null;
		con = dataSource.getConnection();
		try {
			String sql = "UPDATE students SET first_name=?, last_name=?, email=? WHERE id=?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, theStudent.getFirstName());
			stmt.setString(2, theStudent.getLastName());
			stmt.setString(3, theStudent.getEmail());
			stmt.setInt(4, theStudent.getId());
			stmt.execute();
		} finally {
			close(con, stmt, null);
		}

	}

	public void deleteStudent(String theStudentId) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			int studentId = Integer.parseInt(theStudentId);
			con = dataSource.getConnection();
			String sql = "DELETE FROM students WHERE id=?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, studentId);
			stmt.execute();
		} finally {
			close(con,stmt,null);
		}
	}

}
