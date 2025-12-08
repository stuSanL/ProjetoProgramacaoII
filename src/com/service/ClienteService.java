package com.service;

import com.Paths;
import com.dao.ClienteDAO;
import com.dao.IdDAO;
import com.model.Cliente;

import java.io.IOException;

public class ClienteService {

    private final ClienteDAO cDao;
    private final IdDAO idDao;
    private final String idPath;

    public ClienteService(){
        this.cDao = new ClienteDAO();
        this.idDao = new IdDAO();
        this.idPath = Paths.IDS.getAbsPath() + "/cliente";
    }

    public void add(Cliente cliente) throws IOException {
        cliente.setId(idDao.recordId(idPath));
        cDao.add(cliente);
    }

    public void delete(Cliente cliente) throws IOException {
        cDao.deleteByID(cliente.getId());
    }

    public Cliente[] listAll() throws IOException {
        return cDao.listAll();
    }

    public Cliente findById(int id) throws IOException {
        return cDao.findByID(id);
    }

    public void update(Cliente cliente) throws IOException {
        cDao.update(cliente);
    }

    public Cliente[] findByName(String name) throws IOException {
        return cDao.findByNome(name);
    }

}
