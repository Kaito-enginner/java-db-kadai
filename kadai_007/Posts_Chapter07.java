package kadai_007;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Posts_Chapter07 {

	public static void main(String[] args) {
		
		Connection con = null;
		Statement statement = null;
		
		
		
		try {
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost/challenge_java",
					"root",
					"PASS"
			);
			
			System.out.println("接続に成功しました");
			
			 // SQLクエリを準備
			statement = con.createStatement();
			String sql1 = """
						 INSERT INTO posts (user_id, posted_at, post_content, likes) VALUES
						 (1003, '2023-02-08', '昨日の夜は徹夜でした…', 13),
						 (1002, '2023-02-08', 'お疲れ様です！', 12),
						 (1003, '2023-02-09', '今日も頑張ります！', 18),
						 (1001, '2023-02-09', '無理は禁物ですよ！', 17),
						 (1002, '2023-02-10', '明日から連休ですね！', 20);
					
						 """;
			
			
			// SQLクエリを実行（DBMSに送信）
			System.out.println("レコードの追加を実行します");
			int rowCnt = statement.executeUpdate(sql1);
			System.out.println(rowCnt + "件のレコードが追加されました");
			
			
			// SQLクエリを準備
			statement = con.createStatement();
			String sql2 = """
						 SELECT posted_at, post_content, likes 
						 FROM posts 
						 WHERE user_id = 1002;
						  """;
			
			
			// SQLクエリを実行（DBMSに送信）
			ResultSet result = statement.executeQuery(sql2);
			
			
			// SQLクエリの実行結果を抽出
			System.out.println("ユーザーIDが1002のレコードを検索しました");
			while(result.next()) {
				Date date  = result.getDate("posted_at");
				String content = result.getString("post_content");
				int like = result.getInt("likes");
				System.out.println(result.getRow() + "件目：投稿日時=" + date
						            + "／投稿内容=" + content + "／いいね数=" + like);
			
			}
		} catch(SQLException e) {
			System.out.println("エラー発生：" + e.getMessage());
		} finally {
			if(con != null) {
				try {con.close();} catch(SQLException ignore) {}
			}
			
			if(statement != null) {
				try {statement.close();} catch(SQLException ignore) {}
			}
		}
	}
}




