import br.com.senior.research.experiments.Experiment;
import br.com.senior.research.persistence.mongodb.MongoTaxiTrajectoryDAO;
import br.com.senior.research.persistence.mysql.MySQLTaxiTrajectoryDAO;


public class Tester {

	public static void main(String[] args) {
		
		
		Experiment eMySQL = new Experiment(new MySQLTaxiTrajectoryDAO());
		eMySQL.write();
		eMySQL.read();
		
		Experiment eMongo = new Experiment(new MongoTaxiTrajectoryDAO());
		eMongo.write();
		eMongo.read();
	}

}
