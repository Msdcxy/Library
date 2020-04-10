/**
 * Copyright (C) 2015  Haiyang Yu Android Source Project
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.common.lib.utils;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.ScaleXSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TextAppearanceSpan;
import android.text.style.TypefaceSpan;
import android.text.style.UnderlineSpan;

import androidx.annotation.ColorRes;

/**
 * Created by dengdayi
 * Date: 2019/01/22 17：45
 * 特殊字符处理
 */
public class TextViewUtils {

    /**
     * 设置字体大小，用px
     *
     * @param str    目标字符串
     * @param start  开始位置
     * @param end    结束位置
     * @param pxSize 像素大小
     * @return
     */
    public static SpannableString getSizeSpanUsePx(String str, int start, int end, int pxSize) {
        SpannableString ss = new SpannableString(str);
        ss.setSpan(new AbsoluteSizeSpan(pxSize), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ss;
    }

    /**
     * 设置字体大小，用dip
     *
     * @param str     目标字符串
     * @param start   开始位置
     * @param end     结束位置
     * @param dipSize 像素大小
     * @return
     */
    public static SpannableString getSizeSpanUseDip(String str, int start, int end, int dipSize) {
        SpannableString ss = new SpannableString(str);
        ss.setSpan(new AbsoluteSizeSpan(dipSize, true), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ss;
    }

    /**
     * 设置字体大小，用sp
     *
     * @param context
     * @param str     目标字符串
     * @param start   开始位置
     * @param end     结束位置
     * @param spSize  sp大小
     * @return
     */
    public static SpannableString getSizeSpanSpToPx(Context context, String str, int start, int end, int spSize) {
        SpannableString ss = new SpannableString(str);
        ss.setSpan(new AbsoluteSizeSpan(DensityUtils.sp2px(context, spSize)), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ss;
    }

    /**
     * 设置字体相对大小
     *
     * @param str          目标字符串
     * @param start        开始位置
     * @param end          结束位置
     * @param relativeSize 相对大小 如：0.5f，2.0f
     * @return
     */
    public static SpannableString getRelativeSizeSpan(String str, int start, int end, float relativeSize) {
        SpannableString ss = new SpannableString(str);
        ss.setSpan(new RelativeSizeSpan(relativeSize), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ss;
    }

    /**
     * 设置字体
     *
     * @param str      目标字符串
     * @param start    开始位置
     * @param end      结束位置
     * @param typeface 字体类型 如：default，efault-bold,monospace,serif,sans-serif
     * @return
     */
    public static SpannableString getTypeFaceSpan(String str, int start, int end, String typeface) {
        SpannableString ss = new SpannableString(str);
        ss.setSpan(new TypefaceSpan(typeface), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ss;
    }

    /**
     * 设置字体形体
     *
     * @param str   目标字符串
     * @param start 开始位置
     * @param end   结束位置
     * @param style 字体类型 如： Typeface.NORMAL正常 Typeface.BOLD粗体 Typeface.ITALIC斜体
     *              Typeface.BOLD_ITALIC粗斜体
     * @return
     */
    public static SpannableString getStyleSpan(String str, int start, int end, int style) {
        SpannableString ss = new SpannableString(str);
        ss.setSpan(new StyleSpan(style), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ss;
    }

    public static SpannableString getStyleSpan2(Context context, String str, int start, int end, int style) {
        SpannableString ss = new SpannableString(str);
        ss.setSpan(new TextAppearanceSpan(context, style), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ss;
    }

    /**
     * 设置字体下划线
     *
     * @param str   目标字符串
     * @param start 开始位置
     * @param end   结束位置
     * @return
     */
    public static SpannableString getUnderLineSpan(String str, int start, int end) {
        SpannableString ss = new SpannableString(str);
        ss.setSpan(new UnderlineSpan(), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ss;
    }

    /**
     * 设置字体删除线
     *
     * @param str   目标字符串
     * @param start 开始位置
     * @param end   结束位置
     * @return
     */
    public static SpannableString getDeleteLineSpan(String str, int start, int end) {
        SpannableString ss = new SpannableString(str);
        ss.setSpan(new StrikethroughSpan(), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ss;
    }

    /**
     * 设置上标
     *
     * @param str   目标字符串
     * @param start 开始位置
     * @param end   结束位置
     * @return
     */
    public static SpannableString getSuperscriptSpan(String str, int start, int end) {
        SpannableString ss = new SpannableString(str);
        ss.setSpan(new SuperscriptSpan(), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ss;
    }

    /**
     * 设置放大系数
     *
     * @param str   目标字符串
     * @param start 开始位置
     * @param end   结束位置
     * @param scale 放大多少倍，x轴方向，y轴不变 如：0.5f， 2.0f
     * @return
     */
    public static SpannableString getScaleSpan(String str, int start, int end, float scale) {
        SpannableString ss = new SpannableString(str);
        ss.setSpan(new ScaleXSpan(scale), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ss;
    }

    /**
     * 设置下标
     *
     * @param str   目标字符串
     * @param start 开始位置
     * @param end   结束位置
     * @return
     */
    public static SpannableString getSubscriptSpan(String str, int start, int end) {
        SpannableString ss = new SpannableString(str);
        ss.setSpan(new SubscriptSpan(), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ss;
    }

    /**
     * 设置背景色
     *
     * @param str   目标字符串
     * @param start 开始位置
     * @param end   结束位置
     * @param color 颜色值 如Color.BLACK
     * @return
     */
    public static SpannableString getBackGroundColorSpan(String str, int start, int end, int color) {
        SpannableString ss = new SpannableString(str);
        ss.setSpan(new BackgroundColorSpan(color), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ss;
    }

    /**
     * 设置背景色
     *
     * @param str   目标字符串
     * @param start 开始位置
     * @param end   结束位置
     * @param color 颜色值 如：#CCCCCC
     * @return
     */
    public static SpannableString getBackGroundColorSpan(String str, int start, int end, String color) {
        SpannableString ss = new SpannableString(str);
        ss.setSpan(new BackgroundColorSpan(Color.parseColor(color)), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ss;
    }

    /**
     * 设置前景色
     *
     * @param str   目标字符串
     * @param start 开始位置
     * @param end   结束位置
     * @param color 颜色值 如Color.BLACK
     * @return
     */
    public static SpannableString getForegroundColorSpan(String str, int start, int end, int color) {
        SpannableString ss = new SpannableString(str);
        ss.setSpan(new ForegroundColorSpan(color), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ss;
    }

    /**
     * 设置前景色
     *
     * @param str   目标字符串
     * @param start 开始位置
     * @param end   结束位置
     * @param color 颜色值 如：#CCCCCC
     * @return
     */
    public static SpannableString getForegroundColorSpan(String str, int start, int end, String color) {
        SpannableString ss = new SpannableString(str);
        ss.setSpan(new ForegroundColorSpan(Color.parseColor(color)), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ss;
    }

    public static SpannableString getForegroundColorSpan(String str, String start, String end, String color) {
        SpannableString ss = new SpannableString(str);
        int indexNumber = str.indexOf(start);
        int endNumber = str.indexOf(end);
        if (indexNumber >= 0 && endNumber >= 0) {
            ss.setSpan(new ForegroundColorSpan(Color.parseColor(color)), indexNumber + 1, endNumber, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return ss;
    }

    public static SpannableString getForegroundColorSpan(String str, String end, String color) {
        SpannableString ss = new SpannableString(str);
        int endNumber = str.indexOf(end);
        if (endNumber >= 0) {
            ss.setSpan(new ForegroundColorSpan(Color.parseColor(color)), 0, endNumber + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return ss;
    }


    public static SpannableString getColorSpan(String str, int start, int color) {
        SpannableString ss = new SpannableString(str);
        ss.setSpan(new ForegroundColorSpan(color), start, str.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ss;
    }

    public static SpannableString getColorSpan(String str, int start, int end, int color) {
        SpannableString ss = new SpannableString(str);
        ss.setSpan(new ForegroundColorSpan(color), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ss;
    }

    public static SpannableString getColorSpan(String str, int start, String color) {
        SpannableString ss = new SpannableString(str);
        ss.setSpan(new ForegroundColorSpan(Color.parseColor(color)), start, str.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ss;
    }

    /**
     * 设置前景色
     *
     * @param context
     * @param str     目标字符串
     * @param start   开始位置
     * @param end     结束位置
     * @param resId   颜色值 如：R.color.white
     * @return
     */
    public static SpannableString getForegroundColorSpan(Context context, String str, int start, int end, @ColorRes int resId) {
        SpannableString ss = new SpannableString(str);
        int color = context.getResources().getColor(resId);
        return getForegroundColorSpan(str, start, end, color);
    }

    public static SpannableString getColorAndSizeSpan(String str, int start, int end, String color, int pxSize) {
        SpannableString ss = new SpannableString(str);
        ss.setSpan(new ForegroundColorSpan(Color.parseColor(color)), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new AbsoluteSizeSpan(pxSize), 4, str.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ss;
    }

    public static SpannableString getColorAndSizeSpan(String str, int start, int end, int color, int pxSize) {
        SpannableString ss = new SpannableString(str);
        ss.setSpan(new ForegroundColorSpan(color), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new AbsoluteSizeSpan(pxSize), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ss;
    }

}
