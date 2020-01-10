package com.game.util;

import com.common.template.MemberTemplate;
import com.game.config.MemberConfig;
import com.game.config.ParameterConfig;

public class PowerUtils {

	/**
	 * 当前属性值= Dynamic + （队员等级-1） * GrowthDynamic 战斗力=(活力+操作+专注)* 5+(坚韧+潜能)*3
	 * 
	 * 
	 * @return
	 */
	public static int getPower(MemberTemplate memberTemplate, int level, int stage) {

		int preMemberId = memberTemplate.getPreMemberId();
		MemberTemplate memberTemplateStar1 = null;
		if (memberTemplate.getStars().intValue() != 1) {
			memberTemplateStar1 = MemberConfig.list.stream()
					.filter(p -> p.getPreMemberId().intValue() == preMemberId && p.getStars() == 1).findFirst().get();
		} else {
			memberTemplateStar1 = memberTemplate;
		}
		int dynamic = memberTemplate.getDynamic();
		int growthDynamic = memberTemplate.getGrowthDynamic();
		int operation = memberTemplate.getOperation();
		int growthOperation = memberTemplate.getGrowthOperation();
		int focus = memberTemplate.getFocus();
		int growthFocus = memberTemplate.getGrowthFocus();
		int tough = memberTemplate.getTough();
		int growthTough = memberTemplate.getGrowthTough();
		int potential = memberTemplate.getPotential();
		int growthPotential = memberTemplate.getGrowthPotential();

		int p3 = Integer.valueOf(ParameterConfig.map.get(Params.parameter_670).getValue());
		int p2 = Integer.valueOf(ParameterConfig.map.get(Params.parameter_680).getValue());

		double jinjie = Math.max(Math.pow(stage / 1.5, 1.5), 1);
		int memberDynamicZb = 0;
		int memberOperationZb = 0;
		int memberFocusZb = 0;
		int memberToughZb = 0;
		int memberPtentialZb = 0;
		
		Double dynamicTotalPower = (dynamic + level * growthDynamic + memberDynamicZb + memberTemplateStar1.getDynamic() * (jinjie - 1));
		Double operationTotalPower = (operation + level * growthOperation + memberOperationZb + memberTemplateStar1.getOperation() * (jinjie - 1));
		Double focusTotalPower = (focus + level * growthFocus + memberFocusZb + memberTemplateStar1.getFocus() * (jinjie - 1));
		Double toughTotalPower = (tough + level * growthTough + memberToughZb + memberTemplateStar1.getTough() * (jinjie - 1));
		Double potentialTotalPower = (potential + level * growthPotential + memberPtentialZb + memberTemplateStar1.getPotential() * (jinjie - 1));

		Double power = (dynamicTotalPower +operationTotalPower
				+ focusTotalPower) * p3
				+ (toughTotalPower + potentialTotalPower) * p2;
		return power.intValue();
	}

}
