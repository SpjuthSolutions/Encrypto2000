import java.io.FileOutputStream;
import java.security.KeyStore;

public class CreateKeyStore {

    public void create() throws Exception{
        createKS();
        System.out.println("create keystore");
    }

    private static void createKS() throws Exception {
        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        char[] pwdArray = "password".toCharArray();
        ks.load(null, pwdArray); //initialize keystore;

        try (FileOutputStream fos = new FileOutputStream("KeyStore.jks")) {
            ks.store(fos, pwdArray);
        }

    }
}
