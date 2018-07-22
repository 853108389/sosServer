package com.kk.apollo.utils;

import com.kk.apollo.biz.model.common.ModelWithMessageVo;

/**
 * Created by Administrator on 2017/12/29.
 */
public class MessageUtils {
    public static ModelWithMessageVo resultTotalCheck(Integer integer) {
        return resultTotalCheck(integer, "成功", "影响表记录数小于0");
    }

    public static Boolean resultTotalFlag(Integer integer) {
        if (integer != null && integer > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static ModelWithMessageVo resultTotalCheck(Integer integer, String sucMes, String failMes) {
        if (integer != null && integer > 0) {
            return DataParser.setMessageVoProps(true, sucMes, null, integer);
        } else {
            return DataParser.setMessageVoProps(false, failMes, null, 0);
        }
    }

    public static ModelWithMessageVo fail() {
        return fail("失败");
    }

    public static ModelWithMessageVo fail(String message) {
        return DataParser.setMessageVoProps(false, message, null, 0);
    }

}
