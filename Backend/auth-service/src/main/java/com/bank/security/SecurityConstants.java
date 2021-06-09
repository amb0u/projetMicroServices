package com.bank.security;

public class SecurityConstants {
    public static final String SECRET = "backend_team";
    public static final long EXPIRATION_TIME= 864_000; //10 days
    public static final String TOKEN_PREFIX ="Bearer ";
    public static final String HEADER_STRING="Authorization";
    // public static RSAPublicKey publicKey;
    // public static RSAPrivateKey privateKey;




   /* public SecurityConstants() throws NoSuchAlgorithmException {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(2048);
        KeyPair kp = kpg.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) kp.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) kp.getPrivate();
    }*/
}
