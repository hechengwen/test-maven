package com.cn.hcw.utils;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Calendar;
import java.util.Date;

/**
 * Copyright (C), 2017，Beijing Zipi Wealth Network Technology Co., Ltd.
 * Author: hechengwen
 * Version: 1.0
 * Date: 2017/4/26 0026
 * Description:
 * Others:
 */
public class Duration {

    private static final long serialVersionUID = 20130918L;

    private int years;

    private int months;

    private int days;

    private Date settledDate;

    public Duration() {
    }

    public Duration(final int years,
                    final int months,
                    final int days) {
        this.years = years;
        this.months = months;
        this.days = days;
    }



    public Duration(final int years,
                    final int months,
                    final int days,
                    final Date settledDate) {
        this.years = years;
        this.months = months;
        this.days = days;
        this.settledDate = settledDate;
    }

    public int getYears() {
        return years;
    }

    public int getMonths() {
        return months;
    }

    public int getDays() {
        return days;
    }


    /**
     * 根据结算日期，计算真实的按天计算利息的天数（目前直投标用）
     *
     * @return
     */
    private int calcTotalDaysBySettledDate(){
        Calendar dt1 = Calendar.getInstance();
        Calendar dt2 = Calendar.getInstance();
        dt1.set(settledDate.getYear(),
                settledDate.getMonth(),
                settledDate.getDate());
        dt2.set(settledDate.getYear(),
                settledDate.getMonth(),
                settledDate.getDate());
        dt2.add(Calendar.YEAR, years);
        dt2.add(Calendar.MONTH, months);
        dt2.add(Calendar.DAY_OF_YEAR, days);

        long daterange = dt2.getTimeInMillis() - dt1.getTimeInMillis();
        Double oneday = Double.parseDouble(String.valueOf(1000 * 3600 * 24)); // A
        // day
        // in
        // milliseconds

        return (int) Math.round(Double.parseDouble(String
                .valueOf(daterange)) / oneday);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(years).append(months).append(days).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Duration other = (Duration) obj;
        if (this.years != other.years) {
            return false;
        }
        if (this.months != other.months) {
            return false;
        }
        if (this.days != other.days) {
            return false;
        }
        return true;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public void setMonths(int months) {
        this.months = months;
    }

    public void setDays(int days) {
        this.days = days;
    }

}
