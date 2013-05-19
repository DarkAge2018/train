import java.sql.*;

public class SqlAction {
	public SqlAction() {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			String url = "jdbc:mysql://localhost:3306/train";
			String user = "root";
			String pass = "admin";
			conn = DriverManager.getConnection(url, user, pass);
			stmt = conn.createStatement();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public ResultSet SearchAction(String start, String end, String date) {
		try {
			sql = "select trainid.*,ticket.* from trainid,ticket "
					+ "where trainid.train_id=ticket.train_id and trainid.start_station='"
					+ start + "' and trainid.dest_station='" + end + "' "
					+ "and ticket.start_date='" + date + "'";
			rs = stmt.executeQuery(sql);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return rs;
	}

	public void BookAction(String id, String start, String dest, String price,
			String stime, String date, String name, int type) {
		try {
			if (type == 1) {
				sql = "insert into book values ('0','" + id + "','" + start
						+ "','" + dest + "','" + price + "','Ó²×ù','" + stime
						+ "','" + date + "','" + name + "')";
			} else if (type == 2) {
				sql = "insert into book values ('0','" + id + "','" + start
						+ "','" + dest + "','" + price + "','ÎÔÆÌ','" + stime
						+ "','" + date + "','" + name + "')";
			}

			stmt.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void TicketAction(String id, String date, int type) {
		try {
			if (type == 1) {
				sql = "update ticket set seat_num=seat_num-1 where train_id='"
						+ id + "' and start_date='" + date + "'";

			} else if (type == 2) {
				sql = "update ticket set bed_num=bed_num-1 where train_id='"
						+ id + "' and start_date='" + date + "'";

			}
			stmt.executeUpdate(sql);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public ResultSet SearchBookAction(String name) {
		String sql = "select * from book where name='" + name + "'";
		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	private String sql;
}
