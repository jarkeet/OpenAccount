package com.anychat.aiselfrecordcompontsdemo;

import com.anychat.aiselfrecordsdk.util.speech.SpeechRuleUtil;
import com.anychat.aiselfrecordsdk.util.speech.SplitSpeechUtil;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void split(){

        String b = "您准备购买的产品是幻方中证1000量化多策略3号私募证券投资基金，\n产品类型为契约型开放式私募证券投资基金，发行机构为宁波幻方量化投资管理合伙企业（有限合伙），本基金风险等级为R5级（高风险），投资期限为0-1年（含），投资品种为权益类。本基金自成立之日起计算的15年为固定存续期限。12346678901234567890\n本基金开放日为每个自然季度第2个月的16号（遇节假日则顺延至下一个工作日）。管理人可根据实际情况增设临时开放日，具体开放日期以管理人通知为准。产品购买起点为100万元人民币（不含认申购费，且本合同“募集对象”中的“特殊合格投资者”不受此限），追加金额不低于1万元。\n请问您是否仔细阅读并理解了本基金合同和风险揭示书，知晓产品相关风险？\n";
//        SplitSpeechUtil.getSpeechContentSplits(b);
//        String a="0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789"+"0123456"+"\n"
//                +"0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789"+"0123458"+"\n"
//                +"0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789"+"012345"
//                ;
        String a="123456789。123456789。123456789。"+"0123456"
                +"123456789。123456789。123456789。"+"0123456"+"\n"
                +"123456789。123456789。123456789。"+"0123456"
                +"123456789。123456789。123456789。"+"0123456"+"\n"
                +"123456789。123456789。123456789。"+"0123456"
                +"123456789。123456789。123456789。"+"0123456"+"\n"
                ;
        String c = "您准备购买的产品是幻方中证1000量化多策略3号私募证券投资基金，产品类型为契约型开放式私募证券投资基金，发行机构为宁波幻方量化投资管理合伙企业（有限合伙），本基金风险等级为R5级（高风险），投资期限为0-1年（含），投资品种为权益类。本基金自成立之日起计算的15年为固定存续期限。\n本基金开放日为每个自然季度第2个月的16号（遇节假日则顺延至下一个工作日）。\n管理人可根据实际情况增设临时开放日，具体开放日期以管理人通知为准。产品购买起点为100万元人民币（不含认申购费，且本合同“募集对象”中的“特殊合格投资者”不受此限），追加金额不低于1万元。\n请问您是否仔细阅读并理解了本基金合同和风险揭示书，知晓产品相关风险？\n";
//        SplitSpeechUtil.getSpeechContentSplits(c);
        String d = "您准备购买的产品是幻方中证1000量化多策略3号私募证券投资基金，产品类型为契约型开放式私募证券投资基金，发行机构为宁波幻方量化投资管理合伙企业（有限合伙），本基金风险等级为R5级（高风险），投资期限为0-1年（含），投资品种为权益类。本基金自成立之日起计算的15年为固定存续期限。本基金开放日为每个自然季度第2个月的16号（遇节假日则顺延至下一个工作日）。管理人可根据实际情况增设临时开放日，具体开放日期以管理人通知为准。产品购买起点为100万元人民币（不含认申购费，且本合同“募集对象”中的“特殊合格投资者”不受此限），追加金额不低于1万元。\n请问您是否仔细阅读并理解了本基金合同和风险揭示书，知晓产品相关风险？\n";
//        SplitSpeechUtil.getSpeechContentSplits(d);
        String f="测试#{11|HTTP|超文本传输协议}简单的请求-响应协议，它通常运行在TCP之上。这个简单模型是早期Web成功的有功#{10|大|dai4}臣，测试#{10|倒|dao2}退，因为它使开#{10|发|fa4}和部署非常888#{10|重|chong2}要， #{10|重|chong2}复直截了当，#{10|更|geng1}加优秀。";
        String test="测试#{11|HTTP|超文本传输协议}简单的请求-响应协议，它通常运行在TCP之上。这个简单模型是早期Web成功的有功#{10|大|dai4}臣，测试#{10|倒|dao2}退，因为它使开#{10|发|fa4}和部署非常888#{10|重|chong2}要， #{10|重|chong2}复直截了当，#{10|更|geng1}加优秀。";

        String e=SpeechRuleUtil.getRequestTtsSpeech(test,1);

        SplitSpeechUtil.getSpeechContentSplits(e);
//        SplitSpeechUtil.getSpeechContentSplits("国都证券代销幻方量化500指数专享31号2期私募证券投资基金风险揭示\n" +
//                "                                                                                                                                                                            \n" +
//                "                            尊敬的投资者：\n" +
//                "   投资有风险。当您认购或申购私募基金时，可能获得投资收益，但同时也面临着投资风险。您在做出投资决策之前，请仔细阅读本基金的风险揭示书和基金合同全文，充分认识本基金的风险收益特征和产品特性，认真考虑本基金的特点和存在的各项风险因素，并充分考虑自身的风险承受能力，理性判断并谨慎做出投资决策。\n" +
//                "   本基金属于R5级（高风险）投资品种,投资期限为15年，投资策略以量化投资策略为主。\n" +
//                "   国都证券股份有限公司与本基金、基金当事人之间不存在关联关系。因本基金的产品设计、运营和基金管理人提供的信息不真实、不准确、不完整而产生的责任由基金管理人承担，国都证券不承担任何担保责任。\n" +
//                "   \n" +
//                "   下面对本基金的主要风险进行简单列举，本基金的风险包括但不限于： \n" +
//                "   一、特殊风险\n" +
//                "   包括：本合同部分内容与中国基金业协会合同指引不一致所涉风险、基金委托募集所涉风险、基金服务事项所涉风险、未在中国基金业协会履行登记备案手续或未能通过协会备案所涉风险、关联交易风险。\n" +
//                "   二、一般风险\n" +
//                "   包括：资金损失风险、基金运营风险、流动性风险、募集失败风险、税收风险、以及包括但不限于法律与政策风险、发生不可抗力事件的风险、技术风险和操作风险等。\n" +
//                "   三、基金投资风险\n" +
//                "  包括：市场风险（包括股票投资风险、债券投资风险等）、基金管理人管理风险、流动性风险、信用风险、金融衍生品及资产管理产品投资风险、特定的投资方法及基金财产所投资的特定投资对象可能引起的特定风险、操作或技术风险、基金本身面临的风险（包括法律及违约风险、购买力风险、管理人不能承诺基金利益的风险、基金终止的风险、基金止损风险）、相关机构的经营风险、净值波动风险、债券正回购风险（如有）、融资融券交易风险（如有）、转融通投资风险（如有）、港股通交易风险（如有） 、证券公司收益凭证风险（如有）、签署电子合同及使用网络系统下达认购指令的风险以及其他风险（战争、自然灾害等不可抗力因素）。\n" +
//                "  本产品可投资于由宁波幻方量化投资管理合伙企业（有限合伙）或浙江九章资产管理有限公司管理的并经基金业协会备案显示由具备证券投资基金托管资格的托管人托管的私募证券投资基金，可能存在相应投资风险。\n" +
//                "  其中，您认/申购的“幻方量化500指数专享31号2期私募证券投资基金”将会投资于沪深等交易所上市交易的品种（指股票、优先股、权证、存托凭证），可能存在以下风险：\n" +
//                "  1、国家货币政策、财政政策、产业政策等的变化对证券市场产生一定的影响，导致市场价格水平波动的风险；\n" +
//                "  2、宏观经济运行周期性波动，对股票市场的收益水平产生影响的风险；\n" +
//                "  3、上市公司的经营状况受多种因素影响，如市场、技术、竞争、管理、财务等都会导致公司盈利发生变化，从而导致股票价格变动的风险；\n" +
//                "  4、本基金可投资于科创板及创业板股票，科创板及创业板股票发行采用注册制，在上市门槛、公司盈利能力、公司股权架构、发行价格、减持制度、交易机制、涨跌幅限制以及退市制度等方面与其他A股板块的股票不同，可能导致本基金净值波动更大。另外，您认/申购的“幻方量化500指数专享31号2期私募证券投资基金”，管理人将在投资者赎回日、固定计提日和基金清算日提取业绩报酬。其中固定计提日为每个自然年度1月20日和7月20日以扣减份额的方式提取业绩报酬，即固定日计提业绩报酬后，基金份额净值保持不变，但基金份额持有人持有的本基金份额将会进行扣减。赎回日、合同终止日的业绩报酬从赎回款、清算款中扣减。\n" +
//                "   上述风险仅为简单列举，未能详尽列明您投资本基金所面临的全部风险和可能导致您投资损失的所有因素，请您务必详尽阅读本基金的基金合同和风险揭示书全文。\n" +
//                "   有关本基金的认购/申购程序，请您知悉并关注：\n" +
//                "   1、您通过线上方式签署本基金合同和风险揭示书与线下签署纸质基金合同和风险揭示书具有同等效力。\n" +
//                "   2、自您认购/申购本基金的委托下单时点开始冻结交纳认购/申购资金款项、并开始计算24小时投资冷静期。投资冷静期满后，国都证券客服人员将对您进行投资回访。如无法完成投资回访，将导致您认购/申购失败，请您务必留存正确的联系电话。您自主决定购买的这款理财产品，适合风险承受能力较高的投资者，请问您是否清楚了解以上内容？请大声回答是或不是。");
    }

    @Test
    public void splitRule(){
//        SpeechRuleUtil.getRequestTtsSpeech("下面对本基金的主要风险进行简单列举，本基金的风险包括但不限于：",1);
//        SpeechRuleUtil.getRequestTtsSpeech("#{10|重|zhong4}要#{11|W3C|网络协议标准}",1);
//        SpeechRuleUtil.getRequestTtsSpeech("#{20|重|zhong4}要#{21|hello word|h2}, nice#{22|123|n2}",2);

//        String test="测试#{11|HTTP|超文本传输协议}简单的请求-响应协议，它通常运行在TCP之上。这个简单模型是早期Web成功的有功#{10|大|dai4}臣，测试#{10|倒|dao2}退，因为它使开#{10|发|fa4}和部署非常888#{10|重|chong2}要， #{10|重|chong2}复直截了当，#{10|更|geng1}加优秀。";
//        SpeechRuleUtil.getRequestTtsSpeech(test,1);


        SpeechRuleUtil.getShowSpeech("测试<sub alias=\"超文本传输协议\">HTTP</sub>简单<sub>ttt</sub>的请求-响应协议，文本传输协议<phoneme>退退</phoneme>文本传输协议。，测试<phoneme alphabet=\"py\" ph=\"dao2\">倒</phoneme>退，",1);
        String test="测试<sub alias=\"超文本传输协议\">HTTP</sub>简单的请求-响应协议，文本传输协议<phoneme 文本传输协议。，测试<phoneme alphabet=\"py\" ph=\"dao2\">倒</phoneme>退，";
        SpeechRuleUtil.match(test,"sub","alias");
//  SpeechRuleUtil.getShowSpeech("下面对本基金的主要风险进行简单列举，本基金的风险包括但不限于：",1);
//        String speechQuestion= SpeechRuleUtil.getRequestTtsSpeech("#{10|重|zhong4}要#{11|W3C|网络协议标准}",1);
//        SpeechRuleUtil.getShowSpeech("#{20|重|zhong4}要#{21|hello word|h2}, nice#{22|123|n2}");
//        SpeechRuleUtil.getSpeakSpeech("#{10|重|zhong4}要#{11|W3C|网络协议标准}");
//        SpeechRuleUtil.getAliasMap("#{10|重|zhong4}要#{11|W3C|网络协议标准}");
//        String speechQuestion="#{10|重|zhong4}#要#{11|W3C|网络协议标准}#";
//        System.out.println(""+speechQuestion);
//        String speechContent = SpeechRuleUtil.getShowSpeech(speechQuestion.trim());
//        HashMap<String,String> aliasMap=SpeechRuleUtil.getAliasMap(speechQuestion.trim());
//        HashMap<Integer, Integer> aliasNumberIndexMap = SpeechRuleUtil.getAliasNumIndex(speechContent,aliasMap);
//        for (Map.Entry<Integer, Integer> entry : aliasNumberIndexMap.entrySet()) {
//            System.out.println("第"+ entry.getKey()+ "个字符等待：" +entry.getValue());
//        }
    }
}