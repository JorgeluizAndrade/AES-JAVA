package aes;

import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class AES {
	static String IV = "AAAAAAAAAAAAAAAA";
	static String senha;
	static String chaveencriptacao = "0123456789abcdef";

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.printf("Informe sua senha :\n");

		
		senha = input.next();
		
		try {
			System.out.println("Texto puro: " + senha);

			byte[] textoencriptado = encrypt(senha, chaveencriptacao);

			System.out.println("Texto Encriptado: " + textoencriptado);

			for (byte b : textoencriptado) {
				System.out.print(b + " ");
			}
			System.out.println();

			String textodecriptado = decrypt(textoencriptado, chaveencriptacao);

			System.out.println("Texto Decriptado: " + textodecriptado);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static byte[] encrypt(String textoPuro, String chaveencriptacao) throws Exception {
		Cipher encripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
		SecretKeySpec key = new SecretKeySpec(chaveencriptacao.getBytes("UTF-8"), "AES");

		encripta.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));
		return encripta.doFinal(textoPuro.getBytes("UTF-8"));
	}

	public static String decrypt(byte[] textoencriptado, String chaveencriptacao) throws Exception {
		Cipher decripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
		SecretKeySpec key = new SecretKeySpec(chaveencriptacao.getBytes("UTF-8"), "AES");

		decripta.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));
		return new String(decripta.doFinal(textoencriptado), "UTF-8");
	}
}
