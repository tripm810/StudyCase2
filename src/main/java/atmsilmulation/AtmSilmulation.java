package atmsilmulation;


import atmsilmulation.repository.AccountRepository;
import atmsilmulation.repository.HistoryRepository;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;

@SpringBootApplication
public class AtmSilmulation extends SpringBootServletInitializer {

	@Autowired
	AccountRepository accountRepository;

	@Autowired
    HistoryRepository historyRepository;

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AtmSilmulation.class);
	}

	public static void main(String[] args) throws Exception{

		SpringApplication.run(AtmSilmulation.class, args);
		String mysqlUrl = "jdbc:mysql://localhost:1440/atm-simulator";
		Connection con = DriverManager.getConnection(mysqlUrl, "root", "1234");
		//Initialize the script runner
		ScriptRunner sr = new ScriptRunner(con);
		//Creating a reader object
		Reader reader = new BufferedReader(new FileReader("D:\\Case Study\\StudyCase2\\src\\main\\resources\\insert-data.sql"));
		//Running the script
		sr.runScript(reader);

	}
}