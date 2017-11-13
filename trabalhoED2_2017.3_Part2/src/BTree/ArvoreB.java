/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BTree;
import java.math.BigInteger;

/**
 *
 * @author gabriel
 */
public class ArvoreB {
    
    private No raiz;
    private int ordem;
    private int nElementos; //Contador para a quantidade de elementos na arvore B;

    public ArvoreB(int n) {
        this.raiz = new No(n);
        this.ordem = n;
        nElementos = 0;
    }

    public int getnElementos() {
        return nElementos;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }

    public int getOrdem() {
        return ordem;
    }

    public No getRaiz() {
        return raiz;
    }

    public void insere(BigInteger k) {

        if (BuscaChave(raiz, k) == null) {
            if (raiz.getN() == 0) {
                raiz.getChave().set(0, k);
                raiz.setN(raiz.getN() + 1);
                System.out.println("Inserção da chave :" + k + " no nó com " + raiz.getN()+ " chaves." );
            } else {
                No r = raiz;
                if (r.getN() == ordem - 1) {
                    No s = new No(ordem);
                    raiz = s;
                    s.setFolha(false);
                    s.setN(0);
                    s.getFilho().set(0, r);
                    divideNo(s, 0, r);
                    insereNoNaoCheio(s, k);
                    System.out.println("Divisão dos nós");

                } else {
                    insereNoNaoCheio(r, k);
                    System.out.println("Inserção em nó não cheio ");
                }
            }
            nElementos++;//incrementa o numero de elementos na arvore B
        }
    }

    public void divideNo(No pai, int i, No filho) {
        int t = (int) Math.floor((ordem - 1) / 2);

        No z = new No(ordem);
        z.setFolha(filho.isFolha());
        z.setN(t);

        for (int j = 0; j < t; j++) {
            if ((ordem - 1) % 2 == 0) {
                z.getChave().set(j, filho.getChave().get(j + t));
            } else {
                z.getChave().set(j, filho.getChave().get(j + t + 1));
            }
            filho.setN(filho.getN() - 1);
        }

        if (!filho.isFolha()) {
            for (int j = 0; j < t + 1; j++) {
                if ((ordem - 1) % 2 == 0) {
                    z.getFilho().set(j, filho.getFilho().get(j + t));
                } else {
                    z.getFilho().set(j, filho.getFilho().get(j + t + 1));
                }

            }
        }

        filho.setN(t);
        for (int j = pai.getN(); j > i; j--) {
            pai.getFilho().set(j + 1, pai.getFilho().get(j));
        }

        pai.getFilho().set(i + 1, z);
        for (int j = pai.getN(); j > i; j--) {
            pai.getChave().set(j, pai.getChave().get(j - 1));
        }

        if ((ordem - 1) % 2 == 0) {
            pai.getChave().set(i, filho.getChave().get(t - 1));
            filho.setN(filho.getN() - 1);

        } else {
            pai.getChave().set(i, filho.getChave().get(t));
        }

        //incrementa o numero de chaves de x
        pai.setN(pai.getN() + 1);

    }

    public void insereNoNaoCheio(No x, BigInteger k) {
        int i = x.getN() - 1;

        if (x.isFolha()) {

            while (i >= 0 && k.compareTo(x.getChave().get(i)) <= -1) {
                x.getChave().set(i + 1, x.getChave().get(i));
                i--;
            }
            i++;
            x.getChave().set(i, k);
            x.setN(x.getN() + 1);

        } else {
            while ((i >= 0 && k.compareTo(x.getChave().get(i)) <= -1)) {
                i--;
            }
            i++;

            if ((x.getFilho().get(i)).getN() == ordem - 1) {
                divideNo(x, i, x.getFilho().get(i));
                if (k.compareTo(x.getChave().get(i)) >= 1) {
                    i++;
                }
            }

            insereNoNaoCheio(x.getFilho().get(i), k);
        }

    }

    public No BuscaChave(No X, BigInteger k) {
        int i = 1;

        while ((i <= X.getN()) && (k.compareTo(X.getChave().get(i - 1)) >= 1)) {
            i++;
        }

        if ((i <= X.getN()) && (k == X.getChave().get(i - 1))) {
            return X;
        }

        if (X.isFolha()) {
            return null;
        } else {
            return (BuscaChave(X.getFilho().get(i - 1), k));
        }
    }

    public void Remove(BigInteger k) {

        if (BuscaChave(this.raiz, k) != null) {
            System.out.println("Entrou ");
            No N = BuscaChave(this.raiz, k);
            int i = 1;

            while (N.getChave().get(i - 1).compareTo(k) <= -1) {
                i++;
            }

            if (N.isFolha()) {
                for (int j = i + 1; j <= N.getN(); j++) {
                    N.getChave().set(j - 2, N.getChave().get(j - 1));
                }
                N.setN(N.getN() - 1);
                System.out.println("Remoção da chave: " + k + " presente em uma folha");
                if (N != this.raiz) {
                    Balanceia_Folha(N);//Balanceia N
                    System.out.println("Balanceamento necessário, nó não é folha");
                }
            } else {
                No S = Antecessor(this.raiz, k);
                BigInteger y = S.getChave().get(S.getN() - 1);
                S.setN(S.getN() - 1);
                N.getChave().set(i - 1, y);
                Balanceia_Folha(S);
                System.out.println("Remoção da chave: " + k + "não presente em uma folha");
            }
            nElementos--;//diminui o numero de elementos na arvoreB
        }
    }

    private void Balanceia_Folha(No F) {

        if (F.getN() < Math.floor((ordem - 1) / 2)) {
            No P = getPai(raiz, F);
            int j = 1;

            while (P.getFilho().get(j - 1) != F) {
                j++;
            }

            if (j == 1 || (P.getFilho().get(j - 2)).getN() == Math.floor((ordem - 1) / 2)) {

                if (j == P.getN() + 1 || (P.getFilho().get(j).getN() == Math.floor((ordem - 1) / 2))) {
                    Diminui_Altura(F);
                } else {
                    Balanceia_Dir_Esq(P, j - 1, P.getFilho().get(j), F);
                }
            } else {
                Balanceia_Esq_Dir(P, j - 2, P.getFilho().get(j - 2), F);
            }
        }
    }

    private void Diminui_Altura(No pai) {
        int j;
        No P = new No(ordem);

        if (pai == this.raiz) {
            if (pai.getN() == 0) {
                this.raiz = pai.getFilho().get(0);
                pai.getFilho().set(0, null);
            }
        } else {//caso contrario(X nao Ã© raiz)
            int t = (int) Math.floor((ordem - 1) / 2);
            //verifica se o numero de chaves de X Ã© menor que o permitido
            if (pai.getN() < t) {
                P = getPai(raiz, pai);//P Ã© pai de X
                j = 1;

                //adquire a posicao correta do filho X em P
                while (P.getFilho().get(j - 1) != pai) {
                    j++;
                }

                //junta os nÃ³s
                if (j > 1) {
                    Juncao_No(getPai(raiz, pai), j - 1);
                } else {
                    Juncao_No(getPai(raiz, pai), j);
                }
                Diminui_Altura(getPai(raiz, pai));
            }
        }
    }

    private void Balanceia_Esq_Dir(No P, int e, No Esq, No Dir) {

        for (int i = 0; i < Dir.getN(); i++) {
            Dir.getChave().set(i + 1, Dir.getChave().get(i));
        }

        if (!Dir.isFolha()) {
            for (int i = 0; i > Dir.getN(); i++) {
                Dir.getFilho().set(i + 1, Dir.getFilho().get(i));
            }
        }
        Dir.setN(Dir.getN() + 1);
        Dir.getChave().set(0, P.getChave().get(e));
        P.getChave().set(e, Esq.getChave().get(Esq.getN() - 1));
        Dir.getFilho().set(0, Esq.getFilho().get(Esq.getN()));
        Esq.setN(Esq.getN() - 1);

    }

    private void Balanceia_Dir_Esq(No P, int e, No Dir, No Esq) {

        Esq.setN(Esq.getN() + 1);
        Esq.getChave().set(Esq.getN() - 1, P.getChave().get(e));
        P.getChave().set(e, Dir.getChave().get(0));
        Esq.getFilho().set(Esq.getN(), Dir.getFilho().get(0));

        for (int j = 1; j < Dir.getN(); j++) {
            Dir.getChave().set(j - 1, Dir.getChave().get(j));
        }

        if (!Dir.isFolha()) {
            for (int i = 1; i < Dir.getN() + 1; i++) {
                Dir.getFilho().set(i - 1, Dir.getFilho().get(i));
            }
        }

        Dir.setN(Dir.getN() - 1);

    }

    private void Juncao_No(No X, int i) {
        No Y = X.getFilho().get(i - 1);
        No Z = X.getFilho().get(i);

        int k = Y.getN();
        Y.getChave().set(k, X.getChave().get(i - 1));

        for (int j = 1; j <= Z.getN(); j++) {
            Y.getChave().set(j + k, Z.getChave().get(j - 1));
        }

        if (!Z.isFolha()) {
            for (int j = 1; j <= Z.getN(); j++) {
                Y.getFilho().set(j + k, Z.getFilho().get(j - 1));
            }
        }

        Y.setN(Y.getN() + Z.getN() + 1);

        X.getFilho().set(i, null);
        for (int j = i; j <= X.getN() - 1; j++) {
            X.getChave().set(j - 1, X.getChave().get(j));
            X.getFilho().set(j, X.getFilho().get(j + 1));
        }

        X.setN(X.getN() - 1);
    }

    private No Antecessor(No N, BigInteger k) {
        int i = 1;
        while (i <= N.getN() && N.getChave().get(i - 1).compareTo(k) <= -1) {
            i++;
        }
        if (N.isFolha()) {
            return N;
        } else {
            return Antecessor(N.getFilho().get(i - 1), k);
        }
    }

    private No getPai(No T, No N) {
        if (this.raiz == N) {
            return null;
        }
        for (int j = 0; j <= T.getN(); j++) {
            if (T.getFilho().get(j) == N) {
                return T;
            }
            if (!T.getFilho().get(j).isFolha()) {
                No X = getPai(T.getFilho().get(j), N);
                if (X != null) {
                    return X;
                }
            }
        }
        return null;
    }

    public void LimparArvore(No N, int ordem) {

        for (int i = 0; i < N.getN() + 1; i++) {
            if (!N.isFolha()) {
                LimparArvore(N.getFilho().get(i), ordem);
            }
            N.getFilho().set(i, null);
            N.setN(0);
        }

        if (N == this.raiz) {
            this.raiz = new No(ordem);
        }
        nElementos = 0;
    }
}
