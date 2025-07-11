package com.astratech.backend_gstrack.Service;

import com.astratech.backend_gstrack.Repository.IDLRepository;
import com.astratech.backend_gstrack.VO.IDL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IDLService {
    @Qualifier("IDLRepository")
    @Autowired
    private IDLRepository idlRepository;

    public IDL getIDLbyIdlNoRequest(String idlNoRequest) { return idlRepository.findByIdlNoRequest(idlNoRequest); }

    public List<IDL> getAllIDL() { return idlRepository.findAllOrderByCustomStatusAndIdlCreatedDateDesc(); }

    public List<IDL> getIDLbyIdlNpk(String idlNpk) { return idlRepository.findByIdlNpkOrderByIdlCreatedDateDesc(idlNpk); }

    public List<IDL> getIDLbyIdlNpkAndIdlStatusAndIdlCreatedDate(String idlNpk, String idlStatus, Integer year) { return idlRepository.findByNpkAndStatusAndYear(idlNpk, idlStatus, year); }

    public List<IDL> getIDLbyIdlNpkAndIdlStatus(String idlNpk, String idlStatus) { return idlRepository.findByNpkAndStatus(idlNpk, idlStatus); }

    public List<IDL> getIDLbyIdlNpkAndIdlCreatedDate(String idlNpk, Integer year) { return idlRepository.findByNpkAndYear(idlNpk, year); }

    public boolean saveIDL(IDL idl) {
        IDL result = idlRepository.save(idl);
        return result != null;
    }

}
