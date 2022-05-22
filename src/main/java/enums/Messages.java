package enums;

public enum Messages {
    INVALID_CHOICE("you entered an invalid choice ...please try again"),
    SUCCESS("success"),
    USERID_EXIST("this user ID exist"),
    INVALID_TYPE("invalid user type"),
    MISMATCH_PASSWORD("mismatch password"),
    SHORT_PASSWORD("short password"),
    LONG_PASSWORD("long password"),
    JUST_NUMBER("just contains number"), JUST_ALPHA("just contains alpha"), WRONG_CREDENTIALS("wrong credentials");
    private String message;
    Messages(String message)
    {
        this.message = message;
    }
    @Override
    public String toString(){
        return this.message;
    }
}
