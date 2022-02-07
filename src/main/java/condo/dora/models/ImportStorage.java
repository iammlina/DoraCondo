package condo.dora.models;

import java.util.ArrayList;


public class ImportStorage {
//    private Collection<Import> items;
    private ArrayList<Import> items;

//    public ImportStorage() { items = new LinkedList<>(); }
    public ImportStorage() { items = new ArrayList<>(); }

    public void addItem(Import item) { items.add(item);}

    public  boolean checkExport(String status) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getStatus().equals(status)) {
                if (status.equals("-- WAITING --")){
                    return true;
                }
            }
        }
        return false;
    }

    public ArrayList<Import> toList() {
        return items;
    }

}
