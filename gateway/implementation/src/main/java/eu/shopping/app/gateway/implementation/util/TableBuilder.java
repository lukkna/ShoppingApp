package eu.shopping.app.gateway.implementation.util;

public class TableBuilder {
    private final String query;

    private TableBuilder(String query) {
        this.query = query;
    }

    public static TableBuilder create(String table) {
        return new TableBuilder("CREATE TABLE IF NOT EXISTS " + table + "(ID SERIAL NOT NULL,");
    }

    public static TableBuilder recreate(String table) {
        return new TableBuilder("DROP TABLE IF EXISTS " + table + ";" +
                "CREATE TABLE IF NOT EXISTS " + table + "(ID SERIAL NOT NULL,");
    }

    public static TableBuilder index(String index) {
        return new TableBuilder("CREATE INDEX IF NOT EXISTS " + index + "_INDEX");
    }

    private static String arrayToString(String[] like) {
        return String.join("", like);
    }

    public TableBuilder on(String table, String... columns) {
        return append(" ON " + table + "(" + arrayToString(columns));
    }

    public TableBuilder varchar(String column) {
        return append(column + " VARCHAR(255) NOT NULL,");
    }

    public TableBuilder varcharNullable(String column) {
        return append(column + " VARCHAR(255),");
    }

    public TableBuilder integer(String column) {
        return append(column + " INT NOT NULL,");
    }

    private static String array2string(String[] array, String separator) {
        return String.join(separator, array);
    }

    public TableBuilder unique(String... columns) {
        return append("UNIQUE (" + array2string(columns, ",") + ")");
    }


    public TableBuilder array(String column) {
        return append(column + " ARRAY NOT NULL, ");
    }

    public String build() {
        return removeTailingComma() + ");";
    }

    public TableBuilder doubl(String column) {
        return append(column + " DOUBLE NOT NULL,");
    }

    public TableBuilder timestamp(String column) {
        return append(column + " TIMESTAMP WITH TIME ZONE NOT NULL,");
    }

    private TableBuilder append(String toAppend) {
        return new TableBuilder(query + "" + toAppend);
    }

    private String removeTailingComma() {
        return query.endsWith(",")
                ? query.substring(0, query.length() - 1)
                : query;
    }
}