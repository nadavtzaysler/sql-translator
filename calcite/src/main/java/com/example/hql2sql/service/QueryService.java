package com.example.hql2sql.service;

import org.apache.calcite.config.Lex;
import org.apache.calcite.schema.SchemaPlus;
import org.apache.calcite.sql.SqlNode;
import org.apache.calcite.sql.parser.SqlParser;
import org.apache.calcite.sql.validate.SqlConformanceEnum;
import org.apache.calcite.tools.*;
import org.apache.calcite.sql.pretty.SqlPrettyWriter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QueryService {

    public List<String> getSqlSyntaxes() {
        List<String> syntaxes = new ArrayList<>();
        syntaxes.add("HQL");
        syntaxes.add("VQL");
        return syntaxes;
    }

    public String translateSyntaxToTrino(String hqlQuery) {
        try {
            SqlParser.Config parserConfig = SqlParser.config()
                    .withLex(Lex.MYSQL)
                    .withConformance(SqlConformanceEnum.BABEL)
                    .withCaseSensitive(false)
                    .withQuotedCasing(SqlParser.Config.DEFAULT.quotedCasing())
                    .withUnquotedCasing(SqlParser.Config.DEFAULT.unquotedCasing())
                    .withQuoting(SqlParser.Config.DEFAULT.quoting());

            SchemaPlus rootSchema = Frameworks.createRootSchema(true);

            Planner planner = Frameworks.getPlanner(
                    Frameworks.newConfigBuilder()
                            .parserConfig(parserConfig)
                            .defaultSchema(rootSchema)
                            .build());

            SqlNode sqlNode = planner.parse(hqlQuery);

            SqlPrettyWriter writer = new SqlPrettyWriter();
            sqlNode.unparse(writer, 0, 0);
            return writer.toString().replaceAll("[\\r\\n]+", " ").trim();

        } catch (Exception e) {
            throw new RuntimeException("Error translating HQL to Trino SQL: " + e.getMessage(), e);
        }
    }
}

