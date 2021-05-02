package monika.projectmanagement.entity;

public enum BookStatus {
    ALREADY_READ("Already read"),
    CURRENTLY_READING("Currently reading"),
    WANT_TO_READ("Want to read");

    private final String displayValue;

    private BookStatus(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
