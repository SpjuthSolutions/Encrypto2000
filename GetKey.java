import javax.crypto.SecretKey;
import java.security.KeyStore;

public class GetKey {

    public static SecretKey getKey() throws Exception {

        SecretKey secretKey = fetchKey();
        return secretKey;
    }

    private static SecretKey fetchKey() throws Exception {
        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());

        char [] password = "password".toCharArray();

        java.io.FileInputStream fis = null;
        try {
            fis = new java.io.FileInputStream("keyStore.jks");
            ks.load(fis, password);
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
        KeyStore.ProtectionParameter protParam =
                new KeyStore.PasswordProtection(password);

        //get my secret key
        KeyStore.SecretKeyEntry skEntry = (KeyStore.SecretKeyEntry)
                ks.getEntry("secKey", protParam);
        SecretKey secretKey = skEntry.getSecretKey();

        java.io.FileOutputStream fos = null;
        try {
            fos = new java.io.FileOutputStream("keyStore.jks");
            ks.store(fos, password);
        } finally {
            if (fos != null) {
                fos.close();
            }
        }

        return secretKey;

    }
}
