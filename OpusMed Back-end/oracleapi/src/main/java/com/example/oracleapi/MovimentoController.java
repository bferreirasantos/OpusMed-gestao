package com.example.oracleapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/api")
public class MovimentoController {
    @Autowired
    private MovimentoService movimentoService;

    @PostMapping("/movimentos")
    public Map<String, Object> inserir(@RequestBody MovimentoDTO dto) throws SQLException {
        return movimentoService.inserirMovimento(dto);
    }

    @GetMapping("/movimentos/{data}")
    public List<Map<String, Object>> listarMovimentos(@PathVariable String data) throws Exception {
        try {
            List<Map<String, Object>> debug = movimentoService.buscarMovimento(data);
            return debug;

        }
        catch (Exception err){
            System.out.println(err.getMessage());
            return null;
        }
    }

    @PutMapping("/movimentos/{id}")
    public Map<String, Object> atualizar(@PathVariable int id, @RequestBody MovimentoDTO dto) throws SQLException {
        return movimentoService.updateMovimento(id, dto);
    }
}
