package com.game.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.common.template.ChapterTemplate;
import com.common.template.CheckPointTemplate;
import com.common.util.AssertUtil;
import com.game.config.ChapterConfig;
import com.game.config.ChapterStarGiftConfig;
import com.game.config.CheckPointConfig;
import com.game.dao.ChapterInfoDao;
import com.game.entity.ChapterInfo;
import com.game.entity.PointsDaoBean;
import com.game.util.CommonUtil;
import com.game.util.Params;

@Service
public class DuplicateService {

	@Autowired
	private ChapterInfoDao chapterInfoDao;

	/** 章节初始化开始，准备初始化参数 **/
	public void init(Long accountId, Integer chapterId) {
		ChapterTemplate chapterTemplate = null;
		if (null == chapterId) {
			chapterTemplate = ChapterConfig.list.get(0);
			AssertUtil.asWarnTrue(null != chapterTemplate, "章节模板id错误");
			chapterId = chapterTemplate.getId();
		} else {
			for (ChapterTemplate chapter : ChapterConfig.list) {
				if (chapter.getId().equals(chapterId)) {
					chapterTemplate = chapter;
					break;
				}
			}
		}
		List<CheckPointTemplate> list = CheckPointConfig.map.get(chapterId);
		if (null != list && list.size() > 0) {
			duplicateInitialize(accountId, chapterId, chapterTemplate.getDifficulty().byteValue(), list);
		}
	}

	/** 章节初始化 **/
	@Transactional
	public void duplicateInitialize(Long accountId, Integer chapterId, Byte difficulty, List<CheckPointTemplate> list) {
		AssertUtil.asWarnTrue(null != chapterId, "章节模板id不能为空");
		AssertUtil.asWarnTrue(null != difficulty && difficulty > 0 && difficulty < 4, "难度等级不能为空，并且难度等级应该是1-3");
		list.stream().forEach(checkPoint -> {
			AssertUtil.asWarnTrue(null != checkPoint.getId(), "关卡模板id不能为空");
		});
		// 章节初始化
		ChapterInfo chapterInfo = new ChapterInfo();
		chapterInfo.setAccountId(accountId);
		chapterInfo.setChapterId(chapterId);
		chapterInfo.setDifficulty((int) difficulty);
		List<ChapterInfo> chapterInfoList = chapterInfoDao.findByChapterInfo(chapterInfo);
		if (null == chapterInfoList || chapterInfoList.size() == 0) {
			chapterInfoDao.create(chapterInfo);
		}
	}

	/** 查询用户章节信息 **/
	public List<ChapterInfo> findChapterList(Long accountId, Byte difficulty) {
		AssertUtil.asWarnTrue(null != difficulty && difficulty > 0 && difficulty < 4, "难度等级不能为空，并且难度等级应该是1-3");
		ChapterInfo chapterInfo = new ChapterInfo();
		chapterInfo.setAccountId(accountId);
		chapterInfo.setDifficulty((int) difficulty);
		List<ChapterInfo> chapterInfoList = chapterInfoDao.findByChapterInfo(chapterInfo);
		return chapterInfoList;
	}

	public ChapterInfo findById(long id) {
		return chapterInfoDao.findById(id);
	}

	/** 领取宝箱 **/
	public Byte receiveBox(Long accountId, Byte boxType, Long chapterInfoId, Byte boxIndex) {
		AssertUtil.asWarnTrue(boxType != null, "参数boxType不能为空！");
		Byte results = 0;// 0 领取宝箱失败 1领取宝箱成功
		if (boxType == 0) {
			ChapterInfo chapterInfo = chapterInfoDao.findById(chapterInfoId);
			AssertUtil.asWarnTrue(chapterInfo != null, "没有此章节宝箱");
			AssertUtil.asWarnTrue(chapterInfo.getChapterBox().intValue() != Params.chapterInfo_chapterBox_1.intValue(),
					"宝箱已领取完毕，无法再次领取！");
			int pointCount = CommonUtil.getPointCountByChapter(chapterInfo.getChapterId());

			List<PointsDaoBean> pointsList = CommonUtil.getPointList(chapterInfo.getPoints());
			AssertUtil.asWarnTrue(pointsList.size() == pointCount, "进度未完成，章节宝箱不能领取");

			chapterInfo.setChapterBox(Params.chapterInfo_chapterBox_1);
			chapterInfoDao.update(chapterInfo);
			// TODO gongb 发放奖励

			results = 1;

		} else {
			AssertUtil.asWarnTrue(chapterInfoId != null, "章节唯一标识不能为空！");
			ChapterInfo chapterInfo = chapterInfoDao.findById(chapterInfoId);
			AssertUtil.asWarnTrue(chapterInfo != null, "章节不存在");
			AssertUtil.asWarnTrue(boxIndex != null, "参数boxIndex不能为空！");

			String[] starArr = ChapterStarGiftConfig.map.get(chapterInfo.getChapterId()).getStar().split(Params.fenge);

			List<PointsDaoBean> list = CommonUtil.getPointList(chapterInfo.getPoints());
			Integer pointsStarSum = CommonUtil.getPointsStarSum(list);
			// 是否领取到了宝箱
			boolean flag = false;
			switch (boxIndex) {
			case 1:
				AssertUtil.asWarnTrue(chapterInfo.getStarLeveBox1().intValue() != 1, "宝箱已领取完毕，无法再次领取！！");
				AssertUtil.asWarnTrue(pointsStarSum >= Integer.valueOf(starArr[0]), "星星不足");
				chapterInfo.setStarLeveBox1(1);
				flag = true;
				// TODO gongb 调用方法，领取宝箱奖励

				break;
			case 2:
				AssertUtil.asWarnTrue(chapterInfo.getStarLeveBox2().intValue() != 1, "宝箱已领取完毕，无法再次领取！！");
				AssertUtil.asWarnTrue(pointsStarSum >= Integer.valueOf(starArr[1]), "星星不足");
				chapterInfo.setStarLeveBox2(1);
				flag = true;
				// TODO gongb 调用方法，领取宝箱奖励

				break;
			case 3:
				AssertUtil.asWarnTrue(chapterInfo.getStarLeveBox3().intValue() != 1, "宝箱已领取完毕，无法再次领取！！");
				AssertUtil.asWarnTrue(pointsStarSum >= Integer.valueOf(starArr[2]), "星星不足");
				chapterInfo.setStarLeveBox3(1);
				flag = true;
				// TODO gongb 调用方法，领取宝箱奖励

				break;
			default:
				AssertUtil.asWarnTrue(boxIndex <= 3 && boxIndex >= 1, "参数boxIndex范围超出！");
			}

			if (flag) {
				chapterInfoDao.update(chapterInfo);
				results = 1;
			}

		}
		return results;
	}

	/**
	 * 关卡扫荡
	 * 
	 * @param accountId
	 * @param chapterId
	 * @param pointId
	 * @param type
	 * @return
	 */
	public Byte saoDang(long accountId, Integer chapterId, Integer pointId, Byte type) {
		Byte results = 0;
		AssertUtil.asWarnTrue(type.byteValue() == 1 || type.byteValue() == 10, "扫荡类型不对！！");
		ChapterInfo tempChapterInfo = new ChapterInfo();
		tempChapterInfo.setAccountId(accountId);
		tempChapterInfo.setChapterId(chapterId);
		List<ChapterInfo> chapterInfoList = chapterInfoDao.findByChapterInfo(tempChapterInfo);
		AssertUtil.asWarnTrue(chapterInfoList.size() == 1, "章节不存在！！");
		ChapterInfo chapterInfo = chapterInfoList.get(0);
		List<PointsDaoBean> list = CommonUtil.getPointList(chapterInfo.getPoints());
		Optional<PointsDaoBean> pointsBeanOptional = list.stream().filter(pb -> pb.getPointId().intValue() == pointId)
				.findFirst();
		AssertUtil.asWarnTrue(pointsBeanOptional.isPresent(), "关卡不存在");
		PointsDaoBean pointsBean = pointsBeanOptional.get();
		AssertUtil.asWarnTrue(pointsBean.getStarCount() == 3, "必须满足3个星星才能扫荡！！");

//		CheckPointTemplate CheckPointTemplate = CheckPointConfig.map1.get(pointsBean.getPointId());
		// 查道具掉落表
//		Integer reward = CheckPointTemplate.getReward();
		// TODO 奖励道具 并且 通知客户端
		results = 1;
		return results;
	}

	public void checkpointProcedures(long accountId, int chapterId, int checkpointId) {

		
		
		ChapterInfo tempChapterInfo = new ChapterInfo();
		tempChapterInfo.setAccountId(accountId);
		tempChapterInfo.setChapterId(chapterId);
		List<ChapterInfo> chapterInfoList = chapterInfoDao.findByChapterInfo(tempChapterInfo);
		AssertUtil.asWarnTrue(chapterInfoList.size() == 1, "章节不存在！！");
		ChapterInfo chapterInfo = chapterInfoList.get(0);
		
		String[] levelIdArr = ChapterConfig.map.get(chapterInfo.getChapterId()).getLevelId().split(Params.fenge);
		AssertUtil.asWarnTrue(
				checkpointId >= Integer.valueOf(levelIdArr[0]) && checkpointId <= Integer.valueOf(levelIdArr[1]),
				"关卡不存在！！");

		List<PointsDaoBean> pointsBeanList = JSONObject.parseArray(chapterInfo.getPoints(), PointsDaoBean.class);
		if (pointsBeanList == null) {
			pointsBeanList = new ArrayList<PointsDaoBean>();
		}
		Optional<PointsDaoBean> opt = pointsBeanList.stream().filter(pb -> pb.getPointId().intValue() == checkpointId)
				.findFirst();
		PointsDaoBean pointsBean = null;
		if (opt.isPresent()) {
			pointsBean = opt.get();
			pointsBean.setStarCount(3);
		} else {
			pointsBean = new PointsDaoBean();
			pointsBean.setId(checkpointId);
			pointsBean.setPointId(checkpointId);
			pointsBean.setStarCount(3);
			pointsBeanList.add(pointsBean);

			if (CommonUtil.getPointCountByChapter(chapterInfo.getChapterId()) == pointsBeanList.size()) {
				// 关卡完成
				chapterInfo.setOver(Params.chapterInfo_over_1);
				// 开启新的章节
				// 判断时候还有下一个章节
				int chapterIdNext = chapterInfo.getChapterId() + 1;
				chapterInfo.setChapterBox(Params.chapterInfo_chapterBox_0);
				ChapterTemplate chapterTemplateNext = ChapterConfig.map.get(chapterIdNext);
				if (chapterTemplateNext != null) {
					ChapterInfo chapterInfoNext = new ChapterInfo();
					chapterInfoNext.setAccountId(accountId);
					chapterInfoNext.setChapterId(chapterIdNext);
					chapterInfoNext.setDifficulty(chapterInfo.getDifficulty());
					chapterInfoDao.create(chapterInfoNext);
				}
			}

		}
		
		String[] starArr = ChapterStarGiftConfig.map.get(chapterInfo.getChapterId()).getStar().split(Params.fenge);
		int starSum = 0;
		for(PointsDaoBean pb: pointsBeanList) {
			starSum += pb.getStarCount();
		}
		
		if(starSum>=Integer.valueOf(starArr[0]) && chapterInfo.getStarLeveBox1() == Params.chapterInfo_chapterBox_2) {
			chapterInfo.setStarLeveBox1(Params.chapterInfo_chapterBox_0);
		}
		if(starSum>=Integer.valueOf(starArr[1]) && chapterInfo.getStarLeveBox2() == Params.chapterInfo_chapterBox_2) {
			chapterInfo.setStarLeveBox2(Params.chapterInfo_chapterBox_0);
		}
		if(starSum>=Integer.valueOf(starArr[2]) && chapterInfo.getStarLeveBox3() == Params.chapterInfo_chapterBox_2) {
			chapterInfo.setStarLeveBox3(Params.chapterInfo_chapterBox_0);
		}
		chapterInfo.setPoints(JSONObject.toJSONString(pointsBeanList));
		chapterInfoDao.update(chapterInfo);
	}

}
