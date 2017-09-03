package cn.it.shop.service;

import cn.it.shop.model.Forder;

public interface ForderService extends BaseService<Forder> {

	//计算价格
	public double cluTotal(Forder forder);

	public void updateStatusById(int id, int sid);
}
