package Rubro_Negra;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;
import trabalhoii.BuscaTuite;
import trabalhoii.Tuite;

/**
 *
 * @author Rian Alves
 */
public class RubroNegra {

    protected No raiz = null;
    protected No folhaNegra = new No();

    public void inserir(BigInteger k) {
        No n = new No(k, true, folhaNegra, folhaNegra);
        inserirRB(this.raiz, n);
    }

    public void inserirRB(No aComparar, No aInserir) {

        if (aComparar == null) {
            aInserir.setCor(false);
            this.raiz = aInserir;
            System.out.println("Inserindo a raíz: " + raiz.getValor() + " Cor : " + raiz.isCor());

        } else {

            if (aInserir.getValor().compareTo(aComparar.getValor()) < 0) {

                if (aComparar.getFesq() == folhaNegra) {
                    aComparar.setFesq(aInserir);
                    aInserir.setPai(aComparar);
                    verificarBalanceamento2(aInserir);
                    System.out.println("Inserindo a esquerda de : " + aComparar.getValor() + " o nó " + aInserir.getValor() + " Cor : " + aInserir.isCor());

                } else {
                    inserirRB(aComparar.getFesq(), aInserir);
                }

            } else if (aInserir.getValor().compareTo(aComparar.getValor()) > 0) {

                if (aComparar.getFdir() == folhaNegra) {
                    aComparar.setFdir(aInserir);
                    aInserir.setPai(aComparar);
                    verificarBalanceamento2(aInserir);
                    System.out.println("Inserindo a direita de : " + aComparar.getValor() + " o nó " + aInserir.getValor() + " Cor : " + aInserir.isCor());

                } else {
                    inserirRB(aComparar.getFdir(), aInserir);
                }

            } else {
                // O nÃ³ jÃ¡ existe
            }
        }
    }

    private void verificarBalanceamento2(No x) {
        while (x.getPai().isCor()) {
            if (x.getPai().getPai() == null) {
                System.out.println("Raíz, não precisa balancear");
            } else if (x.getPai().getPai() != null) {
                if (x.getPai().getPai().getFesq().isCor() == true) {
                    x.getPai().setCor(false);
                    x.getPai().getPai().getFesq().setCor(false);
                    System.out.println("Caso 1 tio a esquerda");
                    System.out.println("Mudança de cor do tio : " + x.getPai().getPai().getFesq().getValor() + " nova cor : " + x.getPai().getPai().getFesq().isCor());
                    System.out.println("Mudança de cor do pai : " + x.getPai().getValor() + " nova cor : " + x.getPai().isCor());
                } else if (x.getPai().getPai().getFesq().isCor() == false) {
                    rotacao_esq(x);
                    x.getPai().setCor(false);
                    x.getPai().getFesq().setCor(true);
                    System.out.println("Caso 2 - tio Preto a esquerda");
                } else {
                    x.getPai().setCor(false);
                    x.getPai().getPai().setCor(true);
                    this.rotacao_dir(x.getPai().getPai());
                }

                if (x.getPai().getPai().getFdir().isCor() == true) {
                    x.getPai().setCor(false);
                    x.getPai().getPai().getFdir().setCor(false);
                    System.out.println("Caso 1 - tio a direita");
                    System.out.println("Mudança de cor do tio : " + x.getPai().getPai().getFesq().getValor() + " nova cor : " + x.getPai().getPai().getFesq().isCor());
                    System.out.println("Mudança de cor do pai : " + x.getPai().getValor() + " nova cor : " + x.getPai().isCor());
                } else if (x.getPai().getPai().getFdir().isCor() == false) {
                    rotacao_dir(x);
                    x.getPai().setCor(false);
                    x.getPai().getFdir().setCor(true);
                    System.out.println("Caso 2 - tio Preto a direita");
                } else {
                    x.getPai().setCor(false);
                    x.getPai().getPai().setCor(true);
                    this.rotacao_esq(x.getPai().getPai());
                }

            }
        }

    }

    private void verificarBalanceamento(No aComparar) {
        No y;
        while (aComparar.getPai().isCor()) {
            if (aComparar.getPai() == aComparar.getPai().getFesq()) {
                y = aComparar.getPai().getPai().getFdir();
                if (y.isCor()) { // caso 1 (tio Ã© vermelho):
                    // muda a cor do pai e do tio para preto e dos avós para vermelho.
                    // Entao, sobe dois níveis na arvore.
                    aComparar.getPai().setCor(false);
                    y.setCor(false);
                    aComparar.getPai().getPai().setCor(true);
                    aComparar = aComparar.getPai().getPai();
                    System.out.println("Caso 1");
                } else { // Ou seja, tio Ã© preto
                    if (aComparar == aComparar.getPai().getFdir()) { // caso 2
                        aComparar = aComparar.getPai();
                        this.rotacao_esq(aComparar);
                        System.out.println("Caso 2");
                    }
                    // caso 3
                    aComparar.getPai().setCor(false);
                    aComparar.getPai().getPai().setCor(true);
                    this.rotacao_dir(aComparar.getPai().getPai());
                    System.out.println("Caso 3");
                }
            } else {
                y = aComparar.getPai().getPai().getFesq();
                if (y.isCor()) { // caso 1
                    y.setCor(false);
                    aComparar.getPai().setCor(false);
                    aComparar.getPai().getPai().setCor(true);
                    aComparar = aComparar.getPai().getPai();
                    System.out.println("Caso 1.1");
                } else {
                    if (aComparar == aComparar.getPai().getFesq()) { // caso 2
                        aComparar = aComparar.getPai();
                        this.rotacao_dir(aComparar);
                        System.out.println("Caso 2.1");
                    }
                    // caso 3
                    aComparar.getPai().setCor(false);
                    aComparar.getPai().getPai().setCor(true);
                    this.rotacao_esq(aComparar.getPai().getPai());
                    System.out.println("Caso 3.1");
                }
            }
        }
        this.raiz.setCor(false);
        System.out.println("Raíz Preta ");
    }

    // As rotações (rotacao_esq e rotacao_dir) servem para manter o balanceamento da árvore,
// especialmente porque a árvore preta e vermelha tem como caracteristica o balanceamento
    private void rotacao_esq(No x) {

        No y = x.getFdir();
        x.setFdir(y.getFesq());
        if (y.getFesq() != null) {
            y.getFesq().setPai(x);
        }
        y.setPai(x.getPai());
        if (x.getPai() == null) {
            this.raiz = y;
        } else if (x == x.getPai().getFesq()) {
            x.getPai().setFesq(y);
        } else {
            x.getPai().setFdir(y);
        }
        y.setFesq(x);
        x.setPai(y);
    }
// Ambas as rotações são idênticas, sendo trocados apenas os "dir" e "esq", referentes a direita e esquerda

    private void rotacao_dir(No x) {
        No y = x.getFesq();
        x.setFesq(y.getFdir());
        if (y.getFdir() != null) {
            y.getFdir().setPai(x);
        }
        y.setPai(x.getPai());
        if (x.getPai() == null) {
            this.raiz = y;
        } else if (x == x.getPai().getFesq()) {
            x.getPai().setFesq(y);
        } else {
            x.getPai().setFdir(y);
        }
        y.setFdir(x);
        x.setPai(y);
    }
    
    
     public void buscar(ArrayList<Tuite> lista)
        { 
            Rubro_Negra.RubroNegra rn = new Rubro_Negra.RubroNegra();
            BuscaTuite busca = new BuscaTuite(rn, lista);
        
      
         
        for (int i = 0; i < lista.size(); i++) {
            System.out.println("Linha " + i + " \n" + lista.get(i).imprime() + "\n");
            rn.inserir(lista.get(i).getTweetID());
        
        }


        if (rn != null) {
            Scanner entrada = new Scanner(System.in);
            int aux = -1;
            do {
                System.out.println("Escolha: 0 p/ sair");
                System.out.println("\t 1 - Busca por tweetID");
                System.out.print("Digite a opção desejada: ");
                aux = entrada.nextInt();
                switch (aux) {
                    case 1:
                        String usID = "";
                    case 2:
                        BigInteger ttID;
                        System.out.print("Digite o tweetID a ser buscado: ");
                        ttID = entrada.nextBigInteger();
                        System.out.println("\n" + rn.procuraElementoTweetID(ttID) + "\n");
                        break;
                  
                    default:
                        if (aux == 0) {
                            System.out.println("Saindo....");
                        } else {
                            System.out.println("Opção não reconhecida.");
                        }
                }
            } while (aux != 0);
        }}
        
         public String procuraElementoTweetID(BigInteger chave){
        if(procuraChave(chave) != null){
            return "Tweet Encontrado!";
        }else{
            return "Não encontrado";
        }
    }

    private No procuraChave(BigInteger chave) {
      No a = raiz;
      while(a != null){
          if(chave.compareTo(a.getValor()) <= -1)
              a = a.getFdir();
          else if(chave.compareTo(a.getValor()) >= 1)
              a = a.getFesq();
          else
              return a;
      }
      return null;
    }

}
