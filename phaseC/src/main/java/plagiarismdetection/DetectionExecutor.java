package plagiarismdetection;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import edu.neu.comparison.Strategy;
import edu.neu.reports.PlagiarismRun;
import edu.neu.reports.ReportService;

public class DetectionExecutor {
	
	private final int THREAD_POOL_SIZE; // defaults to 2
	private ExecutorService executor;
	private static final Logger log = Logger.getLogger(DetectionExecutor.class);
	private static DetectionExecutor mInstance = null;
	private long checkCount = 0;
	

	private ReportService reportService;
	
	private DetectionExecutor(ReportService reportService, int threadPoolSize) {
		this.reportService = reportService;
		THREAD_POOL_SIZE = threadPoolSize;
		log.info("Starting executor with "+THREAD_POOL_SIZE+ " threads");
		this.executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
	}
	
	private DetectionExecutor(ReportService reportService) {
		this(reportService, 2);
	}
	
	public static DetectionExecutor getInstance(ReportService reportService) {
		if(mInstance == null) {
			mInstance = new DetectionExecutor(reportService);
		}
		return mInstance;
	}
	
	public static DetectionExecutor getInstance(ReportService reportService, int n) {
		if(mInstance == null) {
			mInstance = new DetectionExecutor(reportService, n);
		}
		return mInstance;
	}
	
	public boolean runPlagiarismCheck(PlagiarismRun plagRun, Strategy comparisonStrategy) {
		executor.submit(new PlagiarismChecker(plagRun, comparisonStrategy, reportService, getUniqueCheckID()));
		log.info("Submitted to executor : "+plagRun);
		return true;
	}
	
	private synchronized String getUniqueCheckID() {
		checkCount ++;
		return checkCount+"";
	}

}
