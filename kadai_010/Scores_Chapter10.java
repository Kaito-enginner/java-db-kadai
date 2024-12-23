package kadai_010;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Scores_Chapter10 {

	public static void main(String[] args) {
		
		Connection con = null;
		Statement statement= null;
		
		try {
			//データベースに接続
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost/challenge_java",
					"root",
					""
			);
			
			System.out.println("データベース接続成功");
			
			
			//SQLクエリを準備
			statement = con.createStatement();
			String sql = "UPDATE scores SET score_math = 95, score_english = 80 WHERE id = 5;";
			
			
			//SQLクエリを実行
			System.out.println("レコード更新を実行します");
			int num = statement.executeUpdate(sql);
			System.out.println(num + "件のレコードが更新されました");
			
			
			//SQLクエリを準備
			statement = con.createStatement();
			String sql2 = "SELECT * FROM scores ORDER BY score_math DESC, score_english DESC;";
					
					
			//SQLクエリを実行		
			System.out.println("数学・英語の点数が高い順に並べ替えました");		
			ResultSet result = statement.executeQuery( sql2 );
			
			
			//レコードを取得する
			while(result.next()) {
				int id = result.getInt("id");
				String name = result.getString("name");
				int mathScore = result.getInt("score_math");
				int englishScore = result.getInt("score_english");
				System.out.println(result.getRow() + "件目：生徒ID=" + id
						+ "／氏名=" + name + "／数学=" + mathScore + "／英語=" + englishScore);
			}
		}catch(SQLException e) {
			System.out.println("エラー発生：" + e.getMessage());
		}finally {
			if(con != null) {
				try {con.close();}catch(SQLException ignore) {}
			}
			
			if(statement != null) {
				try {statement.close();}catch(SQLException ignore) {}	
			}
		}
		

	}

}
