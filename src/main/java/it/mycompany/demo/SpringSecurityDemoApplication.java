package it.mycompany.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.util.Assert;

@SpringBootApplication
public class SpringSecurityDemoApplication implements CommandLineRunner {
	
	Logger logger = LogManager.getLogger(getClass());

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		logger.info("Starting...");
		
		// Create an encoder with strength 16
		// SLOOOOW
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
		String result = encoder.encode("myPassword");
		logger.info(result);
		Assert.isTrue(encoder.matches("myPassword", result));
		
		// EVEN SLOOOOOWER
		// Create an encoder with all the defaults
		Pbkdf2PasswordEncoder encoder2 = new Pbkdf2PasswordEncoder();
		String result2 = encoder2.encode("myPassword");
		logger.info(result2);
		Assert.isTrue(encoder2.matches("myPassword", result2));
		
		BytesKeyGenerator generator = KeyGenerators.secureRandom();
		// KeyGenerators.secureRandom(16);
		// KeyGenerators.shared(16);
		// KeyGenerators.string();
		byte[] key = generator.generateKey();
		logger.info(key);
		
	}

}
