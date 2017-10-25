/*
 * Classe referente à criptografia de senhas no padrão MD5
 */
package salaovirtual;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
 * Classe referente à criptografia de senhas no padrão MD5
 * 
 * @author Rafael Tavares
 */
public class Criptografar {
    private static MessageDigest md;

    /**
     * Método para criptografar a senha
     * @param senha
     * @return Senha criptografada (MD5)
     */
    public static String criptografarMD5(String senha){
        try {
            md = MessageDigest.getInstance("MD5");
            byte[] passBytes = senha.getBytes();
            md.reset();
            byte[] digested = md.digest(passBytes);
            StringBuilder sb = new StringBuilder();
            for(int i=0;i<digested.length;i++){
                sb.append(Integer.toHexString(0xff & digested[i]));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            //Logger.getLogger(CryptWithMD5.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
   }
}
