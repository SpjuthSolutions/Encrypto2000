import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.KeyStore;

public class KeyGen {

    public void genKey() throws Exception {
        keyGen();
    }

    private void keyGen() throws Exception {


        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());

        // get user password and file input stream
        char[] password = "password".toCharArray();

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

        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256);
        SecretKey secKey = keyGenerator.generateKey();

        KeyStore.SecretKeyEntry skEntry = new KeyStore.SecretKeyEntry(secKey);
        ks.setEntry("secKey",skEntry,protParam);

        System.out.println("keystore entry i keygen: " + ks.containsAlias("secKey"));

        java.io.FileOutputStream fos = null;
        try {
            fos = new java.io.FileOutputStream("keyStore.jks");
            ks.store(fos, password);
        } finally {
            if (fos != null) {
                fos.close();
            }
        }

    }
}
