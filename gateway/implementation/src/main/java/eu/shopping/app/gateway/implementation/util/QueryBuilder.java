package eu.shopping.app.gateway.implementation.util;

import static java.util.stream.Collectors.joining;
import static java.util.stream.IntStream.range;

public class QueryBuilder {
    private final String query;

    private QueryBuilder(String query) {
        this.query = query;
    }

    public static QueryBuilder select() {
        return select("*");
    }

    public static QueryBuilder select(String... select) {
        return new QueryBuilder("SELECT " + arrayToString(select));
    }

    public static QueryBuilder selectDistinct() {
        return selectDistinct("*");
    }

    public static QueryBuilder selectDistinct(String... select) {
        return new QueryBuilder("SELECT DISTINCT " + arrayToString(select));
    }

    public static QueryBuilder insert(String table, String... insert) {
        return new QueryBuilder("INSERT INTO " + table + "(" + arrayToString(insert, ",") + ")" + values(insert.length));
    }

    public static QueryBuilder delete() {
        return new QueryBuilder("DELETE ");
    }

    public QueryBuilder orderBy(String... fields) {
        return append("ORDER BY " + arrayToString(fields, ","));
    }

    public QueryBuilder from(String... from) {
        return append("FROM " + arrayToString(from));
    }

    public QueryBuilder join(String... join) {
        return append("JOIN " + arrayToString(join));
    }

    public QueryBuilder on(String... on) {
        return append("ON " + arrayToString(on));
    }

    public QueryBuilder where(String... where) {
        return append("WHERE " + arrayToString(where));
    }

    public QueryBuilder and(String... and) {
        return append("AND " + arrayToString(and));
    }

    public QueryBuilder like(String... like) {
        return append("LIKE " + arrayToString(like));
    }

    private QueryBuilder append(String toAppend) {
        return new QueryBuilder(query + " " + toAppend);
    }

    private static String values(int numberOfValues) {
        String values = range(0, numberOfValues)
                .mapToObj(i -> "?")
                .collect(joining(", "));
        return (" VALUES (" + values + ")");
    }

    public String build(String... ending) {
        return query + " " + arrayToString(ending);
    }

    public String build() {
        return query;
    }

    private static String arrayToString(String[] like) {
        return arrayToString(like, "");
    }

    private static String arrayToString(String[] like, String separator) {
        return String.join(separator, like);
    }

    public static class QueryPartsBuilder {
        public static String castToInt(String column, String as) {
            return String.format("CAST(%s AS INT) AS %s", column, as);
        }

        private QueryPartsBuilder() {
        }
    }
}