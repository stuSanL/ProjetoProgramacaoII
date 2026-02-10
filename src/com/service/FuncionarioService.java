package com.service;

import com.Paths;
import com.dao.FuncionarioDAO;
import com.dao.IdDAO;
import com.model.Funcionario;

import java.io.IOException;

public class FuncionarioService {

    private final FuncionarioDAO fDao;
    private final IdDAO idDao;
    private final String idPath;

    public FuncionarioService(){
        this.fDao = new FuncionarioDAO();
        this.idDao = new IdDAO();
        this.idPath = Paths.IDS.getAbsPath() + "/funcionario";
    }

    public void add(Funcionario f) throws IOException {
        f.setId(idDao.recordId(idPath));
        fDao.add(f);
    }

    public void delete(Funcionario f){
        fDao.deleteByID(f.getId());
    }

    public Funcionario[] listAll() throws IOException {
        return fDao.listAll();
    }

    public Funcionario findById(int id) throws IOException {
        return fDao.findByID(id);
    }

    public void update(Funcionario f) throws IOException {
        fDao.update(f);
    }

    public Funcionario[] findByName(String name) throws IOException {
        return fDao.findByNome(name);
    }

    public Funcionario[] findByData(String data) throws IOException {
        return fDao.findByData(data);
    }
}
