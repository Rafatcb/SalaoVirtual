/*
 * Classe encontrada em https://stackoverflow.com/a/18311712
 * Serve para forçar a seleção de uma única linha em uma tabela
 */
package salaovirtual.gui.tableModel;

import javax.swing.DefaultListSelectionModel;
import javax.swing.ListSelectionModel;

/*
 * Classe encontrada em https://stackoverflow.com/a/18311712
 * Serve para forçar a seleção de uma única linha em uma tabela
 *
 * @author MadProgrammer - stackoverflow
 */
public class ForcedListSelectionModel extends DefaultListSelectionModel {

    public ForcedListSelectionModel () {
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    @Override
    public void clearSelection() {
    }

    @Override
    public void removeSelectionInterval(int index0, int index1) {
    }

}