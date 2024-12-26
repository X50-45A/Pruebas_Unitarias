package data;

/**
 * Value class representing a Vehicle identifier.
 * Format: "PMV" followed by 5 digits (e.g., "PMV00001")
 */
public final class VehicleID {
    private final String value;

    /**
     * Creates a new VehicleID.
     * @param value Vehicle identifier in format "PMV" followed by 5 digits
     * @throws IllegalArgumentException if value is invalid
     * @throws NullPointerException if value is null
     */
    public VehicleID(String value) {
        if (value == null) {
            throw new NullPointerException("Vehicle ID cannot be null");
        }
        if (!isValidFormat(value)) {
            throw new IllegalArgumentException("Invalid vehicle ID format. Must be 'PMV' followed by 5 digits");
        }
        this.value = value;
    }

    /**
     * Validates the vehicle ID format.
     * @param id The ID to validate
     * @return true if format is valid
     */
    private boolean isValidFormat(String id) {
        return id.matches("PMV\\d{5}");
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VehicleID vehicleID = (VehicleID) o;
        return value.equals(vehicleID.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return "VehicleID{" +
                "value='" + value + '\'' +
                '}';
    }
}
