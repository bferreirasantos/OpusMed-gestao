package com.example.oracleapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/api")
public class ProdutoController {

    @Autowired
    private PatrimonioService patrimonioService;

    @PostMapping("/produtos")
    public Map<String, Object> inserir(@RequestBody PatrimonioDTO dto) throws SQLException {
        return patrimonioService.inserirPatrimonio(dto);
    }

    @GetMapping("/produtos")
    public List<Map<String, Object>> listar() throws SQLException {
        return patrimonioService.buscarPatrimonio();
    }

    @GetMapping("/produtos/{codigoBarras}")
    public List<Map<String, Object>> listarCodigoBarras(@PathVariable String codigoBarras) throws SQLException {
        return patrimonioService.buscarPatrimonioCDBarras(codigoBarras);
    }

    @DeleteMapping("/produtos/{codigoBarras}")
    public Map<String, Object> deletar(@PathVariable String codigoBarras) throws SQLException {
        return patrimonioService.deletarPatrimonio(codigoBarras);
    }

    @PutMapping("/produtos/{codigoBarras}")
    public List<Map<String, Object>> atualizar(@PathVariable int codigoBarras, @RequestBody PatrimonioDTO dto) throws SQLException {
        return patrimonioService.updatePatrimonio(codigoBarras, dto);
    }

}
