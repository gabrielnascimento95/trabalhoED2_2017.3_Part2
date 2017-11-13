package trabalhoii;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;
import splaytree.SplayTree;

/**
 * @author Rian Alves
 */
public class TrabalhoII {

    public static void main(String[] args) throws IOException {

        ArrayList<Tuite> lista = new ArrayList<>();
        Validacao validacao = new Validacao();
        String linha = null;
        String linha2 = null;

        try {
            String endereco = "C:\\Users\\Gabriel\\Downloads\\cargaMenor.txt";
            BufferedReader br = new BufferedReader(new FileReader(endereco));
            while ((linha = br.readLine()) != null) {
                String[] cod = linha.split("	");

                if (cod.length == 4 && validacao.eValido(cod)) {
                    Tuite e = new Tuite();
                    e.setUserID(cod[0]);
                    e.setTweetID(cod[1]);
                    e.setTweet(cod[2]);
                    e.setDate(cod[3]);
                    lista.add(e);
                    

                } else {
                    System.out.println("Formato Invalido: " + linha);

                }

            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        //ArvoreAVL.ArvoreAvl avl = new ArvoreAVL.ArvoreAvl();
        //Rubro_Negra.Arvore rb = new Rubro_Negra.Arvore();
        SplayTree sp = new SplayTree();
        BuscaTuite busca = new BuscaTuite(sp, lista);
        
        for (int i = 0; i < lista.size(); i++) {
            System.out.println("Linha " + i + " \n" + lista.get(i).imprime()+"\n");
            sp.insereElemento(lista.get(i).getTweetID());
            //rb.adiciona(lista.get(i).getTweetID());
        }
        
//------------------------------------------------------------------------------------------------------------------
//------------------------------------------------------PARTE 2-----------------------------------------------------
//------------------------------------------------------------------------------------------------------------------

        if(!sp.ehVazia()){
            Scanner entrada = new Scanner(System.in);
            int aux = -1;
            do{
            System.out.println("Escolha a forma de busca: 0 p/ sair");
            System.out.println("\t 1 - Busca por userID");
            System.out.println("\t 2 - Busca por tweetID");
            System.out.println("\t 3 - Busca por tweet");
            System.out.println("\t 4 - Busca por data");
            System.out.print("Digite a opção desejada: ");
            aux = entrada.nextInt();
            switch(aux){
                case 1:
                    String usID = "";
                    System.out.print("Digite o userID a ser buscado: ");
                    usID = entrada.next();
                    System.out.println("\n" + busca.procuraElementoUserID(lista, usID) + "\n");
                    break;
                case 2:
                    BigInteger ttID;
                    System.out.print("Digite o tweetID a ser buscado: ");
                    ttID = entrada.nextBigInteger();
                    System.out.println("\n" + sp.procuraElementoTweetID(ttID) + "\n");
                    break;
                case 3:
                    String twt = "";
                    System.out.print("Digite o tweet a ser buscado: ");
                    twt = entrada.next();
                    System.out.println("\n" + busca.procuraElementoTweet(lista, twt) + "\n");
                    break;
                case 4:
                    String dataTT = "";
                    System.out.print("Digite a data a ser buscado: ");
                    dataTT = entrada.next();
                    System.out.println("\n" + busca.procuraElementoUserID(lista, dataTT) + "\n");
                    break;
                default:
                    if(aux == 0){
                        System.out.println("Saindo....");
                    }else{
                        System.out.println("Opção não reconhecida.");
                    }
            }
        }while(aux != 0);
      } 
   }
}
