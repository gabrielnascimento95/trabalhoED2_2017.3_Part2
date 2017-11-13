/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhoii;

/**
 *
 * @author Gabriel_Nascimento
 */
public class Validacao {
    public Validacao() {
    }

    public boolean eValido(String[] a) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == null) {
                return false;
            } else if (a[i].isEmpty()) {
                return false;
            }

        }
        return true;
    }
}
