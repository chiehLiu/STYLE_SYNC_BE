package StyleSync.demo.exception;

import java.text.MessageFormat;
import StyleSync.demo.bean.ResponseBean;
import StyleSync.demo.comn.ResultCode;
import StyleSync.demo.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Data;

/**
 * 業務異常
 */
@Data
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 2976915612533815825L;

    protected String errCode;
    protected final String message;

    private JsonUtil jsonUtil = new JsonUtil();

    public BusinessException(String message, Throwable e) {
        super(message, e);
        this.message = message;
    }

    public BusinessException(ResultCode resultCode) {
        this(resultCode, null);
    }

    public BusinessException(ResultCode resultCode, String columnName) {
        ResponseBean<?> responseBean = new ResponseBean<>();
        responseBean.setResultCode(resultCode.getResultCode());

        if (columnName != null) {
            responseBean.setResultMsg(MessageFormat.format(resultCode.getResultMsg(), columnName));
        } else {
            responseBean.setResultMsg(resultCode.getResultMsg());
        }

        this.errCode = resultCode.getResultCode();
        this.message = jsonUtil.toJSON(responseBean);
    }

    public BusinessException(String resultCode, String resultMsg) {
        ResponseBean<?> responseBean = new ResponseBean<>();
        responseBean.setResultCode(resultCode);
        responseBean.setResultMsg(resultMsg);

        this.errCode = responseBean.getResultCode();
        this.message = jsonUtil.toJSON(responseBean);
    }

    public ResponseBean<?> getOriginalBean() {
        try {
            return jsonUtil.convertToObject(ResponseBean.class, this.message);
        } catch (Exception e) {
            return null;
        }
    }
}