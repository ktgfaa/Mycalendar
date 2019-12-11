package calendarProgramFx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import cCalendar.Main;

public class Db_Login {

	private static String title = "";

	public static String getTitle() {
		return title;
	}

	public static String getDetail() {
		return detail;
	}

	public static int getYear() {
		return year;
	}

	public static int getMonth() {
		return month;
	}

	public static int getDay() {
		return day;
	}

	private static String detail = "";
	private static int year = 0;
	private static int month = 0;
	private static int day = 0;

	private static Connection conn = null;
	private static Statement stmt = null;
	private static ResultSet rs = null;
	private static PreparedStatement pstmt = null;
	private static String owner_id = "test";
	private static String owner_pw = "test";
	private static String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private static String loginstate = "";
	private static String user_id = "";
	private static String user_pw = "";

	public static String login(String getuserid, String getuserpw) { // 로그인 버튼 메소드
		// public static void main(String[] args) {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection(url, owner_id, owner_pw);

			stmt = conn.createStatement();

			rs = stmt.executeQuery("select * from member");

			while (rs.next()) {
				user_id = rs.getString("id");
				user_pw = rs.getString("pw");
				//System.out.println(user_id);
				if (user_id.equals(getuserid) && user_pw.equals(getuserpw)) {
					loginstate = "1";
					break;
				} else if (!user_id.equals(getuserid)) {
					loginstate = "2";
				} else if (!user_pw.equals(getuserpw)) {
					loginstate = "3";
				}
				//System.out.println(loginstate);
			}

		} catch (ClassNotFoundException e) {
			System.err.print("드라이버 로딩 실패");
		}

		catch (SQLException e) {
			System.err.print("해당하는 데이터를 찾지 못했습니다. ");
		}

		finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (stmt != null && !stmt.isClosed()) {
					stmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// return user_id;

		return loginstate;

	}

	public static String idCheck(String signupid) { // 아이디 중복 체크 버튼 메소드

		String idcheck = "";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection(url, owner_id, owner_pw);

			stmt = conn.createStatement();

			rs = stmt.executeQuery("select * from member");

			while (rs.next()) {
				String user_id = rs.getString("id");
				if (user_id.equals(signupid)) {
					idcheck = "이미 존재하는 아이디 입니다";
					break;
				} else {
					idcheck = "사용 가능한 아이디 입니다";
				}
			}
		} catch (ClassNotFoundException e) {
			System.err.print("드라이버 로딩 실패");
		}

		catch (SQLException e) {
			System.err.print("해당하는 데이터를 찾지 못했습니다. ");
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (stmt != null && !stmt.isClosed()) {
					stmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return idcheck;
	}

	public static String signup(String id, String pw, String birthday) { // 회원가입 메소드

		String signupcheck = "";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, owner_id, owner_pw);
			pstmt = conn.prepareStatement("insert into member(id,pw,birthday) values(?,?,?)");

			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.setString(3, birthday);

			int cnt = pstmt.executeUpdate();

			signupcheck = "회원가입 성공";

		} catch (ClassNotFoundException e) {
			System.err.print("드라이버 로딩 실패");
		} catch (SQLException e) {
			System.err.print("해당하는 데이터를 찾지 못했습니다. ");
		} finally {

			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return signupcheck;

	}

	public static String out_birthday_month(String id, String date) {
		// public static void main(String[] args) {

		String command4 = "SELECT EXTRACT(" + date + " FROM BIRTHDAY)FROM MEMBER where id like " + "\'" + id + "\'";

		String birthday = "";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection(url, owner_id, owner_pw);

			stmt = conn.createStatement();

			rs = stmt.executeQuery(command4);

			while (rs.next()) {

				birthday = rs.getString("EXTRACT(" + date + "FROMBIRTHDAY)");
			}
			// System.out.println(birthday);

		} catch (ClassNotFoundException e) {
			System.err.print("드라이버 로딩 실패");
		}

		catch (SQLException e) {
			System.err.print("해당하는 데이터를 찾지 못했습니다. ");
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (stmt != null && !stmt.isClosed()) {
					stmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return birthday;
	}

	public static String out_memo(String id,int year, int month, String day) {
		// public static void main(String[] args) {
		String num = "";
		title = "";
		detail = "";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection(url, owner_id, owner_pw);

			stmt = conn.createStatement();

			// rs = stmt.executeQuery("select * from calendar where id like " + "\'" + id +
			// "\'");
			rs = stmt.executeQuery("select title,detail from calendar where(id like " + "\'" + id + "\'"
					+ " and year like " + year + " and month like " + month + " and day like " + day + ")");

			while (rs.next()) {

				title = rs.getString("title");
				detail = rs.getString("detail");
				
			}
			if(!title.equals("") && !detail.equals("")) {
			num =  "1";
			} else {
				num = "2";
			}

		} catch (ClassNotFoundException e) {
			System.err.print("드라이버 로딩 실패");
		}

		catch (SQLException e) {
			System.err.print("해당하는 데이터를 찾지 못했습니다. ");
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (stmt != null && !stmt.isClosed()) {
					stmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return num;
	}

	public static void insert_memo(String id, String title, String detail, int year, int month, String day) {
		// public static void main(String[] args) {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection(url, owner_id, owner_pw);
			stmt = conn.createStatement();
			int count = 0;
			count = stmt.executeUpdate(
					"insert into calendar(id,title,detail,year,month,day) " + "values(" + "\'" + id + "\'" + "," + "\'"
							+ title + "\'" + "," + "\'" + detail + "\'" + "," + year + "," + month + "," + day + ")");

			/*
			pstmt.setString(1, id);
			pstmt.setString(2, title);
			pstmt.setString(3, detail);
			pstmt.setInt(4, year);
			pstmt.setInt(5, month);
			pstmt.setString(6, day);
	*/
			//System.out.println(count);
		} catch (ClassNotFoundException e) {
			System.err.print("드라이버 로딩 실패");
		}

		catch (SQLException e) {
			System.err.print("해당하는 데이터를 찾지 못했습니다. ");
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (stmt != null && !stmt.isClosed()) {
					stmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// return birthday;
	}
}
