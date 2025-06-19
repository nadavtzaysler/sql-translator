package com.example.hql2sql.controller;

import com.example.hql2sql.service.QueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class QueryController {

    private final QueryService queryService;

    @GetMapping("/supported")
    public ResponseEntity<List<String>> getSqlSyntaxes() {
        return ResponseEntity.ok(queryService.getSqlSyntaxes());
    }

    @PostMapping("/translate")
    public ResponseEntity<Map<String, String>> translateSyntaxToTrino(
            @RequestBody Map<String, String> request,
            @RequestParam String input,
            @RequestParam String output) {
        String query = request.get("query");
        if (query == null || query.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "No query provided"));
        }
        String trinoSql = queryService.translateSyntaxToTrino(query);
        return ResponseEntity.ok(Map.of("trinoSql", trinoSql));
    }
}
