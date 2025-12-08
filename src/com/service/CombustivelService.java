package com.service;

import com.Paths;
import com.dao.CombustivelDAO;
import com.dao.IdDAO;
import com.dao.TanqueDAO;
import com.exception.ResourceInUseException;
import com.model.Combustivel;
import com.model.Tanque;

import java.io.IOException;

public class CombustivelService {

    private final CombustivelDAO cDao;
    private final IdDAO idDao;
    private final String idPath;

    public CombustivelService() {
        cDao = new CombustivelDAO();
        idDao = new IdDAO();
        idPath = Paths.IDS.getAbsPath() + "/combustivel";
    }

    public void add(Combustivel combustivel) throws IOException {
        combustivel.setId(idDao.recordId(idPath));
        cDao.add(combustivel);
    }

    public void delete(Combustivel c) throws IOException {
        TanqueDAO tDAO = new TanqueDAO();
        for(Tanque t : tDAO.listAll()){
            if(t.getCombustivel().getId() == c.getId()) throw new ResourceInUseException(c.getNome(), "Tanque " + t.getId());
        }
        cDao.deleteByID(c.getId());
    }

    public Combustivel[] listAll() throws IOException {
        return cDao.listAll();
    }

    public Combustivel findById(int id) throws IOException {
        return cDao.findByID(id);
    }

    public void update(Combustivel combustivel) throws IOException {
        cDao.update(combustivel);
    }

    public Combustivel[] findByName(String name) throws IOException {
        return cDao.findByNome(name);
    }
}
