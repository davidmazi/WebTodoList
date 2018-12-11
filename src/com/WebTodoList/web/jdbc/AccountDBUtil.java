package com.WebTodoList.web.jdbc;

import java.sql.*;
import java.util.*;

import javax.sql.DataSource;

public class AccountDBUtil {

	private DataSource dataSource;

	public AccountDBUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}

	public List<Account> getAccounts() throws Exception {
		List<Account> accounts = new ArrayList<Account>();
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		try {
			myConn = dataSource.getConnection();
			myStmt = myConn.createStatement();
			String sql = "select * from account";
			myRs = myStmt.executeQuery(sql);
			while (myRs.next()) {
				int id = myRs.getInt("idaccount");
				String username = myRs.getString("username");
				String password = myRs.getString("password");
				String role = myRs.getString("role");
				Account tempAccount = new Account(id, username, password, role);
				accounts.add(tempAccount);
			}
			return accounts;
		} finally {
			close(myConn, myStmt, myRs);
		}
	}

	public int checkRole(String user, String pass) throws Exception {
		int status = -1;
		String role = "";
		Connection myConn = null;
		PreparedStatement myPStmt = null;
		ResultSet myRs = null;
		try {
			myConn = dataSource.getConnection();
			myPStmt = myConn.prepareStatement("select role from account where username=? and password=?");
			myPStmt.setString(1, user);
			myPStmt.setString(2, pass);
			myRs = myPStmt.executeQuery();
			while (myRs.next()) {
				role = myRs.getString("role");
			}
			switch (role) {
			case "Student":
				status = 0;
				break;
			case "Prof":
				status = 1;
				break;
			default:
				status = -1;
				break;
			}
		} finally {
			close(myConn, myPStmt, myRs);
		}
		return status;
	}

	public void addAccount(Account account) {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try {
			myConn = dataSource.getConnection();
			String sql = "insert into account (username, password, role) values (?, ?, ?)";
			myStmt = myConn.prepareStatement(sql);
			String username = account.getUsername();
			String password = account.getPassword();
			String role = account.getRole();
			myStmt.setString(1, username);
			myStmt.setString(2, password);
			myStmt.setString(3, role);
			myStmt.execute();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			close(myConn, myStmt, myRs);
		}
	}

	public Account fetchAccount(int id) {
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		Account account = null;
		try {
			myConn = dataSource.getConnection();
			myStmt = myConn.createStatement();
			String sql = "select * from account where idaccount=" + id;
			myRs = myStmt.executeQuery(sql);
			while (myRs.next()) {
				String username = myRs.getString("username");
				String password = myRs.getString("password");
				String role = myRs.getString("role");
				account = new Account(id, username, password, role);
			}
			return account;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			close(myConn, myStmt, myRs);
		}
	}

	public void updateAccount(Account account) {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
			myConn = dataSource.getConnection();
			String sql = "update account set username=?, password=?,role=? where idaccount=?";
			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, account.getUsername());
			myStmt.setString(2, account.getPassword());
			myStmt.setString(3, account.getRole());
			myStmt.setInt(4, account.getId());
			myStmt.execute();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			close(myConn, myStmt, null);
		}
	}

	public void deleteAccount(int id) {
		Connection myConn = null;
		Statement myStmt = null;
		try {
			myConn = dataSource.getConnection();
			myStmt = myConn.createStatement();
			String sql = "delete from account where idaccount=" + id;
			myStmt.execute(sql);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			close(myConn, myStmt, null);
		}
	}

	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		try {
			if (myStmt != null)
				myStmt.close();
			if (myRs != null)
				myRs.close();
			if (myConn != null)
				myConn.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
