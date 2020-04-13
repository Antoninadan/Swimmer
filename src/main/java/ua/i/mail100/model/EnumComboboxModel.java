package ua.i.mail100.model;

import javax.swing.*;

public class EnumComboboxModel extends AbstractListModel implements ComboBoxModel {

    private Enum<?>[] values;
    private Object selected;

    public EnumComboboxModel(Class<Enum<?>> enumClass) {
        values = enumClass.getEnumConstants();
    }

    public Object getElementAt(int index) {
        return values[index].name();
    }

    public int getSize() {
        return values.length;
    }

    @Override
    public void setSelectedItem(Object anItem) {
        Object selected = null;
        for (Enum<?> val : values) {
            if (val == anItem) {
                selected = val;
                break;
            }
        }
        this.selected = selected;
    }

    @Override
    public Object getSelectedItem() {
        return selected;
    }

}