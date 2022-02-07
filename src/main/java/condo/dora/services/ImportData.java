package condo.dora.services;

import condo.dora.models.ImportStorage;

public interface ImportData {
    ImportStorage getImportDataLetter();
    void setImportDataLetter(ImportStorage items);
    ImportStorage getImportDataDocument();
    void setImportDataDocument(ImportStorage items);
    ImportStorage getImportDataBox();
    void setImportDataBox(ImportStorage items);
}