var sendJsonStr;


function loginAction() {
var json = {
	"loginIp": "dev.bairuitech.cn",
	"loginPort": "18603",
	"loginAppId": "00A4B0B4-ECBF-5B4A-A65E-91FC22F59BD1",
	"nickName": "13528762678",
	"busSerialNumber": "2223335555",
	"targetModel": 4,
    "processBool": true,
	"qualityRuleJsonStr": "[{\"checkQuestion\":\"尊敬的投资者您好！没有只涨不跌的市场，也没有包赚不赔的投资。在您进入证券市场之前， 为了保护您的权益，下面我们将向您揭示客户账户开立须知及风险事项，并全程进行录音录像，请您仔细聆听并配合面对摄像镜头，谢谢！\",\"type\":0},{\"checkQuestion\":\"请问您是否已知晓参与证券投资可能承担的费用及相关投资损失？请回答\\u201c已知晓\\u201d或\\u201c未知晓\",\"expectAnswer\":\"已知晓,是的,是的,知晓\",\"rightAnswerStr\":\"是,似,对\",\"type\":1,\"errorAnswerStr\":\"不,部,否\"},{\"checkQuestion\":\"请用普通话朗读以下内容\",\"expectAnswer\":\"我自愿开户！\",\"type\":2},{\"checkQuestion\":\"您的开户已完毕，感谢您的支持与配合\",\"type\":0}]"
};
var  params=JSON.stringify(json);

    var box01 = document.getElementById('result_div');
    var debug = ''
    debug = "w3242342343"
    box01.innerHTML = debug


    AnyChatJSBridge.loginAction(params);
//     window.webkit.messageHandlers.loginAction.postMessage(params);

}


function receiveParams(message) {
    sendJsonStr = message
//    sendJsonStr =JSON.parse(message)
    console.log(sendJsonStr)
    var box01 = document.getElementById('result_div');
    var debug = ''
    try{
        JSON.parse(message)
    }catch(err){
        debug = err.message
    }
    box01.innerHTML = debug + typeof message + "登录：<br\>" + message;
}


function receiveResult(message) {
    var resultJsonStr = message
//    sendJsonStr =JSON.parse(message)
    console.log(resultJsonStr)
    var box01 = document.getElementById('result_div');
    var debug = ''
    try{
        JSON.parse(message)
    }catch(err){
        debug = err.message
    }
    box01.innerHTML = debug + typeof message + "登录：<br\>" + message;
}
