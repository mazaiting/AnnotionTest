package com.mazaiting.annotiontest;

import android.support.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 测试源码注解
 * @author mazaiting
 * @date 2018/1/16
 */

public class SourceAnnotation {
    /**状态值*/
    public static final int STATUS_OPEN = 1;
    public static final int STATUS_CLOSE = 2;

    public static int mStatus = STATUS_OPEN;

    public static int getStatus() {
        return mStatus;
    }

    public static void setStatus(@Status int status) {
        mStatus = status;
    }

    public static String getStatusDesc() {
        if (mStatus == STATUS_OPEN) {
            return "打开状态";
        } else {
            return "关闭状态";
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @Target(ElementType.PARAMETER)
    @IntDef({STATUS_OPEN,STATUS_CLOSE})
    public @interface Status {
    }
}
