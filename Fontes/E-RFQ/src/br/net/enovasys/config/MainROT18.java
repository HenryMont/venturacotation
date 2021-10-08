package br.net.enovasys.config;

import java.util.Date;

import br.net.enovasys.controle.ROT18;

/**
 * Classe usada testar a criptografia.
 * 
 * @author alexlirio
 * 
 */
@SuppressWarnings("unused")
public class MainROT18 {
	
	public static void main(String[] args) {
		
		ROT18 rot18 = new ROT18();	
		String desc = "1112233344555";
				
		System.out.println(rot18.criptografa(desc));
		System.out.println(rot18.descriptografa(rot18.criptografa(desc)));
		
//		// Usado para teste de troca de posicoes da String
//		String valorCriptografado = "1112233344555";
//		
//		String s1 = valorCriptografado.substring(0, 3);
//		String s2 = valorCriptografado.substring(3, 5);
//		String s3 = valorCriptografado.substring(5, 8);
//		String s4 = valorCriptografado.substring(8, 10);
//		String s5 = valorCriptografado.substring(10, 13);
//		
//		String desc = s1+s2+s3+s4+s5;
//		String crip = s5+s4+s2+s1+s3;
//		
//		System.out.println(desc);
//		System.out.println(crip);
		
	}

}
