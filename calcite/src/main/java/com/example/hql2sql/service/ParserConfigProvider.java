package com.example.hql2sql.service;

import org.apache.calcite.config.Lex;
import org.apache.calcite.sql.parser.SqlParser;
import org.apache.calcite.sql.validate.SqlConformanceEnum;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.function.Supplier;

public class ParserConfigProvider {
    private static final Map<String, Supplier<SqlParser.Config>> CONFIG_MAP = new HashMap<>();

    static {
        CONFIG_MAP.put("hive", () -> SqlParser.config()
                .withLex(Lex.JAVA)
                .withConformance(SqlConformanceEnum.BABEL)
                .withCaseSensitive(false)
                .withQuotedCasing(SqlParser.Config.DEFAULT.quotedCasing())
                .withUnquotedCasing(SqlParser.Config.DEFAULT.unquotedCasing())
                .withQuoting(SqlParser.Config.DEFAULT.quoting()));
        CONFIG_MAP.put("oracle", () -> SqlParser.config()
                .withLex(Lex.ORACLE)
                .withConformance(SqlConformanceEnum.ORACLE_12)
                .withCaseSensitive(false)
                .withQuotedCasing(SqlParser.Config.DEFAULT.quotedCasing())
                .withUnquotedCasing(SqlParser.Config.DEFAULT.unquotedCasing())
                .withQuoting(SqlParser.Config.DEFAULT.quoting()));
        CONFIG_MAP.put("mssql", () -> SqlParser.config()
                .withLex(Lex.SQL_SERVER)
                .withConformance(SqlConformanceEnum.DEFAULT)
                .withCaseSensitive(false)
                .withQuotedCasing(SqlParser.Config.DEFAULT.quotedCasing())
                .withUnquotedCasing(SqlParser.Config.DEFAULT.unquotedCasing())
                .withQuoting(SqlParser.Config.DEFAULT.quoting()));
    }

    public static SqlParser.Config getParserConfig(String dialect) {
        Supplier<SqlParser.Config> supplier = CONFIG_MAP.get(dialect.toLowerCase(Locale.ROOT));
        if (supplier == null) {
            throw new IllegalArgumentException("Unsupported dialect: " + dialect);
        }
        return supplier.get();
    }
} 
