package com.example.hql2sql.service;

import org.apache.calcite.schema.SchemaPlus;
import org.apache.calcite.sql.SqlNode;
import org.apache.calcite.sql.parser.SqlParser;
import org.apache.calcite.tools.*;
import org.apache.calcite.sql.pretty.SqlPrettyWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryService {

    private static final Logger log = LoggerFactory.getLogger(QueryService.class);

    public List<String> getSqlSyntaxes() {
        log.info("Fetching supported SQL syntaxes");
        return List.of("hive", "oracle", "msSql", "trino");
    }

    public String translateSyntaxToTrino(String hqlQuery, String inputDialect) {
        log.info("Translating {} to Trino SQL. Input query: {}", inputDialect, hqlQuery);
        try {
            String normalizedQuery = hqlQuery.replace('`', '\"').replace('\'', '\"').replaceAll(";\\s*$", "");

            SqlParser.Config parserConfig = ParserConfigProvider.getParserConfig(inputDialect);

            SchemaPlus rootSchema = Frameworks.createRootSchema(true);

            Planner planner = Frameworks.getPlanner(
                    Frameworks.newConfigBuilder()
                            .parserConfig(parserConfig)
                            .defaultSchema(rootSchema)
                            .build());

            SqlNode sqlNode = planner.parse(normalizedQuery);

            SqlPrettyWriter writer = new SqlPrettyWriter();
            sqlNode.unparse(writer, 0, 0);
            String trinoSql = writer.toString().replaceAll("[\\r\\n]+", " ").trim();
            if (!trinoSql.endsWith(";")) {
                trinoSql = trinoSql + ";";
            }
            log.info("Translation successful. Output query: {}", trinoSql);
            return trinoSql;
        } catch (Exception e) {
            log.error("Error translating HQL to Trino SQL: {}", e.getMessage(), e);
            throw new RuntimeException("Error translating HQL to Trino SQL: " + e.getMessage(), e);
        }
    }
}

