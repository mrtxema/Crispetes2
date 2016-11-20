package cat.mrtxema.crispetes.store;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DtoRegistry {

    public static List<Class<?>> getDtoClasses() {
        return Arrays.asList(
                FavoriteMovieDto.class,
                VideoSourceDto.class,
                CredentialParameterDto.class
        );
    }

    public static Class<?>[] getDtoClassesAsArray() {
        return getDtoClasses().toArray(new Class<?>[0]);
    }

    public static List<Class<?>> getDtoClassesInReverseOrder() {
        List<Class<?>> result = getDtoClasses();
        Collections.reverse(result);
        return result;
    }
}
