package cn.tofucat.toolkit.webapi.service.impl;

import cn.tofucat.toolkit.llc.LLC;
import cn.tofucat.toolkit.webapi.service.LLCService;
import org.springframework.stereotype.Service;

@Service
public class LLCServiceImpl implements LLCService {

    private final LLC llc;

    public LLCServiceImpl(LLC llc) {
        this.llc = llc;
    }

    @Override
    public String exec(String llcCode) {
        return llc.exec(llcCode);
    }
}
