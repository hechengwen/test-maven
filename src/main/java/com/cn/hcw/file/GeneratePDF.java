package com.cn.hcw.file;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * Copyright (C), 2017，Beijing Zipi Wealth Network Technology Co., Ltd.
 * Author: hechengwen
 * Version: 1.0
 * Date: 2017/5/25 0025
 * Description:
 * Others:
 */
@Slf4j
public class GeneratePDF {

    private static final String CN_FONT_FAMILY = "STSongStd-Light";

    private static final String ENCODING = "UniGB-UCS2-H";

    private static String templateFile = null;
    //主标题
    private static final String TITLE_MAIN="收益权转让委托代理协议";
    //声明
    private static final String ENTRY_STATEMENT="就甲方通过由乙方运营管理的优点智投 （域名为www.udrmb.com）平台（下称“优点智投”）授权委托乙方转让其投资所形成的收益权事宜，双方根据平等、自愿的原则，达成协议如下：";
    //标题1
    private static final String TITLE_1="1.收益权转让情况";
    //标题1.2
    private static final String TITLE_1_2="1.2委托事项及费用";
    //标题2
    private static final String TITLE_2="2.保证与承诺";
    //标题3
    private static final String TITLE_3="3.违约条款";
    //标题4
    private static final String TITLE_4="4.适用法律和争议解决";
    //标题5
    private static final String TITLE_5="5.保密条款";
    //标题6
    private static final String TITLE_6="6.其他约定";

    //标题字体
    private static Font titleFont = getCustomFont(12.0F,1);

    //段落字体
    private static Font paragraphFont = getParagraphFont();

    //段落1
    private static final StringBuffer PAGE_1=new StringBuffer();
    //段落1.2
    private static final StringBuffer PAGE_1_2=new StringBuffer();
    //段落2
    private static final StringBuffer PAGE_2=new StringBuffer();
    //段落3
    private static final StringBuffer PAGE_3=new StringBuffer();
    //段落4
    private static final StringBuffer PAGE_4=new StringBuffer();
    //段落5
    private static final StringBuffer PAGE_5=new StringBuffer();
    //段落6
    private static final StringBuffer PAGE_6=new StringBuffer();

    static {
        PAGE_1.append("1.1标的收益权信息及转让" +
                "\n甲方同意将其通过优点智投进行投资所形成的有关收益权（下称“标的收益权”）委托优点智投进行转让。" +
                "\n1.1.1标的收益权信息" +
                "\n(见附件)" +
                "\n1.1.2标的收益权转让信息" +
                "\n转让本金：%s元" +
                "\n转让价格：%s元" +
                "\n委托转让手续费：%s元" +
                "\n");
        PAGE_1_2.append("1.2.1委托代理事项：甲方委托乙方代为寻找，办理转让的其收益权的事项。" +
                "\n1.2.2委托转让手续费从甲方收到的成功收益权转让获得的价款中按上述约定收取。" +
                "\n1.2.3甲方收到结算资金日起不再拥有被转让收益权的任何权利，收益权受让人从转让日起享有该笔转让收益权的所有权利，并承担相应义务。" +
                "\n");
        PAGE_2.append("2.1甲方保证其转让的收益权系其合法、有效的收益权，不存在转让的限制。甲方同意并承诺按有关协议及优点智投网站的相关规则和说明向优点智投支付收益权转让手续费。" +
                "\n");
        PAGE_3.append("3.1本协议任意一方违反其在本协议中所作的保证、承诺或任何其他义务，致使其他方遭受或发生损害、损失等责任，违约方须向守约方赔偿守约方因此遭受的一切经济损失。" +
                "\n");
        PAGE_4.append("4.1本协议的订立、效力、解释、履行、修改和终止以及争议的解决适用中国的法律。" +
                "\n4.2本协议在履行过程中，如发生任何争执或纠纷，协议双方应友好协商解决；若协商不成，任何一方均有权向乙方所在地有管辖权的人民法院提起诉讼。" +
                "\n");
        PAGE_5.append("5.1协议双方应对在本协议签订过程中所获悉的其他方信息，包括但不限于身份、财务及商业信息等，承担保密义务。非经其他方书面同意，任何一方均不得将上述信息对外进行披露。" +
                "\n");
        PAGE_6.append("6.1协议双方可以书面协议方式对本协议作出修改和补充。经过双方签署的有关本协议的修改协议和补充协议是本协议的组成部分，具有与本协议同等的法律效力。" +
                "\n6.2本协议及其修改或补充均通过优点智投以电子文本形式制成，可以有一份或者多份并且每一份具有同等法律效力；同时甲方委托优点智投代为保管并永久保存，并认可该形式的协议效力。" +
                "\n6.3双方均确认，本协议的签订、生效和履行以不违反中国的法律法规为前提。如果本协议中的任何一条或多条违反适用的法律法规，则该条将被视为无效，但该无效条款并不影响本协议其他条款的效力。" +
                "\n6.4除本协议上下文有定义外，本协议项下的用语和定义应具有优点智投服务协议及其有关规定中定义的含义。若有冲突，则以本协议为准。" +
                "\n（以下无内容）" +
                "\n");
    }

    private static String gettemplateFile() {
        /****path*******/
        //templateFile=new SealAutoServceImpl().getClass().getResource("/").getPath();
        templateFile = new File("").getAbsolutePath();
        //windows下
        if ("\\".equals(File.separator)) {
            templateFile = templateFile.substring(0, templateFile.indexOf("\\"));
            templateFile = templateFile.replace("/", "\\");
        }
        //linux下
        if ("/".equals(File.separator)) {
            templateFile = System.getProperty("user.dir");
            templateFile = templateFile.substring(0, templateFile.indexOf("/"));
            templateFile = templateFile.replace("\\", "/");
        }
        System.out.println("templateFile****is******* = " + templateFile);
        return templateFile;
        /*****path******/
    }

    public static void main(String[] args) {
        try {
            long start = System.currentTimeMillis();
            gettemplateFile();
            writeFile(writeSimplePdf());
            long end = System.currentTimeMillis();
            long miao = end - start;
//            readFile();
            System.out.println("耗时：" + miao + "毫秒！！!");
        } catch (Exception e) {
            System.out.println("生成PDF文件失败！！！");
        }

        File file = new File("D:\\var" + File.separator);
        log.info(""+ gettemplateFile() + File.separator);
        lists(file);

    }

    public static void lists(File file){
        if (file.isDirectory()){
            File[] files = file.listFiles();
            if (files != null){
                for (int i = 0; i<=files.length-1;i++){
                    lists(files[i]);
                    if (files[i].isFile()){
                        System.out.println(files[i]);
                    }
                }
            }
        }
    }

    public static Document createReportDocument(){
        Document document = new Document(PageSize.A4);
        document.setMargins(40.0F, 40.0F, 80.0F, 50.0F);
        return document;
    }

    public static byte[] writeSimplePdf() throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document document = createReportDocument();
        PdfWriter pdfWriter = getInstanceForPdfWriterWithoutLogo(document, outputStream);
        document.open();
        document.add(createTitleParagraph("FIRST   PDF", getTitleFont(), Element.ALIGN_CENTER));
        document.add(new Paragraph("\n"));
        document.add(createCustomerParagraph("First page of the  document.",paragraphFont,Element.ALIGN_RIGHT,0f,0f,0f));
        document.add(new Paragraph("\n"));
        document.add(createTitleParagraph(TITLE_1,titleFont,Element.ALIGN_CENTER));
        document.add(createCustomerParagraph(String.format(PAGE_1.toString(),"1000","1900","100"),paragraphFont,Element.ALIGN_LEFT,0f,0f,0f));
        document.add(createTitleParagraph(TITLE_1_2,titleFont,Element.ALIGN_CENTER));
        document.add(createCustomerParagraph(PAGE_1_2.toString(),paragraphFont,Element.ALIGN_LEFT,0f,0f,0f));
        document.add(createTitleParagraph(TITLE_2,titleFont,Element.ALIGN_CENTER));
        document.add(createCustomerParagraph(PAGE_2.toString(),paragraphFont,Element.ALIGN_LEFT,0f,0f,0f));
        document.add(createTitleParagraph(TITLE_3,titleFont,Element.ALIGN_CENTER));
        document.add(createCustomerParagraph(PAGE_3.toString(),paragraphFont,Element.ALIGN_LEFT,0f,0f,0f));
        document.add(createTitleParagraph(TITLE_4,titleFont,Element.ALIGN_CENTER));
        document.add(createCustomerParagraph(PAGE_4.toString(),paragraphFont,Element.ALIGN_LEFT,0f,0f,0f));
        document.add(createTitleParagraph(TITLE_5,titleFont,Element.ALIGN_LEFT));
        document.add(createCustomerParagraph(PAGE_5.toString(),paragraphFont,Element.ALIGN_LEFT,0f,0f,0f));
        document.add(createTitleParagraph(TITLE_6,titleFont,Element.ALIGN_LEFT));
        document.add(createCustomerParagraph(PAGE_6.toString(),paragraphFont,Element.ALIGN_LEFT,0f,0f,0f));
        document.add(createTitleParagraph(TITLE_6,titleFont,Element.ALIGN_LEFT));
        document.add(createCustomerParagraph(PAGE_6.toString(),paragraphFont,Element.ALIGN_LEFT,0f,0f,0f));
        document.add(createTitleParagraph(TITLE_6,titleFont,Element.ALIGN_LEFT));
        document.add(createCustomerParagraph(PAGE_6.toString(),paragraphFont,Element.ALIGN_LEFT,0f,0f,0f));
        document.add(createTitleParagraph(TITLE_6,titleFont,Element.ALIGN_LEFT));
        document.add(createCustomerParagraph(PAGE_6.toString(),paragraphFont,Element.ALIGN_LEFT,0f,0f,0f));
        document.add(createTitleParagraph(TITLE_6,titleFont,Element.ALIGN_LEFT));
        document.add(createCustomerParagraph(PAGE_6.toString(),paragraphFont,Element.ALIGN_LEFT,0f,0f,0f));
        document.newPage();
        table(document,getTableFont());
        // 5.关闭文档
        document.close();
        log.info("pdf文档绘画完成");
        return outputStream.toByteArray();
    }

    public static void table(Document document,Font font) throws DocumentException {
        PdfPTable table = new PdfPTable(3);
        PdfPTable pTable = new PdfPTable(2);
        pTable.addCell(createCell("I'm inner",font));
        pTable.addCell(createCell("I'm 中文",font));
        pTable.addCell(createCell("I'm inner",font));
        pTable.addCell(createCell("I'm inner",font));
        table.setWidths(new float[]{10, 10, 40});
        table.addCell(createCell("姓名",font));
        table.addCell(createCell("手机",font));
        table.addCell(createCell("身份证",font));
        table.addCell(createCell("111",font));
        table.addCell(createCell("111",font));
        table.addCell(createCell("111",font));
        table.addCell(createCell("222",font));
        table.addCell(createCell("222",font));
        table.addCell(pTable);
        table.addCell(createCell("总计",font));
        PdfPCell p = new PdfPCell(createCell("100000元",font));
        p.setColspan(2);
        table.addCell(p);
        document.add(table);
        log.info("表格画完！！");
    }

    /**
     * 创建指定字体的表格Cell,
     * @param content 表格内容
     * @param font 字体
     * @return PdfPCell
     */
    public static PdfPCell createCell(String content,Font font){
        PdfPCell cell = null;
        if(content == null){
            cell = new PdfPCell();
        }else{
            cell = new PdfPCell(new Paragraph(content,font));
        }
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setMinimumHeight(25f);
        cell.setBorderColor(BaseColor.RED);
//        cell.setBorderColorRight(BaseColor.PINK);
//        cell.setBorderWidthRight(0.5f);
//        cell.setBorderColorLeft(BaseColor.YELLOW);
//        cell.setBorderWidthLeft(0.5f);
//        cell.setBorderColorTop(BaseColor.GREEN);
//        cell.setBorderWidthTop(0.5f);
//        cell.setBorderColorBottom(BaseColor.RED);
//        cell.setBorderWidthBottom(0.5f);
        return cell;
    }

    public static boolean createDirectory(String descDirName) {
        String descDirNames = templateFile + File.separator + "wakaka" + File.separator + descDirName;
        if (!descDirNames.endsWith(File.separator)) {
            descDirNames = descDirNames + File.separator;
        }
        File descDir = new File(descDirNames);
        if (descDir.exists()) {
            return false;
        }
        // 创建目录
        if (descDir.mkdirs()) {
            return true;
        } else {
            return false;
        }
    }

    public static File writeFile(byte[] bytes) throws Exception {
        FileOutputStream outputStream = null;
        String path = templateFile + File.separator + "wakaka" + File.separator + "pdf" + File.separator + "haha.pdf";
        File file = new File(path);
        // 创建目录
        createDirectory("pdf");
        // 上传文件
        outputStream = new FileOutputStream(file);
        outputStream.write(bytes);
        outputStream.close();
        log.info("上传文档成功，路劲为：{}",path);
        return file;
    }

    /** 需要注意的是，如果这个方法用在从本地文件读取数据时，一般不会遇到问题，但如果是用于网络操作，就经常会遇到一些麻烦。
     * 比如，Socket通讯时，对方明明发来了1000个字节，但是自己的程序调用available()方法却只得到900，或者100，甚至是0，感觉有点莫名其妙，怎么也找不到原因。
     * 其实，这是因为网络通讯往往是间断性的，一串字节往往分几批进行发送。本地程序调用available()方法有时得到0，这可能是对方还没有响应，也可能是对方已经响应了，
     * 但是数据还没有送达本地。对方发送了1000个字节给你，也许分成3批到达，这你就要调用3次available()方法才能将数据总数全部得到。
     *    需要改成这样：
     int count = 0;
     while (count == 0) {
     count = in.available();
     }
     byte[] b = new byte[count];
     * */
    // 复制文件
    public static byte[] readFile() throws Exception{
        File file = new File(templateFile + File.separator + "wakaka" + File.separator + "pdf" + File.separator + "haha.pdf");
        FileInputStream fileInputStream = new FileInputStream(file);
        FileOutputStream outputStream = new FileOutputStream(new File(templateFile + File.separator + "wakaka" + File.separator + "pdf" + File.separator + "hihi.pdf"));
        byte[] bytes = new byte[fileInputStream.available()];
        int len;
        while ((len = fileInputStream.read(bytes)) != -1){
            outputStream.write(bytes,0,len);
        }
        outputStream.flush();
        outputStream.close();
        return bytes;
    }

    public static Paragraph createTitleParagraph(String stringTitle, Font titleFont, int alignment) {
        Paragraph titleP = new Paragraph(stringTitle, titleFont);
        titleP.setSpacingBefore(10.0F);
        titleP.setAlignment(alignment);//对齐方式
        return titleP;
    }

    /**
     * 自定义段落
     * @param stringTitle
     * @param titleFont
     * @param alignment
     * @return
     */
    public static Paragraph createCustomerParagraph(String stringTitle, Font titleFont,int alignment,
                                                    float spacingBefore,float spacingAfter,float firstLineIndent){
        Paragraph titleP = new Paragraph(stringTitle, titleFont);
        titleP.setSpacingBefore(spacingBefore);
        titleP.setFirstLineIndent(firstLineIndent);
        titleP.setSpacingAfter(spacingAfter);
        titleP.setAlignment(alignment);//对齐方式
        return titleP;
    }

    public static Font getTitleFont() {
        try {
            return new Font(createBaseFont(), 16.0F, 1);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 构造 表格字体
     * @return
     */
    public static Font getTableFont(){
        try {
            return new Font(createBaseFont(), 9.0F, 0);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static BaseFont createBaseFont() throws DocumentException, IOException {
        return BaseFont.createFont(CN_FONT_FAMILY, ENCODING, BaseFont.NOT_EMBEDDED);
    }

    /**
     * 构造 自定义大小的字体
     * @return
     */
    public static Font getCustomFont(float size, int style){
        try {
            return new Font(createBaseFont(), size, style);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 构造 段落字体
     * @return
     */
    public static Font getParagraphFont(){
        try {
            return new Font(createBaseFont(), 12.0F, 0);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    // pdf分页
    public static PdfWriter getInstanceForPdfWriterWithoutLogo(Document document, ByteArrayOutputStream out) throws Exception {
        PdfWriter writer = PdfWriter.getInstance(document, out);
        writer.setPageEvent(new PdfPageEventHelper() {
            @Override
            public void onEndPage(PdfWriter writer, Document document) {
                PdfContentByte cb = writer.getDirectContent();
                cb.saveState();
                cb.beginText();
                BaseFont bf = null;
                try {
                    bf = createBaseFont();
                    //Footer
                    cb.setFontAndSize(bf, 10);

                    float y = document.bottom(-20);

                    //右
                    cb.showTextAligned(PdfContentByte.ALIGN_RIGHT,
                            writer.getPageNumber() + "",
                            document.right(), y, 0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                cb.setFontAndSize(bf, 10);
                PdfTemplate tphead = cb.createTemplate(150, 50);
                cb.addTemplate(tphead, 30, 780);
                cb.endText();
                cb.restoreState();
            }
        });
        return writer;
    }
}
