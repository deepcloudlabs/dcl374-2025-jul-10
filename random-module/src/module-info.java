import com.example.random.service.RandomDoubleService;
import com.example.random.service.RandomIntegerService;
import com.example.random.service.business.FastRandomDoubleService;
import com.example.random.service.business.FastRandomIntegerService;
import com.example.random.service.business.SecureRandomDoubleService;
import com.example.random.service.business.SecureRandomIntegerService;

module com.example.random {
	exports com.example.random.service;
	provides RandomIntegerService with FastRandomIntegerService, SecureRandomIntegerService;
	provides RandomDoubleService with FastRandomDoubleService, SecureRandomDoubleService;
	
}