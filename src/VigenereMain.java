public class VigenereMain {

    public static void main(String[] args) {
        VigenereCypher cypher = new VigenereCypher();
        String plainText = "Ola mundo!";

        System.out.println("Chave: " + KEY);
        System.out.println("Texto puro: " + plainText);

        String encrypted = cypher.encrypt(plainText, KEY);
        System.out.println("Encriptado: " + encrypted);

        String decrypted = cypher.decrypt(encrypted, KEY);
        System.out.println("Desencriptado: " + decrypted);
    }

    public static String KEY = "MinhaChave";

}
