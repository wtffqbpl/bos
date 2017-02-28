package com.feng.bos.service;

import com.feng.bos.domain.Decidedzone;
import com.feng.bos.utils.PageBean;

public interface DecidedzoneService {

	public void save(Decidedzone model,String[] subareaid);

	public void pageQuery(PageBean pageBean);

}
