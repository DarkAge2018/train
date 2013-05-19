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
				.println("——————————————————————————————————————————————————");
		while (str.equalsIgnoreCase(new String("y"))) {
			System.out.println("1)订票");
			System.out.println("2)查询订单");
			System.out.println("3)退出");
			System.out.println("请输入相应的数字：");
			flag = bfr.readLine();
			if (flag.equals(new String("1"))) {
				System.out.println("欢迎进入订票系统");
				System.out.println("请输入起始站：");
				String start = "";
				start = bfr.readLine();
				System.out.println("请输入终点站：");
				String end = "";
				end = bfr.readLine();
				System.out.println("请输入订票日期（例如4月9号，0409）：");
				String date = "";
				date = bfr.readLine();
				rs = Order.Search(start, end, date);
				boolean flag2 = rs.next();
				if (flag2) {
					Order.book(rs);
				}

			} else if (flag.equals(new String("2"))) {
				System.out.println("请输入订票人姓名：");
				String name = bfr.readLine();
				Order.SearchBook(name);
			} else if (flag.equals(new String("3"))) {
				break;
			} else {
				System.out.println("对不起，你的输入有误");
			}
			System.out.println("是否继续？(输入Y继续，任意键退出)");
			str = bfr.readLine();
		}
		System.out.println("谢谢使用本系统！");
	}
}
