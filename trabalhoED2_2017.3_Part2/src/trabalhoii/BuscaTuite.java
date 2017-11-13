/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhoii;

import java.util.ArrayList;
import splaytree.NoSplay;
import splaytree.SplayTree;

/**
 *
 * @author Gabriel
 */
public class BuscaTuite {
    private SplayTree k = new SplayTree();
    private ArrayList<Tuite> listaAux = new ArrayList<>();

    public BuscaTuite() {
    }
    
    public BuscaTuite(SplayTree a, ArrayList<Tuite> lista){
        this.k = a;
        this.listaAux = lista;
    }
            
    public String procuraElementoUserID(ArrayList<Tuite> lista, String usID) {
        if(procuraChaveUserId(lista, usID) == 1){
            return "Tweet Encontrado!";
        }else{
            return "Não encontrado";
        }
    }

    private int procuraChaveUserId(ArrayList<Tuite> lista, String usID) {
        int aux = lista.size();
        while(aux != 0){
            int i = 0;
            if(usID.compareTo(lista.get(i).getUserID()) == 0){
               return 1;
            }else{
                i++;
                aux--;
            }
        }
        return 0;
    }
    
    public String procuraElementoTweet(ArrayList<Tuite> lista, String tweet) {
        if(procuraChaveUserId(lista, tweet) == 1){
            return "Tweet Encontrado!";
        }else{
            return "Não encontrado";
        }
    }

    private int procuraChaveTweet(ArrayList<Tuite> lista, String tweet) {
        int aux = lista.size();
        while(aux != 0){
            int i = 0;
            if(tweet.compareTo(lista.get(i).getTweet()) == 0){
               return 1;
            }else{
                i++;
                aux--;
            }
        }
        return 0;
    }
    
    public String procuraElementoDate(ArrayList<Tuite> lista, String usID) {
        if(procuraChaveUserId(lista, usID) == 1){
            return "Tweet Encontrado!";
        }else{
            return "Não encontrado";
        }
    }

    private int procuraChaveDate(ArrayList<Tuite> lista, String usID) {
        int aux = lista.size();
        while(aux != 0){
            int i = 0;
            if(usID.compareTo(lista.get(i).getDate()) == 0){
               return 1;
            }else{
                i++;
                aux--;
            }
        }
        return 0;
    }
}
