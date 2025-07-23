import java.util.concurrent.ThreadLocalRandom;

// javac App.java
// jar --create --file app.jar App.class
// java -cp app.jar App
// set PATH=d:\DEVEL\stage\opt\graalvm-jdk-24.0.2\bin;%PATH%
// native-image -cp app.jar App
// spring-boot:build-image -Pnative
public class App {

	public static void main(String[] args) {
		ThreadLocalRandom.current()
		                 .ints(1,60)
		                 .distinct()
		                 .limit(6)
		                 .sorted()
		                 .forEach(System.out::println);
	}

}
