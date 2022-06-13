package Strings.StrongPasswordChecker;

class StrongPasswordChecker {

    public boolean strongPasswordCheckerII(String password) {
        boolean lengthGreaterThan8 = password.length() >= 8;
        boolean hasLowercase = false;
        boolean hasUppercase = false;
        boolean hasDigit = false;
        boolean hasSpecialCharacter = false;
        boolean doesNotContainAdjacent = true;

        String specialCharacter  = "!@#$%^&*()-+";

        for (int i = 0; i < password.length(); i++){
            char ch = password.charAt(i);

            if (i != 0  && ch == password.charAt(i-1))
                doesNotContainAdjacent = false;

            if (specialCharacter.indexOf(ch) != -1)
                hasSpecialCharacter = true;

            if (ch >= 'a'  &&  ch <= 'z')
                hasLowercase = true;

            if (ch >= 'A'  && ch <= 'Z')
                hasUppercase = true;

            if (ch >= '0'  &&  ch <= '9')
                hasDigit = true;
        }
        return lengthGreaterThan8 && hasDigit && hasLowercase && hasSpecialCharacter && hasUppercase && doesNotContainAdjacent;
    }
}