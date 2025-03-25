package com.snhu.sslserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


@SpringBootApplication
public class SslServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SslServerApplication.class, args);
	}

}

//Added route to enable check sum return of static data example:  String data = "Hello World Check Sum!";
@RestController
class SslServerController{  
//Added a hash function to return the checksum value for the data string that contains your name.    
    @RequestMapping("/hash")
    public String myHash(){
    	String data = "Nii Amatey Tagoe";
    	String hash = generateHash(data);
        String cipherAlgo = "SHA-256";
        return "<p>Data: " + data + "</p><p>ChecksumValue: " + hash + "</p><p>Cipher Algorithm: " + cipherAlgo + "</p>";
    }

    private String generateHash(String data) {
        try {
            // Created a MessageDigest instance for SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            // Applied the hash function to the data
            byte[] hashBytes = digest.digest(data.getBytes());
            return bytesToHex(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            return "Algorithm not supported";
        }
    }
    //converting byte array into a hex string
    private String bytesToHex(byte[] hashBytes) {
        StringBuilder hexString = new StringBuilder(2 * hashBytes.length);
    for (int i = 0; i < hashBytes.length; i++) {
        String hex = Integer.toHexString(0xff & hashBytes[i]);
        if (hex.length() == 1) {
            hexString.append('0');
        }
        hexString.append(hex);
    }
    return hexString.toString();
}

}
