import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class Index {
	public static void main(String[] args) throws IOException, SQLException {
		BufferedReader bfr = new BufferedReader(
				new InputStreamReader(System.in));
		String str = "y";
		String flag = "";

		ResultSet rs = null;
		System.out
				.println("����������������������������������������������������������������������������������������������������");
		while (str.equalsIgnoreCase(new String("y"))) {
			System.out.println("1)��Ʊ");
			System.out.println("2)��ѯ����");
			System.out.println("3)�˳�");
			System.out.println("��������Ӧ�����֣�");
			flag = bfr.readLine();
			if (flag.equals(new String("1"))) {
				System.out.println("��ӭ���붩Ʊϵͳ");
				System.out.println("��������ʼվ��");
				String start = "";
				start = bfr.readLine();
				System.out.println("�������յ�վ��");
				String end = "";
				end = bfr.readLine();
				System.out.println("�����붩Ʊ���ڣ�����4��9�ţ�0409����");
				String date = "";
				date = bfr.readLine();
				rs = Order.Search(start, end, date);
				boolean flag2 = rs.next();
				if (flag2) {
					Order.book(rs);
				}

			} else if (flag.equals(new String("2"))) {
				System.out.println("�����붩Ʊ��������");
				String name = bfr.readLine();
				Order.SearchBook(name);
			} else if (flag.equals(new String("3"))) {
				break;
			} else {
				System.out.println("�Բ��������������");
			}
			System.out.println("�Ƿ������(����Y������������˳�)");
			str = bfr.readLine();
		}
		System.out.println("ллʹ�ñ�ϵͳ��");
	}
}
