import com.example.random.service.RandomDoubleService;
import com.example.random.service.RandomIntegerService;

module com.example.simulation {
	requires com.example.random;
	uses RandomIntegerService;
	uses RandomDoubleService;
}