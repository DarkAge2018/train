import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class Order {

	public static ResultSet Search(String start, String end, String date)
			throws SQLException {
		ResultSet rs = null;
		int flag = 0;

		SqlAction sa = new SqlAction();
		rs = sa.SearchAction(start, end, date);

		if (!rs.next()) {
			System.out.println("对不起，无相关车次信息！");
			rs.beforeFirst();
			return rs;
		}

		rs.beforeFirst();
		System.out.print("序号" + "\t");
		System.out.print("车次" + "\t");
		System.out.print("始发站" + "\t");
		System.out.print("终点站" + "\t");
		System.out.print("开车时间" + "\t");
		System.out.print("到达时间" + "\t");
		System.out.print("历时" + "\t");
		System.out.print("硬座价格" + "\t");
		System.out.print("硬卧价格" + "\t");
		System.out.print("日期" + "\t");
		System.out.print("硬座余票" + "\t");
		System.out.print("卧铺余票" + "\t" + "\n");
		while (rs.next()) {
			flag++;
			System.out.print(flag + "\t");
			System.out.print(rs.getString("trainid.train_id") + "\t");
			System.out.print(rs.getString("trainid.start_station") + "\t");
			System.out.print(rs.getString("trainid.dest_station") + "\t");
			System.out.print(rs.getString("trainid.start_time") + "\t");
			System.out.print(rs.getString("trainid.end_time") + "\t");
			System.out.print(rs.getString("trainid.take_time") + "\t");
			System.out.print(rs.getString("trainid.seat_price") + "\t");
			System.out.print(rs.getString("trainid.bed_price") + "\t");
			System.out.print(rs.getString("ticket.start_date") + "\t");
			System.out.print(rs.getString("ticket.seat_num") + "\t");
			System.out.print(rs.getString("ticket.bed_num") + "\t" + "\n");
		}

		rs.beforeFirst();
		return rs;
	}

	public static int book(ResultSet rs) {
		String str, type;
		int seatNum, bedNum;
		SqlAction sa = new SqlAction();
		String id = "";
		String date = "";
		String start = "";
		String dest = "";
		String stime = "";
		String sprice = "";
		String bprice = "";
		String name = "";

		try {
			BufferedReader bfr = new BufferedReader(new InputStreamReader(
					System.in));
			System.out.println("请输入欲订车次的序号：");
			str = bfr.readLine();

			System.out.println("请输入欲订车票的类型：（硬座（1）卧铺（2））");
			type = bfr.readLine();

			System.out.println("请输入订票人姓名：");
			name = bfr.readLine();
			int value = Integer.parseInt(str);
			int type2 = Integer.parseInt(type);

			if (rs.absolute(value)) {

				seatNum = Integer.parseInt(rs.getString("ticket.seat_num"));
				bedNum = Integer.parseInt(rs.getString("ticket.bed_num"));
				id = rs.getString("trainid.train_id");
				date = rs.getString("ticket.start_date");
				start = rs.getString("trainid.start_station");
				dest = rs.getString("trainid.dest_station");
				stime = rs.getString("trainid.start_time");
				sprice = rs.getString("trainid.seat_price");
				bprice = rs.getString("trainid.bed_price");

				if (type2 == 1) {
					if (seatNum <= 0) {
						System.out.println("对不起，硬座票数量不足");
					} else {
						sa.TicketAction(id, date, type2);
						sa.BookAction(id, start, dest, sprice, stime, date,
								name, type2);
						System.out.println(name + "订票成功！");
					}

				} else if (type2 == 2) {
					if (bedNum <= 0) {
						System.out.println("对不起，卧铺票数量不足");
					} else {
						sa.TicketAction(id, date, type2);
						sa.BookAction(id, start, dest, bprice, stime, date,
								name, type2);
						System.out.println(name + "订票成功！");
					}

				} else {
					System.out.println("对不起，没有该席位");
				}
			} else {
				System.out.println("对不起，车次序号选择错误");
			}

		} catch (Exception e) {
			System.out.println("输入的车次序号或车票类型有误！");
			return 0;
		}
		return 0;

	}

	public static int SearchBook(String name) {
		SqlAction sa = new SqlAction();
		ResultSet rs = sa.SearchBookAction(name);

		try {
			if (!rs.next()) {
				System.out.println("对不起，无相关订票人信息！");
				return 0;
			}

			rs.beforeFirst();

			System.out.print("订单号" + "\t");
			System.out.print("订票人" + "\t");
			System.out.print("车次" + "\t");
			System.out.print("始发站" + "\t");
			System.out.print("开车时间" + "\t");
			System.out.print("开车日期" + "\t");
			System.out.print("类型" + "\t");
			System.out.print("价格" + "\t\n");

			while (rs.next()) {

				System.out.print(rs.getString("order_id") + "\t");
				System.out.print(rs.getString("name") + "\t");
				System.out.print(rs.getString("train_id") + "\t");
				System.out.print(rs.getString("start_station") + "\t");
				System.out.print(rs.getString("start_time") + "\t");
				System.out.print(rs.getString("strat_date") + "\t");
				System.out.print(rs.getString("type") + "\t");
				System.out.print(rs.getString("price") + "\t" + "\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
