package com.service;

import com.Paths;
import com.dao.IdDAO;
import com.dao.VendaDAO;
import com.exception.DataException;
import com.model.Cliente;
import com.model.Funcionario;
import com.model.Tanque;
import com.model.Venda;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class VendaService {

    private final VendaDAO vDao;
    private final IdDAO idDao;
    private final String idPath;

    public VendaService() {
        vDao = new VendaDAO();
        idDao = new IdDAO();
        idPath = Paths.IDS.getAbsPath() + "/venda";
    }

    public void add(Venda v) throws IOException {
        TanqueService ts = new TanqueService();

        if(v.getLitragem() > v.getTanque().getVolumeAtual()){
            throw new DataException("Combustível insuficiente no tanque.");
        }

        atualizarValor(v);
        atualizaVolume(v);

        v.setDataVenda(LocalDate.now().format(DateTimeFormatter.ISO_DATE));
        v.setId(idDao.recordId(idPath));


        ts.update(v.getTanque());

        vDao.add(v);
    }

    public void delete(Venda v){
        vDao.deleteById(v.getId());
    }

    public Venda[] listAll() throws IOException {
        return vDao.listAll();
    }

    public Venda findById(int id) throws IOException {
        return vDao.findById(id);
    }

    public void update(Venda v) throws IOException {
        vDao.update(v);
    }

    public Venda[] findByCliente(Cliente c) throws IOException {
        return vDao.findByCliente(c);
    }

    public Venda[] findByFuncionario(Funcionario f) throws IOException {
        return vDao.findByFuncionario(f);
    }

    public Venda[] findByTanque(Tanque t) throws IOException {
        return vDao.findByTanque(t);
    }

    public Venda[] findByData(String date) throws IOException {
        return vDao.findByData(date);
    }

    public void atualizarValor(Venda v){
        double valor = v.getTanque().getCombustivel().getValor() * v.getLitragem();
        double descontoAssinatura = 0.1;
        if(v.getCliente().haveAssinatura())valor -= (valor*descontoAssinatura);
        v.setValor(valor);
    }
    public void atualizaVolume(Venda v){
        v.getTanque().setVolumeAtual(v.getTanque().getVolumeAtual() - v.getLitragem());
    }
}
