package inc.itnity.elbilad.domain.exceptions;

/**
 * Created by st1ch on 04.11.2016.
 */

public class ServerForbiddenException extends Throwable {
    @Override
    public String getMessage() {
        return "Server forbidden";
    }
}
