package tech.flapweb.apps.rest.beans;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JWTVerifierProvider {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(JWTVerifierProvider.class);
    private static final String PROPERTIES_RESOURCE = "app.properties";
    
    private JWTVerifier verifier;
    
    public JWTVerifierProvider(){
        LOGGER.info("KEYPROVIDER CONSTRUCTOR");
        setupVerifier();
    }
    
    public JWTVerifier getVerifier(){
        return this.verifier;
    }
    
    private void setupVerifier(){
        try {
            Properties properties = new Properties();
            File propsFile = new File(PROPERTIES_RESOURCE);
            if(!propsFile.exists()){
                String path = System.getProperty("jetty.base") + "/resources/" + PROPERTIES_RESOURCE;
                LOGGER.info("from file {}", path);
                propsFile = (new File(path));
            }
            properties.load(new FileInputStream(propsFile));
        
            byte[] publicKeyBytes = Files.readAllBytes(Paths.get(properties.getProperty("ssl.pub.location")));
            X509EncodedKeySpec publicSpec = new X509EncodedKeySpec(publicKeyBytes);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            
            RSAPublicKey key = (RSAPublicKey) kf.generatePublic(publicSpec);
            Algorithm algorithm = Algorithm.RSA256(key, null);
            
            this.verifier = JWT.require(algorithm)
                .withIssuer("flapweb_auth")
                .build(); 
            
        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException ex) {
            LOGGER.error("Cannot read public key",ex);
            //throw ex; 
            /** @TODO handle the error */
        }
    }
    
    
}
