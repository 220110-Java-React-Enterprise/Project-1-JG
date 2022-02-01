package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.sql.JDBCType;

@Retention(RetentionPolicy.RUNTIME) // discover annotation via reflection
@Target(ElementType.FIELD)          // any "table" Object should have "columns" like this
public @interface Column {
    // SQL types supported by JDBC
    JDBCType type();

    // primary key should be set to false if not applicable to column
    boolean primaryKey() default false;

    // columns can be null unless otherwise specified
    boolean nonNull() default false;

    // the length limit of a given column
    // TODO what is this exactly?
    int length() default -1;
}
