import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class EncryptFile {

    static Cipher cipher;

    public static void run() throws Exception {
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
}
