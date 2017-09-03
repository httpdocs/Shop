package cn.it.shop.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.it.shop.model.BackData;
import cn.it.shop.model.SendData;
import cn.it.shop.service.PayService;
import cn.it.shop.utils.DigestUtil;

@Service("payService")
public class PayServiceImpl implements PayService {
	//密钥
    @Value("#{prop.key}")
    private String key; 
    @Value("#{prop.p1_MerId}")
    //商户账号（不是订单号）
    private String p1_MerId; 
    //支付成功的返回地址
    @Value("#{prop.p8_Url}")
    private String p8_Url; 
	
    private SendData finishSendData(SendData sendData) {
            sendData.setP0_Cmd("Buy");
            sendData.setP1_MerId(p1_MerId);
            sendData.setP4_Cur("CNY");
            sendData.setP5_Pid("");
            sendData.setP6_Pcat("");
            sendData.setP7_Pdesc("");
            sendData.setP8_Url(p8_Url);
            sendData.setP9_SAF("0");
            sendData.setPr_NeedResponse("0");
            return sendData;
        }
    
    
    // 完成数据的追加，返回追加的明文
    private String joinSendDataParam(SendData sendData) {
        // 填充的所有数据
        sendData = this.finishSendData(sendData);
        StringBuffer infoBuffer = new StringBuffer();
        infoBuffer.append(sendData.getP0_Cmd());
        infoBuffer.append(sendData.getP1_MerId());
        infoBuffer.append(sendData.getP2_Order());
        infoBuffer.append(sendData.getP3_Amt());
        infoBuffer.append(sendData.getP4_Cur());
        infoBuffer.append(sendData.getP5_Pid());
        infoBuffer.append(sendData.getP6_Pcat());
        infoBuffer.append(sendData.getP7_Pdesc());
        infoBuffer.append(sendData.getP8_Url());
        infoBuffer.append(sendData.getP9_SAF());
        infoBuffer.append(sendData.getPa_MP());
        infoBuffer.append(sendData.getPd_FrpId());
        infoBuffer.append(sendData.getPr_NeedResponse());
        return infoBuffer.toString();
    }
    
	@Override
	public Map<String, Object> saveDataToRequest(Map<String, Object> request, SendData sendData) {
		   // 返回了被追加的字符串（即明文）
        String joinParam = joinSendDataParam(sendData);
        request.put("p0_Cmd", sendData.getP0_Cmd());
        request.put("p1_MerId", sendData.getP1_MerId());
        request.put("p2_Order", sendData.getP2_Order());
        request.put("p3_Amt", sendData.getP3_Amt());
        request.put("p4_Cur", sendData.getP4_Cur());
        request.put("p5_Pid", sendData.getP5_Pid());
        request.put("p6_Pcat", sendData.getP6_Pcat());
        request.put("p7_Pdesc", sendData.getP7_Pdesc());
        request.put("p8_Url", sendData.getP8_Url());
        request.put("p9_SAF", sendData.getP9_SAF());
        request.put("pa_MP", sendData.getPa_MP());
        request.put("pd_FrpId", sendData.getPd_FrpId());
        request.put("pr_NeedResponse", sendData.getPr_NeedResponse());
        request.put("hmac", DigestUtil.hmacSign(joinParam, key));//追加之后的签名（密文）
        return request;
	}

	@Override
	public boolean checkBackData(BackData backData) {
		// TODO Auto-generated method stub
		return false;
	}

}