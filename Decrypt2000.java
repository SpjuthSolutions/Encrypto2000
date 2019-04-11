import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.util.Base64;

public class Decrypt2000 {
    static Cipher cipher;

    private static void setCipher() throws Exception {
        cipher = Cipher.getInstance("AES");
    }

    private static void decryptFile() throws Exception {

    }

    private static void decrypto2000() throws Exception{

        System.out.println("decrypto2000");
        String encryptedText = new String(Files.readAllBytes(Paths.get("C:\\Users\\saeri2\\IdeaProjects\\Encrypto2000\\secret file.txt")));

        GetKey gk = new GetKey();

        SecretKey secretKey = gk.getKey();
        String decryptedText = (decrypt(encryptedText, secretKey));
        System.out.println("Avkrypterad text: " + decryptedText);

        String cleanDecrypt = decryptedText.substring(30);

        System.out.println(cleanDecrypt);
    }

    private static String decrypt(String encryptedText, SecretKey secretKey)
            throws Exception {

        Base64.Decoder decoder = Base64.getDecoder();
        System.out.println(decoder.toString());
        byte[] encryptedTextByte = decoder.decode(encryptedText);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedByte = cipher.doFinal(encryptedTextByte);
        String decryptedText = new String(decryptedByte);

        char[] pwdArray = "password".toCharArray();

        KeyStore ks = KeyStore.getInstance("JKS");
        ks.load(new FileInputStream("KeyStore.jks"), pwdArray);

        return decryptedText;
    }
}
