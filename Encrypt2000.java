import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.util.Base64;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;

public class Encrypt2000{
    static Cipher cipher;
    static KeyStore ks;


    public void runCode() throws Exception {

        setCipher();
        encryptFile();


    }

    private static void setCipher() throws Exception {
        cipher = Cipher.getInstance("AES");
    }



    private static void encryptFile() throws Exception {

        File inFile = new File("test.txt");
        GetKey key2000 = new GetKey();
        SecretKey secretKey = key2000.getKey();

        encryptTheFile(inFile,secretKey);

    }

    private static void encryptImport() throws Exception{
        String testText = new String(Files.readAllBytes(Paths.get("test.txt")));


        String salt = getSaltString();

        GetKey key2000 = new GetKey();

        SecretKey secretKey = key2000.getKey();
        System.out.println(secretKey);



        String encryptImport = encrypt(salt + testText, secretKey);
        System.out.println("Importerad, krypterad och saltad text: " + encryptImport);


        PrintWriter writer = new PrintWriter("secret file.txt", "UTF-8");
        writer.println(encryptImport);
        writer.close();

       /* String decryptedText = (decrypt(encryptImport, secretKey));

        System.out.println("Avkrypterad text " + decryptedText);

        String cleanDecrypt = decryptedText.substring(30);

        System.out.println(cleanDecrypt);*/

    }

    private static String encryptTheFile(File inputFile, SecretKey secretKey) throws Exception {

        GetKey key2000 = new GetKey();
        SecretKey sk = key2000.getKey();
        Cipher ci = cipher;

        FileInputStream inputStream = new FileInputStream(inputFile);
        byte[] inputBytes = new byte[(int) inputFile.length()];
        inputStream.read(inputBytes);


        ci.init(Cipher.ENCRYPT_MODE, sk);
        byte[] outputBytes = cipher.doFinal(inputBytes);


        Path file = Paths.get("Encrypted-file.encrypted");
        Files.write(file,outputBytes);


        System.out.println(sk.getFormat());

        return null;


    }




    private static String encrypt(String plainText, SecretKey sKey) throws Exception {

        GetKey key2000 = new GetKey();

        byte[] plainTextByte = plainText.getBytes("UTF8"); //test att ha UTF8 i getbytes
        System.out.println(plainTextByte + " plaintextbytes");
        Cipher ci = cipher;
        SecretKey sk = key2000.getKey();
        ci.init(Cipher.ENCRYPT_MODE, sk);
        byte[] encryptedByte = ci.doFinal(plainTextByte);
        Base64.Encoder encoder = Base64.getEncoder();
        String encryptedText = encoder.encodeToString(encryptedByte);


        return encryptedText;
    }




    private static String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzëéäöóá1234567890!#¤%&/()=?`´¨^*-_.:;,§½@£$€{[]}";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 30) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();

        return saltStr;

    }

}

