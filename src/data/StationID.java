package data;

/**
 * Value class representing a Station identifier.
 * Format: "ST" followed by 4 digits (e.g., "ST0001")
 */
public final class StationID {
    private final String value;

    /**
     * Creates a new StationID.
     * @param value Station identifier in format "ST" followed by 4 digits
     * @throws IllegalArgumentException if value is invalid
     * @throws NullPointerException if value is null
     */
    public StationID(String value) {
        if (value == null) {
            throw new NullPointerException("Station ID cannot be null");
        }
        if (!isValidFormat(value)) {
            throw new IllegalArgumentException("Invalid station ID format. Must be 'ST' followed by 4 digits");
        }
        this.value = value;
    }

    /**
     * Validates the station ID format.
     * @param id The ID to validate
     * @return true if format is valid
     */
    private boolean isValidFormat(String id) {
        return id.matches("ST\\d{4}");
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StationID stationID = (StationID) o;
        return value.equals(stationID.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return "StationID{" +
                "value='" + value + '\'' +
                '}';
    }
}