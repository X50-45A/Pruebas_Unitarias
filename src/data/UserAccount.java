package data;

/**
 * Value class representing a User Account.
 * Format: Valid email address
 */
public final class UserAccount {
    private final String email;

    /**
     * Creates a new UserAccount.
     * @param email Valid email address
     * @throws IllegalArgumentException if email is invalid
     * @throws NullPointerException if email is null
     */
    public UserAccount(String email) {
        if (email == null) {
            throw new NullPointerException("Email cannot be null");
        }
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format");
        }
        this.email = email;
    }

    /**
     * Validates the email format.
     * Basic email validation using regex.
     * @param email The email to validate
     * @return true if format is valid
     */
    private boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAccount that = (UserAccount) o;
        return email.equals(that.email);
    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "email='" + email + '\'' +
                '}';
    }
}