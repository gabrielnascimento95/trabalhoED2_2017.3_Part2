package ArvoreAVL;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;
import splaytree.SplayTree;
import trabalhoii.BuscaTuite;
import trabalhoii.Tuite;

/**
 *
 * @author Rian Alves
 */
public class ArvoreAvl {
    protected No raiz;

	public void inserir(BigInteger k) {
		No n = new No(k);
		inserirAVL(this.raiz, n);
	}

	public void inserirAVL(No aComparar, No aInserir) {

		if (aComparar == null) {
			this.raiz = aInserir;
                        System.out.println("Inserindo raíz :  " + aInserir.getChave());
                        

		} else {

			if (aInserir.getChave().compareTo(aComparar.getChave())<=-1) {

				if (aComparar.getEsquerda() == null) {
					aComparar.setEsquerda(aInserir);
					aInserir.setPai(aComparar);
					verificarBalanceamento(aComparar);
                                         System.out.println("Inserindo " + aInserir.getChave() + " a esquerda de " + aComparar.getChave());

				} else {
					inserirAVL(aComparar.getEsquerda(), aInserir);
                                         System.out.println("Inserindo " + aInserir.getChave() + " a esquerda de " + aComparar.getChave());
				}

			} else if (aComparar.getChave().compareTo(aInserir.getChave())<= -1) {

				if (aComparar.getDireita() == null) {
					aComparar.setDireita(aInserir);
					aInserir.setPai(aComparar);
					verificarBalanceamento(aComparar);
                                        System.out.println("Inserindo " + aInserir.getChave() + " a direita de " + aComparar.getChave());

				} else {
					inserirAVL(aComparar.getDireita(), aInserir);
                                        System.out.println("Inserindo " + aInserir.getChave() + " a direita de " + aComparar.getChave());
				}

			} else {
				//O nó já existe
			}
		}
                
               
	}

	public void verificarBalanceamento(No atual) {
		setBalanceamento(atual);
		int balanceamento = atual.getBalanceamento();

		if (balanceamento == -2) {

			if (altura(atual.getEsquerda().getEsquerda()) >= altura(atual.getEsquerda().getDireita())) {
				atual = rotacaoDireita(atual);

			} else {
				atual = duplaRotacaoEsquerdaDireita(atual);
			}

		} else if (balanceamento == 2) {

			if (altura(atual.getDireita().getDireita()) >= altura(atual.getDireita().getEsquerda())) {
				atual = rotacaoEsquerda(atual);

			} else {
				atual = duplaRotacaoDireitaEsquerda(atual);
			}
		}

		if (atual.getPai() != null) {
			verificarBalanceamento(atual.getPai());
		} else {
			this.raiz = atual;
		}
	}

	public void remover(BigInteger k) {
		removerAVL(this.raiz, k);
	}

	public void removerAVL(No atual, BigInteger k) {
		if (atual == null) {
			return;

		} else {

			if (atual.getChave().compareTo(k)>= 1) {
				removerAVL(atual.getEsquerda(), k);
                              

			} else if (atual.getChave().compareTo(k)<= -1) {
				removerAVL(atual.getDireita(), k);
                               

			} else if (atual.getChave().compareTo(k)== 0) {
				removerNoEncontrado(atual);
                                System.out.println("Removido : " + atual.getChave());
			}
		}
	}

	public void removerNoEncontrado(No aRemover) {
		No r;

		if (aRemover.getEsquerda() == null || aRemover.getDireita() == null) {

			if (aRemover.getPai() == null) {
				this.raiz = null;
				aRemover = null;
				return;
			}
			r = aRemover;

		} else {
			r = sucessor(aRemover);
			aRemover.setChave(r.getChave());
		}

		No p;
		if (r.getEsquerda() != null) {
			p = r.getEsquerda();
		} else {
			p = r.getDireita();
		}

		if (p != null) {
			p.setPai(r.getPai());
		}

		if (r.getPai() == null) {
			this.raiz = p;
		} else {
			if (r == r.getPai().getEsquerda()) {
				r.getPai().setEsquerda(p);
			} else {
				r.getPai().setDireita(p);
			}
			verificarBalanceamento(r.getPai());
		}
		r = null;
	}
        
        
	public No rotacaoEsquerda(No inicial) {

		No direita = inicial.getDireita();
		direita.setPai(inicial.getPai());

		inicial.setDireita(direita.getEsquerda());

		if (inicial.getDireita() != null) {
			inicial.getDireita().setPai(inicial);
		}

		direita.setEsquerda(inicial);
		inicial.setPai(direita);

		if (direita.getPai() != null) {

			if (direita.getPai().getDireita() == inicial) {
				direita.getPai().setDireita(direita);
			
			} else if (direita.getPai().getEsquerda() == inicial) {
				direita.getPai().setEsquerda(direita);
			}
		}

		setBalanceamento(inicial);
		setBalanceamento(direita);

		return direita;
	}

	public No rotacaoDireita(No inicial) {

		No esquerda = inicial.getEsquerda();
		esquerda.setPai(inicial.getPai());

		inicial.setEsquerda(esquerda.getDireita());

		if (inicial.getEsquerda() != null) {
			inicial.getEsquerda().setPai(inicial);
		}

		esquerda.setDireita(inicial);
		inicial.setPai(esquerda);

		if (esquerda.getPai() != null) {

			if (esquerda.getPai().getDireita() == inicial) {
				esquerda.getPai().setDireita(esquerda);
			
			} else if (esquerda.getPai().getEsquerda() == inicial) {
				esquerda.getPai().setEsquerda(esquerda);
			}
		}

		setBalanceamento(inicial);
		setBalanceamento(esquerda);

		return esquerda;
	}

	public No duplaRotacaoEsquerdaDireita(No inicial) {
		inicial.setEsquerda(rotacaoEsquerda(inicial.getEsquerda()));
		return rotacaoDireita(inicial);
	}

	public No duplaRotacaoDireitaEsquerda(No inicial) {
		inicial.setDireita(rotacaoDireita(inicial.getDireita()));
		return rotacaoEsquerda(inicial);
	}

	public No sucessor(No q) {
		if (q.getDireita() != null) {
			No r = q.getDireita();
			while (r.getEsquerda() != null) {
				r = r.getEsquerda();
			}
			return r;
		} else {
			No p = q.getPai();
			while (p != null && q == p.getDireita()) {
				q = p;
				p = q.getPai();
			}
			return p;
		}
	}

	private int altura(No atual) {
		if (atual == null) {
			return -1;
		}

		if (atual.getEsquerda() == null && atual.getDireita() == null) {
			return 0;
		
		} else if (atual.getEsquerda() == null) {
			return 1 + altura(atual.getDireita());
		
		} else if (atual.getDireita() == null) {
			return 1 + altura(atual.getEsquerda());
		
		} else {
			return 1 + Math.max(altura(atual.getEsquerda()), altura(atual.getDireita()));
		}
	}

	private void setBalanceamento(No no) {
		no.setBalanceamento(altura(no.getDireita()) - altura(no.getEsquerda()));
	}

	final protected ArrayList<No> inorder() {
		ArrayList<No> ret = new ArrayList<No>();
		inorder(raiz, ret);
                System.out.println("Nó: "+ret+"; \n");
		return ret;
                
	}

	final protected void inorder(No no, ArrayList<No> lista) {
		if (no == null) {
			return;
		}
		inorder(no.getEsquerda(), lista);
		lista.add(no);
		inorder(no.getDireita(), lista);
                
	}
        
        
              
        public void buscar(ArrayList<Tuite> lista)
        { 
            ArvoreAVL.ArvoreAvl avl = new ArvoreAVL.ArvoreAvl();
            BuscaTuite busca = new BuscaTuite(avl, lista);
        
      
         
        for (int i = 0; i < lista.size(); i++) {
            System.out.println("Linha " + i + " \n" + lista.get(i).imprime() + "\n");
            avl.inserir(lista.get(i).getTweetID());
        
        }


        if (avl != null) {
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
                        System.out.println("\n" + avl.procuraElementoTweetID(ttID) + "\n");
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
          if(chave.compareTo(a.getChave()) == -1)
              a = a.getDireita();
          else if(chave.compareTo(a.getChave()) == 1)
              a = a.getEsquerda();
          else
              return a;
      }
      return null;
    }
               
}
