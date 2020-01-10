package com.game.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.entity.Hero;
import com.common.util.AssertUtil;
import com.game.config.HeroConfig;
import com.game.dao.HeroDao;

@Service
public class HeroService {
	
	@Autowired
	private HeroDao heroDao;
	
//	private static final Logger logger = LoggerFactory.getLogger(MemberService.class);
	
	public List<Hero> getByList(Long accountId) {
		AssertUtil.asWarnTrue(accountId > 0, "accountId参数错误");
		return heroDao.getByList(accountId);
	}
	
	public Hero getById(Long heroId) {
		AssertUtil.asWarnTrue(heroId > 0, "heroId参数错误");
		return heroDao.getById(heroId);
	}

	public Hero add(Long accountId, Integer templateId) {
		AssertUtil.asWarnTrue(HeroConfig.map.containsKey(templateId), "该英雄模板Id不存在");
		Boolean isExistHero = heroDao.isExistHero(accountId, templateId);
		AssertUtil.asWarnTrue(!isExistHero, "该英雄已经存在");
		Hero hero = new Hero();
		hero.setAccountId(accountId);
//		hero.setTemplateId(templateId);
		heroDao.add(hero);
		return hero;
	}
	
	@Transactional
	public void addAllHero(Long accountId) {
		HeroConfig.map.forEach((heroTemplateId, heroTemplate) -> {
			add(accountId, heroTemplateId);
		});
	}
}
