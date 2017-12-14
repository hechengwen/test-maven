package com.cn.hcw.utils;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.Years;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.apache.commons.lang3.time.DateUtils.addDays;
import static org.apache.commons.lang3.time.DateUtils.addMonths;
import static org.apache.commons.lang3.time.DateUtils.addYears;

/**
 * Copyright (C), 2017，Beijing Zipi Wealth Network Technology Co., Ltd.
 * Author: hechengwen
 * Version: 1.0
 * Date: 2017/4/26 0026
 * Description:
 * Others:
 */
public class DateUtils {
    public static final String DATE_SIMPLE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_SIMPLE_FORMAT1 = "yyyy/MM/dd";
    public static SimpleDateFormat sdfMD=new SimpleDateFormat("M月d日");
    public static SimpleDateFormat  sdfYMD=new SimpleDateFormat("yyyy年M月d日");
    public static SimpleDateFormat  sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static SimpleDateFormat  sdf_Y=new SimpleDateFormat("yyyy-MM-dd");
    public static SimpleDateFormat  sdf_H=new SimpleDateFormat("HH:mm:ss");
    public static SimpleDateFormat  sdf_YMD=new SimpleDateFormat("yyyyMMdd");
    public static SimpleDateFormat  sdf_YMD_local=new SimpleDateFormat("yyyy年M月dd日");
    public static SimpleDateFormat  sdf_MD=new SimpleDateFormat("MM-dd");
    public static SimpleDateFormat  sdf_DHM=new SimpleDateFormat("dd日 HH:mm");
    static Logger logger = LoggerFactory.getLogger(DateUtils.class);

    private static final GregorianCalendar calendar = (GregorianCalendar) GregorianCalendar.getInstance();
    public static final Date FIRST_DATE = new Date(0);

    public static Date offset(final Date asOfDate, final Duration duration) {
        Date result = addYears(asOfDate, duration.getYears());
        result = addMonths(result, duration.getMonths());
        result = addDays(result, duration.getDays());
        return result;
    }

    /**
     * Joda Time versoin of offset
     *
     * @param asOfDate
     * @param duration
     * @return
     */
    public static LocalDate offset(LocalDate asOfDate, Duration duration) {
        return asOfDate.plusDays(duration.getDays()).plusMonths(duration.getMonths()).plusYears(duration.getYears());
    }

    /**
     * list all dates between start date and end date, both day included
     */
    public static List<Date> listDates(Date start, Date end) {
        List<Date> dates = new ArrayList();

        Date date = start;
        calendar.setTime(start);
        while (date.before(end)) {
            dates.add(date);
            calendar.add(Calendar.DAY_OF_WEEK, 1);
            date = calendar.getTime();
        }
        if (date.equals(end)) {
            dates.add(end);
        }

        return dates;
    }

    /**
     * return the 0'clock time for a date, like 2013/8/1 0:0:0
     *
     * @param date
     * @return
     */
    public static Date get0OClock(Date date) {
        if (date == null) {
            return null;
        }

        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.set(year, month, day, 0, 0, 0);
        return calendar.getTime();
    }
    /**
     * return the 0'clock time for a date, like 2013/8/1 0:0:0
     *
     * @param date
     * @return
     */
    public static Date get0OClock2(Date date) {
        if (date == null) {
            return null;
        }

        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.set(year, month, day, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
    /**
     * 获取24点
     * @param date
     * @return
     */
    public static Date get24OClock(Date date) {
        if (date == null) {
            return null;
        }

        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.set(year, month, day, 23, 59, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    /**
     * 为使stock曲线平滑，填满遗漏的值
     *
     * @param data
     */
    public static void enrichStockData(Map<LocalDate, BigDecimal> data) {
        Map.Entry<LocalDate, BigDecimal> current = null;
        Map<LocalDate, BigDecimal> extraData = new HashMap();
        for (Map.Entry<LocalDate, BigDecimal> entry : data.entrySet()) {
            if (current == null) {
                current = entry;
            } else {
                LocalDate date = current.getKey().plusDays(1);
                while (date.isBefore(entry.getKey())) {
                    extraData.put(date, current.getValue());
                    date = date.plusDays(1);
                }
                current = entry;
            }
        }
        data.putAll(extraData);
    }

    /**
     * 根据身份证号计算年龄，按照周岁计算
     *
     * @param idNumber
     * @return 0 idNumber格式错误
     */
    public static int getAgeFromIdNumber(String idNumber) {
        LocalDate birthday = LocalDate.now();
        try {
            if (idNumber.length() == 18) {
                birthday = LocalDate.parse(idNumber.substring(6, 14), DateTimeFormat.forPattern("yyyyMMdd"));
            } else if (idNumber.length() == 15) {
                birthday = LocalDate.parse(idNumber.substring(6, 12), DateTimeFormat.forPattern("yyMMdd"));
            }
        } catch (Exception ex) {
            logger.error("Error happend when parse age from idNumber.[idNumber=" + idNumber + "]", ex);
        }
        return Years.yearsBetween(birthday, LocalDate.now()).getYears();
    }

    public static Date stringParseDate(String date, String patern) {
        SimpleDateFormat formatDate = new SimpleDateFormat(patern);
        try {
            return formatDate.parse(date);
        } catch (ParseException e) {
            logger.warn("sorry i formate  date false ");
        }
        return null;
    }

    public static String dateFormateString(Date date, String patern) {
        SimpleDateFormat formatDate = new SimpleDateFormat(patern);
        return formatDate.format(date);
    }

    public static Date dateFormateDate(Date date, String patern) {
        SimpleDateFormat formatDate = new SimpleDateFormat(patern);
        return stringParseDate(formatDate.format(date), patern);
    }

    public static Date getAddOneDate(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);
        return calendar.getTime();
    }

    /*
     *用于将当前天date加n天或者减n天后的时间  addorminus表示加减的天数 例 1表示加一天 -1表示减一天
     * */
    public static Date getAddOrMinusDate(Date date, int addorminus) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, addorminus);
        return calendar.getTime();
    }

    /***
     * 将日期格式字符串转化成YYYY-mm-dd格式字符串
     * @param dateStr
     * @return
     */
    public static String converDateStrToSimpleDateStr(String dateStr) {
        String result = "";
        try{
            Date date = stringParseDate(dateStr, DATE_SIMPLE_FORMAT);
            result = dateFormateString(date, DATE_SIMPLE_FORMAT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     *  返回债权首次还款日期：规则如下：
     借款人在31-15日自己的借款，账单日为每月30【31号的借款为次月30】
     借款人在16-30日之间的借款账单日为次月15日
     * @param date 初始借款日期
     * @return String
     * * @author haojunfu
     */
    public static Date claimFirstReturnDate(Date date){

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
        if(dayOfMonth<=15 || dayOfMonth==31 ){//处理31号和1号到15号之间的账单日
            if(dayOfMonth==31){//如果为31号，则增加一个月
                c.add(Calendar.MONTH, 1);
            }
            if(c.get(Calendar.MONTH)==1){// 特殊二月份处理【定位到28号】
                c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
            }else{
                c.set(Calendar.DAY_OF_MONTH, 30);
            }
        }else{//处理16号到30号之间的账单日，则为次月的15
            c.add(Calendar.MONTH, 1);//定位次月
            c.set(Calendar.DAY_OF_MONTH, 15);//定位15
        }
//		System.out.println("******************"+dateFormateString(c.getTime(),DATE_SIMPLE_FORMAT));
        return c.getTime();
    }

    /**
     * * @author haojunfu
     * 日期差天数(按照时间比较,如果不足一天会自动补足)
     * @param startDate 开始日期
     * @return 两日期差天数
     * @throws Exception
     */
    public static int betweenDays(Date startDate, Date endDate) throws Exception {
        long day = 24L * 60L * 60L * 1000L;
        String str1=dateFormateString(startDate, "yyyy-MM-dd");
        startDate=stringParseDate(str1, "yyyy-MM-dd");
        String str2=dateFormateString(endDate, "yyyy-MM-dd");
        endDate=stringParseDate(str2, "yyyy-MM-dd");
        return (int) (((endDate.getTime() - startDate.getTime()) /day));
//	   		return (int) Math.ceil((((date2.getTime() - date1.getTime()) / (24 * 60 * 60 * 1000d))));
    }


    /**
     * 日期只差，不足一天算就一天
     * 2016年6月2日  下午2:58:29
     * zhaoqiang
     */
    public static int betweenDaysCeil(Date date1, Date date2) throws Exception {
        long day = 24L * 60L * 60L * 1000L;
        String str1=dateFormateString(date1, "yyyy-MM-dd");
        date1=stringParseDate(str1, "yyyy-MM-dd");
        String str2=dateFormateString(date2, "yyyy-MM-dd");
        date2=stringParseDate(str2, "yyyy-MM-dd");
        return (int) Math.ceil((((date2.getTime() - date1.getTime()) / (24 * 60 * 60 * 1000d))));
    }


    /**
     * 日期只差，不足一天算一天
     * 2016年6月2日  下午2:58:29
     * zhaoqiang
     */
    public static int betweenDaysMax(Date date1, Date date2) throws Exception {
        return (int) Math.ceil((double)(date2.getTime() - date1.getTime())/1000/60/60/24);
    }

    /**
     * @author haojunfu
     * 日期差天数(和当前时间比)
     * @param date 比较日期
     * @return 和当前日期差天数
     * @throws Exception
     */
    public static int diff(Date date) throws Exception {
        return betweenDays(new Date(), date);
    }

    /**
     * 获取某日期时间范围
     * @param day
     * @return
     * @throws ParseException
     */
    public static Date[] getOneDayRange(String day) {
        if (StringUtils.isNotEmpty(day)) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                return new Date[]{sdf.parse(day+" 00:00:00"), sdf.parse(day+" 23:59:59")};
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    /**
     * 获取月息通的下一个回款日
     * @param date
     * @param billDateType
     * @return
     */
    public static Date getNextRepayDate(Date date, short billDateType){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
        int month =c.get(Calendar.MONTH);

        if(billDateType == 15){
            //日期大于15, 下一个月15日
            if(dayOfMonth>15){
                c.add(Calendar.MONTH, 1);
            }

            c.set(Calendar.DAY_OF_MONTH, 15);

            return c.getTime();
        }

        //30账单日

        //2月比较特殊, 以及1月31号 那么都是2月最后一天
        if(month == 1 || (month == 0 && dayOfMonth == 31)){
            c.set(Calendar.MONTH, 1);
            c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
            return c.getTime();
        }

        //小于等于30的,都返回当月30
        if(dayOfMonth<=30){
            c.set(Calendar.DAY_OF_MONTH, 30);
            return c.getTime();
        }

        //31号的情况,取下一个月的30号
        c.add(Calendar.MONTH, 1);
        c.set(Calendar.DAY_OF_MONTH, 30);

        return c.getTime();
    }


    /**
     * 获取上一个账单日
     * @param date
     * @param billDateType
     * @return
     */
    public static Date getPrevRepayDate(Date date, short billDateType){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
        int month =c.get(Calendar.MONTH);

        if(billDateType == 15){
            //日期大于15, 下一个月15日
            if(dayOfMonth<15){
                c.add(Calendar.MONTH, -1);
            }
            c.set(Calendar.DAY_OF_MONTH, 15);
            return c.getTime();
        }

        //30账单日

        //大于等于30的,都返回当月30
        if(dayOfMonth>=30){
            c.set(Calendar.DAY_OF_MONTH, 30);
            return c.getTime();
        }

        //3月比较特殊, 以及2月28,29号 那么都是2月最后一天
        if(month == 2 || (month == 1 && dayOfMonth == c.getActualMaximum(Calendar.DAY_OF_MONTH))){
            c.set(Calendar.MONTH, 1);
            c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
            return c.getTime();
        }

        //其他的情况,取上个月的30号
        c.add(Calendar.MONTH, -1);
        c.set(Calendar.DAY_OF_MONTH, 30);

        return c.getTime();
    }

    /**
     * 获取24小时前
     * @return
     */
    public static Date getBefore24HoursDate(){
        LocalDateTime time = new LocalDateTime();
        return time.minusHours(24).toDate();
    }

  /*  @Test
    public void fun1(){
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    	String dstr="2015-12-30";
    	try{
    		java.util.Date date=sdf.parse(dstr);
    		Date addOrMinusDate = DateUtils.getAddOrMinusDate(date,-1);
        	Date prevRepayDate = DateUtils.getPrevRepayDate(addOrMinusDate,Short.valueOf("30"));
        	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        	System.out.println(sf.format(prevRepayDate));
    	}catch(Exception e){
    		e.printStackTrace();
    	}


    }*/

    /**
     * 获取本月第一天
     * 2016年4月20日  下午4:50:08
     * zhaoqiang
     */
    public static  Date getFirstDayInTheMonth(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        return calendar.getTime();
    }

    /**
     * 日期加(天数)
     */
    public static java.util.Date addTimeByDay(java.util.Date date,int days) throws Exception
    {
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE,calendar.get(Calendar.DATE)+days);
        return calendar.getTime();
    }
    /**
     * 日期加(小时)
     * 2016年6月16日  下午3:26:20
     * zhaoqiang
     */
    public static Date addTimeByHour(Date date,int hour) throws Exception{
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR,calendar.get(Calendar.HOUR)+hour);
        return calendar.getTime();
    }
    /**
     * 获取两个日期之间的差值         示例::2天5小时59分钟49秒
     * 2016年6月16日  下午4:26:04
     * zhaoqiang
     */
    public static String  betweenTime(Date  date1 ,Date date2) throws Exception{
        int  mill=(int) (date2.getTime()-date1.getTime());
        mill=mill/1000;
        int second=mill%60;
        mill=(mill-second)/60;
        int minute=mill%60;
        mill=(mill-minute)/60;
        int hour=mill%24;
        int day=(mill-hour)/24;
        return day+"天"+hour+"小时"+minute+"分"+second+"秒";
    }
    /**
     * 获取当前时间与当天00:00:00之间所差得秒数 如现在是2016年7月27日13:40:31，则返回的结果为2016年7月27日13:40:31与2016年7月27日00:00:00之间的秒数
     * @author linzc
     */
    public static long betweenTimeSeconds(Date currentTime) throws RuntimeException{
        long milltime1 = get0OClock2(currentTime).getTime();
        long milltime2 = currentTime.getTime();
        return (milltime2-milltime1)/1000;
    }

    /**
     * 获取月份和天（例:8月31日）
     */
    public static String getMonthAndDay(Date date){
        if(null==date)return  null;
        return sdfMD.format(date);
    }
    /**
     * 获取相差小时数
     * @param date1
     * @param date2
     * @return
     */
    public static long getbetweenHours(Date date1,Date date2) throws Exception{
        long milltime1 = date1.getTime();
        long milltime2 = date2.getTime();
        return Math.abs(milltime2-milltime1)/(1000L*60L*60L);
    }
    /**
     * 获取时间进度(按小时)
     * @return
     */
    public static BigDecimal getDateProgressByHour(Date start,Date end) throws Exception{
        BigDecimal min = new BigDecimal("0.1");
        Date now=new Date();
        if(now.after(end)){
            return new BigDecimal("100");
        }else{
            BigDecimal hoursToEnd = new BigDecimal(getbetweenHours(start, end));
            BigDecimal hoursToNow = new BigDecimal(getbetweenHours(start, now));
            BigDecimal progress = new BigDecimal("100").multiply(hoursToNow.divide(hoursToEnd, 5, RoundingMode.HALF_EVEN));
            //不到0.1显示0.1
            if (BigDecimal.ZERO.compareTo(progress) <= 0 && progress.compareTo(min) < 0) {
                return min;
            }
            return progress.setScale(1, RoundingMode.DOWN);
        }
    }
    /**
     * 获取时间进度(按天)
     * @param start
     * @param end
     * @return
     */
    public static BigDecimal getDateProgressByDay(Date start,Date end) throws Exception{
        Date now=new Date();
        if(now.after(end)){
            return new BigDecimal("100");
        }else if(now.before(start)){
            return new BigDecimal("0");
        }else{
            BigDecimal daysToEnd = new BigDecimal(betweenDaysMax(start, end));
            BigDecimal daysToNow = new BigDecimal(betweenDaysMax(start, now));
            BigDecimal progress = new BigDecimal("100").multiply(daysToNow.divide(daysToEnd, 5, RoundingMode.HALF_EVEN));
            return progress.setScale(1, RoundingMode.DOWN);
        }
    }

    public static String getDiffTimeFormatHHmmss(Date beforeDate,Date afterDate){

        //两时间差,精确到毫秒
        long diff = afterDate.getTime() - beforeDate.getTime();
        long day = diff / 86400000;                         //以天数为单位取整
        long hour= diff % 86400000 / 3600000;               //以小时为单位取整
        long min = diff % 86400000 % 3600000 / 60000;       //以分钟为单位取整
        long seconds = diff % 86400000 % 3600000 % 60000 / 1000;   //以秒为单位取整
        //天时分秒
        return day*24+hour+"时"+min+"分"+seconds+"秒";
    }

}
