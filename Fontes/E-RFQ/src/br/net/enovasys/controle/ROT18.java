package br.net.enovasys.controle;


/**
 * Classe Responsavel pela logica da criptografia
 * 
 * @author Israel, Alex Lirio
 *
 */
public class ROT18 {
	
	// Listas que serao usadas para substituicao das letras
	final String list1 = "abcdefghijklmnopqr";
	final String list2 = "stuvwxyz0123456789";
	
	/**
	 * Metodo utilizado para criptografar a String
	 * 
	 * @param palavra
	 * @return
	 */
	public String criptografa(String palavra){
			
		// Seta todas as letras para caixa baixa, para evitar erro
		palavra = palavra.toLowerCase();
		
		// Converte o parametro recebido para array de char.
		char[] letras = palavra.toCharArray();
		
		for(int i = 0 ; i < letras.length ; i++){
			
			// Para cada letra do array, verifica-se sua posicao na primeira lista.
			// Se estiver presente nela (retorno != -1), a letra atual e substituida
			// pela letra da posicao correspondente na outra lista. Caso contrario,
			// faz a mesma coisa invertendo a ordem das listas.
			int posicao = list1.indexOf(letras[i]);
			
			if(posicao != -1){
				letras[i] = list2.charAt(posicao);
			} else {
				posicao = list2.indexOf(letras[i]);
				letras[i] = list1.charAt(posicao);
			}
		}
	
		// Concatena todas as letras em uma String e retorna o valor
		String valorCriptografado = new String(letras);
		
		if (valorCriptografado.length() == 13) {
			
			String s1 = valorCriptografado.substring(0, 3);
			String s2 = valorCriptografado.substring(3, 5);
			String s3 = valorCriptografado.substring(5, 8);
			String s4 = valorCriptografado.substring(8, 10);
			String s5 = valorCriptografado.substring(10, 13);

			valorCriptografado = s5+s2+s1+s4+s3;
			
		}

		return valorCriptografado;
		
	}
	
	/**
	 * Metodo utilizado para descriptografar a String
	 * 
	 * @param palavra
	 * @return
	 */
	public String descriptografa(String palavra){
		
		// Seta todas as letras para caixa baixa, para evitar erro
		palavra = palavra.toLowerCase();
		
		// Converte o parametro recebido para array de char.
		char[] letras = palavra.toCharArray();
		
		for(int i = 0 ; i < letras.length ; i++){
			
			// Para cada letra do array, verifica-se sua posicao na primeira lista.
			// Se estiver presente nela (retorno != -1), a letra atual e substituida
			// pela letra da posicao correspondente na outra lista. Caso contrario,
			// faz a mesma coisa invertendo a ordem das listas.
			int posicao = list1.indexOf(letras[i]);
			
			if(posicao != -1){
				letras[i] = list2.charAt(posicao);
			} else {
				posicao = list2.indexOf(letras[i]);
				letras[i] = list1.charAt(posicao);
			}
		}
	
		// Concatena todas as letras em uma String e retorna o valor
		String criptografado = new String(letras);
		
		if (criptografado.length() == 13) {
			
			String s1 = criptografado.substring(0, 3);
			String s2 = criptografado.substring(3, 5);
			String s3 = criptografado.substring(5, 8);
			String s4 = criptografado.substring(8, 10);
			String s5 = criptografado.substring(10, 13);

			criptografado = s3+s2+s5+s4+s1;
			
		}

		return criptografado;
		
	}
	
}
