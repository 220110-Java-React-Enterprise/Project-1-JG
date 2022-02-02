package enums;

/**
 * Identifiers to indicate SQL field type.
 *   Primarily used for Column annotations.
 */
public enum SQLType {
    BOOL,       //java boolean - 1 bit
    INT,        //java int - 4 byte
    BIGINT,     //java long - 8 byte
    NUMERIC,    //java float/double with selectable precision
    VARCHAR     //variable length character string
}
