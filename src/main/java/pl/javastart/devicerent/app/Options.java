package pl.javastart.devicerent.app;

enum Options {

    ADD_DEVICE(1, "Dodaj urządzenie"),
    ADD_CATEGORY(2, "Dodaj kategorię"),
    ADD_CUSTOMER(3, "Dodaj klienta"),
    RENT(4, "Wypożycz urządzenie"),
    SEARCH_DEVICE(5, "Wyszukaj urządzenie"),
    REMOVE_DEVICE(6, "Usuń urządzenie"),
    REMOVE_CATEGORY(7, "Usuń kategorię"),
    REMOVE_CUSTOMER(8, "Usuń klienta"),
    END(9, "Koniec");

    private int number;
    private String description;

    Options(int number, String description) {
        this.number = number;
        this.description = description;
    }

    @Override
    public String toString() {
        return number + " - " + description;
    }

    static Options numberToCategory(int number) {
        if (number < 1 || number > Options.values().length)
            throw new InvalidOptionException();
        return values()[number - 1];

    }

    //metoda numberToCategory - zamiana liczby na kategorię

}
