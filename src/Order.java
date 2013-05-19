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
			System.out.println("�Բ�������س�����Ϣ��");
			rs.beforeFirst();
			return rs;
		}

		rs.beforeFirst();
		System.out.print("���" + "\t");
		System.out.print("����" + "\t");
		System.out.print("ʼ��վ" + "\t");
		System.out.print("�յ�վ" + "\t");
		System.out.print("����ʱ��" + "\t");
		System.out.print("����ʱ��" + "\t");
		System.out.print("��ʱ" + "\t");
		System.out.print("Ӳ���۸�" + "\t");
		System.out.print("Ӳ�Լ۸�" + "\t");
		System.out.print("����" + "\t");
		System.out.print("Ӳ����Ʊ" + "\t");
		System.out.print("������Ʊ" + "\t" + "\n");
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
			System.out.println("�������������ε���ţ�");
			str = bfr.readLine();

			System.out.println("������������Ʊ�����ͣ���Ӳ����1�����̣�2����");
			type = bfr.readLine();

			System.out.println("�����붩Ʊ��������");
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
						System.out.println("�Բ���Ӳ��Ʊ��������");
					} else {
						sa.TicketAction(id, date, type2);
						sa.BookAction(id, start, dest, sprice, stime, date,
								name, type2);
						System.out.println(name + "��Ʊ�ɹ���");
					}

				} else if (type2 == 2) {
					if (bedNum <= 0) {
						System.out.println("�Բ�������Ʊ��������");
					} else {
						sa.TicketAction(id, date, type2);
						sa.BookAction(id, start, dest, bprice, stime, date,
								name, type2);
						System.out.println(name + "��Ʊ�ɹ���");
					}

				} else {
					System.out.println("�Բ���û�и�ϯλ");
				}
			} else {
				System.out.println("�Բ��𣬳������ѡ�����");
			}

		} catch (Exception e) {
			System.out.println("����ĳ�����Ż�Ʊ��������");
			return 0;
		}
		return 0;

	}

	public static int SearchBook(String name) {
		SqlAction sa = new SqlAction();
		ResultSet rs = sa.SearchBookAction(name);

		try {
			if (!rs.next()) {
				System.out.println("�Բ�������ض�Ʊ����Ϣ��");
				return 0;
			}

			rs.beforeFirst();

			System.out.print("������" + "\t");
			System.out.print("��Ʊ��" + "\t");
			System.out.print("����" + "\t");
			System.out.print("ʼ��վ" + "\t");
			System.out.print("����ʱ��" + "\t");
			System.out.print("��������" + "\t");
			System.out.print("����" + "\t");
			System.out.print("�۸�" + "\t\n");

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
