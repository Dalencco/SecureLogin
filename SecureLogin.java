import java.util.Scanner;
import java.io.BufferedReader; 
import java.io.FileNotFoundException; 
import java.io.FileReader; 
import java.math.BigInteger;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecureLogin {

    public static String SHA512(String hash) {
        try {
            MessageDigest msgDigest = MessageDigest.getInstance("SHA-512");

            byte[] hashDigest = msgDigest.digest(hash.getBytes());

            BigInteger inputDigestBigInt = new BigInteger(1, hashDigest);

            String hashtext = inputDigestBigInt.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }

        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void Creds(String user, String pass) throws FileNotFoundException, IOException {
        String cadena; 
        FileReader f = new FileReader("creds.txt"); 
        BufferedReader b = new BufferedReader(f); 
        while((cadena = b.readLine()) != null) { 
            String[] data = cadena.split("-");
            String username = data[0];
            String password = data[1];
            if (user.length() >= 10 && pass.length() >= 10) {
                System.out.print("Mucho Texto");
            } else {
                String userof = SHA512(user);
                String passof = SHA512(pass);
                if (userof.equals(username) && passof.equals(password)) {
                    System.out.print("¡Inicio de Sesion Correcto!");
                } else {
                    System.out.print("¡User/Password incorrecto!");
                }
            }
        } 
        b.close(); 
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        Scanner input = new Scanner(System.in);
        try {
            System.out.print("Ingrese el User: ");
            String user0 = input.nextLine();
            System.out.print("Ingrese la Pass: ");
            String pass0 = input.nextLine();
            Creds(user0, pass0);
        } finally {
            input.close();
        }
    }
}