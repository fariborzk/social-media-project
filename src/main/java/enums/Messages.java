package enums;

public enum Messages {
    INVALID_CHOICE("you entered an invalid choice ...please try again"),
    SUCCESS("success"),
    USERID_EXIST("this user ID exist"),
    INVALID_TYPE("invalid user type"),
    MISMATCH_PASSWORD("mismatch password"),
    SHORT_PASSWORD("short password"),
    LONG_PASSWORD("long password"),
    JUST_NUMBER("just contains number"), JUST_ALPHA("just contains alpha"),
    WRONG_CREDENTIALS("wrong credentials"),
    FIRST_NAME_CANT_BE_EMPTY("first name cannot be empty ... please enter your first name"),
    LAST_NAME_CANT_BE_EMPTY("last name cannot be empty ... please enter your last name"),
    USER_NAME_CANT_BE_EMPTY("username cannot be empty ... please enter your username"),
    BIRTHDAY_CANT_BE_EMPTY("birthday cannot be empty ... please enter your birthday"),
    PASSWORD_CANT_BE_EMPTY("password cannot be empty ... please enter your password"),
    REPEAT_PASSWORD("please repeat your password"),
    GENDER_CANT_BE_EMPTY("gender cannot be empty ... please enter your message"),
    INVALID_PHONE_NUMBER("please enter a valid phone number"),
    INVALID_GENDER("please Enter a valid gender... F for female / M for male "),
    NO_USER_EXIST("no user exist with that username..."),
    INVALID_GROUP_ID("group id format is invalid"),
    GROUP_ID_EXISTS("this user id exists"),
    SQL_EXCEPTION("SQL exception"),
    GROUP_ID_DO_NOT_EXIST("this group_id do not exist");
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
