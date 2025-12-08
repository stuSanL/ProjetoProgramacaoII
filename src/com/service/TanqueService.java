package com.service;

import com.Paths;
import com.dao.IdDAO;
import com.dao.TanqueDAO;
import com.model.Combustivel;
import com.model.Tanque;

import java.io.IOException;

public class TanqueService {

    private final TanqueDAO tDao;
    private final IdDAO idDao;
    private final String idPath;

    public TanqueService() {
        tDao = new TanqueDAO();
        idDao = new IdDAO();
        idPath = Paths.IDS.getAbsPath() + "/tanque";
    }

    public void add(Tanque t) throws IOException {
        t.setId(idDao.recordId(idPath));
        tDao.add(t);
    }

    public void delete(Tanque t){
        tDao.deleteByID(t.getId());
    }

    public Tanque[] listAll() throws IOException {
        return tDao.listAll();
    }

    public Tanque findById(int id) throws IOException {
        return tDao.findByID(id);
    }

    public void update(Tanque t) throws IOException {
        tDao.update(t);
    }

    public Tanque[] findByCombustivel(Combustivel c) throws IOException {
        return tDao.findByCombustivel(c);
    }

}
