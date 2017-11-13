package trabalhoii;

import ArvoreAVL.ArvoreAvl;
import BTree.ArvoreB;
import Rubro_Negra.RubroNegra;
import com.sun.xml.internal.fastinfoset.util.StringArray;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.ProcessBuilder.Redirect.to;
import java.math.BigInteger;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JFrame;
import splaytree.SplayTree;

/**
 * @author Rian Alves
 */
public class TrabalhoII {

    public static void main(String[] args) throws IOException {
         Tela janela = new Tela();
        janela.setSize(850, 600);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setVisible(true);
    

    }

    public static void escolha(int arvore) {

        ArrayList<Tuite> lista = new ArrayList<>();
        ArrayList<Tuite> lista2 = new ArrayList<>();
        Validacao validacao = new Validacao();
        String linha = null;
        String linha2 = null;
        int cont = 0;
        Timestamp hora;
        BigInteger num = null;

        try {
            //String endereco = "C:\\Users\\Rian Alves\\Desktop\\UFJF\\Estrutura de Dados II\\test_set_tweets.txt";
            String endereco = "C:\\Users\\Rian Alves\\Desktop\\cargamenor.txt";
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

        ArvoreB ab = new ArvoreB(10); // Árvore B de ordem 10
        ArvoreAVL.ArvoreAvl avl = new ArvoreAvl();
        Rubro_Negra.RubroNegra rn = new Rubro_Negra.RubroNegra();

        for (int i = 0; i < 10; i++) {
            lista2.add(lista.get(i + 1));
        }

        switch (arvore) {
            case 1:

                insercaoAVL(lista);
                avl.buscar(lista);
                parteDois(lista);
                break;
            case 2:
                insercaoAB(lista, ab);
                parteDois(lista);
                break;
            case 3:
                insercaoSplay(lista);
                parteDois(lista);
                break;
            case 4:
                insercaoRN(lista);
                parteDois(lista);
                break;
                   }
        

    }

    public static void insercaoAVL(ArrayList<Tuite> lista) {
        System.out.println("Hora Inicial ");
        ArvoreAVL.ArvoreAvl avl = new ArvoreAVL.ArvoreAvl();
        for (int i = 0; i < lista.size(); i++) {
            System.out.println("Linhas: " + lista.get(i).getTweet() + "\n");
            avl.inserir(lista.get(i).getTweetID());
        }

    }
    

    public static void insercaoAB(ArrayList<Tuite> lista, ArvoreB ab) {

        for (int i = 0; i < lista.size(); i++) {
            System.out.println("Linhas: " + lista.get(i).getTweet() + "\n");
            ab.insere(lista.get(i).getTweetID());
        }

    }

    public static void insercaoSplay(ArrayList<Tuite> lista) {
        SplayTree sp = new SplayTree();
        for (int i = 0; i < lista.size(); i++) {
            System.out.println("Linhas: " + lista.get(i).getTweet() + "\n");
            sp.insereElemento(lista.get(i).getTweetID());
        }

    }

    public static void insercaoRN(ArrayList<Tuite> lista) {
        Rubro_Negra.RubroNegra rb = new RubroNegra();
        for (int i = 0; i < lista.size(); i++) {
            System.out.println("Linhas: " + lista.get(i).getTweet() + "\n");
            rb.inserir(lista.get(i).getTweetID());
        }

    }

      
    public static void parteDois(ArrayList<Tuite> lista){
    //------------------------------------------------------------------------------------------------------------------
//------------------------------------------------------PARTE 2-----------------------------------------------------
//------------------------------------------------------------------------------------------------------------------
        SplayTree sp = new SplayTree();
        BuscaTuite busca = new BuscaTuite(sp, lista);
        
      
         
        for (int i = 0; i < lista.size(); i++) {
            System.out.println("Linha " + i + " \n" + lista.get(i).imprime() + "\n");
            sp.insereElemento(lista.get(i).getTweetID());
           
            //rb.adiciona(lista.get(i).getTweetID());
        }


        if (!sp.ehVazia()) {
            Scanner entrada = new Scanner(System.in);
            int aux = -1;
            do {
                System.out.println("Escolha a forma de busca: 0 p/ sair");
                System.out.println("\t 1 - Busca por userID");
                System.out.println("\t 2 - Busca por tweetID");
                System.out.println("\t 3 - Busca por tweet");
                System.out.println("\t 4 - Busca por data");
                System.out.print("Digite a opção desejada: ");
                aux = entrada.nextInt();
                switch (aux) {
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
                        if (aux == 0) {
                            System.out.println("Saindo....");
                        } else {
                            System.out.println("Opção não reconhecida.");
                        }
                }
            } while (aux != 0);
        }
    }

}
